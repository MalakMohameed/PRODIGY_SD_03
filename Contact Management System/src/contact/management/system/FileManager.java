package contact.management.system;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManager {

 public static void saveContact(Contact contact, String filename) {
        List<Contact> contacts = loadContacts(filename); // Load existing contacts
        if (contacts == null) {
            contacts = new ArrayList<>();
        }
        contacts.add(contact); // Add the new contact
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            for (Contact c : contacts) {
                oos.writeObject(c); // Write all contacts to the file
            }
            System.out.println("Contact saved successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Contact> loadContacts(String contactsbin) {
    ArrayList<Contact> contacts = new ArrayList<>();
    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(contactsbin))) {
        Object data;
        while ((data = ois.readObject()) != null) {
            if (data instanceof Contact) {
                Contact contact = (Contact) data;
                contacts.add(contact);
            }
        }
    } catch (EOFException e) {
        // End of file reached, no need to handle
    } catch (IOException | ClassNotFoundException e) {
        System.err.println("Error loading contacts: " + e.getMessage());
    }
    return contacts;
}




    public static void deleteContact(Contact selectedContact, String filename) {
        List<Contact> contactList = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            Object data;
            while ((data = ois.readObject()) != null) {
                if (data instanceof Contact) {
                    Contact contact = (Contact) data;
                    if (!contact.equals(selectedContact)) {
                        contactList.add(contact);
                    }
                }
            }
        } catch (EOFException e) {
            // End of file reached
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            for (Contact contact : contactList) {
                oos.writeObject(contact);
            }
            System.out.println("Contact deleted successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
