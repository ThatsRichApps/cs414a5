package cs414.a5.rjh2h.common;

import java.math.BigDecimal;

public class CreditPayment extends Payment {
	
	private String cardNumber;
	private int expMonth;
	private int expYear;
	private BigDecimal totalCharged;
	
	public CreditPayment(){
	}
	
	public CreditPayment(BigDecimal amountDue, String cardNumber) {
		super(amountDue);
		this.cardNumber = cardNumber;
	}
	
	@Override
	public String toString() {
		return "CreditPayment";
	}

	@Override
	public boolean initiatePayment() {
		
		// charge card here
		if (this.cardNumber == "0000 0000 0000 0000") {
			// use 0000 0000 0000 0000 card to test decline
			return (false);
		}
		
		return true;
	}


	
	
}
