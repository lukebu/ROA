package com.lukebu.workmt.dashboard;

import com.lukebu.workmt.Main;
import com.lukebu.workmt.tasks.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;

import java.io.IOException;
import java.sql.SQLException;

public class DashboardController {

    private ObservableList<Task> tasks = FXCollections.observableArrayList();

    private FXMLLoader loader = new FXMLLoader();

    @FXML
    private ListView<Task> taskListView;
    @FXML
    private TextArea taskAreaDetails;
    @FXML
    private Label dueDateLabel;
    @FXML
    private Label dueDateLabelText;
    @FXML
    private Button deleteTaskButton;
    @FXML
    private Button modifyTaskButton;

    private TaskDataProcessing taskDataProcessing = new TaskDataProcessing();

    @FXML
    public void initialize() throws SQLException {
        taskDataProcessing.loadTaskListFormDB();
        tasks = TaskData.getInstance().getTaskList();
        findListChange();
        taskListView.setItems(tasks);
        taskListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        taskListView.getSelectionModel().selectFirst();
        disableFormData();
    }

    @FXML
     public void refreshView() throws SQLException, IOException {
        loader.setLocation(getClass().getResource("/scenes/dashboard/dashboard.fxml"));
        loader.load();
        DashboardController dashboardController = loader.getController();
        tasks.setAll(taskDataProcessing.getTaskListAfterOperations());
        dashboardController.findListChange();
        dashboardController.taskListView.setItems(tasks);
        dashboardController.taskListView.getSelectionModel().selectFirst();
    }

    @FXML
    private void findListChange() {
        taskListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Task>() {
            @Override
            public void changed(ObservableValue<? extends Task> observable, Task oldValue, Task newValue) {
                if (newValue != null) {
                    Task item = taskListView.getSelectionModel().getSelectedItem();
                    taskAreaDetails.setText(item.getTaskDescription());
                    dueDateLabel.setText(item.getTaskDueDate().toString());
                    taskListView.getSelectionModel().select(item);
                    disableFormData();
                }
            };
        });
    }

    @FXML
    private void disableFormData() {
        if (taskListView.getSelectionModel().isEmpty()){
            taskAreaDetails.clear();
            dueDateLabel.setText("");
            dueDateLabelText.setText("");
            modifyTaskButton.setDisable(true);
            deleteTaskButton.setDisable(true);
        } else {
            modifyTaskButton.setDisable(false);
            deleteTaskButton.setDisable(false);
        }
    }

    @FXML
    public void selectFirstItem() {
        taskListView.getSelectionModel().selectFirst();
    }

    @FXML
    public void selectCustomTask(Task task) {
        taskListView.getSelectionModel().select(task);
    }

    @FXML
    public void showModifyDialog() throws IOException, SQLException {

        ModifyTaskController modifyTaskController = new ModifyTaskController();
        modifyTaskController.showModifyTaskDialog(taskListView.getSelectionModel().getSelectedItem());
    }

    @FXML
    private void deleteTaskFromList() throws SQLException, IOException {
        Task task = taskListView.getSelectionModel().getSelectedItem();
        taskDataProcessing.deleteTask(tasks, task);
        disableFormData();
    }

    public FXMLLoader loadDashboard() throws IOException {
        loader.setLocation(getClass().getResource("/scenes/dashboard/dashboard.fxml"));
        return loader;
    }
}
