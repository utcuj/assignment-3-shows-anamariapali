package Client;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;



public class AdminView {
	private JFrame frame;
	private JTextField idUser;
	private JTextField username;
	private JTextField password;
	private JTextField tip;
	private JTextField idShow;
	private JTextField nameShow;
	private JTextField detali;
	private JTextField type;
	private JTextField rate;
	private JTextField date;
	private JTextField no;
	private DefaultTableModel tableModel;
    private String col_table[]= {"id","Name", "Type","username"};
    private JTable table_1;
    private JScrollPane scrollPane;
	private Client.ClientCont client;
	public JFrame getAFrame()
	{
		return this.frame;
	}
	/**
	 * Create the application.
	 */
	public AdminView(ClientCont client) {  
    	System.out.println("View Admin");
        this.client = client;
		initialize();
	}
	 public void start() {
	        EventQueue.invokeLater(new Runnable() {
	            public void run() {
	                try { System.out.println("Client View run");
	                    AdminView window = new AdminView(client);
	                    window.frame.setVisible(true);

	                } catch (Exception e) {
	                    e.printStackTrace();
	                }
	            }
	        });
	    }
	/**
	 * 
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 700, 700);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	        frame.addWindowListener(new java.awt.event.WindowAdapter() {
	            @Override
	            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
	                if (JOptionPane.showConfirmDialog(frame,
	                        "Are you sure to close this window?", "Really Closing?",
	                        JOptionPane.YES_NO_OPTION,
	                        JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){

	                    client.closeServer();
	                    System.exit(0);
	                }
	            }
	        });
		frame.getContentPane().setLayout(null);
		
		 table_1 = new JTable();
	        table_1.setBounds(45, 480, 300, 250);
	        frame.getContentPane().add(table_1);
	        scrollPane = new JScrollPane(table_1);
		
	        scrollPane.setBounds(45, 480, 300, 100);
	        frame.getContentPane().add(scrollPane);
	        tableModel = new DefaultTableModel(col_table, 0);
	        
		JButton btnAddUser = new JButton("Add user");
		btnAddUser.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                client.btnAddUserClicked();
            }
        });
		btnAddUser.setBounds(45, 229, 110, 25);
		frame.getContentPane().add(btnAddUser);
		
		JButton btnDeleteUser = new JButton("Delete user");
		btnDeleteUser.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                client.btnDeleteUserClicked();
            }
        });
		btnDeleteUser.setBounds(45, 279, 110, 25);
		frame.getContentPane().add(btnDeleteUser);
		
		JButton btnUpdateUser = new JButton("Update user");
		btnUpdateUser.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                client.btnUpDateUserClicked();
            }
        });
		btnUpdateUser.setBounds(45, 329, 110, 25);
		frame.getContentPane().add(btnUpdateUser);
		
		JButton btnListUsers = new JButton("List users");
		btnListUsers.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                client.btnListUsersClicked();
            }
        });
		btnListUsers.setBounds(45, 379, 110, 25);
		frame.getContentPane().add(btnListUsers);
		/////show/////
		JButton btnAddShow = new JButton("Add Show");
		btnAddShow.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                client.btnAddShowClicked();
            }
        });
		btnAddShow.setBounds(202, 279, 110, 25);
		frame.getContentPane().add(btnAddShow);
		
		JButton btnDeleteShow = new JButton("Delete Show");
		btnDeleteShow.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                client.btnDeleteShowClicked();
            }
        });
		btnDeleteShow.setBounds(202, 329, 110, 25);
		frame.getContentPane().add(btnDeleteShow);
		
		JButton btnUpdateShow = new JButton("Update Show");
		btnUpdateShow.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                client.btnUpDateShowClicked();
            }
        });
		btnUpdateShow.setBounds(202, 379, 110, 25);
		frame.getContentPane().add(btnUpdateShow);
		
		JButton btnListShow = new JButton("List Show");
		btnListShow.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                client.btnUpListShowClicked();
            }
        });
		btnListShow.setBounds(202, 429, 110, 25);
		frame.getContentPane().add(btnListShow);
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		idUser = new JTextField();
		idUser.setBounds(139, 13, 116, 22);
		frame.getContentPane().add(idUser);
		idUser.setColumns(10);
		
		idShow = new JTextField();
		idShow.setBounds(265, 13, 116, 22);
		frame.getContentPane().add(idShow);
		idShow.setColumns(10);
		
		username = new JTextField();
		username.setBounds(139, 59, 116, 22);
		frame.getContentPane().add(username);
		username.setColumns(10);
		
		nameShow = new JTextField();
		nameShow.setBounds(265, 59, 116, 22);
		frame.getContentPane().add(nameShow);
		nameShow.setColumns(10);
		
		password = new JTextField();
		password.setBounds(139, 108, 116, 22);
		frame.getContentPane().add(password);
		password.setColumns(10);
		
		detali = new JTextField();
		detali.setBounds(265, 108, 116, 22);
		frame.getContentPane().add(detali);
		detali.setColumns(10);
		
		tip = new JTextField();
		tip.setBounds(139, 156, 116, 22);
		frame.getContentPane().add(tip);
		tip.setColumns(10);
		
		type = new JTextField();
		type.setBounds(265, 156, 116, 22);
		frame.getContentPane().add(type);
		type.setColumns(10);
		
		rate = new JTextField();
		rate.setBounds(265, 189, 116, 22);
		frame.getContentPane().add(rate);
		rate.setColumns(10);
		
		date = new JTextField();
		date.setBounds(139, 189, 116, 22);
		frame.getContentPane().add(date);
		date.setColumns(10);
		
		no = new JTextField();
		no.setBounds(202, 229, 110, 25);
		frame.getContentPane().add(no);
		no.setColumns(10);
		
		
		JLabel lblIdUser = new JLabel("ID user/Id show");
		lblIdUser.setBounds(45, 16, 80, 16);
		frame.getContentPane().add(lblIdUser);
		
		JLabel lblUsername = new JLabel("Username/Show name");
		lblUsername.setBounds(45, 62, 80, 16);
		frame.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password/Detali");
		lblPassword.setBounds(45, 111, 80, 16);
		frame.getContentPane().add(lblPassword);
		
		JLabel lblType = new JLabel("Type/Show Type");
		lblType.setBounds(45, 159, 80, 16);
		frame.getContentPane().add(lblType);
		
		
		JLabel lblRate = new JLabel("Date/Rate");
		lblRate.setBounds(45, 189, 80, 16);
		frame.getContentPane().add(lblRate);
		
		JButton btnLogOut = new JButton("Log out");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LogInView log = new LogInView();
				log.getFrame().setVisible(true);
				frame.setVisible(false);
			}
		});
		btnLogOut.setBounds(45, 429, 110, 25);
		frame.getContentPane().add(btnLogOut);
	}
	
	 public void displayShows(List<Object[]> data) {

	        tableModel.setRowCount(0);	 // delete the old rows
	        table_1.setModel(tableModel);

	        System.out.println("View table: ");
	        for(Object[] ob : data) {
	            System.out.println(ob[0] + " " + ob[1] + " "+ ob[2]+" " +ob[3]);
	        }

	        for(Object[] show: data) {
	            tableModel.addRow(show);
	        }

	    }

	public String getIdUser() {
		 String s=idUser.getText();
        if(s== null)
        idUser.setText("introduceti id");
        
        return s;
    }
	public String getUserName() {
		 String s= username.getText();
		if(s!= null)
			return s;
       return"name";
    }
	public String getPassword() {
      
        
        String s=password.getText();
		if(s!= null)
			return s;
       return"name";
        
    }
	public String getType() {
		
		String s=tip.getText();
		if(s!= null)
			return s;
       return"name";
        
    }
	public void setIdUser(int id1) {
		String id=String.valueOf(id1);
         idUser.setText(id);
    }
	
	public String getIdShow() {
        return idShow.getText();
    }
	public String getNameShow() {
        return nameShow.getText();
    }
	public String getDetalii() {
        return detali.getText();
    }
	public String getTypeS() {
        return type.getText();
    }
	public String getRate() {
        return rate.getText();
    }
	public String getDate() {
        return date.getText();
    }
	public String getNo() {
        return no.getText();
    }
	
	
}
