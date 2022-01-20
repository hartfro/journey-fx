package journey.fx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.Style;
import journey.fx.scenes.LoginMenu;
import journey.fx.scenes.MainMenu;

public class App extends Application {

    @Override
    public void start(Stage stage) {
        Scene loginMenu = LoginMenu.create(stage);

        // Style
        JMetro jMetro = new JMetro(Style.DARK);
        jMetro.setScene(loginMenu);

        stage.setScene(MainMenu.scene(stage));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}
