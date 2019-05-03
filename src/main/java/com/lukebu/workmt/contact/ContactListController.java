package com.lukebu.workmt.contact;

import com.lukebu.workmt.ChangeSceneProcessor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ContactListController implements Initializable {

    private ObservableList<Contact> contacts = FXCollections.observableArrayList();

    @FXML
    private TableView tableView;
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
            contactDataProcessing.loadContactListFormDB();
            contacts = ContactData.getInstance().getContactsList();
            //findListChange();
            tableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
            tableView.getSelectionModel().selectFirst();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
