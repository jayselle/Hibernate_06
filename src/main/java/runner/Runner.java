package runner;

import dao.ConcesionarioDAO;
import dao.ConcesionarioDAOFactory;
import entities.Auto;
import entities.Cliente;
import entities.Concesionario;
import entities.Vendedor;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Runner {

    public static void main(String[] args){

        ConcesionarioDAO concesDao = ConcesionarioDAOFactory.createConcesionarioDAO();

        // 1) ---------- Agregar un concesionario ----------
        /*Concesionario c1 = new Concesionario("4 de Febrero","D' Angelo Autos");
        Concesionario c2 = new Concesionario("Av. Corrientes 1234","New Car");
        concesDao.agregarConcesionario(c1);
        concesDao.agregarConcesionario(c2);*/

        // 2) ---------- Agregar autos a un concesionario ----------
        /*Auto a1 = new Auto("BMW","Serie 3",850000.0,"AC 123 CC",null);
        Auto a2 = new Auto("Chevrolet","Orlando",780000.0,"AC 343 FG",new Date(System.currentTimeMillis()));
        Auto a3 = new Auto("Citroen","C4",900000.0,"AB 555 AR",new Date(System.currentTimeMillis()));
        Set<Auto> autos1 = new HashSet<>();
        autos1.add(a1);
        autos1.add(a2);
        autos1.add(a3);
        concesDao.agregarAutosAConcesionario(1l,autos1);

        Auto a4 = new Auto("Chevrolet Corsa","Modelo 3",800000.0,"AC 655 TR",null);
        Auto a5 = new Auto("Suzuki Vitara","Modelo 3",890000.0,"AC 578 OP",new Date(System.currentTimeMillis()));
        Auto a6 = new Auto("Volkswagen Gol","Modelo 3",790000.0,"AD 690 FR",new Date(System.currentTimeMillis()));
        Set<Auto> autos2 = new HashSet<>();
        autos2.add(a4);
        autos2.add(a5);
        autos2.add(a6);
        concesDao.agregarAutosAConcesionario(2l,autos2);*/


        // 3) ---------- Agregar vendedores a un concesionario ----------
        /*Vendedor v1 = new Vendedor("Gonzalo","Tombolini","Maipu 2353","103677",new Date(System.currentTimeMillis()),34000.0);
        Vendedor v2 = new Vendedor("Adrian","Martinez","Santa Fe 3122","103678",new Date(System.currentTimeMillis()),35000.0);
        Set<Vendedor> vendedores1 = new HashSet<>();
        vendedores1.add(v1);
        vendedores1.add(v2);
        concesDao.agregarVendedoresAConcesionario(1l,vendedores1);

        Vendedor v3 = new Vendedor("Mariano","Perez","Av. Jujuy 432","103524",new Date(System.currentTimeMillis()),12000.0);
        Vendedor v4 = new Vendedor("Pedro","Morales","Tucuman 354","103525",new Date(System.currentTimeMillis()),12000.0);
        Set<Vendedor> vendedores2 = new HashSet<>();
        vendedores2.add(v3);
        vendedores2.add(v4);
        concesDao.agregarVendedoresAConcesionario(2l,vendedores2);*/

        // 4) ---------- Informar de cada concesionario: gastos en sueldos y gastos en compra de autos (60% del precio de venta) ----------
        //concesDao.informarGastosDeConcesionarios();

        // 5) ---------- Informar la facturacion de cada concesionario junto con la cantidad de autos vendidos ----------
        //concesDao.informarFacturacionDeConcesionarios();

        // 6) ---------- Informar la ganancia de cada concesionario en un año determinado ----------
        //concesDao.informarGananciasDeConcesionarios(2019);

        // 7) ---------- Actualizar el sueldo fijo de la concesionario 1 con un incremento de 13% ----------
        //concesDao.actualizarSueldosDeConcesionario(1l,1.13);

        // 8) ---------- Agregar un cliente ----------
        //concesDao.agregarCliente(new Cliente("Ramiro","Vazquez","Paraguay 1288",1,1));

        // 9) ---------- Registrar compra de un auto ----------
        //concesDao.venderAutoDeConcesionario(1l,3l,13l);

        // 10) ---------- Informar autos comprados por un cliente ----------
        //concesDao.informarAutosDeConcesionario(1l);

        // 11) ---------- Informar autos disponibles en todas las concesionarias ordenadas por precio de mayor a menor ----------
        //concesDao.informarAutosDisponibles();

    }

}
