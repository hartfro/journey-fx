package journey.fx.components;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputControl;
import javafx.scene.layout.VBox;

public class TextInputControlWithLabel {
    public static VBox create(TextInputControl inputControl, String controlPromptText, String labelText, double width) {
        inputControl.setPromptText(controlPromptText);

        VBox vBox = new VBox(5, new Label(labelText), inputControl);
        vBox.setAlignment(Pos.CENTER_LEFT);

        vBox.setMaxWidth(width);

        return vBox;
    }
}
