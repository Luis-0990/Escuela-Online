package uia.dapp1.miembros.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import uia.dapp1.miembros.crud.CarreraDAO;
import uia.dapp1.miembros.crud.ConnectDB;
import uia.dapp1.miembros.crud.UserDAO;
import uia.dapp1.miembros.model.CarreraBean;
import uia.dapp1.miembros.model.UserBean;

import javax.jws.soap.SOAPBinding;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class TablaUserViewController {
    //Button and TextField
    @FXML private Button btnCancelar;
    @FXML private Button btnInsertar;
    @FXML private Button btnModificar;
    @FXML private Button btnEliminar;
    @FXML private Button btnActualizar;
    @FXML private TextField txtNombre;
    @FXML private TextField txtPaterno;
    @FXML private TextField txtMaterno;
    @FXML private TextField txtFecha;
    @FXML private ComboBox<String> comboGenero;
    @FXML private TextField txtUser;
    @FXML private PasswordField txtPassword;
    @FXML private Label idLabel;
    @FXML private Label mensajeLabel;
    //TableView and TableColumn
    @FXML private TableView<UserBean> tablaUser;
    @FXML private TableColumn<CarreraBean,Integer> idCL;
    @FXML private TableColumn<CarreraBean,String>  nombreCL;
    @FXML private TableColumn<CarreraBean,String>  paternoCL;
    @FXML private TableColumn<CarreraBean,String>  maternoCL;
    @FXML private TableColumn<CarreraBean,String>  fechaCL;
    @FXML private TableColumn<CarreraBean,String>  generoCL;
    private ObservableList<UserBean> usuarios;

    @FXML
    private void actionInsertar(){
        Integer id =null;
        String nombre=txtNombre.getText();
        String paterno=txtPaterno.getText();
        String materno=txtMaterno.getText();
        String fecha=txtFecha.getText();
        String genaro=comboGenero.getValue();
        String user=txtUser.getText();
        String password=txtPassword.getText();
        if (!txtNombre.getText().isEmpty()&&!txtPaterno.getText().isEmpty()&&!txtMaterno.getText().isEmpty()&&!txtUser.getText().isEmpty()&&!txtPassword.getText().isEmpty()) {
            //Conexion
            try {
                ConnectDB myConn = new ConnectDB();
                Connection conn = myConn.conecta();
                System.out.println("Connected to the database!");
                UserBean bean = new UserBean(id, nombre, paterno, materno, fecha, genaro, user, password);
                UserDAO dao = new UserDAO();
                int res = dao.insert2(conn, bean);
                if (res == 1) {
                    mensajeLabel.setText("Aregado");
                    System.out.println("Aregado");
                    limpiar();
                } else {
                    System.out.println("¡Error!!!!");
                }
                myConn.desconecta(conn);
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        } else{
            System.out.println("Ingrese datos compretos");
        }
    }
    @FXML
    private void actionModificar(){
        int id = Integer.parseInt(idLabel.getText());
        String nombre=txtNombre.getText();
        String paterno=txtPaterno.getText();
        String materno=txtMaterno.getText();
        String fecha=txtFecha.getText();
        //Conexion
        if (!txtNombre.getText().isEmpty()&&!txtPaterno.getText().isEmpty()&&!txtMaterno.getText().isEmpty()&&!txtFecha.getText().isEmpty()) {
            try {
                ConnectDB myConn = new ConnectDB();
                Connection conn = myConn.conecta();
                System.out.println("Connected to the database!");
                UserBean bean = new UserBean(id, nombre, paterno,materno,fecha,null,null,null);
                UserDAO dao = new UserDAO();
                int res = dao.updata2(conn, bean);
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
            System.out.println("Seleccione Opcion");
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
            UserBean bean=new UserBean(id,null,null,null,null,null,null,null);
            UserDAO dao = new UserDAO();
            int res= dao.delete2(conn, bean);
            if(res==1){
                mensajeLabel.setText("Eliminado...");
                System.out.println("Eliminado...");
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
            UserDAO dao=new UserDAO();
            List<UserBean> list=dao.findAll2(conn);
            usuarios=FXCollections.observableList(list);
            this.idCL.setCellValueFactory(new PropertyValueFactory("id_usuario"));
            this.nombreCL.setCellValueFactory(new PropertyValueFactory("nombre"));
            this.paternoCL.setCellValueFactory(new PropertyValueFactory("paterno"));
            this.maternoCL.setCellValueFactory(new PropertyValueFactory("materno"));
            this.fechaCL.setCellValueFactory(new PropertyValueFactory("fecha"));
            this.generoCL.setCellValueFactory(new PropertyValueFactory("genero"));
            comboGenero.getItems().addAll("M", "F");
            //comboGenero.setPromptText(comboGenero.getValue());
            this.tablaUser.setItems(usuarios);
            limpiar();
            myConn.desconecta(conn);//Desconectamos
        }catch (SQLException ex){
            System.out.printf(ex.getMessage());
        }
    }

    private void limpiar(){
        txtNombre.setText("");
        txtMaterno.setText("");
        txtPaterno.setText("");
        txtFecha.setText("");
        comboGenero.setPromptText("Genero");
        txtUser.setText("");
        txtPassword.setText("");
        idLabel.setText("");
        mensajeLabel.setText("");
    }
    //////////////////
    @FXML
    private void initialize() {
        showUserDetails(null);
        // Listen for selection changes and show the person details when changed.
        tablaUser.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showUserDetails(newValue));
    }
    private void showUserDetails(UserBean usuarios) {
        if (usuarios != null) {
            // Fill the labels with info from the person object.
            idLabel.setText(Integer.toString(usuarios.getId_usuario()));
            txtNombre.setText(usuarios.getNombre());
            txtPaterno.setText(usuarios.getPaterno());
            txtMaterno.setText(usuarios.getMaterno());
            txtFecha.setText(usuarios.getFecha());
            comboGenero.setPromptText(usuarios.getGenero());
            // TODO: We need a way to convert the birthday into a String!
            // birthdayLabel.setText(...);
        } else {
            // Person is null, remove all the text.
            txtNombre.setText("");
            txtPaterno.setText("");
            txtMaterno.setText("");
            txtFecha.setText("");
        }
    }
    @FXML
    private void actionCancelar(){
        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        // do what you have to do
        stage.close();
    }
}
