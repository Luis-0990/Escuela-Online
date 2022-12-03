package uia.dapp1.miembros.crud;

import uia.dapp1.miembros.model.CarreraBean;
import uia.dapp1.miembros.model.UserBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    /**
     * findAll2
     * @param myConn
     * @return
     */
    public List<UserBean> findAll2(Connection myConn){
        List<UserBean> list=null;
        try {
            String query = "SELECT id_usuario,nombre,paterno,materno,fecha_nacimiento,genero,user_name,user_pass FROM usuario";
            list=new ArrayList<>();
            PreparedStatement stmt = myConn.prepareStatement(query);
            ResultSet rst = stmt.executeQuery();
            while (rst.next()) {
                list.add(new uia.dapp1.miembros.model.UserBean(rst.getInt(1),rst.getString(2),rst.getString(3),
                        rst.getString(4),rst.getString(5),rst.getString(6),rst.getString(7),rst.getString(8)));
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
     * insert2
     * @param myConn
     * @param usuarios
     * @return
     */
    public int insert2(Connection myConn, UserBean usuarios){
        int result=0;
        try{
            String query="INSERT INTO usuario(nombre,paterno,materno,fecha_nacimiento,genero,user_name,user_pass,id_plataforma_fk) VALUES(?,?,?,?,?,?,?,?)";
            PreparedStatement stmt = myConn.prepareStatement(query);
            stmt.setString(1,usuarios.getNombre());
            stmt.setString(2, usuarios.getPaterno());
            stmt.setString(3, usuarios.getMaterno());
            stmt.setString(4, usuarios.getFecha());
            stmt.setString(5, usuarios.getGenero());
            stmt.setString(6, usuarios.getUser());
            stmt.setString(7, usuarios.getPassword());
            stmt.setString(8, "1");
            result=stmt.executeUpdate();
            stmt.close();
            return result;
        }catch (SQLException e){
            System.out.printf(e.getMessage());
        }
        return result;
    }
    /**
     * delete2
     * @param myConn
     * @param usuarios
     * @return
     */
    public int delete2(Connection myConn,UserBean usuarios){
        int result=0;
        try{
            String query="DELETE FROM usuario WHERE id_usuario=?";
            PreparedStatement stmt= myConn.prepareStatement(query);
            stmt.setInt(1,usuarios.getId_usuario());
            result=stmt.executeUpdate();
            stmt.close();
        }catch (SQLException e){
            System.out.printf(e.getMessage());
            return 0;
        }
        return result;
    }
    /**
     * updata2
     * @param myConn
     * @param usuarios
     * @return
     */
    public int updata2(Connection myConn,UserBean usuarios){
        int result=0;
        try{
            String query="UPDATE usuario SET nombre=?, paterno=?, materno=?, fecha_nacimiento=? WHERE id_usuario=?";
            PreparedStatement stmt= myConn.prepareStatement(query);
            stmt.setString(1,usuarios.getNombre());
            stmt.setString(2,usuarios.getPaterno());
            stmt.setString(3,usuarios.getMaterno());
            stmt.setString(4,usuarios.getFecha());
            stmt.setInt(5,usuarios.getId_usuario());
            result=stmt.executeUpdate();
        }catch (SQLException e){
            System.out.printf(e.getMessage());
            return 0;
        }
        return result;
    }

    public int login(Connection myConn, String user, String password)   {
        int result=0;
        try{
           //String query="SELECT user_name, user_pass FROM usuario where user_name=?";
            String query="SELECT user_name, user_pass FROM usuario WHERE user_name=? AND user_pass=?";
            PreparedStatement stmt = myConn.prepareStatement(query);
            stmt.setString(1,user);
            stmt.setString(2,password);
            ResultSet rst = stmt.executeQuery();
            if(rst.next()) {
              // if (BCrypt.checkpw(password, rst.getString(2)))
                //    System.out.println("It matches");
                    result = 1;
            }else{
                result=0;
            }
            stmt.close();
            return result;
        }catch (SQLException e){
            System.out.printf(e.getMessage());
            }
        return result;
    }
}
