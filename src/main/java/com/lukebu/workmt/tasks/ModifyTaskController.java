package com.lukebu.workmt.tasks;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.lukebu.workmt.events.EventProcessor;
import com.lukebu.workmt.events.task.ModifyTaskEvent;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ModifyTaskController implements Initializable {

    @FXML
    private JFXTextField taskNameTF;
    @FXML
    private JFXTextArea taskDescriptionTA;
    @FXML
    private JFXDatePicker taskDueDateDP;
    @FXML
    private JFXButton modifyTaskButton;
    @FXML
    private JFXButton cancelButton;

    private TaskDataProcessing taskDataProcessing = new TaskDataProcessing();
    private ObservableList<Task> taskObservableList = TaskData.getInstance().getTaskList();

    private ModifyTaskEvent modifyTaskEvent = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

       EventProcessor.getInstance().registerListener( event -> {
           if (event instanceof ModifyTaskEvent){
               int index = taskObservableList.indexOf(modifyTaskEvent.returnTask());
               fillModifyForm(index);
           }
       });
    }

    @FXML
    private void modifyTask() throws SQLException {
       // modifyTaskOnList(taskObservableList.indexOf(ModifyTaskEvent.getInstance().getTask()),ModifyTaskEvent.getInstance().getTask().getTaskId() );
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
        //EventProcessor.getInstance().sendEvent(new ModifyTaskEvent());
    }

    @FXML
    private void fillModifyForm(int index){
        Task task = TaskData.getInstance().getTaskByIndex(index);
        taskNameTF.setText(task.getTaskName());
        taskDescriptionTA.setText(task.getTaskDescription());
        taskDueDateDP.setValue(task.getTaskDueDate());
    }


}
