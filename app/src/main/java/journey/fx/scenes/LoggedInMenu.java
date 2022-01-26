package journey.fx.scenes;

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
import journey.fx.Utils;
import journey.fx.components.MenuButton;

public class LoggedInMenu {

    private static Node menu() {
        HBox menuHBox = new HBox(20);
        menuHBox.setAlignment(Pos.CENTER);

        MenuButton ingInfoDiariaBtn = new MenuButton("Ingresar información diaria");
        Utils.styleNoOverride(ingInfoDiariaBtn, "-fx-background-color: #4f65aa");
        menuHBox.getChildren().add(ingInfoDiariaBtn);

        MenuButton visInfoDiariaBtn = new MenuButton("Visualizar información y diagnósticos diarios");
        Utils.styleNoOverride(visInfoDiariaBtn, "-fx-background-color: #4f8faa");
        menuHBox.getChildren().add(visInfoDiariaBtn);

        MenuButton visPerfilBtn = new MenuButton("Visualizar perfil de paciente");
        Utils.styleNoOverride(visPerfilBtn, "-fx-background-color: #4faaa0");
        menuHBox.getChildren().add(visPerfilBtn);

        return menuHBox;
    }

    public static Scene scene(Stage stage) {
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

        Label subtitle = new Label("Menú");
        subtitle.setStyle("-fx-font-size: 24");
        mainVBox.getChildren().add(subtitle);

        var menu = menu();
        mainVBox.getChildren().add(menu);

        Button logoutBtn = new Button("Cerrar sesión");

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
