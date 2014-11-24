package cs414.a5.rjh2h.exit;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.math.BigDecimal;
import java.rmi.RemoteException;

import cs414.a5.rjh2h.common.BillingAccount;
import cs414.a5.rjh2h.common.Garage;
import cs414.a5.rjh2h.common.Transaction;
import cs414.a5.rjh2h.ui.RegisterUI;

public class Register implements ActionListener, Serializable {
	
	private static final long serialVersionUID = 1L;
	private RegisterUI registerUI;
	private ExitKiosk exitKiosk;
	private Transaction currentTransaction;
	private Garage garage;
	
	public Register() {
		super();
	}
	
	public Register(Garage garage) {
		this.garage = garage;
		registerUI = new RegisterUI();
		registerUI.addAllActionListeners(this);
	}
	
	@Override
	public String toString() {
		return "Register";
	}

	public void setExitKiosk(ExitKiosk exitKiosk) {
		this.exitKiosk = exitKiosk;
	}

	public void setCurrentTransaction(Transaction currentTransaction) {
		this.currentTransaction = currentTransaction;
		registerUI.setAmountDue(currentTransaction.getAmount());
	}

	public boolean validatePayment(Transaction transaction) {
		String paymentType = transaction.getPayment().toString();
		boolean paid = false;
		
		switch (paymentType) {
		
		case "CashPayment":
			registerUI.setCashPayment();
			break;
		case "CreditPayment":
			registerUI.setCreditPayment();
			paid = transaction.getPayment().initiatePayment();
			
			if (paid) {
				registerUI.setCreditCardLabel("Card Accepted");
				registerUI.setAmountDue(new BigDecimal(0));
				registerUI.resetUI();
				this.exitKiosk.openGate();	
			} else {
				registerUI.setCreditCardLabel("Card Declined");
			}
			
			break;
		case "AccountPayment":
			registerUI.setAccountPayment();
			paid = transaction.getPayment().initiatePayment();
			
			if (paid) {
				// marks a account as paid and opens gate
				registerUI.setAccountNumberLabel("Paid on Account");
				registerUI.setAmountDue(new BigDecimal(0));
				registerUI.resetUI();
				this.exitKiosk.openGate();	
			} else {
				registerUI.setAccountNumberLabel("Account Not Found");
			}
			
			// set the account paid field to license
			break;
		}
		
		return (paid);
	
	}
	
	public void setAmountDue(BigDecimal amount) {
		registerUI.setAmountDue(amount);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
	
		String eventName = event.getActionCommand();
		
		switch (eventName) {
		case "RetryPayment":
			registerUI.resetUI();
			exitKiosk.retryPayment();
			break;
		case "CashField":
			// get cash tendered and set changeDue amount
			BigDecimal cashTendered = registerUI.getCashTendered();
			BigDecimal change = cashTendered.subtract(currentTransaction.getAmount());
			registerUI.setChangeLabel(change);
			if (change.compareTo(BigDecimal.ZERO) >= 0) {
				// enable paid button
				registerUI.setCashPaid(true);
			} else {
				registerUI.setCashPaid(false);
			}
			
			break;
		case "Paid":
			// marks a cash deal as paid and opens gate
			registerUI.setAmountDue(new BigDecimal(0));
			registerUI.resetUI();
			this.exitKiosk.openGate();	
			break;
		case "CreateAccount":
			// validate input from fields 
			boolean valid = true;
			// create account if valid, otherwise return
			String firstName = registerUI.getFirstName();
			String lastName = registerUI.getLastName();
			String licensePlate = registerUI.getLicensePlate();
			
			if ((firstName.equals(""))||(lastName.equals(""))||(licensePlate.equals(""))) {
				registerUI.setAccountNumberLabel("Invalid Account Information");
				break;
			}
			
			BillingAccount newAccount = new BillingAccount(licensePlate);
			newAccount.setFirstName(firstName);
			newAccount.setLastName(lastName);
			
			//System.out.println("Create Account:" + firstName + lastName + licensePlate + valid);
			
			try {
				garage.addBillingAccount(newAccount);
			} catch (RemoteException e) {
				System.out.println("Trouble: " + e);
				valid = false;
			}
			
			
			if (valid) {
				registerUI.setAmountDue(new BigDecimal(0));
				registerUI.resetUI();
				this.exitKiosk.openGate();	
			} else {
				
				registerUI.setAccountNumberLabel("Invalid Account Information");
				
			}
			break;
			
		}
		
	}
	
}
