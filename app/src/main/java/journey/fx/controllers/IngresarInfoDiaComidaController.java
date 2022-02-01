package journey.fx.controllers;

import java.io.IOException;
import java.util.HashMap;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;
import journey.core.Alimento;
import journey.core.Emocion;
import journey.core.IntensidadEjercicio;
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

    public static class Data extends IngresarInfoDiaController.Data {
        HashMap<Alimento, Integer> desayuno;
        HashMap<Alimento, Integer> almuerzo;
        HashMap<Alimento, Integer> merienda;

        public Data(Emocion emocion, IntensidadEjercicio intensidad, int tiempoEjercicio) {
            super(emocion, intensidad, tiempoEjercicio);
        }
    }

    public void initData(Stage stage, Journey journey, IngresarInfoDiaController.Data oldData, int comidaIndex) throws IOException {
        IngresarInfoDiaComidaController.Data data = new IngresarInfoDiaComidaController.Data(oldData.emocion, oldData.intensidadEjercicio, oldData.tiempoEjercicio);

        initData(stage, journey, data, comidaIndex);
    }

    public void initData(Stage stage, Journey journey, IngresarInfoDiaComidaController.Data data, int comidaIndex) throws IOException {
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

        final Scene nextPage = getNextPage(stage, journey, data, comidaIndex);

        // Set button action
        continueBtn.setOnAction((e) -> {
            stage.setScene(nextPage);
        });

        // Add alimentos to TilePane
        for (var alimento : journey.bancoAlimentos) {
            alimentosTilePane.getChildren().add(new Label(alimento.getNombre()));
        }
    }

    private Scene getNextPage(Stage stage, Journey journey, IngresarInfoDiaComidaController.Data data, int comidaIndex) throws IOException {
        Scene nextPage = null;

        if (comidaIndex < 2) {
            nextPage = IngresarInfoDiaComidaPage.scene(stage, journey, data, comidaIndex + 1);
        }
        else {
            nextPage = LoggedInMenu.scene(stage, journey);
        }

        return nextPage;
    }
}
