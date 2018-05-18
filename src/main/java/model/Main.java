package model;


import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class Main {


    public static void main(String[] args) {


        User u = new User(2, "ana", "12345","admin");       create(u);
        System.out.println(" =======READ =======");
        List<Shows> ems1 = read();

        for(Shows e: ems1) System.out.println(e.getNameShow()+ e.getTypeShow());



    }


    private static SessionFactory getSessionFactory() {
        Configuration configuration = new Configuration().configure();

        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()

                .applySettings(configuration.getProperties());

        SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());

        return sessionFactory;

    }
    public static Integer create(User u) {

        Session session = getSessionFactory().openSession();

        session.beginTransaction();

        session.save(u);

        session.getTransaction().commit();

        session.close();

        System.out.println("Successfully created " + u.getIdUser()+ u.getUserName());

        return u.getIdUser();


    }

    public static List<Shows> read() {

        Session session = getSessionFactory().openSession();


        String hql="FROM Shows ";
        Query e = session.createQuery(hql);
        List<Shows> employees= e.list();
        session.close();

        System.out.println("Found " + employees.size() + " Employees");

        return employees;



    }



}