package cs414.a5.rjh2h.common;

import java.math.BigDecimal;

public class AccountPayment extends Payment {
	
	private static final long serialVersionUID = 1L;
	// the car license plate is the billing account number or key
	private String licensePlate;
	
	public AccountPayment() {
	}
	
	public AccountPayment(BigDecimal amountDue) {
		super(amountDue);
	}
	
	@Override
	public String toString() {
		return "AccountPayment";
	}

	@Override
	public boolean initiatePayment() {
		// account payment needs visibility into BillingAccounts from server
		
		
		
		// track the account by license plate
		return (true);
	}

	public String getLicensePlate() {
		return licensePlate;
	}

	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}

}
