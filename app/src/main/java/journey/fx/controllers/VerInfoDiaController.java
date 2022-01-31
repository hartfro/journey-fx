package journey.fx.controllers;

import java.util.HashMap;

import javafx.fxml.FXML;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;
import journey.core.Alimento;
import journey.core.Constantes;
import journey.core.InfoAlimentacion;
import journey.core.InfoDia;
import journey.core.InfoEjercicio;

public class VerInfoDiaController {
    @FXML
    Button regresarBtn;

    @FXML
    Button continueBtn;

    @FXML
    Label titleLabel;

    @FXML
    Accordion desayunoAccordion;

    @FXML
    Accordion almuerzoAccordion;

    @FXML
    Accordion meriendaAccordion;

    @FXML
    Label emocionLabel;

    @FXML
    Label tiempoEjercicioLabel;

    @FXML
    Label intensidadEjercicioLabel;

    @FXML
    Label caloriasTotalesLabel;

    public void initData(InfoDia infoDia) {
        InfoEjercicio ejercicio = infoDia.getInfoEjercicio();
        InfoAlimentacion alimentacion = infoDia.getInfoAlimentacion();

        titleLabel.setText("Registro del día: " + infoDia.getFecha().format(Constantes.DATE_FORMATTER));

        emocionLabel.setText(infoDia.getEmocion().toString());
        tiempoEjercicioLabel.setText(Integer.toString(ejercicio.getTiempo()));
        intensidadEjercicioLabel.setText(ejercicio.getIntensidad().toString());

        caloriasTotalesLabel.setText(Float.toString(alimentacion.caloriasTotales()));

        // Comida Accordions
        populateComidaAccordion(desayunoAccordion, alimentacion.getDesayuno());
        populateComidaAccordion(almuerzoAccordion, alimentacion.getAlmuerzo());
        populateComidaAccordion(meriendaAccordion, alimentacion.getMerienda());
    }

    private void populateComidaAccordion(Accordion comidaAccordion, HashMap<Alimento, Integer> comida) {
        for (var alimento : comida.keySet()) {
            var porciones = comida.get(alimento);

            VBox content = new VBox();

            Label porcionesLabel = new Label("Porciones: " + porciones);
            content.getChildren().add(porcionesLabel);

            Label tipoLabel = new Label("Tipo: " + alimento.getTipo());
            content.getChildren().add(tipoLabel);

            Label caloriasPorcionLabel = new Label("Cal. por porción: " + alimento.getCaloriasPorPorcion());
            content.getChildren().add(caloriasPorcionLabel);

            float calorias = alimento.getCaloriasPorPorcion() * porciones;
            Label caloriasLabel = new Label("Cal. totales: " + calorias);
            content.getChildren().add(caloriasLabel);

            TitledPane pane = new TitledPane(alimento.getNombre(), content) ;
            comidaAccordion.getPanes().add(pane);
        }
    }
}
