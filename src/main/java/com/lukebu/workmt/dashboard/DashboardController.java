package com.lukebu.workmt.dashboard;

import com.lukebu.workmt.Main;
import com.lukebu.workmt.WorkManagerToolUtil;
import com.lukebu.workmt.alert.AlertMaker;
import com.lukebu.workmt.tasks.*;
import com.sun.javafx.scene.control.skin.ComboBoxListViewSkin;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DashboardController implements Initializable {

    private ObservableList<Task> tasks = FXCollections.observableArrayList();

    private FXMLLoader loader = new FXMLLoader();

    @FXML
    private BorderPane rootPane;
    @FXML
    private ListView<Task> taskListView;
    @FXML
    private TextArea taskAreaDetails;
    @FXML
    private Label dueDateLabel;
    @FXML
    private Label dueDateLabelText;
    @FXML
    private Button deleteTaskButton;
    @FXML
    private Button modifyTaskButton;

    private TaskDataProcessing taskDataProcessing = new TaskDataProcessing();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            taskDataProcessing.loadTaskListFormDB();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        tasks = TaskData.getInstance().getTaskList();
        findListChange();
        taskListView.setItems(tasks);
        taskListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        taskListView.getSelectionModel().selectFirst();
        disableFormData();
    }

    public Object getDashboardController () {
        return WorkManagerToolUtil.loadWindow(getClass().getResource("/scenes/dashboard/dashboard.fxml"));
    }

     public void refreshView() {
        taskListView.getItems();
        taskListView.getSelectionModel().selectLast();
    }


    private void findListChange() {
        taskListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Task>() {
            @Override
            public void changed(ObservableValue<? extends Task> observable, Task oldValue, Task newValue) {
                if (newValue != null) {
                    Task item = taskListView.getSelectionModel().getSelectedItem();

                    taskAreaDetails.setText(item.getTaskDescription());
                    dueDateLabel.setText(item.getTaskDueDate().toString());
                    disableFormData();
                }
            };
        });
    }

    private void disableFormData() {
        if (taskListView.getSelectionModel().isEmpty()){
            taskAreaDetails.clear();
            dueDateLabel.setText("");
            dueDateLabelText.setText("");
            modifyTaskButton.setDisable(true);
            deleteTaskButton.setDisable(true);
        } else {
            modifyTaskButton.setDisable(false);
            deleteTaskButton.setDisable(false);
        }
    }

    public void showModifyDialog() throws IOException, SQLException {
        ModifyTaskController modifyTaskController = new ModifyTaskController();
        modifyTaskController.showModifyTaskDialog(taskListView.getSelectionModel().getSelectedItem());
    }

    @FXML
    private void deleteTaskFromList() throws SQLException, IOException {
        Task task = taskListView.getSelectionModel().getSelectedItem();
        taskDataProcessing.deleteTask(tasks, task);
        disableFormData();
        taskListView.getSelectionModel().selectLast();
    }

}
