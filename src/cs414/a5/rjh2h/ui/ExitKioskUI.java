package cs414.a5.rjh2h.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.text.MaskFormatter;

public class ExitKioskUI extends JFrame {

	private static final long serialVersionUID = -8347099823510457669L;
	private JLabel messageLabel;
    
    private JFormattedTextField enterTicketField;
    private JFormattedTextField licensePlateField;
    private JButton lostTicketButton;
    
    private JLabel topMessageLabel;
    
    private JLabel paymentMessageLabel;
    private JLabel selectPaymentMessageLabel;
    
    private JFormattedTextField creditCardField;
    private JFormattedTextField creditCardMMYYYYField;
    
    private JButton payCashButton;
    private JButton payOnAccountButton;
    private JButton payCreditButton;
    private JButton canNotPayButton;
    
    private JLabel gateStatusLabel;
    private JLabel paymentStatusLabel;
    
    
	public ExitKioskUI() {
	
		initUI();
		// first the ticket needs to be entered and validated
		enableFindTicketButtons(true);
		// then the payment should be selected
		enablePaymentFields(false);
		// start with the gate closed
		setGateStatus(false);
	
	}
	
	@Override
	public String toString() {
		return "ExitKioskUI";
	}

	private void initUI() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Exit Kiosk");
        
        topMessageLabel = new JLabel("Enter Ticket Num or License Plate", SwingConstants.CENTER);
        
        messageLabel = new JLabel("", SwingConstants.CENTER);
        
        // create enter ticket field with integer as input   
        enterTicketField = new JFormattedTextField(NumberFormat.getIntegerInstance());
        enterTicketField.setFocusLostBehavior(JFormattedTextField.PERSIST);
        enterTicketField.setActionCommand("TicketField");
        //enterTicketField.setText("1");
        
        /*
        try {
			enterTicketField.commitEdit();
		} catch (ParseException e) {
			// catch block
			e.printStackTrace();
		}
		*/

        // create enter license plate field   
        licensePlateField = new JFormattedTextField(createFormatter("UU-UUU-###"));
        licensePlateField.setFocusLostBehavior(JFormattedTextField.COMMIT_OR_REVERT);
        licensePlateField.setActionCommand("LicenseField");
        //licensePlateField.setText("CO-AAA-111");
        
        /*
        licensePlateField.addFocusListener(new FocusListener(){
            @Override
            public void focusGained(FocusEvent e){
                licensePlateField.setText("");
            }
            
			@Override
			public void focusLost(FocusEvent e) {
				// do nothing 
			}
        });
        */
        
        lostTicketButton = new JButton("Lost Ticket");
        lostTicketButton.setActionCommand("LostTicket");
        
        paymentMessageLabel = new JLabel("", SwingConstants.CENTER);
        selectPaymentMessageLabel = new JLabel("Enter CC or Select Payment Type", SwingConstants.CENTER);
        
        creditCardField = new JFormattedTextField(createFormatter("#### #### #### ####"));
        creditCardField.setFocusLostBehavior(JFormattedTextField.COMMIT_OR_REVERT);
        //creditCardField.setActionCommand("PayCreditCard");
        //creditCardField.setText("1111 1111 1111 11111");
        
        // clear the 1111... field when clicked
        
        /*
        creditCardField.addFocusListener(new FocusListener(){
            @Override
            public void focusGained(FocusEvent e){
                creditCardField.setText("");
            }
            
			@Override
			public void focusLost(FocusEvent e) {
				// do nothing 
			}
        });
        */
        
        creditCardMMYYYYField = new JFormattedTextField(createFormatter("##/####"));
        creditCardMMYYYYField.setFocusLostBehavior(JFormattedTextField.COMMIT_OR_REVERT);
        creditCardMMYYYYField.setActionCommand("CreditCardMMYY");
        //creditCardMMYYField.setText("11/2014");
        
        /*
        // clear the field when clicked
        creditCardMMYYField.addFocusListener(new FocusListener(){
            @Override
            public void focusGained(FocusEvent e){
                creditCardMMYYField.setText("");
            }
            
			@Override
			public void focusLost(FocusEvent e) {
				// do nothing 
			}
        });
        */
        
        payCashButton = new JButton("Pay by Cash");
        payCashButton.setActionCommand("PayCash");
        
        payCreditButton = new JButton("Charge Card");
        payCreditButton.setActionCommand("PayCreditCard");
        
        payOnAccountButton = new JButton("Pay on Account");
        payOnAccountButton.setActionCommand("PayOnAccount");
    
        canNotPayButton = new JButton("I Can Not Pay");
        canNotPayButton.setActionCommand("CanNotPay");
        
        gateStatusLabel = new JLabel("", SwingConstants.CENTER);
        paymentStatusLabel = new JLabel("", SwingConstants.CENTER);
		
        JPanel pane = new JPanel(new GridLayout(12, 2));
        
        pane.add(topMessageLabel);
        pane.add(new JLabel("", SwingConstants.CENTER));
        
        pane.add(new JLabel("Ticket Number:", SwingConstants.CENTER));
        pane.add(enterTicketField);
        
        pane.add(new JLabel("License Plate:", SwingConstants.CENTER));
        pane.add(licensePlateField);
        
        pane.add(new JLabel("Lost Ticket:", SwingConstants.CENTER));
        pane.add(lostTicketButton);
        
        pane.add(new JLabel("", SwingConstants.CENTER));
        pane.add(messageLabel);
        
        pane.add(new JLabel("", SwingConstants.CENTER));
        pane.add(paymentMessageLabel);
        
        pane.add(selectPaymentMessageLabel);
        pane.add(new JLabel("", SwingConstants.CENTER));
        
        pane.add(new JLabel("Card Number:", SwingConstants.CENTER));
        pane.add(creditCardField);
        
        pane.add(new JLabel("Exp Date (MM/YYYY)", SwingConstants.CENTER));
        pane.add(creditCardMMYYYYField);
        
        pane.add(payOnAccountButton);
        pane.add(payCreditButton);
        pane.add(payCashButton);
        pane.add(canNotPayButton);
        
        pane.add(gateStatusLabel);
        pane.add(paymentStatusLabel);
        
        pane.setBorder(BorderFactory.createEmptyBorder(
                                        30, //top
                                        30, //left
                                        10, //bottom
                                        30) //right
                                        );

        getContentPane().add(pane, BorderLayout.CENTER);
                
	    pack();
	    setLocation(615, 150);
	    setVisible(true);
    }
    
    protected MaskFormatter createFormatter(String s) {
        MaskFormatter formatter = null;
        try {
            formatter = new MaskFormatter(s);
        } catch (java.text.ParseException exc) {
            System.err.println("formatter is bad: " + exc.getMessage());
            System.exit(-1);
        }
        return formatter;
    }
    
    public String getCreditCardNumber () {
    	return creditCardField.getText();
    }
    
    public int getExpMonth() {
    	String expiration = creditCardMMYYYYField.getText();
    	String[] parts = expiration.split("/");
    	int month;
    	try {
    		month = Integer.parseInt(parts[0]);
    	} catch (NumberFormatException e) { 
    		month = 0;
    	}
    		
    		
    	return (month);
    }
    
    public int getExpYear() {
    	String expiration = creditCardMMYYYYField.getText();
    	String[] parts = expiration.split("/");
    	int year;
    	try {
    		year = Integer.parseInt(parts[1]);
    	} catch (NumberFormatException e) { 
    		year = 0;
    	}
    	return (year);
    }
    
	public int getTicketNumber () {	
		int ticketNumber = Integer.parseInt(enterTicketField.getText());
		return ticketNumber;
	}

	public String getLicensePlate () {
		String licensePlate = licensePlateField.getText();
		return licensePlate;
	}
    
	public void setMessage(String message) {
		messageLabel.setText(message);
	}

	public void setPaymentMessage(String message) {
		paymentMessageLabel.setText(message);
	}

	public void setSelectPaymentMessage(String message) {
		selectPaymentMessageLabel.setText(message);
	}
	
	public void addActionListeners(ActionListener listener) {
		enterTicketField.addActionListener(listener);
		licensePlateField.addActionListener(listener);
	    lostTicketButton.addActionListener(listener);
	    payCreditButton.addActionListener(listener);
	    payCashButton.addActionListener(listener);
	    payOnAccountButton.addActionListener(listener);
	    canNotPayButton.addActionListener(listener);
	}
	
	public void enablePaymentFields(boolean enabled) {
		creditCardField.setEnabled(enabled);
		creditCardMMYYYYField.setEnabled(enabled);
		payCreditButton.setEnabled(enabled);
		payCashButton.setEnabled(enabled);
		payOnAccountButton.setEnabled(enabled);
		canNotPayButton.setEnabled(enabled);
	}
	
	public void enableFindTicketButtons(Boolean enabled) {
		enterTicketField.setEnabled(enabled);
		licensePlateField.setEnabled(enabled);
		lostTicketButton.setEnabled(enabled);
	}
	
	public void setGateStatus (boolean isOpen) {
		if (isOpen) {
			gateStatusLabel.setText("Gate is Open, wait for car exit");
			gateStatusLabel.setForeground(Color.green);
		} else {
			gateStatusLabel.setText("Gate is Closed");
			gateStatusLabel.setForeground(Color.red);
		}
	}

}
