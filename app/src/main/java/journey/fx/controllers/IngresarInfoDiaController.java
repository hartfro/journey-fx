package journey.fx.controllers;

import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import journey.core.Emocion;
import journey.core.IntensidadEjercicio;

public class IngresarInfoDiaController {
    @FXML
    ChoiceBox<String> emocionChoiceBox;

    @FXML
    ChoiceBox<String> intensidadChoiceBox;

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
    }
}
