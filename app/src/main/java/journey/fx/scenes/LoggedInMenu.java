package journey.fx.scenes;

import java.io.IOException;
import java.time.LocalDate;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.JMetroStyleClass;
import jfxtras.styles.jmetro.Style;
import journey.core.Journey;
import journey.fx.Utils;
import journey.fx.components.MenuButton;

public class LoggedInMenu {
    private static Node menu(Stage stage, Journey journey, Label contextLabel) {
        HBox menuHBox = new HBox(20);
        menuHBox.setAlignment(Pos.CENTER);

        MenuButton ingInfoDiariaBtn = new MenuButton("Ingresar información diaria");
        Utils.styleNoOverride(ingInfoDiariaBtn, "-fx-background-color: #4f65aa");
        
        ingInfoDiariaBtn.setOnAction((event) -> {
            // Check if infoDia for today already exists.
            var infoDiaNow = journey.loggedInPaciente.buscarInfoDiaPorFecha(LocalDate.now());

            if (infoDiaNow != null) {
                contextLabel.setText("Ya existe un registro para hoy.");
                return;
            }

            try {
                stage.setScene(IngresarInfoDiaPage.scene(stage, journey));
            } catch (IOException e) {
                System.out.println(e);
            }
        });

        menuHBox.getChildren().add(ingInfoDiariaBtn);

        // VisInfoDiariaBtn
        MenuButton visInfoDiariaBtn = new MenuButton("Visualizar información y diagnósticos diarios");
        Utils.styleNoOverride(visInfoDiariaBtn, "-fx-background-color: #4f8faa");

        visInfoDiariaBtn.setOnAction((event) -> {
            try {
                stage.setScene(SeleccionarInfoDiaPage.scene(stage, journey));
            } catch (IOException e) {
                System.out.println(e);
            }
        });
        
        menuHBox.getChildren().add(visInfoDiariaBtn);

        // VisPerfilBtn
        MenuButton visPerfilBtn = new MenuButton("Visualizar perfil de paciente");
        Utils.styleNoOverride(visPerfilBtn, "-fx-background-color: #4faaa0");

        visPerfilBtn.setOnAction((event) -> {
            try {
                stage.setScene(PerfilPacientePage.scene(stage, journey));
            } catch (IOException e) {
                System.out.println(e);
            }
        });

        menuHBox.getChildren().add(visPerfilBtn);

        return menuHBox;
    }

    public static Scene scene(Stage stage, Journey journey) {
        return scene(stage, journey, "¡Bienvenido/a!");
    }

    public static Scene scene(Stage stage, Journey journey, String contextMsg) {
        StackPane root = new StackPane();
        root.getStyleClass().add(JMetroStyleClass.BACKGROUND);

        root.setPadding(new Insets(0, 0, 25, 0));

        Scene scene = new Scene(root, 640, 480);
        stage.setResizable(false);

        // Components
        VBox mainVBox = new VBox(20);
        mainVBox.setAlignment(Pos.CENTER);
        root.getChildren().add(mainVBox);

        Label title = new Label("Journey");
        title.setStyle("-fx-font-size: 42");
        mainVBox.getChildren().add(title);

        Label subtitle = new Label("¡Hola, " + journey.loggedInPaciente.getPrimerNombre() + "!");
        subtitle.setStyle("-fx-font-size: 24");
        mainVBox.getChildren().add(subtitle);

        Label contextLabel = new Label(contextMsg);

        var menu = menu(stage, journey, contextLabel);
        mainVBox.getChildren().add(menu);

        mainVBox.getChildren().add(contextLabel);

        Button logoutBtn = new Button("Cerrar sesión");
        logoutBtn.setOnAction((e) -> {
            journey.logout();

            stage.setScene(LoginMenuPage.scene(stage, journey));
        });

        HBox logoutBtnWrapper = new HBox(logoutBtn);
        logoutBtnWrapper.setPadding(new Insets(0, 30, 0, 0));
        logoutBtnWrapper.setAlignment(Pos.CENTER_RIGHT);

        mainVBox.getChildren().add(logoutBtnWrapper);

        // Setup scene

        JMetro jMetro = new JMetro(Style.DARK);
        jMetro.setScene(scene);

        return scene;
    }
}
