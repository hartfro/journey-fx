package journey.fx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import journey.core.Estado;
import journey.fx.scenes.LoginMenuPage;

public class App extends Application {
    Estado journey = new Estado(true);

    @Override
    public void start(Stage stage) {
        Scene loginMenu = LoginMenuPage.scene(stage, journey);

        stage.setScene(loginMenu);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}
