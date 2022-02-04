package journey.fx.scenes;

import java.io.IOException;
import java.util.HashMap;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.JMetroStyleClass;
import jfxtras.styles.jmetro.Style;
import journey.core.Alimento;
import journey.core.Estado;
import journey.fx.controllers.IngresarInfoDiaComidaController;
import journey.fx.controllers.IngresarInfoDiaController;

/**
 * Esta clase permite mostrar los componentes gráficos de ingresar info de comida
 * @author Grupo 23
 * @version 02/01/2022
 */
public class IngresarInfoDiaComidaPage {
    /**
     * Método que pone en escena la página del ingreso de información sobre alimentos, con porciones que selecciona el usuario
     * @param stage
     * @param journey
     * @param data
     * @param porcionFields
     * @param comidaIndex
     * @return _scene
     * @throws IOException
     */
    public static Scene scene(Stage stage, Estado journey, IngresarInfoDiaComidaController.Data data, HashMap<Alimento, TextField> porcionFields, int comidaIndex) throws IOException {
        var pageLoader = new FXMLLoader(ClassLoader.getSystemResource("IngresarInfoDiaComidaPage.fxml"));

        Node page = pageLoader.load();
        IngresarInfoDiaComidaController pageController = pageLoader.getController();
        pageController.initData(stage, journey, data, porcionFields, comidaIndex);

        return _scene(stage, page);
    }

    /**
     * Método que que pone en escena la página del ingreso de información sobre alimentos
     * @param stage
     * @param journey
     * @param data
     * @param comidaIndex
     * @return _scene
     * @throws IOException
     */
    public static Scene scene(Stage stage, Estado journey, IngresarInfoDiaController.Data data, int comidaIndex) throws IOException {
        var pageLoader = new FXMLLoader(ClassLoader.getSystemResource("IngresarInfoDiaComidaPage.fxml"));

        Node page = pageLoader.load();
        IngresarInfoDiaComidaController pageController = pageLoader.getController();
        pageController.initData(stage, journey, data, comidaIndex);

        return _scene(stage, page);
    }

    /**
     * Método que pone en escena la página de ingreso de info de alimentos, tomando en cuenta los nodos y el paso entre escenas
     * @param stage
     * @param page
     * @return scene
     */
    private static Scene _scene(Stage stage, Node page) {
        StackPane root = new StackPane();
        root.getStyleClass().add(JMetroStyleClass.BACKGROUND);

        Scene scene = new Scene(root, 640, 480);
        stage.setResizable(false);

        // Components

        root.getChildren().add(page);

        // Setup scene

        JMetro jMetro = new JMetro(Style.DARK);
        jMetro.setScene(scene);

        return scene;

    }
}
