package journey.fx.components;

import javafx.geometry.Pos;
import javafx.scene.control.Button;

public class MenuButton extends Button {
    public MenuButton(String text) {
        super(text);

        this.setWrapText(true);
        this.setPrefSize(180, 180);
        this.setAlignment(Pos.BOTTOM_LEFT);
        this.setStyle("-fx-font-size: 16");
    }
}
