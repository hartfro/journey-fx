package journey.fx.controllers;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import journey.core.InfoDia;
import journey.core.Journey;

public class SeleccionarInfoDiaController {
    @FXML
    ListView<InfoDia> infoDiariaListView;

    @FXML
    DatePicker datePicker;

    @FXML
    Button resetDatePickerBtn;

    private void resetInfoDiariaItems(ObservableList<InfoDia> items, Journey journey) {
        items.clear();

        for (var infoDia : journey.loggedInPaciente.infoDiaria) {
            items.add(infoDia);
        }

        infoDiariaListView.refresh();
    }

    public void initData(Stage stage, Journey journey) {
        ObservableList<InfoDia> infoDiariaItems = FXCollections.observableArrayList();
        resetInfoDiariaItems(infoDiariaItems, journey);

        infoDiariaListView.setItems(FXCollections.observableList(infoDiariaItems));

        // Handle date filter.
        datePicker.setOnAction((e) -> {
            infoDiariaItems.clear();

            var foundInfoDia = journey.loggedInPaciente.buscarInfoDiaPorFecha(datePicker.getValue());

            if (foundInfoDia != null)
                infoDiariaItems.add(foundInfoDia);

            infoDiariaListView.refresh();
        });
    }
}
