package uia.dapp1.miembros.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AdministradorViewController {
    @FXML
    private Button btnCancelar;
    @FXML
    private Button btnUsuarios;
    @FXML
    private Button btnCarreras;
    @FXML
    private Button btnFacturas;
    @FXML
    private void actionCancelar(){
        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        // do what you have to do
        stage.close();
    }
    @FXML
    private void actionUsuario(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("TablaUserView.fxml"));
            AnchorPane root1 = (AnchorPane) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("Alumno");
            stage.setScene(new Scene(root1));
            stage.show();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void actionCarrera(){
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("TablaCarreraView.fxml"));
                AnchorPane root1 = (AnchorPane) fxmlLoader.load();
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.initStyle(StageStyle.UNDECORATED);
                stage.setTitle("Alumno");
                stage.setScene(new Scene(root1));
                stage.show();
            }catch(Exception e) {
                e.printStackTrace();
            }
    }
    @FXML
    private void actionFactura(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("TablaFacturaView.fxml"));
            AnchorPane root1 = (AnchorPane) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("Alumno");
            stage.setScene(new Scene(root1));
            stage.show();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
}
