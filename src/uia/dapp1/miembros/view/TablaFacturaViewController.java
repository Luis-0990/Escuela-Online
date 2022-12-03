package uia.dapp1.miembros.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import uia.dapp1.miembros.crud.ConnectDB;
import uia.dapp1.miembros.crud.FacturaDAO;
import uia.dapp1.miembros.model.FacturaBean;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class TablaFacturaViewController {
    //Button and TextField
    @FXML
    private Button btnCancelar;
    @FXML private Button btnInsertar;
    @FXML private Button btnModificar;
    @FXML private Button btnEliminar;
    @FXML private Button btnActualizar;
    @FXML private TextField txtFecha;
    @FXML private TextField txtTotal;
    @FXML private TextField txtRFC;
    @FXML private TextField txtCliente;
    @FXML private Label idLabel;
    @FXML private Label mensajeLabel;
    //TableView and TableColumn
    @FXML private TableView<FacturaBean> tablaFacturas;
    @FXML private TableColumn<FacturaBean,Integer> idCL;
    @FXML private TableColumn<FacturaBean,String>  fechaCL;
    @FXML private TableColumn<FacturaBean,String>  totalCL;
    @FXML private TableColumn<FacturaBean,String>  rfcCL;
    @FXML private TableColumn<FacturaBean,String>  clienteCL;
    private ObservableList<FacturaBean> facturas;

    @FXML
    private void actionInsertar(){
        Integer id =null;
        String fechaFC=txtFecha.getText();
        String totalS = txtTotal.getText();
        Integer total=Integer.parseInt(totalS);
        String rfc=txtRFC.getText();
        String idS = txtCliente.getText();
        Integer idCliente=Integer.parseInt(idS);
        if (!txtFecha.getText().isEmpty()&&!idLabel.getText().isEmpty()) {
            //Conexion
            try {
                ConnectDB myConn = new ConnectDB();
                Connection conn = myConn.conecta();
                System.out.println("Connected to the database!");

                FacturaBean bean = new FacturaBean(id, fechaFC, total, rfc, idCliente);
                FacturaDAO dao = new FacturaDAO();
                int res = dao.insert3(conn, bean);
                if (res == 1) {
                    mensajeLabel.setText("Aregado");
                    System.out.println("Aregado");
                    limpiar();
                } else {
                    System.out.println("¡Error!");
                }
                myConn.desconecta(conn);
            } catch (SQLException ex) {
                System.out.printf(ex.getMessage());
            }
        }else{
            System.out.println("Añade los datos que vas a insertar");
        }
    }
    @FXML
    private void actionModificar(){
        int id = Integer.parseInt(idLabel.getText());
        String fechaFC=txtFecha.getText();
        String rfc=txtRFC.getText();
        //Conexion
        if (!txtFecha.getText().isEmpty()&&!idLabel.getText().isEmpty()) {
            try {
                ConnectDB myConn = new ConnectDB();
                Connection conn = myConn.conecta();
                System.out.println("Connected to the database!");
                FacturaBean bean = new FacturaBean(id, fechaFC, null,rfc,null);
                FacturaDAO dao = new FacturaDAO();
                int res = dao.updata3(conn, bean);
                if (res == 1) {
                    mensajeLabel.setText("Modificado");
                    System.out.println("Modificado");
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
        if (!txtFecha.getText().isEmpty()&&!idLabel.getText().isEmpty()) {
            //Conexion
            try {
                ConnectDB myConn = new ConnectDB();
                Connection conn = myConn.conecta();
                System.out.println("Connected to the database!");
                FacturaBean bean = new FacturaBean(id, null, null, null, null);
                FacturaDAO dao = new FacturaDAO();
                int res = dao.delete3(conn, bean);
                if (res == 1) {
                    mensajeLabel.setText("Eliminado...");
                    System.out.println("Eliminado...");
                    limpiar();
                } else {
                    System.out.println("No se encontro elemento");
                }
                myConn.desconecta(conn);
            } catch (SQLException ex) {
                System.out.printf(ex.getMessage());
            }
        }else{
            System.out.println("No se encontro elemento");
        }
    }
    @FXML
    private void actionActualizar(){
        try{
            ConnectDB myConn=new ConnectDB();
            Connection conn=myConn.conecta();
            System.out.println("Connected to the database!");
            //SELECT
            FacturaDAO dao=new FacturaDAO();
            List<FacturaBean> list=dao.findAll3(conn);
            facturas= FXCollections.observableList(list);
            this.idCL.setCellValueFactory(new PropertyValueFactory("id_factura"));
            this.fechaCL.setCellValueFactory(new PropertyValueFactory("fecha_factura"));
            this.totalCL.setCellValueFactory(new PropertyValueFactory("total"));
            this.rfcCL.setCellValueFactory(new PropertyValueFactory("RFC"));
            this.clienteCL.setCellValueFactory(new PropertyValueFactory("cliente"));
            this.tablaFacturas.setItems(facturas);
            //btnInsertar.setDisable(false);
            limpiar();
            myConn.desconecta(conn);//Desconectamos
        }catch (SQLException ex){
            System.out.printf(ex.getMessage());
        }
    }
    private void limpiar(){
        txtFecha.setText("");
        txtTotal.setText("");
        txtRFC.setText("");
        idLabel.setText("");
        txtCliente.setText("");
        mensajeLabel.setText("");
    }
    //////////////////
    @FXML
    private void initialize() {
        showFacturasDetails(null);
        // Listen for selection changes and show the person details when changed.
        tablaFacturas.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showFacturasDetails(newValue));
    }
    private void showFacturasDetails(FacturaBean factura) {
        if (factura != null) {
            // Fill the labels with info from the person object.
            idLabel.setText(Integer.toString(factura.getId_factura()));
            txtFecha.setText(factura.getFecha_factura());
            txtTotal.setText(Integer.toString(factura.getTotal()));
            txtRFC.setText(factura.getRFC());
            txtCliente.setText(Integer.toString(factura.getCliente()));
            // TODO: We need a way to convert the birthday into a String!
            // birthdayLabel.setText(...);
        }
    }
    @FXML
    private void actionCancelar(){
        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        // do what you have to do
        stage.close();
    }
}
