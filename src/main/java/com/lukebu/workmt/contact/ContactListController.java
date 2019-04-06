package com.lukebu.workmt.contact;

import com.lukebu.workmt.ChangeSceneProcessor;
import com.lukebu.workmt.events.EventProcessor;
import com.lukebu.workmt.events.contact.AddNewContactEvent;
import com.lukebu.workmt.events.contact.ContactListEvent;
import com.lukebu.workmt.events.task.ModifyTaskEvent;
import com.lukebu.workmt.events.task.NewTaskEvent;
import com.lukebu.workmt.events.task.TaskListEvent;
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
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ContactListController implements Initializable {

    private ObservableList<Contact> contacts = FXCollections.observableArrayList();

    @FXML
    private TableView tableView = new TableView();
    @FXML
    private TableColumn contactName;
    @FXML
    private TableColumn contactSurname;
    @FXML
    private TableColumn mobilePhone;
    @FXML
    private TableColumn desktopPhone;
    @FXML
    private TableColumn email;
    @FXML
    private TableColumn contactType;
    @FXML
    private BorderPane rootPane;

    private ContactDataProcessing contactDataProcessing = new ContactDataProcessing();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            EventProcessor.getInstance().registerListener( event -> {
                if(event instanceof TaskListEvent ) {
                    try {
                        Parent parent = FXMLLoader.load(getClass().getResource("/scenes/dashboard/dashboard.fxml"));
                        Stage stage = (Stage) tableView.getScene().getWindow();
                        Scene scene = new Scene(parent);
                        stage.setScene(scene);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });

            contactDataProcessing.loadContactListFormDB();
            contacts = ContactData.getInstance().getContactsList();
            //findListChange();
            tableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
            tableView.getSelectionModel().selectFirst();
            Scene scene = tableView.getScene();
            System.out.println(scene);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        /*EventProcessor.getInstance().registerListener(event -> {
            if(event instanceof AddNewContactEvent || event instanceof ModifyTaskEvent) {
                refreshView();
            }  else if (event instanceof TaskListEvent) {
                Parent parent = null;
                try {
                    parent = FXMLLoader.load(getClass().getResource("/scenes/dashboard/dashboard.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Scene scene = tableView.getScene();
                scene.setRoot(parent);
            }
        });*/


        //disableFormData();
    }

    /*private void refreshView() {
        tableView.getSelectionModel().selectLast();
    }

    private void findListChange() {
        tableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Contact>() {
            @Override
            public void changed(ObservableValue<? extends Contact> observable, Contact oldValue, Contact newValue) {
                if (newValue != null) {

                }
            };
        });
    }*/

    @FXML
    private void handleModifyTaskOnList(ActionEvent event) {
        ChangeSceneProcessor.changeScene(getClass().getResource("/scenes/task/modifyTask.fxml"), "Zmodyfikuj zadanie", null);

        /*Contact contact = tableView.getSelectionModel().getSelectedItem();
        ModifyTaskEvent modifyTaskEvent = new ModifyTaskEvent();
        modifyTaskEvent.setTask(task);
        EventProcessor.getInstance().sendEvent(modifyTaskEvent);*/
    }

    @FXML
    private void deleteTaskFromList() throws SQLException, IOException {
       /* Task task = taskListView.getSelectionModel().getSelectedItem();
        taskDataProcessing.deleteTask(tasks, task);
        disableFormData();
        taskListView.getSelectionModel().selectLast();
    }*/
}



}
