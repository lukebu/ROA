package com.lukebu.workmt.menu;

import com.lukebu.workmt.ChangeSceneProcessor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuBar;
import javafx.stage.Stage;

public class MenuController {

    @FXML
    private MenuBar menuBar;

    @FXML
    private void handleAddTaskToList(ActionEvent event) {
        ChangeSceneProcessor.changeScene(getClass().getResource("/scenes/task/addTask.fxml"), "Dodaj nowe zadanie", null);
    }

    @FXML
    private void handleContactList(ActionEvent event) {
        Stage stage = (Stage) menuBar.getScene().getWindow();
        ChangeSceneProcessor.changeScene(getClass().getResource("/scenes/contact/contactList.fxml"), "Work MT", stage);
    }

    @FXML
    private void handleTaskList(ActionEvent event) {
        Stage stage = (Stage) menuBar.getScene().getWindow();
        ChangeSceneProcessor.changeScene(getClass().getResource("/scenes/dashboard/dashboard.fxml"), "Work MT", stage);
    }

    @FXML
    private void handleCancelButtonAction(ActionEvent event) {
        System.exit(0);
    }
}
