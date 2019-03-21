package com.lukebu.workmt.menu;

import com.lukebu.workmt.WorkManagerToolUtil;
import com.lukebu.workmt.tasks.AddTaskController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import java.io.IOException;
import java.sql.SQLException;

public class MenuController {

    @FXML
    private void handleAddTaskToList(ActionEvent event) {
        WorkManagerToolUtil.loadNewWindow(getClass().getResource("/scenes/task/addTask.fxml"), "Dodaj nowe zadanie", null);
    }
}
