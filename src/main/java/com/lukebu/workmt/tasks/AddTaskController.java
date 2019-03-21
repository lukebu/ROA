package com.lukebu.workmt.tasks;

import com.lukebu.workmt.WorkManagerToolUtil;
import com.lukebu.workmt.dashboard.DashboardController;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

public class AddTaskController {
    @FXML
    private TextField taskNameTF;
    @FXML
    private TextArea taskDescriptionTA;
    @FXML
    private DatePicker taskDueDateDP;
    @FXML
    private Button addNewTaskButton;
    @FXML
    private Button cancelButton;

    private TaskDataProcessing taskDataProcessing = new TaskDataProcessing();

    @FXML
    private void addTaskToList() throws SQLException, IOException {
        String taskName = taskNameTF.getText().trim();
        String taskDescription = taskDescriptionTA.getText().trim();
        LocalDate taskEndDate = taskDueDateDP.getValue();
        taskDataProcessing.addTask(taskName, taskDescription,taskEndDate);
        cancel();

        DashboardController dashboardController = new DashboardController();
        dashboardController.getDashboardController();
        dashboardController.refreshView();
    }

    @FXML
    private void cancel() {
        Stage stage = (Stage) taskNameTF.getScene().getWindow();
        stage.close();
    }

}
