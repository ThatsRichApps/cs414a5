package cs414.a5.rjh2h.ui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class SignUI extends JFrame {
	
	// Sign should observe ParkingGarage for changes in occupancy.

	private static final long serialVersionUID = 8443698131673275729L;
	private String title;
	private String message;
    JLabel messageLabel = new JLabel("Welcome to the Garage", SwingConstants.CENTER);

	public SignUI() {
		initUI();
	}
	
    @Override
	public String toString() {
		return "SignUI";
	}

	private void initUI() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        title = "Entry Sign";
        setTitle(title);
        message = "Welcome to the Garage";
        // add a jLabel with the message
                
        messageLabel.setText(message);
        add(messageLabel);
       
        setSize(150, 100);
        setLocation(5, 5);
        //setLocationRelativeTo(null);
        setVisible(true);
    }

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
		messageLabel.setText(message);
	}
	
}
