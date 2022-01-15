package journey.fx;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.JMetroStyleClass;
import jfxtras.styles.jmetro.Style;

public class App extends Application {
    private VBox textInputControlWithLabel(TextInputControl inputControl, String controlPromptText, String labelText, double width) {
        inputControl.setPromptText(controlPromptText);

        VBox vBox = new VBox(5, new Label(labelText), inputControl);
        vBox.setAlignment(Pos.CENTER_LEFT);

        vBox.setMaxWidth(width);

        return vBox;
    }

    @Override
    public void start(Stage stage) {
        StackPane root = new StackPane();
        root.getStyleClass().add(JMetroStyleClass.BACKGROUND);

        Scene scene = new Scene(root, 640, 480); // new Scene(640, 480);
        stage.setResizable(false);

        // Components
        Label title = new Label("Journey");
        title.setFont(Font.font(42));

        double fieldWidth = root.getWidth() / 2;

        VBox usernameFieldBox = textInputControlWithLabel(new TextField(), "Ingrese su nombre de usuario", "Nombre de usuario", fieldWidth);

        VBox passwordFieldBox = textInputControlWithLabel(new PasswordField(), "Ingrese su contraseña", "Contraseña", fieldWidth);

        Button submitButton = new Button("Iniciar sesión");

        Label registerPrompt = new Label("¿No tienes cuenta? ¡Regístrate!");

        // Main VBox
        VBox vBox = new VBox(20, title, usernameFieldBox, passwordFieldBox, submitButton, registerPrompt);
        vBox.setAlignment(Pos.CENTER);

        // Scene setup
        root.getChildren().addAll(vBox);

        // Style
        JMetro jMetro = new JMetro(Style.DARK);
        jMetro.setScene(scene);

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}
