package journey.fx.components;

import javafx.geometry.Pos;
import javafx.scene.control.Button;

/**
 * Esta clase sirve para manejar el botón del menú
 * @author Grupo 23
 * @version 02/01/2022
 */
public class MenuButton extends Button {
    /**
     * Método que permite manejar el botón del menú
     * @param text
     */
    public MenuButton(String text) {
        super(text);

        this.setWrapText(true);
        this.setPrefSize(180, 180);
        this.setAlignment(Pos.BOTTOM_LEFT);
        this.setStyle("-fx-font-size: 16");
    }
}
