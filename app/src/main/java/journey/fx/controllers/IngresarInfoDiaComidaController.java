package journey.fx.controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;
import journey.core.Journey;
import journey.fx.scenes.IngresarInfoDiaComidaPage;
import journey.fx.scenes.LoggedInMenu;

public class IngresarInfoDiaComidaController {
    @FXML
    Label nombreComidaLabel;

    @FXML
    TilePane alimentosTilePane;

    @FXML
    Button continueBtn;

    @FXML
    private void initialize() {
        continueBtn.setDefaultButton(true);
    }

    public void initData(Stage stage, Journey journey, int comidaIndex) throws IOException {
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

        final Scene nextPage = getNextPage(stage, journey, comidaIndex);

        // Set button action
        continueBtn.setOnAction((e) -> {
            stage.setScene(nextPage);
        });

        // Add alimentos to TilePane
        for (var alimento : journey.bancoAlimentos) {
            alimentosTilePane.getChildren().add(new Label(alimento.getNombre()));
        }
    }

    private Scene getNextPage(Stage stage, Journey journey, int comidaIndex) throws IOException {
        Scene nextPage = null;

        if (comidaIndex < 2) {
            nextPage = IngresarInfoDiaComidaPage.scene(stage, journey, comidaIndex + 1);
        }
        else {
            nextPage = LoggedInMenu.scene(stage, journey);
        }

        return nextPage;
    }
}
