package entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@DiscriminatorValue("ve")
public class Vendedor extends Persona{

    @Column(name="ve_legajo")
    private String legajo;

    @Column(name="ve_fecha_ingreso")
    private Date fecha_ingreso;

    @Column(name="ve_sueldo")
    private Double sueldo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="pe_ve_co_id")
    private Concesionario concesionario;

    public Vendedor(){

    }

    public Vendedor(String nombre, String apellido, String direccion, String legajo, Date fecha_ingreso, Double sueldo) {
        super(nombre, apellido, direccion);
        this.legajo = legajo;
        this.fecha_ingreso = fecha_ingreso;
        this.sueldo = sueldo;
    }

    public String getLegajo() {
        return legajo;
    }

    public void setLegajo(String legajo) {
        this.legajo = legajo;
    }

    public Date getFecha_ingreso() {
        return fecha_ingreso;
    }

    public void setFecha_ingreso(Date fecha_ingreso) {
        this.fecha_ingreso = fecha_ingreso;
    }

    public Double getSueldo() {
        return sueldo;
    }

    public void setSueldo(Double sueldo) {
        this.sueldo = sueldo;
    }

    public Concesionario getConcesionario() {
        return concesionario;
    }

    public void setConcesionario(Concesionario concesionario) {
        this.concesionario = concesionario;
    }

}
