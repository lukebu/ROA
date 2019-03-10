package com.lukebu.workmt.dashboard;

import com.lukebu.workmt.Main;
import com.lukebu.workmt.conector.Connector;
import com.lukebu.workmt.query.task.DeleteTaskQuery;
import com.lukebu.workmt.query.task.SelectTasksQuery;
import com.lukebu.workmt.tasks.ModifyTaskController;
import com.lukebu.workmt.tasks.Task;
import com.lukebu.workmt.tasks.TaskData;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;

public class DashboardController {

    private ObservableList<Task> tasks;

    @FXML
    private ListView<Task> taskListView;
    @FXML
    private TextArea taskAreaDetails;
    @FXML
    private Label dueDateLabel;

    private int result;

    private Connector connector = new Connector();
    private SelectTasksQuery selectTasksQuery = new SelectTasksQuery();
    private DeleteTaskQuery deleteTaskQuery = new DeleteTaskQuery();

    public void initialize() throws SQLException {
        refreshView();
    }

    @FXML
    public void deleteTask()throws SQLException {
        connector.createConnectionToDb();
        Task task = taskListView.getSelectionModel().getSelectedItem();
        result = connector.insertUpdateStatement(deleteTaskQuery.prepareQuery(task.getTaskId()));
        if (result == 1) {
            connector.closeConnectionWithCommit();
            TaskData.getInstance().removeFromTaskList(tasks.indexOf(task));
        } else {
            connector.closeConnectionWithCommit();
        }
        refreshView();
    }

    @FXML
    public void showModifyTaskDialog() throws SQLException{
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(Main.getInstance().mainBorderPane.getScene().getWindow());
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/scenes/task/modifyTask.fxml"));

        try {
            dialog.getDialogPane().setContent(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Nie udało sie wyświetlić panelu modyfikacji zadania prosimy spróbować później");
            e.printStackTrace();
            return;
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        Task task = taskListView.getSelectionModel().getSelectedItem();
        ModifyTaskController controller = loader.getController();
        controller.fillModifyForm(tasks.indexOf(task), task.getTaskId());

        Optional<ButtonType> result = dialog.showAndWait();

        if(result.isPresent() && result.get().equals(ButtonType.OK)) {
            controller.modifyTask(tasks.indexOf(task), task.getTaskId());
            System.out.println("OK, pressed");
            refreshView();
        } else {
            System.out.println("CANCEL, pressed");
        }
    }

    private ObservableList<Task> loadTaskListFormDB() throws SQLException {
        tasks = TaskData.getInstance().getTaskList();
        tasks.clear();
        ResultSet rs = null;

        String statementQuery = selectTasksQuery.prepareQuery();

        connector.createConnectionToDb();
        rs = connector.insertQueryStatement(statementQuery);

        while (rs.next()) {
            if (rs.getInt("TSK_ID") != 0 && rs.getInt("TSK_USR_ID") != 0) {
                int taskId = rs.getInt("TSK_ID");
                int userId = rs.getInt("TSK_USR_ID");
                String taskName = rs.getString("TSK_NAME");
                String taskDescription = rs.getString("TSK_DESCRIPTION");
                LocalDate taskDueDate = rs.getDate("TSK_DUE_DATE").toLocalDate();

                Task task = new Task(taskId, userId, taskName, taskDescription, taskDueDate);
                tasks.add(task);
            }
        }
        connector.closeConnectionWithCommit();
        return tasks;
    }

     public void refreshView() throws SQLException {
        taskListView.refresh();
        findListChange();
        ObservableList<Task> taskObservableList = loadTaskListFormDB();

        taskListView.setItems(taskObservableList);
        taskListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
         taskListView.getSelectionModel().selectFirst();
    }

    private void findListChange() {
        taskListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Task>() {
            @Override
            public void changed(ObservableValue<? extends Task> observable, Task oldValue, Task newValue) {
                if (newValue != null) {
                    Task item = taskListView.getSelectionModel().getSelectedItem();
                    taskAreaDetails.setText(item.getTaskDescription());
                    dueDateLabel.setText(item.getTaskDueDate().toString());
                    taskListView.getSelectionModel().select(item);
                }
            };
        });
    }
}
