package model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import Bridge.ShowData;

public class RecomandariData {
	
	 private static
	    SessionFactory getSessionFactory() {
	        Configuration configuration = new Configuration().configure();

	        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()

	                .applySettings(configuration.getProperties());

	        SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());

	        return sessionFactory;}
	 
	 
	 
	    public static void create(Recomandari u) {

	        Session session = getSessionFactory().openSession();

	        session.beginTransaction();

	        session.save(u);

	        session.getTransaction().commit();

	        session.close();

	        System.out.println("Successfully created " + u.getIdUser()+ u.getIdUserTo());

	        


	    }
	 
	 
	 
	
    public List<Shows> retriveAll(int id) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = null;
        ShowData showData= new ShowData();
        List<Shows> shows=null;
        List<Shows> showsF = new ArrayList<Shows>();
        List<Recomandari> rec = null;
        try {
            transaction = session.beginTransaction();
            Query q = session.createQuery("FROM Recomandari WHERE idUserTo = :x");
           
            q.setInteger("x", id);
            rec = q.list();
            System.out.println(rec.toString());
            transaction.commit();
            for(Recomandari i:rec) {
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

}
