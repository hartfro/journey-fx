package journey.fx.controllers;

import java.io.IOException;
import java.util.HashMap;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import journey.core.Alimento;
import journey.core.Emocion;
import journey.core.IntensidadEjercicio;
import journey.core.Journey;
import journey.fx.scenes.IngresarInfoDiaComidaPage;
import journey.fx.scenes.LoggedInMenu;
import journey.fx.utils.KeyEventConsumers;

public class IngresarInfoDiaComidaController {
    @FXML
    Label nombreComidaLabel;

    @FXML
    VBox alimentosVBox;

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

        HashMap<Alimento, TextField> porcionFields = new HashMap<>();

        this.initData(stage, journey, data, porcionFields, comidaIndex);
    }

    public void initData(Stage stage, Journey journey, IngresarInfoDiaComidaController.Data data, HashMap<Alimento, TextField> porcionFields, int comidaIndex) throws IOException {
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
            var hBox = alimentoHBox(alimento, porcionFields);

            alimentosVBox.getChildren().add(hBox);
        }
    }

    private Node alimentoHBox(Alimento alimento, HashMap<Alimento, TextField> fields) {
        HBox hBox = new HBox();
        hBox.setSpacing(10);

        Label nombreLabel = new Label(alimento.getNombre());
        nombreLabel.setPrefWidth(150);
        hBox.getChildren().add(nombreLabel);

        TextField porcionesField = new TextField();
        porcionesField.addEventHandler(KeyEvent.KEY_TYPED, (e) -> KeyEventConsumers.consumeNonDigits(e));

        hBox.getChildren().add(porcionesField);

        fields.put(alimento, porcionesField);

        return hBox;
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
