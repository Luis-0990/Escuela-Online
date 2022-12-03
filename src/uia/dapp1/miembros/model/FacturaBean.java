package uia.dapp1.miembros.model;

import java.util.Objects;

public class FacturaBean {
    private Integer id_factura;
    private String fecha_factura;
    private Integer total;
    private String RFC;
    private Integer cliente;

    public FacturaBean(Integer id_factura, String fecha_factura, Integer total, String RFC, Integer cliente) {
        this.id_factura = id_factura;
        this.fecha_factura = fecha_factura;
        this.total = total;
        this.RFC = RFC;
        this.cliente = cliente;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FacturaBean that = (FacturaBean) o;
        return Objects.equals(id_factura, that.id_factura) && Objects.equals(fecha_factura, that.fecha_factura) && Objects.equals(total, that.total) && Objects.equals(RFC, that.RFC) && Objects.equals(cliente, that.cliente);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_factura, fecha_factura, total, RFC, cliente);
    }

    @Override
    public String toString() {
        return "FacturaBean{" +
                "id_factura=" + id_factura +
                ", fecha_factura='" + fecha_factura + '\'' +
                ", total=" + total +
                ", RFC='" + RFC + '\'' +
                ", cliente='" + cliente + '\'' +
                '}';
    }

    public Integer getId_factura() {
        return id_factura;
    }

    public void setId_factura(Integer id_factura) {
        this.id_factura = id_factura;
    }

    public String getFecha_factura() {
        return fecha_factura;
    }

    public void setFecha_factura(String fecha_factura) {
        this.fecha_factura = fecha_factura;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public String getRFC() {
        return RFC;
    }

    public void setRFC(String RFC) {
        this.RFC = RFC;
    }

    public Integer getCliente() {
        return cliente;
    }

    public void setCliente(Integer cliente) {
        this.cliente = cliente;
    }
}
