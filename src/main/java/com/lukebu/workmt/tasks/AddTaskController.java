package com.lukebu.workmt.tasks;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.lukebu.workmt.events.EventProcessor;
import com.lukebu.workmt.events.task.NewTaskEvent;
import com.lukebu.workmt.events.task.TaskListEvent;
import javafx.fxml.FXML;

import javafx.scene.control.*;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.time.LocalDate;

public class AddTaskController {
    @FXML
    private JFXTextField taskNameTF;
    @FXML
    private JFXTextArea taskDescriptionTA;
    @FXML
    private JFXDatePicker taskDueDateDP;
    @FXML
    private JFXButton addNewTaskButton;
    @FXML
    private JFXButton cancelButton;

    private TaskDataProcessing taskDataProcessing = new TaskDataProcessing();

    @FXML
    private void addTaskToList() throws SQLException {
        String taskName = taskNameTF.getText().trim();
        String taskDescription = taskDescriptionTA.getText().trim();
        LocalDate taskEndDate = taskDueDateDP.getValue();
        taskDataProcessing.addTask(taskName, taskDescription,taskEndDate);
        cancel();
        EventProcessor.getInstance().sendEvent(new TaskListEvent());
    }

    @FXML
    private void cancel() {
        Stage stage = (Stage) taskNameTF.getScene().getWindow();
        stage.close();
    }

}
