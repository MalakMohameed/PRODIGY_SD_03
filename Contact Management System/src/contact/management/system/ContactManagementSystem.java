package contact.management.system;

public class ContactManagementSystem {

    public static void main(String[] args) {
        Contact[] contacts = new Contact[2];
        contacts[0] = new Contact("John Doe", "123456789", "john@example.com");
        contacts[1] = new Contact("Jane Smith", "987654321", "jane@example.com");
        FileManager.saveContacts(contacts, "contacts.bin");

        Contact[] loadedContacts = FileManager.loadContacts("contacts.bin");
        if (loadedContacts != null) {
            for (Contact contact : loadedContacts) {
                System.out.println(contact.getContactName() + ", " + contact.getContactNumber() + ", " + contact.getConactEmail());
            }
        }
    }
}