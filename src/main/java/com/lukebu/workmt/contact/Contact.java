package com.lukebu.workmt.contact;

import javafx.beans.property.SimpleStringProperty;

public class Contact {
    public int getUsrId() {
        return usrId;
    }

    public String getContactName() {
        return contactName.get();
    }

    public SimpleStringProperty contactNameProperty() {
        return contactName;
    }

    public String getContactSurname() {
        return contactSurname.get();
    }

    public SimpleStringProperty contactSurnameProperty() {
        return contactSurname;
    }

    public String getContactMobilePhone() {
        return contactMobilePhone.get();
    }

    public SimpleStringProperty contactMobilePhoneProperty() {
        return contactMobilePhone;
    }

    public String getContactDesktopPhone() {
        return contactDesktopPhone.get();
    }

    public SimpleStringProperty contactDesktopPhoneProperty() {
        return contactDesktopPhone;
    }

    public String getContactEmail() {
        return contactEmail.get();
    }

    public SimpleStringProperty contactEmailProperty() {
        return contactEmail;
    }

    public String getContactType() {
        return contactType.get();
    }

    public SimpleStringProperty contactTypeProperty() {
        return contactType;
    }

    private int contactId;
    private int usrId;
    private SimpleStringProperty contactName;
    private SimpleStringProperty contactSurname;
    private SimpleStringProperty contactMobilePhone;
    private SimpleStringProperty contactDesktopPhone;
    private SimpleStringProperty contactEmail;
    private SimpleStringProperty contactType;

    public Contact(int usrId, String contactName, String contactSurname, String contactMobilePhone, String contactDesktopPhone, String contactEmail, String contactType) {
        this.usrId = usrId;
        this.contactName = new SimpleStringProperty(contactName);
        this.contactSurname = new SimpleStringProperty(contactSurname);
        this.contactMobilePhone = new SimpleStringProperty(contactMobilePhone);
        this.contactDesktopPhone = new SimpleStringProperty(contactDesktopPhone);
        this.contactEmail = new SimpleStringProperty(contactEmail);
        this.contactType = new SimpleStringProperty(contactType);
    }

    public Contact(int contactId, int usrId, String contactName, String contactSurname, String contactMobilePhone, String contactDesktopPhone, String contactEmail, String contactType) {
        this.contactId = contactId;
        this.usrId = usrId;
        this.contactName = new SimpleStringProperty(contactName);
        this.contactSurname = new SimpleStringProperty(contactSurname);
        this.contactMobilePhone = new SimpleStringProperty(contactMobilePhone);
        this.contactDesktopPhone = new SimpleStringProperty(contactDesktopPhone);
        this.contactEmail = new SimpleStringProperty(contactEmail);
        this.contactType = new SimpleStringProperty(contactType);
    }

    public int getContactId() {
        return contactId;
    }
}
