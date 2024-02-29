package contact.management.system;


import java.io.Serializable;
import java.util.UUID;

public class Contact implements Serializable {
    private static final long serialVersionUID = 1L;
    private  String contactName;
    private  String contactNumber;
    private  String contactEmail;
    private String contactID;

    public Contact(String contactName, String contactNumber, String contactEmail) {
        this.contactName = contactName;
        this.contactNumber = contactNumber;
        this.contactEmail = contactEmail;
        this.contactID = genConID();
    }

    private String genConID() {
        return UUID.randomUUID().toString();
    }
    
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getContactName() {
        return contactName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public String getContactID() {
        return contactID;
    }
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public void setContactID(String contactID) {
        this.contactID = contactID;
    }
   
    @Override
    public String toString() {
        return contactName + " | " + contactNumber + " | " + contactEmail;
    }

}
