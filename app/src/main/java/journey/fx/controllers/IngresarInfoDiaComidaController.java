package journey.fx.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;
import journey.core.Journey;

public class IngresarInfoDiaComidaController {
    @FXML
    Label nombreComidaLabel;

    @FXML
    TilePane alimentosTilePane;

    public void initData(Stage stage, Journey journey, int comidaIndex) {
        if (comidaIndex < 0 || comidaIndex > 2)
            throw new IllegalArgumentException("comidaIndex must be an integer from 0 to 2.");

        switch(comidaIndex) {
            case 0:
                nombreComidaLabel.setText("Desayuno");
                break;
            case 1:
                nombreComidaLabel.setText("Almuerzo");
                break;
            case 2:
                nombreComidaLabel.setText("Merienda");
                break;
        }

        for (var alimento : journey.bancoAlimentos) {
            alimentosTilePane.getChildren().add(new Label(alimento.getNombre()));
        }
    }
}
