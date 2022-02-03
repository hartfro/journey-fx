package journey.fx.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import journey.core.Constantes;
import journey.core.Estado;
import journey.fx.scenes.LoggedInMenu;

public class PerfilPacienteController {
    @FXML
    Label nombreLabel;

    @FXML
    Label fechaNacimientoLabel;

    @FXML
    Label edadLabel;

    @FXML
    Label sexoLabel;

    @FXML
    Label pesoLabel;

    @FXML
    Label alturaLabel;

    @FXML
    Label imcLabel;

    @FXML
    Label numeroContactoLabel;

    @FXML
    Label ocupacionLabel;

    @FXML
    Label diagnosticoIMCLabel;

    @FXML
    Label caloriasRecomLabel;

    @FXML
    Label factorActividadLabel;

    @FXML
    Button regresarBtn;

    public void initData(Stage stage, Estado journey) {
        // regresarBtn
        regresarBtn.setOnAction((e) -> {
            stage.setScene(LoggedInMenu.scene(stage, journey));
        });

        // Mostrar datos
        var paciente = journey.getLoggedInPaciente();
        var ultimoInfoDia = paciente.getInfoDiaMasReciente();

        // TODO: show custom text if ultimoInfoDia doesn't exist.
        nombreLabel.setText(paciente.nombreCompleto());
        fechaNacimientoLabel.setText(paciente.getFechaNacimiento().format(Constantes.DATE_FORMATTER));
        edadLabel.setText(Integer.toString(paciente.calcularEdad()));
        sexoLabel.setText(paciente.getSexo().toString());

        if (ultimoInfoDia != null) {
            pesoLabel.setText(Float.toString(ultimoInfoDia.getPeso()));
            alturaLabel.setText(Integer.toString(ultimoInfoDia.getAltura()));
            imcLabel.setText(Float.toString(ultimoInfoDia.calcularImc()));
            diagnosticoIMCLabel.setText(ultimoInfoDia.diagnosticoIMC());
        } else {
            String error = "No hay suficiente información.";

            pesoLabel.setText(error);
            alturaLabel.setText(error);
            imcLabel.setText(error);
            diagnosticoIMCLabel.setText(error);
        }

        numeroContactoLabel.setText(paciente.getNumeroContacto());
        ocupacionLabel.setText(paciente.getOcupacion());

        try {
            caloriasRecomLabel.setText(paciente.rangoIdealCalorias());
        } catch (Exception e) {
            caloriasRecomLabel.setText("No hay suficiente información.");
        }

        factorActividadLabel.setText(Float.toString(paciente.calcularFactorActividad()));
    }
}
