package entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@DiscriminatorValue("cl")
public class Cliente extends Persona{

    @Column(name="cl_numero")
    private Integer numero;

    @Column(name="cl_tipo")
    private Integer tipo;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "cliente",cascade = CascadeType.ALL)
    private Set<Auto> autos = new HashSet<>();

    public Cliente(){

    }

    public Cliente(String nombre, String apellido, String direccion, Integer numero, Integer tipo) {
        super(nombre, apellido, direccion);
        this.numero = numero;
        this.tipo = tipo;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }
}
