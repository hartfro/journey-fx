package journey.fx.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.TilePane;
import journey.core.Journey;

public class IngresarInfoDiaComidaController {
    Journey journey;

    @FXML
    Label nombreComidaLabel;

    @FXML
    TilePane alimentosTilePane;

    @FXML
    private void initialize() {
        for (var alimento : journey.bancoAlimentos) {
            alimentosTilePane.getChildren().add(new Label(alimento.getNombre()));
        }
    }

    public void initData(Journey journey) {
        this.journey = journey;
    }
}
