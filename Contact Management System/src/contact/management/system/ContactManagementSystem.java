package contact.management.system;

import java.util.ArrayList;

public class ContactManagementSystem {

    public static void main(String[] args) {
        Contact[] contacts = new Contact[2];
        contacts[0] = new Contact("John Doe", "123456789", "john@example.com");
        contacts[1] = new Contact("Jane Smith", "987654321", "jane@example.com");
        FileManager.saveContact(contacts[0], "contacts.bin");
        FileManager.saveContact(contacts[1], "contacts.bin");
        ArrayList<Contact> loadedContacts = FileManager.loadContacts("contacts.bin");

        for (int i = 0; i < 2; i++) {
            System.out.println(contacts[i] + "\n");
        }
    }
}
