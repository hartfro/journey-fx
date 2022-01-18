package journey.fx;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.JMetroStyleClass;
import jfxtras.styles.jmetro.Style;
import journey.fx.scenes.LoginMenu;

public class App extends Application {

    @Override
    public void start(Stage stage) {
        Scene loginMenu = LoginMenu.create(stage);

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
