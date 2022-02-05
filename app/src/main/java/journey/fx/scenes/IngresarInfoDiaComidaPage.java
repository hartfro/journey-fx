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
 * Contiene el méotodo scene que devuelve la escena de ingresar info de comida (desayuno, almuerzo o merienda).
 * @author Grupo 23
 * @version 02/01/2022
 */
public class IngresarInfoDiaComidaPage {
    /**
     * Método que devuelva la escena de la página del ingreso de información sobre comida: alimentos, con sus respectivas porciones. 
     *
     * Este utiliza la información de una pantalla {@link IngresarInfoDiaComidaPage} anterior.
     *
     * @param stage
     * @param journey
     * @param data Información de la pantalla {@link IngresarInfoDiaComidaPage} anterior.
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
     * Método que devuelva la escena de la página del ingreso de información sobre comida: alimentos, con sus respectivas porciones. 
     *
     * Este utiliza la información procedente de la pantalla {@link IngresarInfoDiaPage}, mas no de una {@link IngresarInfoDiaComidaPage}.
     *
     * @param stage
     * @param journey
     * @param data Ingormación de la pantalla {@link IngresarInfoDiaPage}.
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
     * Devuelve la escena con el estilo jMetro añadido.
     *
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
