package com.lukebu.workmt.menu;

import com.lukebu.workmt.ChangeSceneProcessor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class MenuController {

    @FXML
    private void handleAddTaskToList(ActionEvent event) {
        ChangeSceneProcessor.changeScene(getClass().getResource("/scenes/task/addTask.fxml"), "Dodaj nowe zadanie", null);
    }
}
