package com.lukebu.workmt.contact;

import com.lukebu.workmt.ChangeSceneProcessor;
import com.lukebu.workmt.events.EventProcessor;
import com.lukebu.workmt.events.task.TaskListEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ContactListController implements Initializable {

    private ObservableList<Contact> contacts = FXCollections.observableArrayList();

    @FXML
    private TableView<Contact> tableView = new TableView<Contact>();
    @FXML
    private TableColumn<Contact, String> contactName;
    @FXML
    private TableColumn<Contact, String> contactSurname;
    @FXML
    private TableColumn<Contact, String> mobilePhone;
    @FXML
    private TableColumn<Contact, String> desktopPhone;
    @FXML
    private TableColumn<Contact, String> email;
    @FXML
    private TableColumn<Contact, String> contactType;
    @FXML
    private BorderPane rootPane;

    private ContactDataProcessing contactDataProcessing = new ContactDataProcessing();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            contactDataProcessing.loadContactListFormDB();
            contacts = ContactData.getInstance().getContactsList();
            //findListChange();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        refreshView();
    }

    private void refreshView() {
        contactName.setCellValueFactory(new PropertyValueFactory<>("contactName"));
        contactSurname.setCellValueFactory(new PropertyValueFactory<>("contactSurname"));
        mobilePhone.setCellValueFactory(new PropertyValueFactory<>("contactMobilePhone"));
        desktopPhone.setCellValueFactory(new PropertyValueFactory<>("contactDesktopPhone"));
        email.setCellValueFactory(new PropertyValueFactory<>("contactEmail"));
        contactType.setCellValueFactory(new PropertyValueFactory<>("contactType"));

        tableView.setItems(contacts);
        tableView.getItems();

//    private void findListChange() {
//        tableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Contact>() {
//            @Override
//            public void changed(ObservableValue<? extends Contact> observable, Contact oldValue, Contact newValue) {
//                if (newValue != null) {
//
//                }
//            };
//        });
//    }*/
//
//    @FXML
//    private void handleModifyTaskOnList(ActionEvent event) {
//        ChangeSceneProcessor.changeScene(getClass().getResource("/scenes/task/modifyTask.fxml"), "Zmodyfikuj zadanie", null);

        /*Contact contact = tableView.getSelectionModel().getSelectedItem();
        StartModifyTaskEvent modifyTaskEvent = new StartModifyTaskEvent();
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
