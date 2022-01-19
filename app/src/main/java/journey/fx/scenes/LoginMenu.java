package journey.fx.scenes;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import jfxtras.styles.jmetro.JMetroStyleClass;
import journey.fx.components.ControlWithLabel;

public class LoginMenu {
    public static Scene create(Stage stage) {
        StackPane root = new StackPane();
        root.getStyleClass().add(JMetroStyleClass.BACKGROUND);

        Scene scene = new Scene(root, 640, 480); // new Scene(640, 480);
        stage.setResizable(false);

        // Components
        Label title = new Label("Journey");
        title.setFont(Font.font(42));

		double fieldWidth = root.getWidth() / 2;

        // Username field
        TextField _usernameField = new TextField();
        _usernameField.setPromptText("Ingrese su nombre de usuario");

        VBox usernameFieldBox = ControlWithLabel.create(_usernameField, "Nombre de usuario", fieldWidth);

        // Password field
        TextField _passwordField = new PasswordField();
        _passwordField.setPromptText("Ingrese su contraseña");

        VBox passwordFieldBox = ControlWithLabel.create(_passwordField,
                "Contraseña", fieldWidth);

        Button submitButton = new Button("Iniciar sesión");
        submitButton.setOnAction((e) -> {
            VBox r = new VBox();

            stage.setScene(new Scene(r, 200, 200));
        });

        Label registerPrompt = new Label("¿No tienes cuenta? ¡Regístrate!");

        // Main VBox
        VBox vBox = new VBox(20, title, usernameFieldBox, passwordFieldBox, submitButton, registerPrompt);
        vBox.setAlignment(Pos.CENTER);

        // Scene setup
        root.getChildren().addAll(vBox);

        return scene;
    }
}
