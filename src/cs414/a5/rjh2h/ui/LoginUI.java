package cs414.a5.rjh2h.ui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

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
    private JButton btnLogin;
    private JButton btnCancel;
    private boolean succeeded;
 
	
	
	
	
	
	
	@Override
	public String toString() {
		return "LoginUI";
	}
	
}
