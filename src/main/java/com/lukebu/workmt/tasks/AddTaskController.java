package com.lukebu.workmt.tasks;

import com.lukebu.workmt.Main;
import com.lukebu.workmt.dashboard.DashboardController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;

public class AddTaskController {
    @FXML
    private TextField taskNameTF;
    @FXML
    private TextArea taskDescriptionTA;
    @FXML
    private DatePicker taskDueDateDP;

    private TaskDataProcessing taskDataProcessing = new TaskDataProcessing();



    @FXML
    public void showAddTaskDialog()throws IOException, SQLException {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(Main.getInstance().mainBorderPane.getScene().getWindow());
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/scenes/task/addTask.fxml"));

        try {
            dialog.getDialogPane().setContent(loader.load());
        } catch (IOException e) {e.printStackTrace();
            System.out.println("Nie udało sie wyświetlić panelu, prosimy spróbować później");
            e.printStackTrace();
            return;
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        Optional<ButtonType> result = dialog.showAndWait();

        if(result.isPresent() && result.get().equals(ButtonType.OK)) {
            AddTaskController controller = loader.getController();
            controller.addTaskToList();
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/scenes/dashboard/dashboard.fxml"));
            fxmlLoader.load();
            DashboardController dashboardController = fxmlLoader.getController();
            dashboardController.refreshView();

            System.out.println("OK, pressed");
        } else {
            System.out.println("CANCEL, pressed");
        }
    }

    private void addTaskToList() throws SQLException {
        String taskName = taskNameTF.getText().trim();
        String taskDescription = taskDescriptionTA.getText().trim();
        LocalDate taskEndDate = taskDueDateDP.getValue();
        taskDataProcessing.addTask(taskName, taskDescription,taskEndDate);
    }
}
