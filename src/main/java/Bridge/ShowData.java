package Bridge;

import model.Shows;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;
import java.util.List;

public
class ShowData {


    public void add(Shows show, ShowBridge sb) { // without id
        sb.add(show);
    }
    private static
    SessionFactory getSessionFactory() {
        Configuration configuration = new Configuration().configure();

        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()

                .applySettings(configuration.getProperties());

        SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());

        return sessionFactory;}





    public List<Shows> retriveAll() {
        Session session = getSessionFactory().openSession();

        Transaction transaction = null;
        List<Shows> shows = null;
        try {
            transaction = session.beginTransaction();
            String hql="FROM Shows";
            Query e = session.createQuery(hql);
            shows = e.list();
            transaction.commit();

        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return shows;
    }

    public List<Shows> search(String name) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = null;
        List<Shows> shows = null;
       
        System.out.println("shows"+name);
        try {
            transaction = session.beginTransaction();
            Query q = session.createQuery("FROM Shows  WHERE nameShow = :x");
            q.setParameter("x", name);
            shows = q.list();
            
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        System.out.println("Showas:"+ shows);
        return shows;
    }
    public List<Shows> searchId(int id) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = null;
        List<Shows> shows = null;
        try {
            transaction = session.beginTransaction();
            Query q = session.createQuery("FROM Shows WHERE idShow = :x");
           
            q.setInteger("x", id);
            shows = q.list();
            System.out.println(shows.toString()+ 1);
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

            session.createQuery("DELETE FROM Shows WHERE idShow = :x")
                    .setInteger("x", id)
                    .executeUpdate();

            System.out.println("Show records deleted!");
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @SuppressWarnings("null")
	public void update(Shows show) {

        Session session = getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            session.beginTransaction();
            session.update(show);
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            //session.close();
        }
    }
}
