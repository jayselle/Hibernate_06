package dao;

import entities.Auto;
import entities.Cliente;
import entities.Concesionario;
import entities.Vendedor;

import java.util.Set;

public interface ConcesionarioDAO {

    void agregarConcesionario(Concesionario c);
    void agregarAutosAConcesionario(Long idConcesionario, Set<Auto> autos);
    void agregarVendedoresAConcesionario(Long idConcesionario, Set<Vendedor> vendedores);
    void informarGastosDeConcesionarios();
    void informarFacturacionDeConcesionarios();
    void informarGananciasDeConcesionarios(int unAnio);
    void actualizarSueldosDeConcesionario(Long idConcesionario, double incrementoPorcentual);
    void agregarCliente(Cliente c);
    void venderAutoDeConcesionario(Long idConcesionario, Long idAuto, Long idCliente);
    void informarAutosDeConcesionario(Long idConcesionario);
    void informarAutosDisponibles();

}
