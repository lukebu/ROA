package com.lukebu.workmt.menu;

import com.lukebu.workmt.ChangeSceneProcessor;
import com.lukebu.workmt.events.EventProcessor;
import com.lukebu.workmt.events.contact.ContactListEvent;
import com.lukebu.workmt.events.task.ModifyTaskEvent;
import com.lukebu.workmt.events.task.NewTaskEvent;
import com.lukebu.workmt.events.task.TaskListEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuController {

    @FXML
    private void handleAddTaskToList(ActionEvent event) {
        ChangeSceneProcessor.changeScene(getClass().getResource("/scenes/task/addTask.fxml"), "Dodaj nowe zadanie", null);
    }

    @FXML
    private void handleContactList(ActionEvent event) {
        EventProcessor.getInstance().sendEvent(new ContactListEvent());
    }

    @FXML
    private void handleTaskList(ActionEvent event) {
        EventProcessor.getInstance().sendEvent(new TaskListEvent());
    }

    @FXML
    private void handleCancelButtonAction(ActionEvent event) {
        System.exit(0);
    }

}
