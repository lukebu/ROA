package com.lukebu.workmt.dashboard;

import com.lukebu.workmt.conector.Connector;
import com.lukebu.workmt.footer.FooterController;
import com.lukebu.workmt.menu.MenuController;
import com.lukebu.workmt.query.SelectTasksQuery;
import com.lukebu.workmt.tasks.Task;
import com.lukebu.workmt.tasks.TaskData;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class DashboardController {

    private ObservableList<Task> tasks;

    @FXML
    private ListView<Task> toDoItemListView;
    @FXML
    private TextArea toDoItemTextAreaDetails;
    @FXML
    private Label dueDateLabel;
    @FXML
    private MenuController menuController;
    @FXML
    private FooterController footerController;

    private Connector connector = new Connector();
    private SelectTasksQuery selectTasksQuery = new SelectTasksQuery();

    public void initialize() throws SQLException{

        tasks = TaskData.getInstance().getTaskList();
        ResultSet rs = null;

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
                    dueDateLabel.setText(item.getTaskDueDate().toString());
            }
        };
        });
        toDoItemListView.setItems(TaskData.getInstance().getTaskList());
        toDoItemListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        toDoItemListView.getSelectionModel().selectFirst();
    }
}
