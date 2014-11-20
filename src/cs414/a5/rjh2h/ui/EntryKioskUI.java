package cs414.a5.rjh2h.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class EntryKioskUI extends JFrame {	
	
	private static final long serialVersionUID = -5276264018987914144L;
	private String title;
	private String message1;
	private JLabel messageLabel1;
	
	private String message2;
	private JLabel messageLabel2;
	
	private JButton enterButton;
	private JButton dispenseTicketButton;
	private JButton virtualTicketButton;
	private JButton noThanksButton;
	
	private JLabel gateStatusLabel;
	
	public EntryKioskUI() {
		initUI();
	}
	
	@Override
	public String toString() {
		return "EntryKioskUI";
	}

	public void setMessage1(String message) {
		this.message1 = message;
		messageLabel1.setText(message);
	}

	public void setMessage2(String message) {
		this.message2 = message;
		messageLabel2.setText(message);
	}
	
	public void setGateStatus (boolean isOpen) {
		if (isOpen) {
			gateStatusLabel.setText("Gate is Open, wait for car entry");
			gateStatusLabel.setForeground(Color.green);
		} else {
			gateStatusLabel.setText("Gate is Closed");
			gateStatusLabel.setForeground(Color.red);
		}
	}
	
	public void enableEnterButton(boolean buttonEnabled) {
		enterButton.setEnabled(buttonEnabled);
	}
	
	public void enableTicketButtons(boolean buttonEnabled) {
		dispenseTicketButton.setEnabled(buttonEnabled);
		virtualTicketButton.setEnabled(buttonEnabled);
		noThanksButton.setEnabled(buttonEnabled);
	}
	
	public void addButtonActionListener(ActionListener listener) {
	
		enterButton.addActionListener(listener);
	    dispenseTicketButton.addActionListener(listener);
	    virtualTicketButton.addActionListener(listener);
	    noThanksButton.addActionListener(listener);
	
	}
	
	private void initUI() {
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	    title = "Entry Kiosk";
	    setTitle(title);
	    
	    // setup labels and buttons
	    message1 = "Press Top Button to Enter";
	    messageLabel1 = new JLabel(message1, SwingConstants.CENTER);
	    message2 = "";
	    messageLabel2 = new JLabel(message2, SwingConstants.CENTER);
		
	    enterButton = new JButton("Press to Enter Garage");
		dispenseTicketButton = new JButton ("Dispense Ticket");
		virtualTicketButton = new JButton ("Virtual Ticket");
		noThanksButton = new JButton ("No Thanks (car leaves)");
		
		// intialize the ticket buttons as disabled
	    dispenseTicketButton.setEnabled(false);
	    virtualTicketButton.setEnabled(false);
	    noThanksButton.setEnabled(false);
	    
		enterButton.setActionCommand("EnterButton");
		dispenseTicketButton.setActionCommand("DispenseTicketButton");
		virtualTicketButton.setActionCommand("VirtualTicketButton");
		noThanksButton.setActionCommand("NoThanks");
		
		gateStatusLabel = new JLabel("", SwingConstants.CENTER);
		
        JPanel pane = new JPanel(new GridLayout(7, 0));
        pane.add(enterButton);
        pane.add(dispenseTicketButton);
        pane.add(virtualTicketButton);
        pane.add(noThanksButton);
        
        pane.add(messageLabel1);
        pane.add(messageLabel2);
        pane.add(gateStatusLabel);
        
        pane.setBorder(BorderFactory.createEmptyBorder(
                                        30, //top
                                        30, //left
                                        10, //bottom
                                        30) //right
                                        );

        getContentPane().add(pane, BorderLayout.CENTER);
	    
	    pack();
	    setSize(300, 300);
	    //setLocationRelativeTo(null);
	    //setLocationByPlatform(true);
	    setLocation(5,150);
	    setVisible(true);
	}

}
