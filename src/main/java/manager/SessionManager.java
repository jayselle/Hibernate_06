package manager;

import entities.*;
import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.util.Properties;

public class SessionManager {

    private static SessionFactory factory;

    public SessionManager() {
    }

    public static Session getSession() throws HibernateException {

        if (factory!=null){

            return factory.openSession();

        } else {

            // Instancia un objeto del tipo Configuration
            Configuration config = new Configuration();

            // Registra los mappers en la configuracion
            registerMappers(config);

            // Establece las propiedades de configuracion
            config.setProperties(getHibernateProperties());

            // Guarda la fabrica de sesiones
            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(config.getProperties());
            factory = config.buildSessionFactory(builder.build());

            // Retorna una sesion de trabajo
            return factory.openSession();

        }

    }

    private static Properties getHibernateProperties() {

        // Instancia un objeto del tipo Properties
        Properties props = new Properties();

        // Establece el driver de conexion dependiente del RDBMS
        props.put("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");

        // Establece la url de conexion dependiente del RDBMS
        props.put("hibernate.connection.url", "jdbc:mysql://localhost:3306/hibernateproy");

        // Establece el usuario
        props.put("hibernate.connection.username", "root");

        // Establece la clave
        props.put("hibernate.connection.password", "");

        // Establece el dialecto a utilizar
        props.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");

        // Establece el uso de logging, deberá existir el archivo log4j.properties
        props.put("hibernate.show_sql", "true");

        // hbm2ddl.auto -> validate/create/update/create-drop
        props.put("hibernate.hbm2ddl.auto","update");

        // Retorna las propiedades
        return props;

    }

    private static void registerMappers(Configuration config) throws MappingException {
        config.addAnnotatedClass(Persona.class);
        config.addAnnotatedClass(Cliente.class);
        config.addAnnotatedClass(Vendedor.class);
        config.addAnnotatedClass(Auto.class);
        config.addAnnotatedClass(Concesionario.class);
    }

}
