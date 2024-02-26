import contact.management.system.Contact;
import contact.management.system.FileManager;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.ArrayList;

public class GUI extends Application {

    private TableView<Contact> tableView;

    @Override
    public void start(Stage primaryStage) {
        Label nameLabel = new Label("Name:");
        TextField nameTextField = new TextField();

        Label numberLabel = new Label("Number:");
        TextField numberTextField = new TextField();

        Label emailLabel = new Label("Email:");
        TextField emailTextField = new TextField();

        Label success = new Label();

        Button addButton = new Button("Add Contact");
        addButton.setStyle("-fx-background-color: green; -fx-text-fill: white;");
        addButton.setOnAction(e -> {
            Contact newContact = new Contact(nameTextField.getText(), numberTextField.getText(), emailTextField.getText());
            FileManager.saveContact(newContact, "contacts.bin");
            success.setText("Contact Added Successfully!");
            success.setStyle("-fx-text-fill: green;");
            nameTextField.clear();
            numberTextField.clear();
            emailTextField.clear();
            tableView.getItems().add(newContact); // Add new contact to TableView
        });

        Button deleteButton = new Button("Delete Contact");
        deleteButton.setStyle("-fx-background-color: red; -fx-text-fill: white;");
        deleteButton.setDisable(true);
        deleteButton.setOnAction(e -> {
            Contact selectedContact = tableView.getSelectionModel().getSelectedItem();
            if (selectedContact != null) {
                tableView.getItems().remove(selectedContact);
                FileManager.deleteContact(selectedContact, "contacts.bin");
            }
        });

        Button saveButton = new Button("Save");
        saveButton.setDisable(true);
        saveButton.setOnAction(e -> {
            // Implement save functionality here
        });

        Button editButton = new Button("Edit Contacts");
        editButton.setOnAction(e -> {
            saveButton.setDisable(false);
            deleteButton.setDisable(false);
            tableView.setDisable(false);
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
        root.add(saveButton, 1, 5);
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
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
