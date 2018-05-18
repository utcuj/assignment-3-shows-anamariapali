package model;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public
class ComentariData {
	  private static
	    SessionFactory getSessionFactory() {
	        Configuration configuration = new Configuration().configure();

	        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()

	                .applySettings(configuration.getProperties());

	        SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());

	        return sessionFactory;}


	   public static Integer create(Comentarii c) {

	        Session session = getSessionFactory().openSession();

	        session.beginTransaction();

	        session.save(c);

	        session.getTransaction().commit();

	        session.close();

	        System.out.println("Successfully created " + c.getIdUser()+ c.getIdShow());

	        return c.getIdComentariu();


	    }


	    public
	    List<Shows> retriveAll() {
	        Session session = getSessionFactory().openSession();

	        Transaction transaction = null;
	        List<Shows> shows = null;
	        try {
	            transaction = session.beginTransaction();
	            shows = session.createQuery("From Istoric").list();
	            transaction.commit();
	        } catch (HibernateException e) {
	            transaction.rollback();
	            e.printStackTrace();
	        } finally {
	            session.close();
	        }
	        return shows;
	    }

	  

	    ////
	    public void delete(int id) {
	        Session session = getSessionFactory().openSession();
	        Transaction transaction = null;
	        try {
	            transaction = session.beginTransaction();

	            session.createQuery("delete from Comentarii where idShow = :x")
	                    .setParameter("x", id)
	                    .executeUpdate();

	            System.out.println("Comentariu deleted!");
	        } catch (HibernateException e) {
	            transaction.rollback();
	            e.printStackTrace();
	        } finally {
	            session.close();
	        }
	    }

	    public void update(Comentarii comentarii) {

	        Session session = getSessionFactory().openSession();
	        Transaction transaction = null;
	        try {
	            session.beginTransaction();
	            session.update(comentarii);
	            session.getTransaction().commit();
	            session.close();
	        } catch (HibernateException e) {
	            transaction.rollback();
	            e.printStackTrace();
	        } finally {
	            session.close();
	        }
	    
}
}
