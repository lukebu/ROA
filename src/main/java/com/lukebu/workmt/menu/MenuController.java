package com.lukebu.workmt.menu;

import com.lukebu.workmt.events.EventProcessor;
import com.lukebu.workmt.events.contact.ContactListEvent;
import com.lukebu.workmt.events.task.TaskListEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class MenuController {

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
