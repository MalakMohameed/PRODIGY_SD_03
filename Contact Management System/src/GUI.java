import contact.management.system.Contact;
import contact.management.system.FileManager;
import java.io.IOException;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GUI extends Application {

    private TableView<Contact> tableView;

    @Override
    public void start(Stage primaryStage) {
        Label nameLabel = new Label("Name:");
        TextField nameTextField = new TextField();

        Label numberLabel = new Label("Number:");
        TextField numberTextField = new TextField();
        numberTextField.setOnKeyTyped(event -> {
            char input = event.getCharacter().charAt(0);
            if (!Character.isDigit(input) && input != '\b'){
                showAlert("Invalid Input", "Please enter numeric values only (0-9).");
                event.consume();
            }
        });

        Label emailLabel = new Label("Email:");
        TextField emailTextField = new TextField();

        Label success = new Label();

        Button addButton = new Button("Add Contact");
        addButton.setStyle("-fx-background-color: green; -fx-text-fill: white;");
        addButton.setOnAction(e -> {
            String name = nameTextField.getText();
            String number = numberTextField.getText();
            String email = emailTextField.getText();

            if (isDuplicate(name, number, email)) {
                showAlert("Duplicate Entry", "The contact already exists in the database.");
                return;
            }
            else if(emailTextField.getText().isBlank()||numberTextField.getText().isBlank()|nameTextField.getText().isBlank()){
                 showAlert("Empty Entry", "Please enter data.");
                 return;
            }

            Contact newContact = new Contact(name, number, email);
            try {
                FileManager.saveContact(newContact, "contacts.bin");
            } catch (IOException ex) {
                Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            success.setText("Contact Added Successfully!");
            success.setStyle("-fx-text-fill: green;");
            nameTextField.clear();
            numberTextField.clear();
            emailTextField.clear();
            tableView.getItems().add(newContact);
        });

        Button deleteButton = new Button("Delete Contact");
        deleteButton.setStyle("-fx-background-color: red; -fx-text-fill: white;");
        deleteButton.setOnAction(e -> {
            Contact selectedContact = tableView.getSelectionModel().getSelectedItem();
            if (selectedContact != null) {
                tableView.getItems().remove(selectedContact);
                try {
                    FileManager.deleteContact(selectedContact, "contacts.bin");
                } catch (IOException ex) {
                    Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        //Button saveButton = new Button("Save");
        primaryStage.setOnCloseRequest(event -> {
           List<Contact> allContacts = tableView.getItems();
            try {
                FileManager.saveContacts(allContacts, "contacts.bin");
            } catch (IOException ex) {
                Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("All contacts saved successfully!");
        });

        Button editButton = new Button("Edit Contact");
        editButton.setOnAction(e -> {
            Contact selectedContact = tableView.getSelectionModel().getSelectedItem();
            if (selectedContact != null) {
                openEditContactWindow(selectedContact);
                
            }
        });

        GridPane root = new GridPane();
        root.setPadding(new Insets(10));
        root.setHgap(10);
        root.setVgap(10);

        root.add(nameLabel, 0, 0);
        root.add(nameTextField, 1, 0);
        root.add(numberLabel, 0, 1);
        root.add(numberTextField, 1, 1);
        root.add(emailLabel, 0, 2);
        root.add(emailTextField, 1, 2);
        root.add(addButton, 0, 3);
        root.add(editButton, 0, 5);
        root.add(deleteButton, 2, 5);
        root.add(success, 1, 3, 2, 1);

        tableView = new TableView<>();

        TableColumn<Contact, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getContactName()));

        TableColumn<Contact, String> numberColumn = new TableColumn<>("Number");
        numberColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getContactNumber()));
        numberColumn.setMinWidth(100);

        TableColumn<Contact, String> emailColumn = new TableColumn<>("Email");
        emailColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getContactEmail()));
        emailColumn.setMinWidth(200);

        tableView.getColumns().addAll(nameColumn, numberColumn, emailColumn);

        ArrayList<Contact> contacts = (ArrayList<Contact>) FileManager.loadContacts("contacts.bin");
        if (contacts != null) {
            tableView.getItems().addAll(contacts);
        }

        root.add(tableView, 0, 4, 3, 1);

        Scene scene = new Scene(root, 370, 400);
        primaryStage.setTitle("Contact Management System");
        primaryStage.getIcons().add(new Image("icon.png"));
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private boolean isDuplicate(String name, String number, String email) {
        for (Contact contact : tableView.getItems()) {
            if (contact.getContactName().equals(name) || contact.getContactNumber().equals(number) || contact.getContactEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    private void openEditContactWindow(Contact contact) {
    Stage editStage = new Stage();
    editStage.setTitle("Edit Contact");
    
    Label nameLabel = new Label("Name:");
    TextField nameTextField = new TextField(contact.getContactName());
    
    Label numberLabel = new Label("Number:");
    TextField numberTextField = new TextField(contact.getContactNumber());
    numberTextField.setOnKeyTyped(event -> {
            char input = event.getCharacter().charAt(0);
            if (!Character.isDigit(input) && input != '\b'){
                showAlert("Invalid Input", "Please enter numeric values only (0-9).");
                event.consume();
            }
        });
        
    Label emailLabel = new Label("Email:");
    TextField emailTextField = new TextField(contact.getContactEmail());
    
    Button saveButton = new Button("Save");
    saveButton.setOnAction(e -> {
        contact.setContactName(nameTextField.getText());
        contact.setContactNumber(numberTextField.getText());
        contact.setContactEmail(emailTextField.getText());
        
        try {
            FileManager.saveContact(contact, "contacts.bin");
        } catch (IOException ex) {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        editStage.close();
        tableView.refresh();
    });
    
    GridPane editGrid = new GridPane();
    editGrid.setPadding(new Insets(10));
    editGrid.setHgap(10);
    editGrid.setVgap(10);
    editGrid.addRow(0, nameLabel, nameTextField);
    editGrid.addRow(1, numberLabel, numberTextField);
    editGrid.addRow(2, emailLabel, emailTextField);
    editGrid.add(saveButton, 0, 3, 2, 1);
    
    Scene editScene = new Scene(editGrid, 250, 150);
    editStage.setScene(editScene);
    editStage.setResizable(false);
    editStage.show();
}
}
