package journey.fx.controllers;

import java.io.IOException;
import java.time.LocalDate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.util.Callback;
import journey.core.InfoDia;
import journey.core.Estado;
import journey.core.Paciente;
import journey.fx.scenes.LoggedInMenu;
import journey.fx.scenes.VerInfoDiaPage;

/**
 * Esta clase permite reconocer la interacción del usuario y seleccionar un día
 * @author Grupo 23
 * @version 02/01/2022
 */
public class SeleccionarInfoDiaController {
    @FXML
    ListView<InfoDia> infoDiariaListView;

    @FXML
    DatePicker datePicker;

    @FXML
    Button resetDatePickerBtn;

    @FXML
    Button regresarBtn;


    /**
     * Método que resetea qué días con información son visibles
     * @param items
     * @param paciente
     */
    private void resetInfoDiariaItems(ObservableList<InfoDia> items, Paciente paciente) {
        items.clear();

        for (var infoDia : paciente.getInfoDiaria()) {
            items.add(infoDia);
        }

        infoDiariaListView.refresh();
    }

    /**
     * Método que permite seleccionar el día del que se quiere ver la información
     * @param stage
     * @param journey
     */
    public void initData(Stage stage, Estado journey) {
        regresarBtn.setOnAction((event) -> {
            stage.setScene(LoggedInMenu.scene(stage, journey));
        });

        // Fill data.
        Paciente paciente = journey.getLoggedInPaciente();

        ObservableList<InfoDia> infoDiariaItems = FXCollections.observableArrayList();
        resetInfoDiariaItems(infoDiariaItems, paciente);

        infoDiariaListView.setItems(FXCollections.observableList(infoDiariaItems));

        // Handle date filter.

        Callback<DatePicker, DateCell> dayCellFactory = new Callback<DatePicker, DateCell>() {
            public DateCell call(final DatePicker datePicker) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item.isAfter(LocalDate.now())) {
                            this.setDisable(true);
                        }
                    }
                };
            }
        };

        datePicker.setDayCellFactory(dayCellFactory);
        datePicker.setOnAction((e) -> {
            infoDiariaItems.clear();

            var foundInfoDia = paciente.buscarInfoDiaPorFecha(datePicker.getValue());

            if (foundInfoDia != null)
                infoDiariaItems.add(foundInfoDia);

            infoDiariaListView.refresh();
        });

        // Handle reset button.
        resetDatePickerBtn.setOnAction((e) -> {
            datePicker.setValue(null);

            resetInfoDiariaItems(infoDiariaItems, paciente);
        });

        // Handle ListView click.
        infoDiariaListView.setOnMouseClicked((event) -> {
            InfoDia selectedInfoDia = infoDiariaListView.getSelectionModel().getSelectedItem();

            try {
                stage.setScene(VerInfoDiaPage.scene(stage, journey, selectedInfoDia));
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        });
    }
}
