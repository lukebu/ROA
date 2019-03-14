package com.lukebu.workmt.tasks;

import com.lukebu.workmt.conector.Connector;
import com.lukebu.workmt.context.ClientContext;
import com.lukebu.workmt.dashboard.DashboardController;
import com.lukebu.workmt.query.task.DeleteTaskQuery;
import com.lukebu.workmt.query.task.InsertNewTask;
import com.lukebu.workmt.query.task.ModifyTask;
import com.lukebu.workmt.query.task.SelectTasksQuery;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class TaskDataProcessing {
    private Connector connector = new Connector();
    private InsertNewTask insertNewTask = new InsertNewTask();
    private ModifyTask modifyTask = new ModifyTask();
    private SelectTasksQuery selectTasksQuery = new SelectTasksQuery();
    private DeleteTaskQuery deleteTaskQuery = new DeleteTaskQuery();
    private int result;

    public void addTask(String taskName, String taskDescription, LocalDate taskDueDate) throws SQLException {
        connector.createConnectionToDb();
        result = connector.insertUpdateStatement(insertNewTask.prepareQuery(taskName, taskDescription, taskDueDate));
        Task task = new Task(ClientContext.getInstance().getUserId(),taskName,taskDescription,taskDueDate);

        if (result == 1) {
            connector.closeConnectionWithCommit();
            TaskData.getInstance().addTaskToList(task);
        } else {
            connector.closeConnectionWithCommit();
        }
        loadTaskListFormDB();
    }

    public void modifyTask(int index, int taskId, String taskName, String taskDescription, LocalDate taskDueDate) throws SQLException {
        Task task = TaskData.getInstance().getTaskByIndex(index);

        connector.createConnectionToDb();
        result = connector.insertUpdateStatement(modifyTask.prepareQuery(taskId,taskName, taskDescription, taskDueDate));

        Task task1 = new Task(taskId,taskName,taskDescription,taskDueDate);

        if (result == 1) {
            connector.closeConnectionWithCommit();
            TaskData.getInstance().modifyTaskList(index,task1);
        } else {
            connector.closeConnectionWithCommit();
        }
        loadTaskListFormDB();
    }

    public void loadTaskListFormDB() throws SQLException {
        ObservableList<Task> tasks = TaskData.getInstance().getTaskList();
        tasks.clear();
        ResultSet rs = null;

        String statementQuery = selectTasksQuery.prepareQuery();

        connector.createConnectionToDb();
        rs = connector.insertQueryStatement(statementQuery);

        while (rs.next()) {
            if (rs.getInt("TSK_ID") != 0 && rs.getInt("TSK_USR_ID") != 0) {
                int taskId = rs.getInt("TSK_ID");
                int userId = rs.getInt("TSK_USR_ID");
                String taskName = rs.getString("TSK_NAME");
                String taskDescription = rs.getString("TSK_DESCRIPTION");
                LocalDate taskDueDate = rs.getDate("TSK_DUE_DATE").toLocalDate();

                Task task = new Task(taskId, userId, taskName, taskDescription, taskDueDate);
                tasks.add(task);
            }
        }
        connector.closeConnectionWithCommit();
        TaskData.getInstance().refreshTaskList(tasks);
    }

    @FXML
    public void deleteTask(ObservableList<Task> tasks, Task task) throws SQLException, IOException {
        connector.createConnectionToDb();
        result = connector.insertUpdateStatement(deleteTaskQuery.prepareQuery(task.getTaskId()));
        if (result == 1) {
            connector.closeConnectionWithCommit();
            TaskData.getInstance().removeFromTaskList(tasks.indexOf(task));
        } else {
            connector.closeConnectionWithCommit();
        }

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/scenes/dashboard/dashboard.fxml"));
        fxmlLoader.load();
        DashboardController dashboardController = fxmlLoader.getController();
        dashboardController.refreshView();
    }

    public ObservableList<Task> getTaskListAfterOperations() {
        ObservableList<Task> tasks = TaskData.getInstance().getTaskList();
        return tasks;
    }


}
