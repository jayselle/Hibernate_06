package entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="concesionarios")
public class Concesionario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="co_id")
    private Long id;

    @Column(name="co_direccion")
    private String direccion;

    @Column(name="co_nombre")
    private String nombre;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "concesionario",cascade = CascadeType.ALL)
    private Set<Auto> autos = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "concesionario",cascade = CascadeType.ALL)
    private Set<Vendedor> vendedores = new HashSet<>();

    public Concesionario(){

    }

    public Concesionario(String direccion, String nombre) {
        this.direccion = direccion;
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Set<Auto> getAutos() {
        return autos;
    }

    public void setAutos(Set<Auto> autos) {
        this.autos = autos;
    }

    public Set<Vendedor> getVendedores() {
        return vendedores;
    }

    public void setVendedores(Set<Vendedor> vendedores) {
        this.vendedores = vendedores;
    }

    @Override
    public String toString() {
        return "Concesionario{" +
                "id=" + id +
                ", direccion='" + direccion + '\'' +
                ", nombre='" + nombre + '\'' +
                '}';
    }

}
