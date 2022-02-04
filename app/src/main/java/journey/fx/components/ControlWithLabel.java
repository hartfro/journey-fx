package journey.fx.components;

import javafx.geometry.Pos;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * Esta clase sirve para manejar el control que va con los labels
 * @author Grupo 23
 * @version 02/01/2022
 */
public class ControlWithLabel {
    /**
     * Método que permite manejar el control que va con los labels
     * @param inputControl
     * @param labelText
     * @param width
     * @return vBox
     */
    public static VBox create(Control inputControl, String labelText, double width) {
        VBox vBox = new VBox(5, new Label(labelText), inputControl);
        vBox.setAlignment(Pos.CENTER_LEFT);

        vBox.setMaxWidth(width);

        return vBox;
    }
}
