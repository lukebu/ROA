package com.lukebu.workmt.tasks;

import com.lukebu.workmt.conector.Connector;
import com.lukebu.workmt.query.task.InsertNewTask;
import com.lukebu.workmt.query.task.ModifyTask;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.time.LocalDate;

public class ModifyTaskController {

    @FXML
    private TextField taskNameTF;
    @FXML
    private TextArea taskDescriptionTA;
    @FXML
    private DatePicker taskDueDateDP;

    private Connector connector = new Connector();
    private ModifyTask modifyTask = new ModifyTask();

    private int result;

    public void modifyTask(int index, int taskId) {
        Task task = TaskData.getInstance().getTaskByIndex(index);

        String taskName = taskNameTF.getText().trim();
        String taskDescription = taskDescriptionTA.getText().trim();
        LocalDate taskDueDate = taskDueDateDP.getValue();

        connector.createConnectionToDb();
        result = connector.insertUpdateStatement(modifyTask.prepareQuery(taskId,taskName, taskDescription, taskDueDate));

        if (result == 1) {
            connector.closeConnectionWithCommit();
            TaskData.getInstance().modifyTaskList(index, new Task(taskId,taskName,taskDescription,taskDueDate));
        } else {
            connector.closeConnectionWithCommit();
        }
    }


    public void fillModifyForm(int index, int taskId){
        Task task = TaskData.getInstance().getTaskByIndex(index);

        taskNameTF.setText(task.getTaskName());
        taskDescriptionTA.setText(task.getTaskDescription());
        taskDueDateDP.setValue(task.getTaskDueDate());
    }
}
