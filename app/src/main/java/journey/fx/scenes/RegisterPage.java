package journey.fx.scenes;

import java.time.LocalDate;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.JMetroStyleClass;
import jfxtras.styles.jmetro.Style;
import journey.core.Estado;
import journey.core.Paciente;
import journey.core.Sexo;
import journey.fx.components.ControlWithLabel;
import journey.fx.utils.KeyEventConsumers;

// Se añade: util
import javafx.util.Callback;

//import javax.security.auth.callback.Callback;

public class RegisterPage {
    private static Node registerForm(Stage stage, Estado journey) {
        VBox formVBox = new VBox(20);

        double fieldWidth = 320;

        // Username field
        TextField _usernameField = new TextField();
        _usernameField.setPromptText("Ingrese su nombre de usuario");
        _usernameField.addEventHandler(KeyEvent.KEY_TYPED, e -> KeyEventConsumers.consumeNonAlphanumeric(e));

        VBox usernameFieldBox = ControlWithLabel.create(_usernameField, "Nombre de usuario", fieldWidth);

        // First name field
        TextField _firstNameField = new TextField();
        _firstNameField.setPromptText("Ingrese su primer nombre");
        _firstNameField.addEventHandler(KeyEvent.KEY_TYPED, e -> KeyEventConsumers.consumeNonLetters(e));

        VBox firstNameFieldBox = ControlWithLabel.create(_firstNameField, "Primer nombre", fieldWidth);

        // Last name field
        TextField _lastNameField = new TextField();
        _lastNameField.setPromptText("Ingrese su apellido");
        _lastNameField.addEventHandler(KeyEvent.KEY_TYPED, e -> KeyEventConsumers.consumeNonLetters(e));

        VBox lastNameFieldBox = ControlWithLabel.create(_lastNameField, "Apellido", fieldWidth);

        // Birth date field
        // FIXME: fix width

        // Se añade: Validación no fechas futuras
        Callback<DatePicker, DateCell> dayCellFactory = new Callback<DatePicker, DateCell>() {
            public DateCell call(final DatePicker datePicker) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item.isAfter(LocalDate.now())) {
                            this.setDisable(true);
                        }
                    }
                };
            }
        };

        DatePicker _birthDatePicker = new DatePicker();
        _birthDatePicker.setDayCellFactory(dayCellFactory);

        VBox birthDatePickerBox = ControlWithLabel.create(_birthDatePicker, "Fecha de nacimiento", fieldWidth);

        // Sex field
        ComboBox<Sexo> _sexComboBox = new ComboBox<>();
        for (var s : Sexo.values())
            _sexComboBox.getItems().add(s);

        VBox sexComboBox = ControlWithLabel.create(_sexComboBox, "Sexo", fieldWidth);

        // Número contacto name field
        TextField _numeroContactoField = new TextField();
        _numeroContactoField.setPromptText("Ingrese su número de contacto");
        _numeroContactoField.addEventHandler(KeyEvent.KEY_TYPED, e -> KeyEventConsumers.consumeNonDigits(e));

        VBox numeroContactoFieldBox = ControlWithLabel.create(_numeroContactoField, "Número de contacto", fieldWidth);
        // Ocupación field
        TextField _ocupacionField = new TextField();
        _ocupacionField.setPromptText("Ingrese su ocupación");
        _ocupacionField.addEventHandler(KeyEvent.KEY_TYPED, e -> KeyEventConsumers.consumeNonLetters(e));

        VBox ocupacionFieldBox = ControlWithLabel.create(_ocupacionField, "Ocupación", fieldWidth);

        // Password field
        TextField _passwordField = new PasswordField();
        _passwordField.setPromptText("Ingrese su contraseña");
        _passwordField.addEventHandler(KeyEvent.KEY_TYPED, e -> KeyEventConsumers.consumeNonAlphanumeric(e));

        VBox passwordFieldBox = ControlWithLabel.create(_passwordField, "Contraseña", fieldWidth);

        Button submitButton = new Button("Registrarse");
        submitButton.setOnAction((event) -> {
            if (_usernameField.getText().isEmpty() || _passwordField.getText().isEmpty() || _firstNameField.getText().isEmpty()
                    || _lastNameField.getText().isEmpty() || _birthDatePicker.getValue() == null || _sexComboBox.getValue() == null
                    || _numeroContactoField.getText().isEmpty() || _ocupacionField.getText().isEmpty() || _numeroContactoField.getLength() != 10)  {

                // Validaciones en blanco
                if (_usernameField.getText().isEmpty()) {
                    _usernameField.setPromptText("No dejar en blanco");
                }

                if (_passwordField.getText().isEmpty()) {
                    _passwordField.setPromptText("No dejar en blanco");
                }

                if (_firstNameField.getText().isEmpty()) {
                    _firstNameField.setPromptText("No dejar en blanco");
                }

                if (_lastNameField.getText().isEmpty()) {
                    _lastNameField.setPromptText("No dejar en blanco");
                }

                if (_birthDatePicker.getValue() == null) {
                    _birthDatePicker.setPromptText("No dejar en blanco");
                }

                if (_sexComboBox.getValue() == null) {
                    _sexComboBox.setPromptText("No dejar en blanco");
                }

                if (_ocupacionField.getText().isEmpty()) {
                    _ocupacionField.setPromptText("No dejar en blanco");
                }

                // Validación número 10 dígitos y no en blanco
                if (_numeroContactoField.getText().isEmpty()) {
                    _numeroContactoField.setPromptText("No dejar en blanco");
                } else if (_numeroContactoField.getLength() != 10) {
                    _numeroContactoField.clear();
                    _numeroContactoField.setPromptText("Debe tener 10 dígitos");
                }

            } else {
                String username = _usernameField.getText();
                String password = _passwordField.getText();
                String primerNombre = _firstNameField.getText();
                String apellido = _lastNameField.getText();
                LocalDate fechaNacimiento = _birthDatePicker.getValue();
                Sexo sexo = _sexComboBox.getValue();
                String numeroContacto = _numeroContactoField.getText();
                String ocupacion = _ocupacionField.getText();

                Paciente paciente = new Paciente(username, password, primerNombre, apellido, fechaNacimiento, sexo, numeroContacto, ocupacion);

                try {
                    journey.registerUser(paciente);

                    stage.setScene(LoginMenuPage.scene(stage, journey));
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        });

		// Return Button
        Button returnButton = new Button("Regresar");
        returnButton.setOnAction((event) -> {
            stage.setScene(LoginMenuPage.scene(stage, journey));
        });

        formVBox.getChildren().addAll(usernameFieldBox, firstNameFieldBox, lastNameFieldBox, birthDatePickerBox,
                sexComboBox, numeroContactoFieldBox, ocupacionFieldBox, passwordFieldBox, submitButton, returnButton);

        formVBox.setAlignment(Pos.CENTER);
        return formVBox;
    }

    public static Scene create(Stage stage, Estado journey) {
        StackPane root = new StackPane();
        root.getStyleClass().add(JMetroStyleClass.BACKGROUND);

        Scene scene = new Scene(root, 640, 480); // new Scene(640, 480);
        stage.setResizable(false);

        // Components
        Label title = new Label("Registro");
        title.setStyle("-fx-font-size: 42");

        var form = registerForm(stage, journey);

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
