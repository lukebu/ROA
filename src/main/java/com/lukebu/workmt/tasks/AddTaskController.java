package com.lukebu.workmt.tasks;

import com.lukebu.workmt.conector.Connector;
import com.lukebu.workmt.context.ClientContext;
import com.lukebu.workmt.query.task.DeleteTask;
import com.lukebu.workmt.query.task.InsertNewTask;
import com.lukebu.workmt.query.task.ModifyTask;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.time.LocalDate;

public class AddTaskController {
    @FXML
    private TextField taskNameTF;
    @FXML
    private TextArea taskDescriptionTA;
    @FXML
    private DatePicker taskDueDateDP;

    private Connector connector = new Connector();
    private InsertNewTask insertNewTask = new InsertNewTask();
    private int result;

    public void addTask() {
        String taskName = taskNameTF.getText().trim();
        String taskDescription = taskDescriptionTA.getText().trim();
        LocalDate taskDueDate = taskDueDateDP.getValue();

        connector.createConnectionToDb();
        result = connector.insertUpdateStatement(insertNewTask.prepareQuery(taskName, taskDescription, taskDueDate));

        if (result == 1) {
            connector.closeConnectionWithCommit();
            TaskData.getInstance().addTaskToList(new Task(ClientContext.getInstance().getUserId(),taskName,taskDescription,taskDueDate));
        } else {
            connector.closeConnectionWithCommit();
        }
    }
}
