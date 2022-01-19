package journey.fx.components;

import javafx.geometry.Pos;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class ControlWithLabel {
    public static VBox create(Control inputControl, String labelText, double width) {
        VBox vBox = new VBox(5, new Label(labelText), inputControl);
        vBox.setAlignment(Pos.CENTER_LEFT);

        vBox.setMaxWidth(width);

        return vBox;
    }
}
