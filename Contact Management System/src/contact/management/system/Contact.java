package contact.management.system;


import java.io.Serializable;

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
        this.contactID = genConID(contactName, contactNumber, contactEmail);
    }

    private String genConID(String contactName, String contactNumber, String contactEmail) {
    String nameSubstring = contactName.length() >= 3 ? contactName.substring(0, 3) : contactName;
    String numberSubstring = contactNumber.length() >= 3 ? contactNumber.substring(0, 3) : contactNumber;
    String emailSubstring = contactEmail.length() >= 3 ? contactEmail.substring(0, 3) : contactEmail;
    
    return nameSubstring + numberSubstring + emailSubstring;
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
