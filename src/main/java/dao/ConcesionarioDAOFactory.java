package dao;

public class ConcesionarioDAOFactory {

    public static ConcesionarioDAO createConcesionarioDAO(){
        return new ConcesionarioDAOImpl();
    }

}
