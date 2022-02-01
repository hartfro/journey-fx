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
import journey.core.InfoAlimentacion;
import journey.core.InfoDia;
import journey.core.InfoEjercicio;
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
        HashMap<Alimento, Integer> desayuno = new HashMap<>();
        HashMap<Alimento, Integer> almuerzo = new HashMap<>();
        HashMap<Alimento, Integer> merienda = new HashMap<>();

        public Data(Emocion emocion, IntensidadEjercicio intensidad, int tiempoEjercicio) {
            super(emocion, intensidad, tiempoEjercicio);
        }
    }

    public void initData(Stage stage, Journey journey, IngresarInfoDiaController.Data oldData, int comidaIndex) {
        System.out.println("Me llamaste???");
        IngresarInfoDiaComidaController.Data data = new IngresarInfoDiaComidaController.Data(oldData.emocion, oldData.intensidadEjercicio, oldData.tiempoEjercicio);

        HashMap<Alimento, TextField> porcionFields = new HashMap<>();

        this.initData(stage, journey, data, porcionFields, comidaIndex);
    }

    public void initData(Stage stage, Journey journey, IngresarInfoDiaComidaController.Data data, HashMap<Alimento, TextField> porcionFields, int comidaIndex) {
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

        // Add alimentos to TilePane
        for (var alimento : journey.bancoAlimentos) {
            var hBox = alimentoHBox(alimento, porcionFields);

            alimentosVBox.getChildren().add(hBox);
        }

        // Set button action
        continueBtn.setOnAction((event) -> {
            if (comidaIndex <= 2) {
                HashMap<Alimento, Integer> comida = new HashMap<>();
                for (var alimento : porcionFields.keySet()) {
                    var field = porcionFields.get(alimento);

                    comida.put(alimento, Integer.parseInt(field.getText()));
                }

                switch (comidaIndex) {
                    case 0:
                        data.desayuno = comida;
                        break;
                    case 1:
                        data.almuerzo = comida;
                        break;
                    case 2:
                        data.merienda = comida;
                        break;
                }
            }

            try {
                var nextPage = getNextPage(stage, journey, data, porcionFields, comidaIndex);
                stage.setScene(nextPage);
            } catch (IOException e) {
                System.out.println(e);
            }
        });
    }

    private Node alimentoHBox(Alimento alimento, HashMap<Alimento, TextField> fields) {
        HBox hBox = new HBox();
        hBox.setSpacing(10);

        Label nombreLabel = new Label(alimento.getNombre());
        nombreLabel.setPrefWidth(150);
        hBox.getChildren().add(nombreLabel);

        TextField porcionesField = new TextField();
        porcionesField.setText("0");
        porcionesField.addEventHandler(KeyEvent.KEY_TYPED, (e) -> KeyEventConsumers.consumeNonDigits(e));

        hBox.getChildren().add(porcionesField);

        fields.put(alimento, porcionesField);

        return hBox;
    }

    private Scene getNextPage(Stage stage, Journey journey, IngresarInfoDiaComidaController.Data data, HashMap<Alimento, TextField> porcionFields, int comidaIndex) throws IOException {
        Scene nextPage = null;

        // Set next page.
        if (comidaIndex < 2) {
            nextPage = IngresarInfoDiaComidaPage.scene(stage, journey, data, porcionFields, comidaIndex + 1);
        }
        else {
            InfoEjercicio infoEjercicio = new InfoEjercicio(data.tiempoEjercicio, data.intensidadEjercicio);

            InfoAlimentacion infoAlimentacion = new InfoAlimentacion(data.desayuno, data.almuerzo, data.merienda);

            InfoDia infoDia = new InfoDia(data.emocion, infoEjercicio, infoAlimentacion);
            journey.loggedInPaciente.agregarInfoDia(infoDia);

            nextPage = LoggedInMenu.scene(stage, journey);
        }

        return nextPage;
    }
}
