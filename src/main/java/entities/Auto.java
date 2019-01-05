package entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="autos")
public class Auto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="au_id")
    private Long id;

    @Column(name="au_marca")
    private String marca;

    @Column(name="au_modelo")
    private String modelo;

    @Column(name="au_precio")
    private Double precio;

    @Column(name="au_patente")
    private String patente;

    @Column(name="au_fecha_venta")
    private Date fecha_venta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="co_id")
    private Concesionario concesionario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="pe_id")
    private Cliente cliente;

    public Auto(){

    }

    public Auto(String marca, String modelo, Double precio, String patente, Date fecha_venta) {
        this.marca = marca;
        this.modelo = modelo;
        this.precio = precio;
        this.patente = patente;
        this.fecha_venta = fecha_venta;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    public Date getFecha_venta() {
        return fecha_venta;
    }

    public void setFecha_venta(Date fecha_venta) {
        this.fecha_venta = fecha_venta;
    }

    public Concesionario getConcesionario() {
        return concesionario;
    }

    public void setConcesionario(Concesionario concesionario) {
        this.concesionario = concesionario;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return "Auto{" +
                "id=" + id +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", precio=" + precio +
                ", patente='" + patente + '\'' +
                ", fecha_venta=" + fecha_venta +
                '}';
    }

}
