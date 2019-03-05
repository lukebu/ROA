package com.lukebu.workmt.menu;

import com.lukebu.workmt.Main;
import com.lukebu.workmt.tasks.AddTaskController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;

import java.io.IOException;
import java.util.Optional;

public class MenuController {
    @FXML
    private TextField taskNameTF;
    @FXML
    private TextArea taskDescriptionTA;
    @FXML
    private DatePicker taskDueDateDP;

    @FXML
    public void showAddTaskDialog() {
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
            controller.processResult();
            System.out.println("OK, pressed");
        } else {
            System.out.println("CANCEL, pressed");
        }
    }
}
