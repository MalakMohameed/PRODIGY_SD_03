package contact.management.system;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
    public static void saveContact(Contact contact, String filename) throws IOException {
        List<Contact> contacts = loadContacts(filename);
        if (contacts == null) {
            contacts = new ArrayList<>();
        }
        contacts.add(contact);
        saveContacts(contacts, filename);
    }

    public static List<Contact> loadContacts(String filename) {
        List<Contact> contacts = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            while (true) {
                Object obj = ois.readObject();
                if (obj instanceof Contact) {
                    contacts.add((Contact) obj);
                }
            }
        } catch (EOFException e) {
            // End of file reached
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace(); // Handle or log the exception appropriately
        }
        return contacts;
    }

    public static void deleteContact(Contact selectedContact, String filename) throws IOException {
    List<Contact> contacts = loadContacts(filename);
    if (contacts != null) {
        contacts.removeIf(contact -> contact.getContactID().equals(selectedContact.getContactID()));
        saveContacts(contacts, filename);
        System.out.println("Contact has been deleted succesfully!");
    }
    }

    public static void saveContacts(List<Contact> contacts, String filename) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            for (Contact contact : contacts) {
                oos.writeObject(contact);
            }
        }
    }
}
