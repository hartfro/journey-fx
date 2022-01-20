package journey.fx;

import javafx.scene.Node;

public class Utils {
    public static void styleNoOverride(Node node, String style) {
        node.setStyle(node.getStyle() + "; " + style);
    }
}
