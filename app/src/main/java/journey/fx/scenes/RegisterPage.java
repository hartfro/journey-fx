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
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.JMetroStyleClass;
import jfxtras.styles.jmetro.Style;
import journey.fx.components.ControlWithLabel;

public class RegisterPage {
        private static Node registerForm(Stage stage) {
                VBox formVBox = new VBox(20);

                double fieldWidth = 320;

                // Username field
                TextField _usernameField = new TextField();
                _usernameField.setPromptText("Ingrese su nombre de usuario");

                VBox usernameFieldBox = ControlWithLabel.create(_usernameField, "Nombre de usuario", fieldWidth);

                // First name field
                TextField _firstNameField = new TextField();
                _firstNameField.setPromptText("Ingrese su primer nombre");

                VBox firstNameFieldBox = ControlWithLabel.create(_firstNameField, "Primer nombre", fieldWidth);

                // Last name field
                TextField _lastNameField = new TextField();
                _lastNameField.setPromptText("Ingrese su apellido");

                VBox lastNameFieldBox = ControlWithLabel.create(_lastNameField, "Apellido", fieldWidth);

                // Birth date field
                // FIXME: fix width
                DatePicker _birthDatePicker = new DatePicker();
                VBox birthDatePickerBox = ControlWithLabel.create(_birthDatePicker, "Fecha de nacimiento", fieldWidth);

                // Sex field
                ComboBox<String> _sexComboBox = new ComboBox<>();
                _sexComboBox.getItems().addAll("hola", "hello");

                VBox sexComboBox = ControlWithLabel.create(_sexComboBox, "Sexo", fieldWidth);

                // Número contacto name field
                TextField _numeroContactoField = new TextField();
                _numeroContactoField.setPromptText("Ingrese su número de contacto");

                VBox numeroContactoFieldBox = ControlWithLabel.create(_numeroContactoField, "Número de contacto",
                                fieldWidth);

                // Ocupación field
                TextField _ocupacionField = new TextField();
                _ocupacionField.setPromptText("Ingrese su ocupación");

                VBox ocupacionFieldBox = ControlWithLabel.create(_ocupacionField, "Ocupación", fieldWidth);

                // Password field
                TextField _passwordField = new PasswordField();
                _passwordField.setPromptText("Ingrese su contraseña");

                VBox passwordFieldBox = ControlWithLabel.create(_passwordField, "Contraseña", fieldWidth);

                Button submitButton = new Button("Registrarse");
                submitButton.setOnAction((e) -> {
                        VBox r = new VBox();

                        stage.setScene(new Scene(r, 200, 200));
                });

                formVBox.getChildren().addAll(usernameFieldBox, firstNameFieldBox, lastNameFieldBox, birthDatePickerBox,
                                sexComboBox, numeroContactoFieldBox, ocupacionFieldBox, passwordFieldBox, submitButton);

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
                title.setStyle("-fx-font-size: 42");

                var form = registerForm(stage);

                // Main VBox
                VBox vBox = new VBox(20, title, form);
                vBox.setAlignment(Pos.CENTER);

                ScrollPane scrollPane = new ScrollPane(vBox);
                scrollPane.setFitToWidth(true);

                // Scene setup
                root.getChildren().addAll(scrollPane);

                JMetro jMetro = new JMetro(Style.DARK);
                jMetro.setScene(scene);

                return scene;
        }
}
