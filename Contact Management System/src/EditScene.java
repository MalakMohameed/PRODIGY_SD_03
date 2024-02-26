import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class EditScene {

    public static void display() {
        Stage stage = new Stage();
        stage.setTitle("Edit Contact");

        // Create text fields for editing contact information
        TextField nameTextField = new TextField();
        TextField numberTextField = new TextField();
        TextField emailTextField = new TextField();

        // Create labels for text fields
        Label nameLabel = new Label("Name:");
        Label numberLabel = new Label("Number:");
        Label emailLabel = new Label("Email:");

        // Create a save button to save the changes
        Button saveButton = new Button("Save");

        // Create layout for the edit scene
        VBox layout = new VBox();
        layout.getChildren().addAll(nameLabel, nameTextField, numberLabel, numberTextField, emailLabel, emailTextField, saveButton);

        // Set the scene
        Scene scene = new Scene(layout, 300, 200);
        stage.setScene(scene);
        stage.show();
    }
}
