package contact.management.system;
import java.io.*;

public class FileManager {
        
    public static void saveContacts(Contact[] contacts, String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(contacts);
            System.out.println("Contacts saved successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    public static Contact[] loadContacts(String filename) {
        Contact[] contacts = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            contacts = (Contact[]) ois.readObject();
            System.out.println("Contacts loaded successfully!");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return contacts;
    }
    
    
    
}