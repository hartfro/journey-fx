package journey.fx.utils;

import javafx.scene.input.KeyEvent;

/**
 * Funciones que consumen eventos en ciertas condiciones.
 *
 * Utilizados para validar texto en TextFields.
 */
public class KeyEventConsumers {
    public static void consumeNonDigits(KeyEvent keyEvent) {
        char key = keyEvent.getCharacter().charAt(0);

        if (!Character.isDigit(key))
            keyEvent.consume();
    }

    public static void consumeNonLetters(KeyEvent keyEvent) {
        char key = keyEvent.getCharacter().charAt(0);

        if (!Character.isLetter(key))
            keyEvent.consume();
    }

    public static void consumeNonAlphanumeric(KeyEvent keyEvent) {
        char key = keyEvent.getCharacter().charAt(0);

        if (!(Character.isDigit(key) || Character.isLetter(key)))
            keyEvent.consume();

    }
}
