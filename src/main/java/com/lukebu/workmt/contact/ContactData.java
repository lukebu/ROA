package com.lukebu.workmt.contact;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ContactData {

    private ObservableList<Contact> contacts = FXCollections.observableArrayList();

    private static ContactData instance = new ContactData();

    public static ContactData getInstance() {
        return instance;
    }

    public ObservableList<Contact> getContactsList(){
        return instance.contacts;
    }

    public void addContactsToList(Contact contact) {
        instance.contacts.add(contact);
    }

    public void removeFromContactList(int index) {
        instance.contacts.remove(contacts.get(index));
    }

    public void modifyContactsList(int index, Contact contact) {
        instance.contacts.set(index,contact);
    }

    public Contact getContactByIndex(int index) {
        return instance.contacts.get(index);
    }

    public void refreshContactList(ObservableList<Contact> contacts) {
        instance.contacts = contacts;
    }
}
