package journey.fx;

import javafx.scene.Node;

/**
 * Esta clase sirve para manejar el estilo de un nodo
 * @author Grupo 23
 * @version 02/01/2022
 */
public class Utils {
    /**
     * Método para manejar el estilo de un nodo
     * @param node
     * @param style
     */
    public static void styleNoOverride(Node node, String style) {
        node.setStyle(node.getStyle() + "; " + style);
    }
}
