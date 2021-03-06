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
import journey.core.InfoDia;
import journey.core.Estado;
import journey.fx.controllers.VerInfoDiaController;

/**
 * Esta clase permite mostrar los componentes gráficos de la página de ver info día
 * @author Grupo 23
 * @version 02/01/2022
 */
public class VerInfoDiaPage {
    /**
     * Método que pone en escena la página de ver info día
     * @param stage
     * @param journey
     * @param infoDia
     * @return scene
     * @throws IOException
     */
    public static Scene scene(Stage stage, Estado journey, InfoDia infoDia) throws IOException {
        StackPane root = new StackPane();
        root.getStyleClass().add(JMetroStyleClass.BACKGROUND);

        Scene scene = new Scene(root, 640, 480);
        stage.setResizable(false);

        // Components
        var pageLoader = new FXMLLoader(ClassLoader.getSystemResource("VerInfoDiaPage.fxml"));
        Node page = pageLoader.load();

        VerInfoDiaController pageController = pageLoader.getController();
        pageController.initData(stage, journey, infoDia.getFecha());

        root.getChildren().add(page);

        // Setup scene
        JMetro jMetro = new JMetro(Style.DARK);
        jMetro.setScene(scene);

        return scene;
    }
}
