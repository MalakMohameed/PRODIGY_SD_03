package contact.management.system;

import java.io.*;

import java.io.Serializable;
public class Contact implements Serializable {
    private String ContactName;
    private String ContactNumber;
    private String ConactEmail;
    private String ConactID;

    public void setContactName(String ContactName) {
        this.ContactName = ContactName;
    }

    public void setContactNumber(String ContactNumber) {
        this.ContactNumber = ContactNumber;
    }

    public void setConactEmail(String ConactEmail) {
        this.ConactEmail = ConactEmail;
    }

    public void setConactID(String ConactID) {
        this.ConactID = ConactID;
    }

    public String getContactName() {
        return ContactName;
    }

    public String getContactNumber() {
        return ContactNumber;
    }

    public String getConactEmail() {
        return ConactEmail;
    }

    public String getConactID() {
        return ConactID;
    }
    
public Contact(String ContactName, String ContactNumber, String ConactEmail) {
    this.ContactName = ContactName;
    this.ContactNumber = ContactNumber;
    this.ConactEmail = ConactEmail;
    this.ConactID = GenConID(ContactName, ContactNumber, ConactEmail);
}

    String GenConID(String ConactName, String ConactNumber, String ConactEmail){
      return ContactName.substring(0, 3) + ConactNumber.substring(0, 3) + ConactEmail.substring(0, 3);
    }    

}
