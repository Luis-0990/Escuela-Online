package uia.dapp1.miembros.view;

import javafx.collections.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import uia.dapp1.miembros.model.CarreraBean;
import uia.dapp1.miembros.crud.CarreraDAO;
import uia.dapp1.miembros.crud.ConnectDB;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class TablaCarreraViewController {
    //Button and TextField
    @FXML private Button btnCancelar;
    @FXML private Button btnInsertar;
    @FXML private Button btnModificar;
    @FXML private Button btnEliminar;
    @FXML private Button btnActualizar;
    @FXML private TextField txtNombre;
    @FXML private TextField txtTiempo;
    @FXML private TextField txtCosto;
    @FXML private Label idLabel;
    @FXML private Label mensajeLabel;
    //TableView and TableColumn
    @FXML private TableView<CarreraBean> tablaCarreras;
    @FXML private TableColumn<CarreraBean,Integer> inCL;
    @FXML private TableColumn<CarreraBean,String>  nombreCL;
    @FXML private TableColumn<CarreraBean,String>  tiempoCL;
    @FXML private TableColumn<CarreraBean,String>  costoCL;
    private ObservableList<CarreraBean> carreras;


    @FXML
    private void actionInsertar(){
        Integer id =null;

        String nombre=txtNombre.getText();
        String tiempo=txtTiempo.getText();
        String costo=txtCosto.getText();
        //Conexion
        try {
            ConnectDB myConn = new ConnectDB();
            Connection conn = myConn.conecta();
            System.out.println("Connected to the database!");

            CarreraBean bean=new CarreraBean(id,nombre,tiempo,costo);
            CarreraDAO dao = new CarreraDAO();
            int res= dao.insert(conn, bean);
            if(res==1){
                mensajeLabel.setText("Aregado");
                System.out.println("Aregado");
                limpiar();
            }else{
                System.out.println("¡Error!");
            }
            myConn.desconecta(conn);
        }catch (SQLException ex){
            System.out.printf(ex.getMessage());
        }
    }
    @FXML
    private void actionModificar(){
        int id = Integer.parseInt(idLabel.getText());
        String nombre = txtNombre.getText();
        String tiempo = txtTiempo.getText();
        String costo = txtCosto.getText();
        //Conexion
        if (!txtNombre.getText().isEmpty()&&!txtTiempo.getText().isEmpty()&&!txtCosto.getText().isEmpty()) {
            try {
                ConnectDB myConn = new ConnectDB();
                Connection conn = myConn.conecta();
                System.out.println("Connected to the database!");
                CarreraBean bean = new CarreraBean(id, nombre, tiempo,costo);
                CarreraDAO dao = new CarreraDAO();
                int res = dao.updata(conn, bean);
                if (res == 1) {
                    mensajeLabel.setText("Modificado");
                    limpiar();
                } else {
                    System.out.println("No se encontro elemento");
                }
                myConn.desconecta(conn);
            } catch (SQLException ex) {
                System.out.printf(ex.getMessage());
            }
        }else{
            System.out.println("Ingrese datos completos");
        }
    }
    @FXML
    private void actionEliminar(){
        int id = Integer.parseInt(idLabel.getText());
        //Conexion
        try {
            ConnectDB myConn = new ConnectDB();
            Connection conn = myConn.conecta();
            System.out.println("Connected to the database!");
            CarreraBean bean=new CarreraBean(id,null,null,null);
            CarreraDAO dao = new CarreraDAO();
            int res= dao.delete(conn, bean);
            if(res==1){
                mensajeLabel.setText("Eliminado...");
                limpiar();
            }else{
                System.out.println("No se encontro elemento");
            }
            myConn.desconecta(conn);
        }catch (SQLException ex){
            System.out.printf(ex.getMessage());
        }
    }
    @FXML
    private void actionActualizar(){
        try{
            ConnectDB myConn=new ConnectDB();
            Connection conn=myConn.conecta();
            System.out.println("Connected to the database!");
            //SELECT
            CarreraDAO dao=new CarreraDAO();
            List<CarreraBean> list=dao.findAll(conn);
             carreras=FXCollections.observableList(list);
                this.inCL.setCellValueFactory(new PropertyValueFactory("id_carrera"));
                this.nombreCL.setCellValueFactory(new PropertyValueFactory("Nombre"));
                this.tiempoCL.setCellValueFactory(new PropertyValueFactory("Tiempo"));
                this.costoCL.setCellValueFactory(new PropertyValueFactory("Costo"));
                this.tablaCarreras.setItems(carreras);
               //btnInsertar.setDisable(false);
               limpiar();
               myConn.desconecta(conn);//Desconectamos
        }catch (SQLException ex){
            System.out.printf(ex.getMessage());
        }
    }
    private void limpiar(){
        txtNombre.setText("");
        txtTiempo.setText("");
        txtCosto.setText("");
        idLabel.setText("");
        mensajeLabel.setText("");
    }
    //////////////////
    @FXML
    private void initialize() {
        showCarreraDetails(null);
        // Listen for selection changes and show the person details when changed.
        tablaCarreras.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showCarreraDetails(newValue));
    }
    private void showCarreraDetails(CarreraBean carrera) {
        if (carrera != null) {
            // Fill the labels with info from the person object.
            idLabel.setText(Integer.toString(carrera.getId_carrera()));
            txtNombre.setText(carrera.getNombre());
            txtTiempo.setText(carrera.getTiempo());
            txtCosto.setText(carrera.getCosto());

            // TODO: We need a way to convert the birthday into a String!
            // birthdayLabel.setText(...);
        } else {
            // Person is null, remove all the text.
            txtNombre.setText("");
            txtTiempo.setText("");
            txtCosto.setText("");
        }
    }
    @FXML
    private void actionCancelar(){
        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        // do what you have to do
        stage.close();
    }
}
