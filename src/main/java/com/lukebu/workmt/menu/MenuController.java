package com.lukebu.workmt.menu;

import com.lukebu.workmt.tasks.AddTaskController;
import javafx.fxml.FXMLLoader;
import java.io.IOException;
import java.sql.SQLException;

public class MenuController {

    public void showDialog() throws SQLException, IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/scenes/task/addTask.fxml"));
        loader.load();
        AddTaskController controller = loader.getController();
        controller.showAddTaskDialog();
    }
}
