package cs414.a5.rjh2h.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.text.MaskFormatter;

public class RegisterUI extends JFrame {

	private static final long serialVersionUID = 836638112157967802L;

	private JLabel amountDueLabel;
	private JLabel amountLabel;
	private JLabel cashTenderedLabel;
	private JFormattedTextField enterCashTenderedField;
	private JLabel changeDueLabel;
	private JLabel changeLabel;
	private JLabel paidByCCLabel;
	private JLabel creditCardResponseLabel;
	private JLabel paidOnAccountLabel;
	private JLabel accountResponseLabel;
	private JButton paidButton;
	private JLabel driverNameLabel;
	private JFormattedTextField driverNameField;
	private JLabel licensePlateLabel;
	private JFormattedTextField licensePlateField;
	private JButton retryPaymentButton;
	
    public RegisterUI () {
    	initUI();
    	setAllPaymentsEnabled(false);
    }
    
    @Override
	public String toString() {
		return "RegisterUI";
	}

	private void initUI() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Cash Register");
        
        amountDueLabel = new JLabel("Amount Due:", SwingConstants.CENTER);
        amountLabel = new JLabel("", SwingConstants.CENTER);
        
        cashTenderedLabel = new JLabel("Cash Tendered:", SwingConstants.CENTER);
        
//        enterCashTenderedField = new JFormattedTextField(createFormatter("###.##"));
        enterCashTenderedField = new JFormattedTextField();

        enterCashTenderedField.setFocusLostBehavior(JFormattedTextField.COMMIT_OR_REVERT);
        enterCashTenderedField.setActionCommand("CashField");
        
	    changeDueLabel = new JLabel("Change Due:", SwingConstants.CENTER);
        changeLabel = new JLabel("", SwingConstants.CENTER);
        
        paidByCCLabel = new JLabel("Paid By CC:", SwingConstants.CENTER);
        creditCardResponseLabel = new JLabel("", SwingConstants.CENTER);

        paidOnAccountLabel = new JLabel("Paid On Account:", SwingConstants.CENTER);
        accountResponseLabel = new JLabel("", SwingConstants.CENTER);
        
        driverNameLabel = new JLabel ("Driver Name:", SwingConstants.CENTER);
    	driverNameField = new JFormattedTextField();
    	licensePlateLabel = new JLabel("License Plate:", SwingConstants.CENTER);
    	licensePlateField = new JFormattedTextField();
    	
        paidButton = new JButton("Paid");
        paidButton.setActionCommand("Paid");
        
        retryPaymentButton = new JButton("Different Payment Method");
        retryPaymentButton.setActionCommand("RetryPayment");
        
        JPanel pane = new JPanel(new GridLayout(8, 2));
        
        pane.add(amountDueLabel);
        pane.add(amountLabel);
        
        pane.add(cashTenderedLabel);
        pane.add(enterCashTenderedField);
        
        pane.add(changeDueLabel);
        pane.add(changeLabel);
        
        pane.add(paidByCCLabel);
        pane.add(creditCardResponseLabel);
        
        pane.add(paidOnAccountLabel);
        pane.add(accountResponseLabel);
        
        pane.add(driverNameLabel);
        pane.add(driverNameField);
        pane.add(licensePlateLabel);
        pane.add(licensePlateField);
    	
        pane.add(paidButton);
        pane.add(retryPaymentButton);
        
        pane.setBorder(BorderFactory.createEmptyBorder(
                                        30, //top
                                        30, //left
                                        10, //bottom
                                        30) //right
                                        );

        getContentPane().add(pane, BorderLayout.CENTER);
                
	    pack();
	    //setSize(300, 300);
	    setLocation(620, 600);
	    // setLocationRelativeTo(null);
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
    
    public void addAllActionListeners(ActionListener listener){
    	// set the controller class (Register) as the action listener
        enterCashTenderedField.addActionListener(listener);
        paidButton.addActionListener(listener);
        retryPaymentButton.addActionListener(listener);	
    }
    
    public void setCashPayment() {
        cashTenderedLabel.setEnabled(true);
        enterCashTenderedField.setEnabled(true);
        
	    changeDueLabel.setEnabled(true);
        changeLabel.setEnabled(true);

        // keep paid button false until enough cash is tendered
        paidButton.setEnabled(false);
        retryPaymentButton.setEnabled(true);
    }
    
    public void setCashPaid(boolean paid) {
        paidButton.setEnabled(paid);
    }
    
    public void setCreditPayment() {
        paidByCCLabel.setEnabled(true);
        creditCardResponseLabel.setEnabled(true);
        retryPaymentButton.setEnabled(true);
    }
    
    public void setAccountPayment() {
        paidOnAccountLabel.setEnabled(true);
        accountResponseLabel.setEnabled(true);
    }
    
    public void resetUI() {
		//setAmountDue(new BigDecimal(0));
		enterCashTenderedField.setText("");
		changeLabel.setText("");
	    creditCardResponseLabel.setText("");
	    setAllPaymentsEnabled(false);
    }
    
    public void setAllPaymentsEnabled(boolean enabled) {
    	// use this to reset the ui after or before payment
    	// usually only used for enabled = false
        cashTenderedLabel.setEnabled(enabled);
        enterCashTenderedField.setEnabled(enabled);
        
	    changeDueLabel.setEnabled(enabled);
        changeLabel.setEnabled(enabled);
        
        paidByCCLabel.setEnabled(enabled);
        creditCardResponseLabel.setEnabled(enabled);

        paidOnAccountLabel.setEnabled(enabled);
        accountResponseLabel.setEnabled(enabled);
        
        paidButton.setEnabled(enabled);
        
        driverNameLabel.setEnabled(enabled);
        licensePlateLabel.setEnabled(enabled);
        driverNameField.setEnabled(enabled);
        licensePlateField.setEnabled(enabled);
        retryPaymentButton.setEnabled(enabled);
    	
    }
    
    public BigDecimal getCashTendered() {
    	BigDecimal cashTendered;	
    	try {
    		cashTendered = new BigDecimal(enterCashTenderedField.getText());
    	} catch (NumberFormatException e) {
    		cashTendered = new BigDecimal("0");
    	}
    	return (cashTendered);
    }
    
    public void setAmountDue(BigDecimal amount) {
		amountLabel.setText("$" + amount.toString());
	}

	public void setChangeLabel(BigDecimal change) {
		this.changeLabel.setText("$" + change.toString());
	}

	public void setCreditCardLabel(String creditCardLabel) {
		this.creditCardResponseLabel.setText(creditCardLabel);
	}

	public void setAccountNumberLabel(String accountNumberLabel) {
		this.accountResponseLabel.setText(accountNumberLabel);
	}
    
}
