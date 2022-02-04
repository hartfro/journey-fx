package journey.fx.scenes;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.JMetroStyleClass;
import jfxtras.styles.jmetro.Style;
import journey.core.Estado;
import journey.fx.components.ControlWithLabel;
import journey.fx.utils.KeyEventConsumers;

/**
 * Esta clase permite mostrar los componentes gráficos de la página para iniciar sesión
 * @author Grupo 23
 * @version 02/01/2022
 */
public class LoginMenuPage {
    /**
     * Método que pone en escena los componentes de iniciar sesión
     * @param stage
     * @param journey
     * @return scene
     */
    public static Scene scene(Stage stage, Estado journey) {
        StackPane root = new StackPane();
        root.getStyleClass().add(JMetroStyleClass.BACKGROUND);

        Scene scene = new Scene(root, 640, 480);
        stage.setResizable(false);

        // Components
        Label title = new Label("Journey");
        title.setStyle("-fx-font-size: 42");

		double fieldWidth = root.getWidth() / 2;

        // Username field
        TextField _usernameField = new TextField();
        _usernameField.setPromptText("Ingrese su nombre de usuario");
        _usernameField.addEventHandler(KeyEvent.KEY_TYPED, (e) -> KeyEventConsumers.consumeNonAlphanumeric(e));

        VBox usernameFieldBox = ControlWithLabel.create(_usernameField, "Nombre de usuario", fieldWidth);

        // Password field
        TextField _passwordField = new PasswordField();
        _passwordField.setPromptText("Ingrese su contraseña");
        _passwordField.addEventHandler(KeyEvent.KEY_TYPED, (e) -> KeyEventConsumers.consumeNonAlphanumeric(e));

        VBox passwordFieldBox = ControlWithLabel.create(_passwordField,
                "Contraseña", fieldWidth);

        // Error label
        Label errorLabel = new Label();

        // Submit button
        Button submitButton = new Button("Iniciar sesión");
        submitButton.setDefaultButton(true);

        EventHandler<ActionEvent> onSubmit = (e) -> {
            var username = _usernameField.getText();
            var password = _passwordField.getText();

            var user = journey.login(username, password);
            if (user != null) {
                // Redirect to LoggedInMenu
                stage.setScene(LoggedInMenu.scene(stage, journey));
            } else {
                errorLabel.setText("Usuario o contraseña incorrecta.");
            }
        };

        submitButton.setOnAction(onSubmit);

        Button registerButton = new Button("Registrarse");
        registerButton.setOnAction((e) -> {
            stage.setScene(RegisterPage.create(stage, journey));
        });

        // Main VBox
        VBox vBox = new VBox(20, title, usernameFieldBox, passwordFieldBox, errorLabel, submitButton, registerButton);
        vBox.setAlignment(Pos.CENTER);

        // Scene setup
        root.getChildren().addAll(vBox);

        JMetro jMetro = new JMetro(Style.DARK);
        jMetro.setScene(scene);

        return scene;
    }
}
