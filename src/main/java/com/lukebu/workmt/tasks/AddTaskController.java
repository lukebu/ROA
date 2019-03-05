package com.lukebu.workmt.tasks;

import com.lukebu.workmt.conector.Connector;
import com.lukebu.workmt.context.ClientContext;
import com.lukebu.workmt.query.InsertNewTask;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class AddTaskController {
    @FXML
    private TextField taskNameTF;
    @FXML
    private javafx.scene.control.TextArea taskDescriptionTA;
    @FXML
    private DatePicker taskDueDateDP;

    private Connector connector = new Connector();
    private InsertNewTask stmt = new InsertNewTask();
    private int result;

    public void processResult() {
        String taskName = taskNameTF.getText().trim();
        String taskDescription = taskDescriptionTA.getText().trim();
        LocalDate taskDueDate = taskDueDateDP.getValue();

        connector.createConnectionToDb();
        result = connector.insertUpdateStatement(stmt.prepareQuery(taskName, taskDescription, taskDueDate));

        if (result == 1) {
            System.out.println("Udało się");
            connector.closeConnectionWithCommit();
        } else {
            System.out.println("Nie udąło się");
            connector.closeConnectionWithCommit();
        }
    }
}
