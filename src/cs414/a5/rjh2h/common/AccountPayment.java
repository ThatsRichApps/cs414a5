package cs414.a5.rjh2h.common;

import java.math.BigDecimal;

public class AccountPayment extends Payment {
	
	private static final long serialVersionUID = 1L;
	// the car license plate is the billing account number or key
	private String licensePlate;
	private BillingAccount billingAccount;
	
	public AccountPayment() {
	}
	
	public AccountPayment(BigDecimal amountDue, BillingAccount billingAccount) {
		super(amountDue);
		this.billingAccount = billingAccount;
	}
	
	@Override
	public String toString() {
		return "AccountPayment";
	}

	@Override
	public boolean initiatePayment() {
		if (billingAccount == null) {
			return false;
		}
		// track the account by license plate
		// if the account exists, then we can bill them
		return (true);
	}

	public String getLicensePlate() {
		return licensePlate;
	}

	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}

}
