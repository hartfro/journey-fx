package journey.fx.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import journey.core.InfoDia;

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

    }
}
