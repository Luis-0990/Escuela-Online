package uia.dapp1.miembros.crud;

import uia.dapp1.miembros.model.CarreraBean;
import uia.dapp1.miembros.model.FacturaBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FacturaDAO {
    /**
     * findAll3
     * @param myConn
     * @return
     */
    public List<FacturaBean> findAll3(Connection myConn){
        List<FacturaBean>list=null;
        try {
            String query = "SELECT id_factura, fecha_factura, total,RFC,cliente_alumnoid FROM factura";
            list=new ArrayList<>();
            PreparedStatement stmt = myConn.prepareStatement(query);
            ResultSet rst = stmt.executeQuery();
            while (rst.next()) {
                list.add(new FacturaBean(rst.getInt(1),rst.getString(2),rst.getInt(3),rst.getString(4),rst.getInt(5)));
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
     * insert3
     * @param myConn
     * @param factura
     * @return
     */
    public int insert3(Connection myConn, FacturaBean factura){
        int result=0;
        try{
            String query="INSERT INTO factura(fecha_factura,total,RFC,cliente_alumnoid) VALUES(?,?,?,?)";
            PreparedStatement stmt = myConn.prepareStatement(query);
            stmt.setString(1,factura.getFecha_factura());
            stmt.setInt(2,factura.getTotal());
            stmt.setString(3, factura.getRFC());
            stmt.setInt(4, factura.getCliente());
            result=stmt.executeUpdate();
            stmt.close();
            return result;
        }catch (SQLException e){
            System.out.printf(e.getMessage());
        }
        return result;
    }
    /**
     * delete3
     * @param myConn
     * @param factura
     * @return
     */
    public int delete3(Connection myConn,FacturaBean factura){
        int result=0;
        try{
            String query="DELETE FROM factura WHERE id_factura=?";
            PreparedStatement stmt= myConn.prepareStatement(query);
            stmt.setInt(1,factura.getId_factura());
            result=stmt.executeUpdate();
            stmt.close();
        }catch (SQLException e){
            System.out.printf(e.getMessage());
            return 0;
        }
        return result;
    }
    /**
     * updata3
     * @param myConn
     * @param factura
     * @return
     */
    public int updata3(Connection myConn,FacturaBean factura){
        int result=0;
        try{
            String query="UPDATE factura set fecha_factura=?, RFC=? WHERE id_factura=?";
            PreparedStatement stmt= myConn.prepareStatement(query);
            stmt.setString(1,factura.getFecha_factura());
            stmt.setString(2,factura.getRFC());
            stmt.setInt(3,factura.getId_factura());
            result=stmt.executeUpdate();
        }catch (SQLException e){
            System.out.printf(e.getMessage());
            return 0;
        }
        return result;
    }

}
