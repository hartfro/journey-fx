package journey.fx.controllers;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import journey.core.InfoDia;
import journey.core.Paciente;
import journey.fx.scenes.VerInfoDiaPage;

public class SeleccionarInfoDiaController {
    @FXML
    ListView<InfoDia> infoDiariaListView;

    @FXML
    DatePicker datePicker;

    @FXML
    Button resetDatePickerBtn;

    private void resetInfoDiariaItems(ObservableList<InfoDia> items, Paciente paciente) {
        items.clear();

        for (var infoDia : paciente.infoDiaria) {
            items.add(infoDia);
        }

        infoDiariaListView.refresh();
    }

    public void initData(Stage stage, Paciente paciente) {
        ObservableList<InfoDia> infoDiariaItems = FXCollections.observableArrayList();
        resetInfoDiariaItems(infoDiariaItems, paciente);

        infoDiariaListView.setItems(FXCollections.observableList(infoDiariaItems));

        // Handle date filter.
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
                stage.setScene(VerInfoDiaPage.scene(stage, selectedInfoDia));
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        });
    }
}
