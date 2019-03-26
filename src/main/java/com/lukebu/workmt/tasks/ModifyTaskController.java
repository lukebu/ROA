package com.lukebu.workmt.tasks;

import com.lukebu.workmt.events.EventProcessor;
import com.lukebu.workmt.events.task.ModifyTaskEvent;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ModifyTaskController implements Initializable {

    @FXML
    private TextField taskNameTF;
    @FXML
    private TextArea taskDescriptionTA;
    @FXML
    private DatePicker taskDueDateDP;
    @FXML
    private Button modifyTaskButton;
    @FXML
    private  Button cancelButton;

    private TaskDataProcessing taskDataProcessing = new TaskDataProcessing();
    private ObservableList<Task> taskObservableList = TaskData.getInstance().getTaskList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fillModifyForm(taskObservableList.indexOf(ModifyTaskEvent.getInstance().getTask()));
    }

    @FXML
    private void modifyTask() throws SQLException {
        modifyTaskOnList(taskObservableList.indexOf(ModifyTaskEvent.getInstance().getTask()),ModifyTaskEvent.getInstance().getTask().getTaskId() );
    }

    @FXML
    private void cancel() {
        Stage stage = (Stage) taskNameTF.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void modifyTaskOnList(int index, int taskId) throws SQLException {
        String taskName = taskNameTF.getText().trim();
        String taskDescription = taskDescriptionTA.getText().trim();
        LocalDate taskEndDate = taskDueDateDP.getValue();
        taskDataProcessing.modifyTask(index, taskId,taskName, taskDescription,taskEndDate);
        cancel();
        EventProcessor.getInstance().sendEvent(new ModifyTaskEvent());
    }

    @FXML
    private void fillModifyForm(int index){
        Task task = TaskData.getInstance().getTaskByIndex(index);
        taskNameTF.setText(task.getTaskName());
        taskDescriptionTA.setText(task.getTaskDescription());
        taskDueDateDP.setValue(task.getTaskDueDate());
    }


}
