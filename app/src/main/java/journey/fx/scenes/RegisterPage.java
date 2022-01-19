package journey.fx.scenes;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import jfxtras.styles.jmetro.JMetroStyleClass;
import journey.fx.components.TextInputControlWithLabel;

public class RegisterPage {
    private static Node registerForm(Stage stage) {
        VBox formVBox = new VBox(20);

        double fieldWidth = 320;

        VBox usernameFieldBox = TextInputControlWithLabel.create(new TextField(), "Ingrese su nombre de usuario",
                "Nombre de usuario", fieldWidth);

        VBox firstNameFieldBox = TextInputControlWithLabel.create(new TextField(), "Ingrese su primer nombre",
                "Primer nombre", fieldWidth);

        VBox lastNameFieldBox = TextInputControlWithLabel.create(new TextField(), "Ingrese su apellido", "Apellido",
                fieldWidth);

        DatePicker birthDatePicker = new DatePicker();

        ComboBox<String> sexComboBox = new ComboBox<>();
        sexComboBox.getItems().addAll("hola", "hello");

        VBox numeroContactoFieldBox = TextInputControlWithLabel.create(new TextField(), "Ingrese su número de contacto",
                "Número de contacto", fieldWidth);

        VBox ocupacionFieldBox = TextInputControlWithLabel.create(new TextField(), "Ingrese su ocupación", "Ocupación",
                fieldWidth);

        VBox weightFieldBox = TextInputControlWithLabel.create(new TextField(), "Ingrese su peso en kg", "Peso",
                fieldWidth);

        VBox heightFieldBox = TextInputControlWithLabel.create(new TextField(), "Ingrese su altura en cm", "Altura",
                fieldWidth);

        VBox passwordFieldBox = TextInputControlWithLabel.create(new PasswordField(), "Ingrese su contraseña",
                "Contraseña", fieldWidth);

        Button submitButton = new Button("Iniciar sesión");
        submitButton.setOnAction((e) -> {
            VBox r = new VBox();

            stage.setScene(new Scene(r, 200, 200));
        });

        Label registerPrompt = new Label("¿No tienes cuenta? ¡Regístrate!");

        formVBox.getChildren().addAll(usernameFieldBox, firstNameFieldBox, lastNameFieldBox, birthDatePicker,
                sexComboBox, numeroContactoFieldBox, ocupacionFieldBox, weightFieldBox, heightFieldBox,
                passwordFieldBox, submitButton, registerPrompt);

        formVBox.setAlignment(Pos.CENTER);
        return formVBox;
    }

    public static Scene create(Stage stage) {
        StackPane root = new StackPane();
        root.getStyleClass().add(JMetroStyleClass.BACKGROUND);

        Scene scene = new Scene(root, 640, 480); // new Scene(640, 480);
        stage.setResizable(false);

        // Components
        Label title = new Label("Registro");
        title.setFont(Font.font(42));

        var form = registerForm(stage);

        // Main VBox
        VBox vBox = new VBox(20, title, form);
        vBox.setAlignment(Pos.CENTER);

        ScrollPane scrollPane = new ScrollPane(vBox);
        scrollPane.setFitToWidth(true);

        // Scene setup
        root.getChildren().addAll(scrollPane);

        return scene;
    }
}
