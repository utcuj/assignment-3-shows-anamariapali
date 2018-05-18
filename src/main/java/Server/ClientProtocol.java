package Server;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


import Bridge.Movie;
import Bridge.ShowBridge;
import Bridge.ShowData;
import Bridge.SportEvent;
import Bridge.Theatre;
import model.Shows;
import model.User;
import model.UserData;
import model.ComentariData;
import model.Comentarii;
import model.Istoric;
import model.IstoricData;
import model.Recomandari;
import model.RecomandariData;


public
class ClientProtocol  {
    private ShowData showData = new ShowData();
    private UserData userData = new UserData();
    private IstoricData istoricData = new IstoricData();
    private RecomandariData recomandariData = new RecomandariData();
   
    private ComentariData comentariData=new ComentariData();
    /*
     * theInput = Object[ Object[Object] ]
     */
    public  Text processInput(Text theInput) {
        Text theOutput = new Text();
        int id_user;
        int id_show;

        String methodName = (String) theInput.methodName;

        if ("retriveAll".equals(methodName)) {
        	int id=(Integer)theInput.data.get(0)[0];
            theOutput.data = retrieveAll(id);
        } else if ("search".equals(methodName)) {
            String name = (String) theInput.data.get(0)[0];
            theOutput.data = search(name);

        } else if ("addShowToUser".equals(methodName)) {
            id_user = (Integer) theInput.data.get(0)[0];
            id_show = (Integer) theInput.data.get(1)[0];
            addShowToUser(id_user, id_show);

        } else if ("getShowsForUser".equals(methodName)) {
            id_user = (Integer) theInput.data.get(0)[0];
            theOutput.data = getShowsForUser(id_user);

        } else if ("updateShow1".equals(methodName)) {
            updateShow1(theInput.data.get(0));
            theOutput = null;
        } else if ("login".equals(methodName)) {
           String username=(String) theInput.data.get(0)[0];
           String pass=(String) theInput.data.get(1)[0];
            theOutput.data = logIn(username,pass);
        } else if ("addUser".equals(methodName)) {
        	int id=(Integer) theInput.data.get(0)[0];
            String usernameAdd=(String) theInput.data.get(1)[0];
            String passAdd=(String) theInput.data.get(2)[0];
            String typeAdd=(String)theInput.data.get(3)[0];
             theOutput.data = AddUser(id,usernameAdd,passAdd,typeAdd);
         }else if ("updateUser".equals(methodName)) {
             updateUser(theInput.data);
             theOutput = null;}
         else if ("deleteUser".equals(methodName)) {
             deleteUser(theInput.data.get(0));
             theOutput = null;}
         else if ("addShow".equals(methodName)) {
         	addShow(theInput.data.get(0));
          }
          else if ("deleteShow".equals(methodName)) {
              deleteShow(theInput.data.get(0));
              theOutput = null;}
         else if ("updateShow".equals(methodName)) {
        	 updateShow(theInput.data.get(0));
        	 theOutput = null;}
         else if ("allUsers".equals(methodName)) {
        	 theOutput.data = retrieveAllUser();}
         else if ("allShows".equals(methodName)) {
        	 theOutput.data = retrieveAllShows();}
         else if ("recomandari".equals(methodName)) {
        	int id=(Integer)theInput.data.get(0)[0];
            theOutput.data = retrieveAllRec(id);}
         else if ("recomanda".equals(methodName)) {
        	 addRecomandare(theInput.data.get(0));
         }else if ("addComent".equals(methodName)) {
          	addComent(theInput.data.get(0));
           }
        else {
            theOutput = null;
        }

        return theOutput;
    }

    private List<Object[]> retrieveAll(int id) {
    	System.out.println("id="+id);
    	List<Shows> istoric = istoricData.retriveAll(id);
        List<Object[]> data = new ArrayList<Object[]>();
        System.out.println("Protocol: retrieveAll ");
        for(Shows s: istoric) {
            Object[] o = {s.getNameShow(),s.getTypeShow()};
            data.add(o);
            System.out.println(s.getIdShow());
        }
        return data;
    }
    
    private List<Object[]> retrieveAllRec(int id) {
    	System.out.println("id="+id);
    	List<Shows> istoric = recomandariData.retriveAll(id);
        List<Object[]> data = new ArrayList<Object[]>();
        System.out.println("Protocol: retrieveAll ");
        for(Shows s: istoric) {
            Object[] o = {s.getNameShow(),s.getTypeShow(),s.getDetali()};
            data.add(o);
            System.out.println(s.getIdShow());
        }
        return data;
    }
    
    
    
    private List<Object[]> AddUser(int id,String username,String password,String type) {
    	User u=new User(id,username,password,type);
    	int id1= userData.create(u);
        List<Object[]> data = new ArrayList<Object[]>();
        Object[] o= {id};
        data.add(o);
        return data;
    }

    private void addRecomandare(Object[] data) {
    	int idUser = (Integer) data[0];
    	int idUserTo = (Integer) data[1];
    	int idShow = (Integer) data[2];
        Recomandari r=new Recomandari(idUser,idUserTo,idShow);
    	recomandariData.create(r);
        
    }
    
    
    private List<Object[]> search(String name) {
        List<Shows> shows = showData.search(name);
        List<Object[]> data = new ArrayList<Object[]>();
        System.out.println("Protocol: search ");
        for(Shows s: shows) {
            Object[] o =  {s.getIdShow(), s.getNameShow(), s.getTypeShow(), s.getDetali(),s.getReleaseDate(),s.getRaiting(),s.getNo()};
            data.add(o);
            System.out.println(s.getNameShow());
        }
        return data;
    }

    private void addShowToUser(int id_user, int id_show) {
       //* historyHibernate.
    	Istoric i=new Istoric(id_user,id_show);
    	int id1= istoricData.create(i);
        
    }

    private List<Object[]> getShowsForUser(int id_user) {
        List<Shows> shows = showData.retriveAll();
        List<Object[]> data = new ArrayList<Object[]>();
        System.out.println("Protocol: getShowsForUser ");
        for(Shows s: shows) {
            Object[] o = {s.getIdShow(), s.getNameShow(), s.getTypeShow(), s.getDetali()};
            data.add(o);
            System.out.println(s.getNameShow());
        }
        return data;
      
    }

    private List<Object[]> retrieveAllShows() {
    	List<Shows> shows = showData.retriveAll();
    	List<Object[]> data = new ArrayList<Object[]>();
    	System.out.println("Protocol: retrieveAllShows ");
    	for(Shows s: shows) {
    		Object[] o = {s.getIdShow(), s.getNameShow(),s.getTypeShow(),s.getDetali() ,s.getReleaseDate(), s.getRaiting(), s.getNo()};
    		data.add(o);
    		System.out.println(s.getNameShow());
    	}
    	return data;
    }
    
    
    private List<Object[]> retrieveAllUser() {
    	List<User> users = userData.retriveAll();
    	List<Object[]> data = new ArrayList<Object[]>();
    	System.out.println("Protocol: retrieveAllUser ");
    	for(User u : users) {
    		Object[] o = {u.getIdUser(),u.getTypeUser(),u.getUserName(),u.getPassword()};
    		data.add(o);
    		System.out.println(u.getUserName());
    	}
    	return data;
    }
    
    
    
    
    private void updateShow1(Object[] data) {
    	System.out.print("Admin Protocol: updateShow");
    	int id = (Integer) data[0];
    	String name = (String) data[1];
		String description = (String) data[2];
		String type = (String) data[3];
		 Date data1=(Date)data[4];
		/*java.sql.Date sqlDate = null;
    	try {
			java.util.Date utilDate = new SimpleDateFormat("yyyy-MM-dd").parse((String)data[4]);
			sqlDate = new java.sql.Date(utilDate.getTime()); 
    	} catch (ParseException e) {
			e.printStackTrace();
		}*/
   
		int imdb_no = Integer.parseInt((String) data[6]);
		int imdb_s = Integer.parseInt((String) data[5]);
		
    	Shows show = new Shows(id, name, type, description, data1, imdb_s, imdb_no);

    	showData.update(show);
    }
    
    
    private void updateShow(Object[] data) {
    	System.out.print("Admin Protocol: updateShow");
    	int id = (Integer) data[0];
    	String name = (String) data[1];
		String description = (String) data[2];
		String type = (String) data[3];
		 // Date data1=(Date)data[4];
		java.sql.Date sqlDate = null;
    	try {
			java.util.Date utilDate = new SimpleDateFormat("yyyy-MM-dd").parse((String)data[4]);
			sqlDate = new java.sql.Date(utilDate.getTime()); 
    	} catch (ParseException e) {
			e.printStackTrace();
		}
   
		int imdb_no = Integer.parseInt((String) data[6]);
		int imdb_s = Integer.parseInt((String) data[5]);
		
    	Shows show = new Shows(id, name, type, description, sqlDate, imdb_s, imdb_no);

    	showData.update(show);
    }
    private void updateUser(List<Object[]>  data) {
        System.out.print("Protocol: updateUser");
        int id = (Integer) data.get(0)[0];
        String name = (String) data.get(1)[0];
        String password = (String) data.get(2)[0];
        String type = (String) data.get(3)[0];
        User u = new User(id,name,password,type);

        userData.update(u);
    }
    private void deleteUser(Object[] data) {
        System.out.print("Protocol: deleteUser");
        int id = (Integer) data[0];
       
      System.out.print("id"+id);
        userData.delete(id);
    }
    
    
    private void deleteShow(Object[] data) {
        System.out.print("Protocol: deleteShow");
        int id = (Integer) data[0];
       

        showData.delete(id);
    }
    private List<Object[]> logIn(String username, String pass) {
        
          List<Object[]> data = new ArrayList<Object[]>();
          System.out.println("Protocol: login ");
          String type=userData.Login(username, pass);
          int id=userData.Id(username, pass);
          Object[] o= {type};
          Object[] o1= {id};
          //System.out.println(type);
          data.add(o);
          data.add(o1);
          
          return data;
        
}
    private void addShow(Object[] data) {
    	System.out.print("Admin Protocol: addShow");
    	int id = (Integer) data[0];
    	String name = (String) data[1];
		String description = (String) data[2];
		String type = (String) data[3];
		java.sql.Date sqlDate = null;
    	try {
			java.util.Date utilDate = new SimpleDateFormat("yyyy-MM-dd").parse((String)data[4]);
			sqlDate = new java.sql.Date(utilDate.getTime()); 
    	} catch (ParseException e) {
			e.printStackTrace();
		}

		int imdb_no = Integer.parseInt((String) data[6]);
		int imdb_s = Integer.parseInt((String) data[5]);
		
    	Shows show = new Shows(id, name, type, description, sqlDate, imdb_s, imdb_no);
    	
    	ShowBridge sb;
    	if (type.equals("movie")) {
    		 sb = new ShowBridge(new Movie());
    	} else if (type.equals("sport event")) {
   		 sb = new ShowBridge(new SportEvent());
    	} else sb = new ShowBridge(new Theatre());
		
    	showData.add(show, sb);
    }


private void addComent(Object[] data) {
	System.out.print("Admin Protocol: addcoment");
	int idU = (Integer) data[1];
	int  idS = (Integer) data[0];
	String coment = (String) data[2];
	
	Comentarii c= new Comentarii(idU,idS,coment);
	int i=comentariData.create(c);
}


	
	
	
	

}