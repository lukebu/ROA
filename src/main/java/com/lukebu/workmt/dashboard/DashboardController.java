package com.lukebu.workmt.dashboard;

import com.lukebu.workmt.ChangeSceneProcessor;
import com.lukebu.workmt.events.EventProcessor;
import com.lukebu.workmt.events.task.ModifyTaskEvent;
import com.lukebu.workmt.events.task.NewTaskEvent;
import com.lukebu.workmt.query.task.ModifyTask;
import com.lukebu.workmt.tasks.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    private ObservableList<Task> tasks = FXCollections.observableArrayList();

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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            taskDataProcessing.loadTaskListFormDB();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        EventProcessor.getInstance().registerListener( event -> {
            if(event instanceof NewTaskEvent || event instanceof ModifyTaskEvent) {
                refreshView();
            }
        });

        tasks = TaskData.getInstance().getTaskList();
        findListChange();
        taskListView.setItems(tasks);
        taskListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        taskListView.getSelectionModel().selectFirst();
        disableFormData();
    }

     private void refreshView() {
        taskListView.getSelectionModel().selectLast();
    }

    private void findListChange() {
        taskListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Task>() {
            @Override
            public void changed(ObservableValue<? extends Task> observable, Task oldValue, Task newValue) {
                if (newValue != null) {
                    Task item = taskListView.getSelectionModel().getSelectedItem();
                    taskAreaDetails.setText(item.getTaskDescription());
                    dueDateLabel.setText(item.getTaskDueDate().toString());
                    disableFormData();
                }
            };
        });
    }

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
    private void handleModifyTaskOnList(ActionEvent event) {
        Task task = taskListView.getSelectionModel().getSelectedItem();
        ModifyTaskEvent modifyTaskEvent = new ModifyTaskEvent();
        modifyTaskEvent.setTaskToModify(task);
        ChangeSceneProcessor.changeScene(getClass().getResource("/scenes/task/modifyTask.fxml"), "Zmodyfikuj zadanie", null);
    }

    @FXML
    private void deleteTaskFromList() throws SQLException, IOException {
        Task task = taskListView.getSelectionModel().getSelectedItem();
        taskDataProcessing.deleteTask(tasks, task);
        disableFormData();
        taskListView.getSelectionModel().selectLast();
    }

}
