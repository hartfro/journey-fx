package journey.fx.controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import journey.core.Emocion;
import journey.core.IntensidadEjercicio;
import journey.core.Journey;
import journey.fx.scenes.IngresarInfoDiaComidaPage;
import journey.fx.utils.KeyEventConsumers;

public class IngresarInfoDiaController {
    @FXML
    ChoiceBox<Emocion> emocionChoiceBox;

    @FXML
    ChoiceBox<IntensidadEjercicio> intensidadChoiceBox;

    @FXML
    TextField tiempoEjercicioField;

    @FXML
    Button continueBtn;

    @FXML
    private void initialize() {
        for (var v : Emocion.values()) {
            emocionChoiceBox.getItems().add(v);
        }

        for (var v : IntensidadEjercicio.values()) {
            intensidadChoiceBox.getItems().add(v);
        }

        // Add filter to tiempoEjercicioField
        tiempoEjercicioField.addEventHandler(KeyEvent.KEY_TYPED, (e) -> KeyEventConsumers.consumeNonDigits(e));

        // Initialize button
        continueBtn.setDefaultButton(true);
    }

    public static class Data {
        Emocion emocion;
        IntensidadEjercicio intensidadEjercicio;
        int tiempoEjercicio;

        public Data(Emocion emocion, IntensidadEjercicio intensidad, int tiempoEjercicio) {
            this.emocion = emocion; 
            this.intensidadEjercicio = intensidad;
            this.tiempoEjercicio = tiempoEjercicio;
        }
    }

    public void initData(Stage stage, Journey journey) {
        continueBtn.setOnAction((event) -> {
            var emocion = emocionChoiceBox.getValue();
            var intensidad = intensidadChoiceBox.getValue();
            var tiempoEjercicio = Integer.parseInt(tiempoEjercicioField.getText());
            IngresarInfoDiaController.Data data = new IngresarInfoDiaController.Data(emocion, intensidad, tiempoEjercicio);

            try {
                stage.setScene(IngresarInfoDiaComidaPage.scene(stage, journey, data, 0));
            } catch (IOException e) {
                System.out.println(e);
            }
        });
    }
}
