package journey.fx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.Style;
import journey.core.Journey;
import journey.fx.scenes.LoginMenuPage;

public class App extends Application {
    Journey journey = new Journey(true);

    @Override
    public void start(Stage stage) {
        Scene loginMenu = LoginMenuPage.scene(stage, journey);

        // Style
        JMetro jMetro = new JMetro(Style.DARK);
        jMetro.setScene(loginMenu);

        stage.setScene(loginMenu);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}
