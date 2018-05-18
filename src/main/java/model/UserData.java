package model;

import org.hibernate.*;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.util.List;

public
class UserData {
    private static
    SessionFactory getSessionFactory() {
        Configuration configuration = new Configuration().configure();

        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()

                .applySettings(configuration.getProperties());

        SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());

        return sessionFactory;}

    public static Integer create(User u) {

        Session session = getSessionFactory().openSession();

        session.beginTransaction();

        session.save(u);

        session.getTransaction().commit();

        session.close();

        System.out.println("Successfully created " + u.getIdUser()+ u.getUserName());

        return u.getIdUser();


    }




    public
    List<User> retriveAll() {
        Session session = getSessionFactory().openSession();

        Transaction transaction = null;
        List<User> users = null;
        try {
            transaction = session.beginTransaction();
            users = session.createQuery("FROM User").list();
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return users;
    }

    public List<User> search(String name) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = null;
        List<User> user = null;
        try {
            transaction = session.beginTransaction();
            Query q = session.createQuery("from User where username = :x");
            q.setParameter("x", name);
            user = q.list();
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return user;
    }
    
    public String Login(String username,String password) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = null;
        List<User> user = null;
        String type=" ";
        try {
            transaction = session.beginTransaction();
            Query q = session.createQuery("FROM User  WHERE userName = :x");
            q.setParameter("x", username);
            user = q.list();
            for(User u: user)
            	if(u.getPassword().equals(password))
            		type=u.getTypeUser();
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
       return type;
    }
    public int Id(String username,String password) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = null;
        List<User> user = null;
        int id=0;
        try {
            transaction = session.beginTransaction();
            Query q = session.createQuery("FROM User  WHERE userName = :x");
            q.setParameter("x", username);
            user = q.list();
            for(User u: user)
            	if(u.getPassword().equals(password))
            	id=u.getIdUser();
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
       return id;
    }


    ////
    public void delete(int id) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();

            session.createQuery("DELETE FROM User where idUser = :x")
                    .setInteger("x", id)
                    .executeUpdate();

            System.out.println("Admin records deleted!");
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @SuppressWarnings("null")
	public void update(User user) {

        Session session = getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            session.beginTransaction();
            session.update(user);
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
          //  session.close();
        }
    }
    
    
    
    
    
}
