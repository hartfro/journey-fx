package journey.fx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import journey.core.Estado;
import journey.fx.scenes.LoginMenuPage;

/**
 * Esta clase sirve como principal para iniciar la app
 * @author Grupo 23
 * @version 02/01/2022
 */
public class App extends Application {
    Estado journey = new Estado(true);

    /**
     * Método para iniciar el proceso
     * @param stage
     */
    @Override
    public void start(Stage stage) {
        Scene loginMenu = LoginMenuPage.scene(stage, journey);

        stage.setScene(loginMenu);
        stage.show();
    }

    /**
     * Método main
     * @param args
     */
    public static void main(String[] args) {
        launch();
    }

}
