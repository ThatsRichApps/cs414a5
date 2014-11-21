package cs414.a5.rjh2h.exit;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.HashMap;

import cs414.a5.rjh2h.common.BillingAccount;
import cs414.a5.rjh2h.common.Transaction;
import cs414.a5.rjh2h.ui.RegisterUI;

public class Register implements ActionListener {
	
	private RegisterUI registerUI;
	private ExitKiosk exitKiosk;
	private Transaction currentTransaction;
	private HashMap<String, BillingAccount> BillingAccounts = new HashMap<String, BillingAccount>();
	
	
	public Register() {
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
			
			// marks a account as paid and opens gate
			registerUI.setAmountDue(new BigDecimal(0));
			registerUI.resetUI();
			this.exitKiosk.openGate();	
			
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
			if (change.compareTo(BigDecimal.ZERO) > 0) {
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
		}
		
	}
	
}
