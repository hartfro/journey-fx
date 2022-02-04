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
import journey.fx.controllers.PerfilPacienteController;

/**
 * Esta clase permite mostrar los componentes gráficos del perfil del paciente
 * @author Grupo 23
 * @version 02/01/2022
 */
public class PerfilPacientePage {
    /**
     * Método que pone en escena los componentes del perfil del paciente
     * @param stage
     * @param journey
     * @return scene
     * @throws IOException
     */
    public static Scene scene(Stage stage, Estado journey) throws IOException {
        StackPane root = new StackPane();
        root.getStyleClass().add(JMetroStyleClass.BACKGROUND);

        Scene scene = new Scene(root, 640, 480);
        stage.setResizable(false);

        // Components

        var pageLoader = new FXMLLoader(ClassLoader.getSystemResource("PerfilPacientePage.fxml"));
        Node page = pageLoader.load();

        PerfilPacienteController pageController = pageLoader.getController();
        pageController.initData(stage, journey);

        root.getChildren().add(page);

        // Setup scene

        JMetro jMetro = new JMetro(Style.DARK);
        jMetro.setScene(scene);

        return scene;
    }
}
