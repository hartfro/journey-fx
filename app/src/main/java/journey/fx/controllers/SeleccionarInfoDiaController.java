package journey.fx.controllers;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import journey.core.InfoDia;
import journey.core.Journey;

public class SeleccionarInfoDiaController {
    @FXML
    ListView<InfoDia> infoDiariaListView;

    public void initData(Stage stage, Journey journey) {
        List<InfoDia> infoDiariaItems = new ArrayList<>();
        for (var infoDia : journey.loggedInPaciente.infoDiaria) {
            infoDiariaItems.add(infoDia);
        }

        infoDiariaListView.setItems(FXCollections.observableList(infoDiariaItems));
    }
}
