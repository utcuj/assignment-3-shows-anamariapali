package model;

import org.hibernate.*;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import Bridge.ShowData;

import java.util.ArrayList;
import java.util.List;

public
class IstoricData {
   // private List<Shows> showsF;



	private static
    SessionFactory getSessionFactory() {
        Configuration configuration = new Configuration().configure();

        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()

                .applySettings(configuration.getProperties());

        SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());

        return sessionFactory;}

    public static Integer create(Istoric i) {

        Session session = getSessionFactory().openSession();

        session.beginTransaction();

        session.save(i);

        session.getTransaction().commit();

        session.close();

        System.out.println("Successfully created " + i.getIdIstoric());

        return i.getIdIstoric();


    }



    @SuppressWarnings("null")
	public List<Shows> retriveAll(int id) {
        Session session = getSessionFactory().openSession();
        ShowData showData= new ShowData();
        Transaction transaction = null;
        List<Istoric> istoric = null;
        List<Shows> shows=null;
        List<Shows> showsF = new ArrayList<Shows>();
        System.out.println("id1="+id);
        try {
            transaction = session.beginTransaction();
            Query q = session.createQuery("From Istoric WHERE idUser = :x");
             q.setInteger("x", id);
             System.out.println("id4="+id);
             istoric= q.list();
             System.out.println(istoric.toString()+2);
             transaction.commit();
             for(Istoric i:istoric) {
            	 //System.out.println(x);
            	 shows=showData.searchId(i.getIdShow());
            	 System.out.println("ana"+shows.toString()+"  "+shows.size());
            	 showsF.addAll(shows);
             }
             
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return showsF;
    }

    public List<Istoric> search(int name) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = null;
        List<Istoric> shows = null;
        try {
            transaction = session.beginTransaction();
            Query q = session.createQuery("from Istoric where idUser = :x");
            q.setParameter("x", name);
            shows = q.list();
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

            session.createQuery("delete from Istoric where idIstoric = :x")
                    .setParameter("x", id)
                    .executeUpdate();

            System.out.println("Istoric records deleted!");
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void update(Istoric istoric) {

        Session session = getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            session.beginTransaction();
            session.update(istoric);
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
