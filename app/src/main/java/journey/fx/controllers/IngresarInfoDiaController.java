package journey.fx.controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import journey.core.Emocion;
import journey.core.IntensidadEjercicio;
import journey.core.Estado;
import journey.fx.scenes.IngresarInfoDiaComidaPage;
import journey.fx.scenes.LoggedInMenu;
import journey.fx.utils.KeyEventConsumers;

/**
 * Esta clase permite reconocer la interacción del usuario e ingresar información del día (excepto comida)
 * @author Grupo 23
 * @version 02/01/2022
 */
public class IngresarInfoDiaController {
	@FXML
	TextField pesoField;

	@FXML
	TextField alturaField;

	@FXML
	ChoiceBox<Emocion> emocionChoiceBox;

	@FXML
	ChoiceBox<IntensidadEjercicio> intensidadChoiceBox;

	@FXML
	TextField tiempoEjercicioField;

	@FXML
	Button continueBtn;

	@FXML
	Button regresarBtn;

	@FXML
	private void initialize() {
		for (var v : Emocion.values()) {
			emocionChoiceBox.getItems().add(v);
		}

		for (var v : IntensidadEjercicio.values()) {
			intensidadChoiceBox.getItems().add(v);
		}

		// Add filters to fields.
		tiempoEjercicioField.addEventHandler(KeyEvent.KEY_PRESSED, (e) -> {
			var text = tiempoEjercicioField.getText();

			boolean isZeroForward = text.isEmpty() && e.getText().equals("0");
			boolean isZeroBackward = text.length() == 2 && text.charAt(0) == '0' && e.getCode() == KeyCode.BACK_SPACE;

			if (isZeroForward || isZeroBackward) {
				intensidadChoiceBox.setValue(null);
				intensidadChoiceBox.setDisable(true);
			} else {
				intensidadChoiceBox.setDisable(false);
			}
		});

		tiempoEjercicioField.addEventHandler(KeyEvent.KEY_TYPED, (e) -> {
			KeyEventConsumers.consumeNonDigits(e);
		});

		pesoField.addEventHandler(KeyEvent.KEY_TYPED, (e) -> KeyEventConsumers.consumeNonDigits(e));

		alturaField.addEventHandler(KeyEvent.KEY_TYPED, (e) -> KeyEventConsumers.consumeNonDigits(e));

		// Initialize button
		continueBtn.setDefaultButton(true);
	}

	/**
	 * Para el manejo de datos del ingreso
	 */
	public static class Data {
		float peso;
		int altura;
		Emocion emocion;
		IntensidadEjercicio intensidadEjercicio;
		int tiempoEjercicio;

		public Data(float peso, int altura, Emocion emocion, IntensidadEjercicio intensidad, int tiempoEjercicio) {
			this.peso = peso;
			this.altura = altura;
			this.emocion = emocion;
			this.intensidadEjercicio = intensidad;
			this.tiempoEjercicio = tiempoEjercicio;
		}
	}

	/**
	 * Método que permite iniciar el ingreso de datos
	 * @param stage
	 * @param journey
	 */
	public void initData(Stage stage, Estado journey) {
		regresarBtn.setOnAction((event) -> {
			stage.setScene(LoggedInMenu.scene(stage, journey));
		});

		continueBtn.setOnAction((event) -> {
			boolean shouldContinue = true;

			if (tiempoEjercicioField.getText().isEmpty()) {
				tiempoEjercicioField.setPromptText("No dejar en blanco");
				shouldContinue = false;
			}

			if (alturaField.getText().isEmpty()) {
				alturaField.setPromptText("No dejar en blanco");
				shouldContinue = false;
			}

			if (pesoField.getText().isEmpty()) {
				pesoField.setPromptText("No dejar en blanco");
				shouldContinue = false;
			}

			if (alturaField.getText().equals("0")) {
				alturaField.clear();
				alturaField.setPromptText("No puede ser 0");
				shouldContinue = false;
			}

			if (pesoField.getText().equals("0")) {
				pesoField.clear();
				pesoField.setPromptText("No puede ser 0");
				shouldContinue = false;
			}

			if (emocionChoiceBox.getValue() == null
					|| (intensidadChoiceBox.getValue() == null && !tiempoEjercicioField.getText().equals("0"))) {
				shouldContinue = false;
			}

			if (!shouldContinue)
				return;

			var peso = Float.parseFloat(pesoField.getText());
			var altura = Integer.parseInt(alturaField.getText());
			var emocion = emocionChoiceBox.getValue();
			var intensidad = intensidadChoiceBox.getValue();
			var tiempoEjercicio = Integer.parseInt(tiempoEjercicioField.getText());
			IngresarInfoDiaController.Data data = new IngresarInfoDiaController.Data(peso, altura, emocion, intensidad,
					tiempoEjercicio);

			try {
				stage.setScene(IngresarInfoDiaComidaPage.scene(stage, journey, data, 0));
			} catch (IOException e) {
				System.out.println(e);
			}
		});
	}
}
