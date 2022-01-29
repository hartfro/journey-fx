package journey.fx.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import journey.core.Journey;

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

    public void initData(Journey journey) {
        var paciente = journey.loggedInPaciente;

        if (paciente == null)
            return;

        var ultimoInfoDia = paciente.getInfoDiaMasReciente();

        // Mostrar datos
        // TODO: show custom text if ultimoInfoDia doesn't exist.
        nombreLabel.setText(paciente.nombreCompleto());
        fechaNacimientoLabel.setText(paciente.getFechaNacimiento().toString());
        edadLabel.setText(Integer.toString(paciente.calcularEdad()));
        sexoLabel.setText(paciente.getSexo().toString());
        pesoLabel.setText(Float.toString(ultimoInfoDia.getPeso()));
        alturaLabel.setText(Integer.toString(ultimoInfoDia.getAltura()));
        imcLabel.setText(Float.toString(ultimoInfoDia.calcularImc()));
        numeroContactoLabel.setText(paciente.getNumeroContacto());
        ocupacionLabel.setText(paciente.getOcupacion());
        diagnosticoIMCLabel.setText(ultimoInfoDia.diagnosticoIMC());
        caloriasRecomLabel.setText(paciente.rangoIdealCalorias());
        factorActividadLabel.setText(Float.toString(paciente.calcularFactorActividad()));
    }
}
