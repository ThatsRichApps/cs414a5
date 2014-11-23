package cs414.a5.rjh2h.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class LoginUI extends JFrame {

	private static final long serialVersionUID = -6563852103411295201L;

	// create a login dialog for getting system account info
	// this would be used for verifying users before 
	// setting system preferences, viewing usage data,
	// and opening the register

	private JTextField username;
    private JPasswordField password;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JButton loginButton;
    private JButton cancelButton;
    private boolean succeeded;
 
	public boolean isSucceeded() {
		return succeeded;
	}

	public void setSucceeded(boolean succeeded) {
		this.succeeded = succeeded;
	}

	public LoginUI() {
		super();
		initUI();
	}

	private void initUI() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    	setTitle("Login");
    	
        loginButton = new JButton("Login");
        loginButton.setActionCommand("Authenticate");
        
        cancelButton = new JButton("Cancel");
        cancelButton.setActionCommand("Cancel");
        
        username = new JTextField();
        password = new JPasswordField();
        
        usernameLabel = new JLabel("Username:", SwingConstants.CENTER);
        passwordLabel = new JLabel("Password:", SwingConstants.CENTER);

        succeeded = false;
        
        JPanel pane = new JPanel(new GridLayout(5, 1));

        pane.add(usernameLabel);
        pane.add(username);
        pane.add(passwordLabel);
        pane.add(password);
        pane.add(cancelButton);
        pane.add(loginButton);
        
        pane.setBorder(BorderFactory.createEmptyBorder(
                30, //top
                30, //left
                10, //bottom
                30) //right
                );

        getContentPane().add(pane, BorderLayout.CENTER);

        pack();
        
        setSize(300, 300);
        setLocation(310, 150);
        setVisible(true);
    }
	
	public void addActionListeners (ActionListener listener) {
		
		loginButton.addActionListener(listener);
		cancelButton.addActionListener(listener);
	}
	
	
	
	
	@Override
	public String toString() {
		return "LoginUI";
	}

	public String getUsername() {
		return username.getText();
	}

	public String getPassword() {
		
		char[] passwordArray;
		try {
			passwordArray = password.getPassword();
		} catch (NullPointerException np) {
			passwordArray = new char[0];
		}
			
		String password = String.valueOf(passwordArray); 
		
		return (password);
	}
	
}
