package uia.dapp1.miembros.model;

import java.util.Objects;

public class CarreraBean {
    private Integer id_carrera;
    private String nombre;
    private String tiempo;
    private String costo;

    public CarreraBean(Integer id_carrera, String nombre, String tiempo, String costo) {
        this.id_carrera = id_carrera;
        this.nombre = nombre;
        this.tiempo = tiempo;
        this.costo = costo;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarreraBean that = (CarreraBean) o;
        return Objects.equals(id_carrera, that.id_carrera) && Objects.equals(nombre, that.nombre) && Objects.equals(tiempo, that.tiempo) && Objects.equals(costo, that.costo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_carrera, nombre, tiempo, costo);
    }

    @Override
    public String toString() {
        return "CarreraBean{" +
                "id_carrera=" + id_carrera +
                ", nombre='" + nombre + '\'' +
                ", tiempo='" + tiempo + '\'' +
                ", costo='" + costo + '\'' +
                '}';
    }

    public Integer getId_carrera() {
        return id_carrera;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTiempo() {
        return tiempo;
    }

    public String getCosto() {
        return costo;
    }

    public void setId_carrera(Integer id_carrera) {
        this.id_carrera = id_carrera;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }

    public void setCosto(String costo) {
        this.costo = costo;
    }


}
