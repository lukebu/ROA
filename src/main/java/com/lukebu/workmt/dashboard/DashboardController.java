package com.lukebu.workmt.dashboard;

import com.lukebu.workmt.conector.Connector;
import com.lukebu.workmt.query.SelectTasksQuery;
import com.lukebu.workmt.tasks.Task;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

public class DashboardController {

    private List<Task> tasks;

    @FXML
    private ListView<Task> toDoItemListView;
    @FXML
    private TextArea toDoItemTextAreaDetails;
    @FXML
    private Label dueDateLabel;

    private Connector connector = new Connector();
    private SelectTasksQuery selectTasksQuery = new SelectTasksQuery();

    public void initialize() throws SQLException {
        ResultSet rs = null;
        tasks = new ArrayList<>();

        String statementQuery = selectTasksQuery.prepareQuery();

        connector.createConnectionToDb();
        rs = connector.insertQueryStatement(statementQuery);

        while (rs.next()) {
            if (rs.getInt("TSK_ID") != 0 && rs.getInt("TSK_USR_ID") != 0 && rs.getString("TSK_NAME") != null) {
                int taskId = rs.getInt("TSK_ID");
                int userId = rs.getInt("TSK_USR_ID");
                String taskName = rs.getString("TSK_NAME");
                String taskDescription = rs.getString("TSK_DESCRIPTION");
                LocalDate taskDueDate = rs.getDate("TSK_DUE_DATE").toLocalDate();

                System.out.println(taskDueDate);

                Task task = new Task(taskId,userId,taskName,taskDescription,taskDueDate);
                tasks.add(task);
            } else {
                connector.closeConnectionWithCommit();
            }
        }

        connector.closeConnectionWithCommit();

        toDoItemListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Task>() {
            @Override
            public void changed(ObservableValue<? extends Task> observable, Task oldValue, Task newValue) {
                if (newValue != null) {
                    Task item = toDoItemListView.getSelectionModel().getSelectedItem();
                    toDoItemTextAreaDetails.setText(item.getTaskDescription());
                    //DateTimeFormatter df = DateTimeFormatter.ISO_LOCAL_DATE;
                    dueDateLabel.setText(item.getTaskDueDate().toString());
            }
        };
        });
        toDoItemListView.getItems().setAll(tasks);
        toDoItemListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        toDoItemListView.getSelectionModel().selectFirst();
    }
}
