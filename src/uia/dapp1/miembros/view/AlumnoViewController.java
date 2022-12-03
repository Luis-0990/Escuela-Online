package uia.dapp1.miembros.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import uia.dapp1.miembros.crud.ConnectDB;
import uia.dapp1.miembros.crud.UserDAO;

import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;

public class AlumnoViewController {
    @FXML
    private TextField txtUser;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private Button btnLogin;
    @FXML
    private Button btnCancelar;
    @FXML
    private Label MensajeError;

    @FXML
    private void actionLogin(){
        try {
            ConnectDB myConn=new ConnectDB();
            Connection conn=myConn.conecta();
            System.out.println("Connected to the database!");

            if(!txtUser.getText().isEmpty()&&!txtPassword.getText().isEmpty()) {
                String user = txtUser.getText();
                String pass = txtPassword.getText();
                UserDAO dao=new UserDAO();
                int res= dao.login(conn,user,pass);
                if(res==1){
                    MensajeError.setText("");
                    txtPassword.setText("");
                    txtUser.setText("");
                    Entrada();
                    System.out.println("ENTRADA!!!");
                } else {
                    MensajeError.setText("Invalido Usuario");
                }
            }
            myConn.desconecta(conn);//Desconectamos
        }catch (SQLException ex){
            System.out.printf(ex.getMessage());
        }
    }
    @FXML
    private void actionCancelar(){
        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        // do what you have to do
        stage.close();
    }
    private void Entrada() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EntradaAlumno.fxml"));
            Pane root1 = (Pane) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("Entrada");
            stage.setScene(new Scene(root1));
            stage.show();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
}
