package uia.dapp1.miembros.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class EntradaAlumnoController {
    @FXML
    private Button btnRegreso;
    @FXML
    private void actionRegreso(){
        Stage stage = (Stage) btnRegreso.getScene().getWindow();
        // do what you have to do
        stage.close();
    }
}
