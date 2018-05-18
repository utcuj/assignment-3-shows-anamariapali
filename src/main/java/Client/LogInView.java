package Client;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;





public class LogInView {
	private JFrame frame;
	private JTextField type;
	private JTextField usern;
	private JPasswordField pass;
	private AdminView adminpanel;
	private ClientView clientpanel;
	private PremiumView premiumpanel;
	private Client.ClientCont client;
	public void start() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try { System.out.println("Client LogIn run");
                    LogInView window = new LogInView();
                    window.frame.setVisible(true);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
	
	public JFrame getFrame()
	{
		return this.frame;
	}
/**
 * Create the application.
 */
	public 	LogInView() {  
		System.out.println("LogInView");
		client = new ClientCont(this,adminpanel,clientpanel);
		initialize();
}
	private void initialize()	{
	frame = new JFrame();
	frame.setBounds(200, 200, 450, 300);
	
	 frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	 frame.getContentPane().setLayout(null);
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
	
	JButton btnLogIn = new JButton("Log In");
	btnLogIn.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent arg0) {
            client.btnLogInCliked();
        }
    });
	btnLogIn.setBounds(138, 184, 97, 25);
	frame.getContentPane().add(btnLogIn);
	
	JLabel lblUsername = new JLabel("Username");
	lblUsername.setBounds(37, 57, 66, 16);
	frame.getContentPane().add(lblUsername);
	
	JLabel lblPassword = new JLabel("Password");
	lblPassword.setBounds(37, 106, 56, 16);
	frame.getContentPane().add(lblPassword);
	

	
	usern = new JTextField();
	usern.setBounds(138, 54, 116, 22);
	frame.getContentPane().add(usern);
	usern.setColumns(10);
	

	
	pass = new JPasswordField();
	pass.setBounds(138, 106, 116, 22);
	frame.getContentPane().add(pass);
	pass.setColumns(10);
}
	public String getUsern() {
		String n=usern.getText();
		return n;
	}



	public String getPass() {
		return pass.getText().toString();
	}
	
public void ViewAdmin() {
	adminpanel=new AdminView(client);
	//adminpanel.start();
	adminpanel.getAFrame().setVisible(true);
	frame.setVisible(false);
}
public void ViewClient() {
	clientpanel=new ClientView(client);
	//clientpanel.start();
	clientpanel.getFrame().setVisible(true);
	frame.setVisible(false);
}
public void ViewPremium() {
	premiumpanel=new PremiumView(client);
	premiumpanel.getFrame().setVisible(true);
	frame.setVisible(false);
}
public ClientView getCV() {
	return this.clientpanel;
}
public AdminView getAV() {
	return this.adminpanel;
}

public PremiumView getPV() {
	return this.premiumpanel;
}
	
}