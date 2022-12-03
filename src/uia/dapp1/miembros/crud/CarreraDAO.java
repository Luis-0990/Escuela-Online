package uia.dapp1.miembros.crud;

import uia.dapp1.miembros.model.CarreraBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarreraDAO {
    /**
     * findAll
     * @param myConn
     * @return
     */
    public List<CarreraBean> findAll(Connection myConn){
        List<CarreraBean>list=null;
        try {
            String query = "SELECT id_carrera, nombre, tiempo,costo FROM carrera";
            list=new ArrayList<>();
            PreparedStatement stmt = myConn.prepareStatement(query);
            ResultSet rst = stmt.executeQuery();
            while (rst.next()) {
                list.add(new CarreraBean(rst.getInt(1),rst.getString(2),rst.getString(3),rst.getString(4)));
            }
            rst.close();
            stmt.close();
        }catch (SQLException e){
            System.out.printf(e.getMessage());
            return null;
        }
        return list;
    }
    /**
     * insert
     * @param myConn
     * @param carrera
     * @return
     */
    public int insert(Connection myConn, CarreraBean carrera){
        int result=0;
        try{
            String query="INSERT INTO carrera(nombre,id_plataforma_fk,tiempo,costo) VALUES(?,?,?,?)";
            PreparedStatement stmt = myConn.prepareStatement(query);
            stmt.setString(1,carrera.getNombre());
            stmt.setString(2, "1");
            stmt.setString(3, carrera.getTiempo());
            stmt.setString(4, carrera.getCosto());
            result=stmt.executeUpdate();
            stmt.close();
            return result;
        }catch (SQLException e){
            System.out.printf(e.getMessage());
        }
        return result;
    }
    /**
     * delete
     * @param myConn
     * @param carrera
     * @return
     */
    public int delete(Connection myConn,CarreraBean carrera){
        int result=0;
        try{
            String query="DELETE FROM carrera WHERE id_carrera=?";
            PreparedStatement stmt= myConn.prepareStatement(query);
            stmt.setInt(1,carrera.getId_carrera());
            result=stmt.executeUpdate();
            stmt.close();
        }catch (SQLException e){
            System.out.printf(e.getMessage());
            return 0;
        }
        return result;
    }
    /**
     * updata
     * @param myConn
     * @param carrera
     * @return
     */
    public int updata(Connection myConn,CarreraBean carrera){
        int result=0;
        try{
            String query="UPDATE carrera set nombre=?, tiempo=?, costo=? WHERE id_carrera=?";
            PreparedStatement stmt= myConn.prepareStatement(query);
            stmt.setString(1,carrera.getNombre());
            stmt.setString(2,carrera.getTiempo());
            stmt.setString(3,carrera.getCosto());
            stmt.setInt(4,carrera.getId_carrera());
            result=stmt.executeUpdate();
        }catch (SQLException e){
            System.out.printf(e.getMessage());
            return 0;
        }
        return result;
    }
}
