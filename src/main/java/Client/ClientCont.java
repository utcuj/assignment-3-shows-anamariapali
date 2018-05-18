package Client;
import Server.Text;

import model.User;
import Server.ClientProtocol;
import Server.ClientServer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.net.Socket;
import java.net.UnknownHostException;

import java.util.ArrayList;
import java.util.List;


    /*
     * The controller which communicates with the user's GUI and its server
     */
// https://www.iplocation.net/find-private-ip-address
// http://www.coderpanda.com/java-socket-programming-transferring-of-java-objects-through-sockets/
    public class ClientCont  {
        //private ClientView clientView;
    	private LogInView logInView;
    	private AdminView adminView;
    	private ClientView clientView;

        private final String hostName = "192.168.23.1";///"192.168.1.2"; // "192.168.0.227"; //"10.128.55.213";
        private final int portNumber = 1342;
        private Socket kkSocket = null;
        private ObjectOutputStream out = null;
        private ObjectInputStream in = null;

        private int id_user;
       private String name = "marvel";

        private ClientServer cs;
       /*
	public Client() {
		System.out.println("Client");
		connect();
	}*/

        public
        ClientCont(LogInView login, AdminView admin,ClientView cView) {
            System.out.println("Client");
            this.logInView = login;
            this.adminView=admin;
            this.clientView=cView;
            //cs = new ClientServer(portNumber); NO bc the server is the first one which must be started
        }

        public
        void connect() {
            System.out.println("Client: try to connect");

            // connect to the server
            try {
                kkSocket = new Socket(hostName, portNumber);
                System.out.println("aici");
                out = new ObjectOutputStream(kkSocket.getOutputStream());
                System.out.println("aici out");
                in = new ObjectInputStream(kkSocket.getInputStream());
                System.out.println("Client: connected");
            } catch (UnknownHostException e) {
                System.err.println("Don't know about host " + hostName);
                System.exit(1);
            } catch (IOException e) {
                System.err.println("Client (connect): Couldn't get I/O for the connection to " + hostName);
                System.exit(1);
            }

        }

        public void btnDisplayAllClicked() {
            retrieveAll(id_user);
            System.out.println("clientcont bntDisp");
          // displayHistory();
            logInView.getCV().displayShows(displayAllShows().data);
        }
       public void btnLogInCliked() {
    	   String pass=logInView.getPass();
    	   String username=logInView.getUsern();
    	  List<Object[]> data = logIn(username,pass);
    	   String typeGet=(String)data.get(0)[0];
    	   id_user=(Integer)data.get(1)[0];
    	   System.out.println(id_user);
    	   if(typeGet.equals("admin"))
    	   {
    		   logInView.ViewAdmin();
    	   }
    	   if(typeGet.equals("premium")) {
    		   logInView.ViewClient();
    		   logInView.ViewPremium();
    		   
    	   }
          if(typeGet.equals("regular")) {
    		   logInView.ViewClient();
    	   }
    	   
    	   
    	   
    	   
       }
       public List<Object[]> logIn(String username,String pass) {

    	   List<Object[]> data = new ArrayList<Object[]>();

           if (out == null) {
               connect();
               System.out.println("login connect");
           }

           try {
               Text fromServer = null;
               Text fromUser = new Text();

               // send the command with data to server
               fromUser.methodName = "login";
               Object[] o = {username};
               Object[] p = {pass};
               fromUser.data.add(o);
               fromUser.data.add(p);

               System.out.println("Client: write to server");
               out.writeObject(fromUser);

               // get data from server
               try {
                   fromServer = (Text) in.readObject();

                   data = fromServer.data;

               } catch (ClassNotFoundException e) {
                   e.printStackTrace();
               }

           } catch (IOException e) {
               System.err.println("Client (login): Couldn't get I/O for the connection to " + hostName);
               System.exit(1);
           } finally {
        	  // closeServer();
           }

           return data;
       }


       
            
        
        public void retrieveAll(int idUser) {

            if (out == null) {
                connect();
            	System.out.println("nulll retriveall");
            }

            try {
            	 System.out.println("clientcont bntDisp");
                Text fromServer = null;
                Text fromUser = new Text();

                // send the command with data to server
                fromUser.methodName = "retriveAll";
                Object[] i = {idUser};
                fromUser.data.add(i);
                System.out.println("Client: write to server");
                out.writeObject(fromUser);

                // get data from server
                try {
                    fromServer = (Text) in.readObject();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                // {s.getId_show(), s.getName(), s.getType(), s.getDescription(), s.getRelease_date(), s.getImdb_no(), s.getImdb_s()}
                // tell and send to gui what to display from the data received from the server
                System.out.println("Client: send data to gui");
                List<Object[]> lo = new ArrayList<Object[]>();
                for(Object[] ob : fromServer.data) {
                	Object[] n={ob[0], ob[1]};
    	        	lo.add(n);
    		    	 System.out.println(ob[0] + " " + ob[1]);
    	        }
                System.out.println(lo);
               logInView.getCV().displayHistory(lo);

                //closeServer();

            } catch (IOException e) {
                System.err.println("Client (retrieveAll): Couldn't get I/O for the connection to " + hostName);
                System.exit(1);
            } finally {
                // tell the server to close
                //closeServer();
            }
        }
        
        public
        List<Object[]> search(String name) {

            List<Object[]> data = new ArrayList<Object[]>();

            if (out == null) {
                connect();
            }

            try {
                Text fromServer = null;
                Text fromUser = new Text();

                // send the command with data to server
                fromUser.methodName = "search";
                Object[] o = {name};
                fromUser.data.add(o);

                System.out.println("Client: write to server");
                out.writeObject(fromUser);

                // get data from server
                try {
                    fromServer = (Text) in.readObject();

                    data = fromServer.data;

                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

            } catch (IOException e) {
                System.err.println("Client (search): Couldn't get I/O for the connection to " + hostName);
                System.exit(1);
            } finally {
            }

            return data;
        }
        
        
        public void retrieveRec(int idUser) {

            if (out == null) {
                connect();
            	System.out.println("nulll retriveall");
            }

            try {
            	 System.out.println("recomandari");
                Text fromServer = null;
                Text fromUser = new Text();

                // send the command with data to server
                fromUser.methodName = "recomandari";
                Object[] i = {idUser};
                fromUser.data.add(i);
                System.out.println("Client: write to server");
                out.writeObject(fromUser);

                // get data from server
                try {
                    fromServer = (Text) in.readObject();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                // {s.getId_show(), s.getName(), s.getType(), s.getDescription(), s.getRelease_date(), s.getImdb_no(), s.getImdb_s()}
                // tell and send to gui what to display from the data received from the server
                System.out.println("Client: send data to gui");
                List<Object[]> lo = new ArrayList<Object[]>();
                for(Object[] ob : fromServer.data) {
                	Object[] n={ob[0], ob[1],ob[2]};
    	        	lo.add(n);
    		    	 System.out.println(ob[0] + " " + ob[1]);
    	        }
                System.out.println(lo);
               logInView.getPV().displayShows(lo);
              // logInView.getPV().displayHistory(lo);

                //closeServer();

            } catch (IOException e) {
                System.err.println("Client (retrieveAll): Couldn't get I/O for the connection to " + hostName);
                System.exit(1);
            } finally {
                // tell the server to close
                //closeServer();
            }
        }
        
       
        
        
        

        public
        void btnSearchClicked() {
            String name = logInView.getCV().getSearchData();
            List<Object[]> data = search(name);

            // {s.getId_show(), s.getName(), s.getType(), s.getDescription(), s.getRelease_date(), s.getImdb_no(), s.getImdb_s()}
            // tell and send to gui what to display from the data received from the server
            System.out.println("Client: send data to gui");
          //  List<Object[]> data1 = new ArrayList<Object[]>();
            List<Object[]> data2 = new ArrayList<Object[]>();
            float imdb = 0;
            for (Object[] ob : data) {
            	if ((Integer) ob[6] != 0) {
                   imdb = (Integer) ob[5] / (Integer) ob[6];//Float.parseFloat(ob[6]);
                }
                data2.add(new Object[]{ob[3], ob[4], imdb});
                System.out.println(ob[1] + " " + ob[2]);
            }
              
            //}
            System.out.println(data);
            //logInView.getCV().displayShows(data);
            logInView.getCV().displayDetails(data2);
        }

        public
        void RowDataSelected() {
            String name = logInView.getCV().getRowData();
            List<Object[]> data = search(name);

            // {s.getId_show(), s.getName(), s.getType(), s.getDescription(), s.getRelease_date(), s.getImdb_no(), s.getImdb_s()}
            // tell and send to gui what to display from the data received from the server
            System.out.println("Client: send data to gui");
            List<Object[]> data1 = new ArrayList<Object[]>();
            List<Object[]> data2 = new ArrayList<Object[]>();
            int imdb = 0;
            for (Object[] ob : data) {
                data1.add(new Object[]{ob[1], ob[2]});
                if ((Integer) ob[6] != 0) {
                    imdb = (Integer) ob[5] / (Integer) ob[6];//Float.parseFloat(ob[6]);
                }
                data2.add(new Object[]{ob[3], ob[4], imdb});
                System.out.println(ob[1] + " " + ob[2]);
            }

            logInView.getCV().displayShows(data1);
            logInView.getCV().displayDetails(data2);
        }

        public
        void btnAddToViewedClicked() {
            String name = logInView.getCV().getAddTextField();
            List<Object[]> data = search(name); // to get the id of the show

            int id_show = (Integer) data.get(0)[0];
            addHistory(id_show);

            displayHistory();
        }


        public
        void addHistory(int id_show) {

            if (out == null) {
                connect();
            }

            try {
                Text fromServer = null;
                Text fromUser = new Text();

                // send the command with data to server
                fromUser.methodName = "addShowToUser";
                Object[] o = {id_user};
                fromUser.data.add(o);
                fromUser.data.add(new Object[]{id_show});

                System.out.println("Client: write to server");
                out.writeObject(fromUser);

                // get data from server
                try {
                    fromServer = (Text) in.readObject();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

            } catch (IOException e) {
                System.err.println("Client (retrieveAll): Couldn't get I/O for the connection to " + hostName);
                System.exit(1);
            } finally {
            }

        }

        public
        void displayHistory() {

            if (out == null) {
                connect();
            }

            try {
                Text fromServer = null;
                Text fromUser = new Text();

                // send the command with data to server
                fromUser.methodName = "getShowsForUser";
                Object[] o = {id_user};
                fromUser.data.add(o);

                System.out.println("Client: write to server");
                out.writeObject(fromUser);

                // get data from server
                try {
                    fromServer = (Text) in.readObject();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                // {s.getId_show(), s.getName(), s.getType(), s.getDescription(), s.getRelease_date(), s.getImdb_no(), s.getImdb_s()}
                // tell and send to gui what to display from the data received from the server
                System.out.println("Client: send data to gui");
                List<Object[]> data = new ArrayList<Object[]>();
                for (Object[] ob : fromServer.data) {
                    data.add(new Object[]{ob[1]}); // the name of the show
                }

                logInView.getCV().displayHistory(data);

            } catch (IOException e) {
                System.err.println("Client (retrieveAll): Couldn't get I/O for the connection to " + hostName);
                System.exit(1);
            } finally {
            }
        }

        public void closeServer() {
            System.out.println("Client: tell server to close");
            try {
                out.writeObject(null);
                in.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Client: closed");
        }


        public void btnRateClicked() {
        	 System.out.println("rate");
            String name = logInView.getCV().getAddTextField();
            String rate = logInView.getCV().getRateTextField();
            List<Object[]> data=new ArrayList<Object[]>();
            data = search(name); // to get the id of the show
            System.out.println(data.get(0).toString());
            String imdb_no = Integer.toString((Integer) data.get(0)[6] + 1);
            String imdb_s =Integer.toString((Integer) data.get(0)[5] + Integer.parseInt(rate));
            System.out.print("aici "+imdb_no+" "+imdb_s);
            //float average = imdb_s/imdb_no;
            data.get(0)[5] = imdb_s;
            data.get(0)[6] = imdb_no;
            System.out.print("aici "+data.get(0)[5] +" "+data.get(0)[6]);
            update1(data);
            btnSearchClicked();
        }

        public void update1(List<Object[]> data) {
            if (out == null) {
                connect();
            }

            try {
                Text fromServer = null;
                Text fromUser = new Text();

                // send the command with data to server
                fromUser.methodName = "updateShow1";
                fromUser.data = data;

                System.out.println("Client: write to server");
                out.writeObject(fromUser);

                // get data from server
                try {
                    fromServer = (Text) in.readObject();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

            } catch (IOException e) {
                System.err.println("Client (update): Couldn't get I/O for the connection to " + hostName);
                System.exit(1);
            } finally {
            }

        }
        public void update(List<Object[]> data) {
            if (out == null) {
                connect();
            }

            try {
                Text fromServer = null;
                Text fromUser = new Text();

                // send the command with data to server
                fromUser.methodName = "updateShow";
                fromUser.data = data;

                System.out.println("Client: write to server");
                out.writeObject(fromUser);

                // get data from server
                try {
                    fromServer = (Text) in.readObject();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

            } catch (IOException e) {
                System.err.println("Client (update): Couldn't get I/O for the connection to " + hostName);
                System.exit(1);
            } finally {
            }

        }
        
        public void addUser() {

            if (out == null) {
                connect();
            }

            try {
                Text fromServer = null;
                Text fromUser = new Text();
                int id=Integer.parseInt(logInView.getAV().getIdUser());
                String type=logInView.getAV().getType();
                String pass=logInView.getAV().getPassword();
                String username=logInView.getAV().getUserName();
                // send the command with data to server
                fromUser.methodName = "addUser";
                Object[] o0 = {id};
                Object[] o = {username};
                Object[] o1 = {pass};
                Object[] o2 = {type};
                fromUser.data.add(o0);
                fromUser.data.add(o);
                fromUser.data.add(o1);
                fromUser.data.add(o2);
               // fromUser.data.add(new Object[]{id_show});

                System.out.println("Client: write to server");
                out.writeObject(fromUser);

                // get data from server
                try {
                    fromServer = (Text) in.readObject();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

            } catch (IOException e) {
                System.err.println("Client (retrieveAll): Couldn't get I/O for the connection to " + hostName);
                System.exit(1);
            } finally {
            }

        }
        public void addRecomandare() {

            if (out == null) {
                connect();
            }

            try {
                Text fromServer = null;
                Text fromUser = new Text();
                int id=Integer.parseInt(logInView.getPV().getIdUser());
                int idS=Integer.parseInt(logInView.getPV().getIdShow());
                fromUser.methodName = "recomanda";
                Object[] o0 = {id_user,id,idS};
                Object[] o = {id};
                Object[] o1 = {idS};
                //fromUser.data.add(o0);
                //fromUser.data.add(o);
                fromUser.data.add(o0);
              
               // fromUser.data.add(new Object[]{id_show});

                System.out.println("Client: write to server");
                out.writeObject(fromUser);

                // get data from server
                try {
                    fromServer = (Text) in.readObject();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

            } catch (IOException e) {
                System.err.println("Client (add recomandare): Couldn't get I/O for the connection to " + hostName);
                System.exit(1);
            } finally {
            }

        }
        
        void deleteUser() {

            if (out == null) {
                connect();
            }

            try {
                Text fromServer = null;
                Text fromUser = new Text();
                
                int id=Integer.parseInt(logInView.getAV().getIdUser());
                
                // send the command with data to server
                fromUser.methodName = "deleteUser";
                Object[] o = {id};
                
                fromUser.data.add(o);
                System.out.println("Client: write to server");
                out.writeObject(fromUser);
                try {
                    fromServer = (Text) in.readObject();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

            } catch (IOException e) {
                System.err.println("Client (deleteUser): Couldn't get I/O for the connection to " + hostName);
                System.exit(1);
            } finally {
            }

        }
        
        
        void deleteShows() {

            if (out == null) {
                connect();
            }

            try {
                Text fromServer = null;
                Text fromUser = new Text();
                
                int id=Integer.parseInt(logInView.getAV().getIdShow());
                
                // send the command with data to server
                fromUser.methodName = "deleteShow";
                Object[] o = {id};
                
                fromUser.data.add(o);
                System.out.println("Client: write to server");
                out.writeObject(fromUser);
                try {
                    fromServer = (Text) in.readObject();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

            } catch (IOException e) {
                System.err.println("Client (deleteUser): Couldn't get I/O for the connection to " + hostName);
                System.exit(1);
            } finally {
            }

        }
        
        
        
        
        
        
        public static void main(String[] args) {

    		//ClientView cv = new ClientView();
        	LogInView lv=new LogInView();
    		lv.start();
    		
    		/*
    		Client c = new Client();
    		c.retrieveAll();
    		System.out.println("SECOND!!!!!!!!");
    		c.retrieveAll();
    		
    		c.closeServer();*/
    		
    	}

		public void btnDeleteUserClicked() {
			// TODO Auto-generated method stub
			deleteUser();
		}

		public void btnAddUserClicked() {
			// TODO Auto-generated method stub
			addUser();
			
		}
		
	      public void updateUser(List<Object[]> data) {
	            if (out == null) {
	                connect();
	            }

	            try {
	                Text fromServer = null;
	                Text fromUser = new Text();

	                // send the command with data to server
	                fromUser.methodName = "updateUser";
	                fromUser.data = data;

	                System.out.println("Client: write to server");
	                out.writeObject(fromUser);

	                // get data from server
	                try {
	                    fromServer = (Text) in.readObject();
	                } catch (ClassNotFoundException e) {
	                    e.printStackTrace();
	                }

	            } catch (IOException e) {
	                System.err.println("Client (updateUser): Couldn't get I/O for the connection to " + hostName);
	                System.exit(1);
	            } finally {
	            }

	        }
	      
	      
	    
	      
	      

		public void btnUpDateUserClicked() {
			// TODO Auto-generated method stub
			int id=Integer.parseInt(logInView.getAV().getIdUser());
			String type=logInView.getAV().getType();
            String pass=logInView.getAV().getPassword();
            String username=logInView.getAV().getUserName();
            User u=new User(id,type,pass,username);
            List<Object[]> data=new ArrayList<Object[]>();
            Object[] o = {id};
            Object[] o1 = {username};
            Object[] o2 = {pass};
            Object[] o3 = {type};
            data.add(o);data.add(o1);data.add(o2);data.add(o3);
            updateUser(data);
		}
		public Text displayAllUsers() {
			if (out == null) {
				connect();
			}
			Text fromServer = null;
	        try {
	             fromServer = null;
	            Text fromUser = new Text();
	 
	            // send the command with data to server
	            fromUser.methodName = "allUsers"; 
	            System.out.println("Admin: write to server all user");
	            out.writeObject(fromUser); 
	            
	            // get data from server
	            try {
					fromServer = (Text) in.readObject();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
	            
	            // {s.getId_show(), s.getName(), s.getType(), s.getDescription(), s.getRelease_date(), s.getImdb_no(), s.getImdb_s()}
	            // tell and send to gui what to display from the data received from the server
	            System.out.println("Admin: send data to gui");        
		        //logInView.getAV().displayShows(fromServer.data);

				//closeServer();

	        } catch (IOException e) {
	            System.err.println("Client (retrieveAll): Couldn't get I/O for the connection to " + hostName);
	            System.exit(1);
	        } finally {
	        	// tell the server to close
	        	//closeServer();
	        }
	        return fromServer;
		}

		public Text displayAllShows() {
			Text fromServer=null;
			if (out == null) {
				connect();
			}
			
	        try {
	             fromServer = null;
	            Text fromUser = new Text();
	 
	            // send the command with data to server
	            fromUser.methodName = "allShows"; 
	            System.out.println("Admin: write to server all shows");
	            out.writeObject(fromUser); 
	            
	            // get data from server
	            try {
					fromServer = (Text) in.readObject();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
	            
	            // {s.getId_show(), s.getName(), s.getType(), s.getDescription(), s.getRelease_date(), s.getImdb_no(), s.getImdb_s()}
	            // tell and send to gui what to display from the data received from the server
	            System.out.println("Admin: send data to gui");        
		       // logInView.getAV().displayShows(fromServer.data);

				//closeServer();

	        } catch (IOException e) {
	            System.err.println("Client (retrieveAll): Couldn't get I/O for the connection to " + hostName);
	            System.exit(1);
	        } finally {
	        	// tell the server to close
	        	//closeServer();
	        }
	        return fromServer;
		}
		 public void addShow() {

	            if (out == null) {
	                connect();
	            }

	            try {
	                Text fromServer = null;
	                Text fromUser = new Text();
	                
	                int id=Integer.parseInt(logInView.getAV().getIdShow());
	                String type=logInView.getAV().getTypeS();
	                String name=logInView.getAV().getNameShow();
	                String detalii=logInView.getAV().getDetalii();
	                String data=logInView.getAV().getDate();
	                String rate=logInView.getAV().getRate();
	                String no=logInView.getAV().getNo();
	                // send the command with data to server
	                fromUser.methodName = "addShow";
	                Object[] o0 = {id,name,type,detalii,data,rate,no};
	                
	                fromUser.data.add(o0);
	                
	               // fromUser.data.add(new Object[]{id_show});

	                System.out.println("Client: write to server");
	                out.writeObject(fromUser);

	                // get data from server
	                try {
	                    fromServer = (Text) in.readObject();
	                } catch (ClassNotFoundException e) {
	                    e.printStackTrace();
	                }

	            } catch (IOException e) {
	                System.err.println("Client (retrieveAllShows): Couldn't get I/O for the connection to " + hostName);
	                System.exit(1);
	            } finally {
	            }

	        }
		
		
		
		
		public void btnAddShowClicked() {
			// TODO Auto-generated method stub
			addShow();
			
		}

		public void btnUpDateShowClicked() {
			// TODO Auto-generated method stub
			int id=Integer.parseInt(logInView.getAV().getIdShow());
            String type=logInView.getAV().getTypeS();
            String name=logInView.getAV().getNameShow();
            String detalii=logInView.getAV().getDetalii();
            String date=logInView.getAV().getDate();
            String rate=logInView.getAV().getRate();
            String no=logInView.getAV().getNo();
            // send the command with data to server
            Object[] o0 = {id,name,type,detalii,date,rate,no};
            Object[] o1 = {name};
            Object[] o2 = {type};
            Object[] o3 = {detalii};
            Object[] o4 = {date};
            Object[] o5 = {rate};
            Object[] o6 = {no};
            List<Object[]> data=new ArrayList<Object[]>();
            data.add(o0);
            update(data);
			
		}

		public void btnDeleteShowClicked() {
			// TODO Auto-generated method stub
			deleteShows();
			
		}

		public void btnUpListShowClicked() {
			// TODO Auto-generated method stub
			logInView.getAV().displayShows(displayAllShows().data);
			//displayAllShows();
		}

		public void btnListUsersClicked() {
			// TODO Auto-generated method stub
			//displayAllUsers();
			logInView.getAV().displayShows(displayAllUsers().data);
		}

		public void btnRecomandariClicked() {
			// TODO Auto-generated method stub
			retrieveRec(id_user);
			 List<Object[]> lo = new ArrayList<Object[]>();
	          for(Object[] ob : displayAllUsers().data) {
	            String type=(String) ob[1];	 
	            System.out.print(type+" ");
	             if(type.equals("premium"))
	               {Object[] n={ob[0], ob[1],ob[2]};
	 	        	lo.add(n);
	               }
	          }
	          logInView.getPV().displayHistory(lo);
			
		}

		public void btnInterestedClicked() {
			// TODO Auto-generated method stub
			
		}

		public void btnRecomandClicked() {
			// TODO Auto-generated method stub
			addRecomandare();
			
		}

		public void btnComentClicked() {
			// TODO Auto-generated method stub
		
	            
	            
	            if (out == null) {
	                connect();
	            }

	            try {
	                Text fromServer = null;
	                Text fromUser = new Text();
	                String name = logInView.getCV().getSearchData();
		            List<Object[]> data = search(name);
		            String coment=logInView.getCV().getComentTextField();
	                //int i=(Integer) data.get(0);
	             
	                // send the command with data to server
	                fromUser.methodName = "addComent";
	                Object[] o0 = {data.get(0)[0],id_user,coment,};
	                
	                fromUser.data.add(o0);
	                
	               // fromUser.data.add(new Object[]{id_show});

	                System.out.println("Client: write to server");
	                out.writeObject(fromUser);

	                // get data from server
	                try {
	                    fromServer = (Text) in.readObject();
	                } catch (ClassNotFoundException e) {
	                    e.printStackTrace();
	                }

	            } catch (IOException e) {
	                System.err.println("Client (add coment): Couldn't get I/O for the connection to " + hostName);
	                System.exit(1);
	            } finally {
	            }

	        }

	
			
		}
   
