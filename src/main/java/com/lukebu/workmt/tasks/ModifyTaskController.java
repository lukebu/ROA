package com.lukebu.workmt.tasks;

import com.lukebu.workmt.Main;
import com.lukebu.workmt.dashboard.DashboardController;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;

public class ModifyTaskController {

    @FXML
    private TextField taskNameTF;
    @FXML
    private TextArea taskDescriptionTA;
    @FXML
    private DatePicker taskDueDateDP;

    private TaskDataProcessing taskDataProcessing = new TaskDataProcessing();

    @FXML
    public void showModifyTaskDialog(Task task) throws SQLException, IOException {
        FXMLLoader loader = new FXMLLoader();
        ObservableList<Task> taskObservableList = TaskData.getInstance().getTaskList();
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(Main.getInstance().mainBorderPane.getScene().getWindow());
        loader.setLocation(getClass().getResource("/scenes/task/modifyTask.fxml"));

        try {
            dialog.getDialogPane().setContent(loader.load());

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Nie udało sie wyświetlić panelu modyfikacji zadania prosimy spróbować później");
            e.printStackTrace();
            return;
        }
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        ModifyTaskController modifyTaskController = loader.getController();
        modifyTaskController.fillModifyForm(taskObservableList.indexOf(task));

        Optional<ButtonType> result = dialog.showAndWait();

        if(result.isPresent() && result.get().equals(ButtonType.OK)) {
            modifyTaskController.modifyTaskOnList(taskObservableList.indexOf(task), task.getTaskId());
            DashboardController dashboardController = new DashboardController();
            dashboardController.refreshView();
        } else {
        }
    }

    private void modifyTaskOnList(int index, int taskId) throws SQLException {
        String taskName = taskNameTF.getText().trim();
        String taskDescription = taskDescriptionTA.getText().trim();
        LocalDate taskEndDate = taskDueDateDP.getValue();
        taskDataProcessing.modifyTask(index, taskId,taskName, taskDescription,taskEndDate);
    }

    @FXML
    public void fillModifyForm(int index){
        Task task = TaskData.getInstance().getTaskByIndex(index);

        taskNameTF.setText(task.getTaskName());
        taskDescriptionTA.setText(task.getTaskDescription());
        taskDueDateDP.setValue(task.getTaskDueDate());
    }
}
