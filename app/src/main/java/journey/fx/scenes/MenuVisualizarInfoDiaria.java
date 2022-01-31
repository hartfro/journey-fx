package journey.fx.scenes;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
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

// TODO: eliminar esta página
public class MenuVisualizarInfoDiaria {

    private static Node menu() {
        HBox menuHBox = new HBox(20);
        menuHBox.setAlignment(Pos.CENTER);

        MenuButton todaInfoBtn = new MenuButton("Toda la información diaria");
        Utils.styleNoOverride(todaInfoBtn, "-fx-background-color: #4f65aa");
        menuHBox.getChildren().add(todaInfoBtn);

        MenuButton diaEspecificoBtn = new MenuButton("Información de un día específico");
        Utils.styleNoOverride(diaEspecificoBtn, "-fx-background-color: #4f8faa");
        menuHBox.getChildren().add(diaEspecificoBtn);

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

        Label subtitle = new Label("Visualizar información y diagnósticos diarios");
        subtitle.setStyle("-fx-font-size: 24");
        mainVBox.getChildren().add(subtitle);

        var menu = menu();
        mainVBox.getChildren().add(menu);

        // Setup scene

        JMetro jMetro = new JMetro(Style.DARK);
        jMetro.setScene(scene);

        return scene;
    }
}
