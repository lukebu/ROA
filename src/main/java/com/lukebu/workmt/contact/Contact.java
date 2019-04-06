package com.lukebu.workmt.contact;

public class Contact {

    private int contactId;
    private int usrId;
    private String contactName;
    private String contactSurname;
    private String contactMobilePhone;
    private String contactDesktopPhone;
    private String contactEmail;
    private String contactType;

    public Contact(int usrId, String contactName, String contactSurname, String contactMobilePhone, String contactDesktopPhone, String contactEmail, String contactType) {
        this.usrId = usrId;
        this.contactName = contactName;
        this.contactSurname = contactSurname;
        this.contactMobilePhone = contactMobilePhone;
        this.contactDesktopPhone = contactDesktopPhone;
        this.contactEmail = contactEmail;
        this.contactType = contactType;
    }

    public Contact(int contactId, int usrId, String contactName, String contactSurname, String contactMobilePhone, String contactDesktopPhone, String contactEmail, String contactType) {
        this.contactId = contactId;
        this.usrId = usrId;
        this.contactName = contactName;
        this.contactSurname = contactSurname;
        this.contactMobilePhone = contactMobilePhone;
        this.contactDesktopPhone = contactDesktopPhone;
        this.contactEmail = contactEmail;
        this.contactType = contactType;
    }

    public int getContactId() {
        return contactId;
    }
}
