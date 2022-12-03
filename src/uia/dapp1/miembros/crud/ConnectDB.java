package uia.dapp1.miembros.crud;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class ConnectDB {
    public Connection myConn = null;
    private String dbUrl = "jdbc:mysql://localhost:3306/cursos_online?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private String user = "root";
    private String pass = "Infernity99%";

    public Connection conecta()throws SQLException{//CONECTA
        this.myConn = DriverManager.getConnection(dbUrl, user, pass);
        if(this.myConn!=null){
            return myConn;
        }else {
            return null;
        }
    }
    public void desconecta(Connection conexion)throws SQLException{//DESCONECTA
        conexion.close();
    }
}