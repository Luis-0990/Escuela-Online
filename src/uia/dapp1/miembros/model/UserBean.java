package uia.dapp1.miembros.model;

import java.sql.Connection;
import java.util.List;
import java.util.Objects;

public class UserBean {
    private Integer id_usuario;
    private String nombre;
    private String paterno;
    private String materno;
    private String fecha;
    private String genero;
    private String user;
    private String password;

    public UserBean(Integer id_usuario, String nombre, String paterno, String materno, String fecha, String genero, String user, String password) {
        this.id_usuario = id_usuario;
        this.nombre = nombre;
        this.paterno = paterno;
        this.materno = materno;
        this.fecha = fecha;
        this.genero = genero;
        this.user = user;
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserBean userBean = (UserBean) o;
        return Objects.equals(id_usuario, userBean.id_usuario) && Objects.equals(nombre, userBean.nombre) && Objects.equals(paterno, userBean.paterno) && Objects.equals(materno, userBean.materno) && Objects.equals(fecha, userBean.fecha) && Objects.equals(genero, userBean.genero) && Objects.equals(user, userBean.user) && Objects.equals(password, userBean.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_usuario, nombre, paterno, materno, fecha, genero, user, password);
    }

    @Override
    public String toString() {
        return "UserBean{" +
                "id_usuario=" + id_usuario +
                ", nomobre='" + nombre + '\'' +
                ", paterno='" + paterno + '\'' +
                ", materno='" + materno + '\'' +
                ", fecha='" + fecha + '\'' +
                ", genero='" + genero + '\'' +
                ", user='" + user + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public Integer getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Integer id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNomobre(String nomobre) {
        this.nombre = nomobre;
    }

    public String getPaterno() {
        return paterno;
    }

    public void setPaterno(String paterno) {
        this.paterno = paterno;
    }

    public String getMaterno() {
        return materno;
    }

    public void setMaterno(String materno) {
        this.materno = materno;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
