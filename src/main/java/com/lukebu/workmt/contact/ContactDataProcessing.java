package com.lukebu.workmt.contact;

import com.lukebu.workmt.conector.Connector;
import com.lukebu.workmt.context.ClientContext;
import com.lukebu.workmt.query.contact.DeleteContactQuery;
import com.lukebu.workmt.query.contact.InsertContact;
import com.lukebu.workmt.query.contact.ModifyContact;
import com.lukebu.workmt.query.contact.SelectContactQuery;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ContactDataProcessing {

        private Connector connector = new Connector();
        private InsertContact insertContact = new InsertContact();
        private ModifyContact modifyContact = new ModifyContact();
        private SelectContactQuery selectContactQuery = new SelectContactQuery();
        private DeleteContactQuery deleteContactQuery = new DeleteContactQuery();
        private int result;

        public void addContact(String contactName, String contactSurname, String contactMobilePhone, String contactDesktopPhone, String email, String contactType ) throws SQLException {
            connector.createConnectionToDb();
            result = connector.insertUpdateStatement(insertContact.prepareQuery(contactName, contactSurname,contactMobilePhone, contactDesktopPhone, email, contactType));
            Contact contact = new Contact(ClientContext.getInstance().getUserId(),contactName, contactSurname,contactMobilePhone, contactDesktopPhone, email, contactType);

            if (result == 1) {
                connector.closeConnectionWithCommit();
                ContactData.getInstance().addContactsToList(contact);
            } else {
                connector.closeConnectionWithCommit();
            }
            loadContactListFormDB();
        }

        public void modifyTask(int index, int contactId, String contactName, String contactSurname, String contactMobilePhone, String contactDesktopPhone, String email, String contactType) throws SQLException {
            Contact contact = ContactData.getInstance().getContactByIndex(index);

            connector.createConnectionToDb();
            result = connector.insertUpdateStatement(modifyContact.prepareQuery(contactId, contactName, contactSurname, contactMobilePhone,contactDesktopPhone, email,contactType));

            Contact contact1 = new Contact(contactId, contactName, contactSurname, contactMobilePhone,contactDesktopPhone, email,contactType);

            if (result == 1) {
                connector.closeConnectionWithCommit();
                ContactData.getInstance().modifyContactsList(index,contact1);
            } else {
                connector.closeConnectionWithCommit();
            }
            loadContactListFormDB();
        }

        public void loadContactListFormDB() throws SQLException {
            ObservableList<Contact> contacts = FXCollections.observableArrayList();
            ResultSet rs = null;

            String statementQuery = selectContactQuery.prepareQuery();

            connector.createConnectionToDb();
            rs = connector.insertQueryStatement(statementQuery);

            while (rs.next()) {
                if (rs.getInt("CNT_ID") != 0 && rs.getInt("CNT_USR_ID") != 0) {
                    int contactId = rs.getInt("CNT_ID");
                    int userId = rs.getInt("CNT_USR_ID");
                    String contactName = rs.getString("CNT_NAME");
                    String contactSurname = rs.getString("CNT_SURNAME");
                    String contactMobilePhone = rs.getString("CNT_PHONE_NUMBER");
                    String contactDesktopPhone = rs.getString("CNT_DESK_NUMBER");
                    String email = rs.getString("CNT_EMAIL");
                    String contactType = rs.getString("CNT_TYPE");

                    Contact contact = new Contact(contactId, userId, contactName, contactSurname, contactMobilePhone, contactDesktopPhone,email, contactType);
                    contacts.add(contact);
                }
            }
            connector.closeConnectionWithCommit();
            ContactData.getInstance().refreshContactList(contacts);
        }

        @FXML
        public void deleteTask(ObservableList<Contact> contacts, Contact contact) throws SQLException, IOException {
            connector.createConnectionToDb();
            result = connector.insertUpdateStatement(deleteContactQuery.prepareQuery(contact.getContactId()));
            if (result == 1) {
                connector.closeConnectionWithCommit();
                ContactData.getInstance().removeFromContactList(contacts.indexOf(contact));
            } else {
                connector.closeConnectionWithCommit();
            }
        }
}
