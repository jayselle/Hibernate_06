package dao;

import entities.Auto;
import entities.Cliente;
import entities.Concesionario;
import entities.Vendedor;
import manager.SessionManager;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.type.LongType;

import java.util.*;

public class ConcesionarioDAOImpl implements ConcesionarioDAO{

    @Override
    public void agregarConcesionario(Concesionario c){

        Session sesion = null;
        Transaction transaction = null;

        try{

            sesion = SessionManager.getSession();
            transaction = sesion.beginTransaction();

            sesion.save(c);

            transaction.commit();

        } catch (Exception e){
            if(transaction!=null){
                transaction.rollback();
            }
        } finally {
            if (sesion!=null){
                sesion.close();
            }
        }

    }

    @Override
    public void agregarAutosAConcesionario(Long idConcesionario, Set<Auto> autos) {

        Session sesion = null;
        Transaction transaction = null;

        try{

            sesion = SessionManager.getSession();
            transaction = sesion.beginTransaction();

            Concesionario c = sesion.load(Concesionario.class,idConcesionario);
            Iterator iter = autos.iterator();
            while(iter.hasNext()){
                Auto a = (Auto) iter.next();
                a.setConcesionario(c);
            }
            c.setAutos(autos);
            sesion.save(c);

            transaction.commit();

        } catch (Exception e){
            System.out.println("Error"+e.getMessage());
            if(transaction!=null){
                transaction.rollback();
            }
        } finally {
            if (sesion!=null){
                sesion.close();
            }
        }

    }

    @Override
    public void agregarVendedoresAConcesionario(Long idConcesionario, Set<Vendedor> vendedores) {

        Session sesion = null;
        Transaction transaction = null;

        try{

            sesion = SessionManager.getSession();
            transaction = sesion.beginTransaction();

            Concesionario c = sesion.load(Concesionario.class,idConcesionario);
            Iterator iter = vendedores.iterator();
            while(iter.hasNext()){
                Vendedor v = (Vendedor) iter.next();
                v.setConcesionario(c);
            }
            c.setVendedores(vendedores);
            sesion.save(c);

            transaction.commit();

        } catch (Exception e){
            System.out.println("Error"+e.getMessage());
            if(transaction!=null){
                transaction.rollback();
            }
        } finally {
            if (sesion!=null){
                sesion.close();
            }
        }

    }

    @Override
    public void informarGastosDeConcesionarios() {

        Session sesion = SessionManager.getSession();
        double gastosSueldos;
        double gastosCompraAutos;
        List<Concesionario> concesionarios = sesion.createQuery("FROM Concesionario").list();
        Iterator<Concesionario> concesIter = concesionarios.iterator();
        while(concesIter.hasNext()){
            gastosSueldos = 0;
            gastosCompraAutos = 0;
            Concesionario c = concesIter.next();
            Set<Vendedor> vendedores = c.getVendedores();
            Iterator<Vendedor> venIter = vendedores.iterator();
            while(venIter.hasNext()){
                gastosSueldos += venIter.next().getSueldo();
            }
            Set<Auto> autos = c.getAutos();
            Iterator<Auto> autoIter = autos.iterator();
            while(autoIter.hasNext()){
                gastosCompraAutos += autoIter.next().getPrecio()*0.6;
            }

            System.out.println("Total de Gastos en sueldos, "+gastosSueldos);
            System.out.println("Total de Gastos en compra de autos, "+gastosCompraAutos);
            System.out.println("Total de Gastos de la concesionario, "+(gastosSueldos+gastosCompraAutos));

        }

        sesion.close();

    }

    @Override
    public void informarFacturacionDeConcesionarios() {

        Session sesion = SessionManager.getSession();
        List<Concesionario> concesionarios = sesion.createQuery("FROM Concesionario").list();
        double facturacionTotal;
        int countAutosVend;
        Iterator<Concesionario> concesIter = concesionarios.iterator();
        while(concesIter.hasNext()){
            facturacionTotal = 0;
            countAutosVend = 0;
            Concesionario c = concesIter.next();
            Query query = sesion.createQuery("FROM Auto WHERE fecha_venta IS NOT NULL AND concesionario=:idCon");
            query.setParameter("idCon",c.getId(),new LongType());
            List<Auto> autos = query.list();
            Iterator<Auto> autoIter = autos.iterator();
            while (autoIter.hasNext()){
                Auto a = autoIter.next();
                facturacionTotal += a.getPrecio();
                countAutosVend++;
            }

            System.out.println("Concesionario: "+c.getId()+", Facturacion total: "+facturacionTotal+", Cantidad de autos vendidos: "+countAutosVend);

        }

        sesion.close();

    }

    @Override
    public void informarGananciasDeConcesionarios(int unAnio) {

        Session sesion = SessionManager.getSession();
        List<Concesionario> concesionarios = sesion.createQuery("FROM Concesionario").list();
        double gananciaTotal;
        Calendar calendar = Calendar.getInstance();
        Iterator<Concesionario> concesIter = concesionarios.iterator();
        while(concesIter.hasNext()){
            gananciaTotal = 0;
            Concesionario c = concesIter.next();
            Query query = sesion.createQuery("FROM Auto WHERE fecha_venta BETWEEN :fechaIni AND :fechaFin AND concesionario=:idCon");
            query.setParameter("idCon",c.getId(),new LongType());
            calendar.set(unAnio,0,1);
            query.setParameter("fechaIni", calendar.getTime());
            calendar.set(unAnio,11,31);
            query.setParameter("fechaFin",calendar.getTime());
            List<Auto> autos = query.list();
            Iterator<Auto> autoIter = autos.iterator();
            while (autoIter.hasNext()){
                Auto a = autoIter.next();
                gananciaTotal += a.getPrecio();
            }

            System.out.println("Ganancia total de "+c.getId()+" en el año "+unAnio+" es de un total de "+gananciaTotal);

        }

        sesion.close();

    }

    @Override
    public void actualizarSueldosDeConcesionario(Long idConcesionario, double incrementoPorcentual) {

        Session sesion = null;
        Transaction transaction = null;

        try{

            sesion = SessionManager.getSession();
            transaction = sesion.beginTransaction();

            Set<Vendedor> vendedores = sesion.get(Concesionario.class,idConcesionario).getVendedores();
            Iterator<Vendedor> vendIter = vendedores.iterator();
            while(vendIter.hasNext()){
                Vendedor v = vendIter.next();
                v.setSueldo(v.getSueldo()*incrementoPorcentual);
                sesion.save(v);
            }

            transaction.commit();

        } catch (Exception e){
            System.out.println("Error"+e.getMessage());
            if (transaction!=null){
                transaction.rollback();
            }
        } finally{
            if (sesion!=null){
                sesion.close();
            }
        }

    }

    @Override
    public void agregarCliente(Cliente c) {

        Session sesion = null;
        Transaction transaction = null;

        try {

            sesion = SessionManager.getSession();
            transaction = sesion.beginTransaction();
            sesion.save(c);
            transaction.commit();

        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
            if(transaction!=null){
                transaction.rollback();
            }
        } finally{
            if (sesion!=null){
                sesion.close();
            }
        }

    }

    @Override
    public void venderAutoDeConcesionario(Long idConcesionario, Long idAuto, Long idCliente) {

        Session sesion = null;
        Transaction transaction = null;

        try{

            sesion = SessionManager.getSession();
            transaction = sesion.beginTransaction();

            Query query = sesion.createQuery("FROM Auto WHERE id=:idauto and concesionario=:idconces and fecha_venta is null");
            query.setParameter("idauto",idAuto,new LongType());
            query.setParameter("idconces",idConcesionario,new LongType());
            Auto a = (Auto) query.uniqueResult();
            Cliente c = sesion.get(Cliente.class,idCliente);
            a.setFecha_venta(new Date(System.currentTimeMillis()));
            a.setCliente(c);
            sesion.save(a);
            transaction.commit();

        } catch(Exception e){
            System.out.println("Error" + e.getMessage());
            if (transaction!=null){
                transaction.rollback();
            }
        } finally{
            if (sesion!=null){
                sesion.close();
            }
        }

    }

    @Override
    public void informarAutosDeConcesionario(Long idConcesionario) {

        Session sesion = SessionManager.getSession();
        Set<Auto> autos = sesion.get(Concesionario.class,idConcesionario).getAutos();
        Iterator<Auto> autoIter = autos.iterator();
        while(autoIter.hasNext()){
            System.out.println(autoIter.next());
        }

        sesion.close();

    }

    @Override
    public void informarAutosDisponibles() {

        Session sesion = SessionManager.getSession();
        List<Auto> autos = sesion.createQuery("FROM Auto WHERE fecha_venta IS NULL ORDER BY precio").list();
        Iterator<Auto> autoIter = autos.iterator();
        while(autoIter.hasNext()){
            System.out.println(autoIter.next());
        }

        sesion.close();

    }

}
