package journey.fx.scenes;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.JMetroStyleClass;
import jfxtras.styles.jmetro.Style;
import journey.core.Estado;
import journey.fx.controllers.SeleccionarInfoDiaController;

public class SeleccionarInfoDiaPage {
    public static Scene scene (Stage stage, Estado journey) throws IOException {
        StackPane root = new StackPane();
        root.getStyleClass().add(JMetroStyleClass.BACKGROUND);

        Scene scene = new Scene(root, 640, 480);
        stage.setResizable(false);

        // Components
        var pageLoader = new FXMLLoader(ClassLoader.getSystemResource("SeleccionarInfoDiaPage.fxml"));
        Node page = pageLoader.load();

        SeleccionarInfoDiaController pageController = pageLoader.getController();
        pageController.initData(stage, journey);;

        root.getChildren().add(page);

        // Scene setup
        JMetro jMetro = new JMetro(Style.DARK);
        jMetro.setScene(scene);

        return scene;
    }
}
