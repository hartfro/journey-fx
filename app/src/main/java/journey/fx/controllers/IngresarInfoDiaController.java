package journey.fx.controllers;

import java.io.IOException;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;
import journey.core.Emocion;
import journey.core.IntensidadEjercicio;
import journey.core.Journey;
import journey.fx.scenes.IngresarInfoDiaComidaPage;

public class IngresarInfoDiaController {
    @FXML
    ChoiceBox<String> emocionChoiceBox;

    @FXML
    ChoiceBox<String> intensidadChoiceBox;

    @FXML
    Button continueBtn;

    @FXML
    private void initialize() {
        ArrayList<String> emocionValues = new ArrayList<>();
        for (var v : Emocion.values()) {
            emocionValues.add(v.toString());
        }

        emocionChoiceBox.getItems().addAll(emocionValues);

        ArrayList<String> intensidadValues = new ArrayList<>();
        for (var v : IntensidadEjercicio.values()) {
            intensidadValues.add(v.toString());
        }

        intensidadChoiceBox.getItems().addAll(intensidadValues);

        // Initialize button
        continueBtn.setDefaultButton(true);
    }

    public void initData(Stage stage, Journey journey) {
        continueBtn.setOnAction((event) -> {
            try {
                stage.setScene(IngresarInfoDiaComidaPage.scene(stage, journey));
            } catch (IOException e) {
                System.out.println(e);
            }
        });
    }
}
