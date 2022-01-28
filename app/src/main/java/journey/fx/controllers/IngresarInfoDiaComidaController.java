package journey.fx.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.TilePane;
import journey.core.Journey;

public class IngresarInfoDiaComidaController {
    @FXML
    Label nombreComidaLabel;

    @FXML
    TilePane alimentosTilePane;

    public void initData(Journey journey) {
        for (var alimento : journey.bancoAlimentos) {
            alimentosTilePane.getChildren().add(new Label(alimento.getNombre()));
        }
    }
}
