package cs414.a5.rjh2h.common;

import java.math.BigDecimal;
import java.util.Calendar;

public class CreditPayment extends Payment {
	
	private static final long serialVersionUID = 1L;
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
	
	public CreditPayment(BigDecimal amountDue, String cardNumber, int expMonth, int expYear) {
		super(amountDue);
		this.cardNumber = cardNumber;
		this.expMonth = expMonth;
		this.expYear = expYear;
	}
	
	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public int getExpMonth() {
		return expMonth;
	}

	public void setExpMonth(int expMonth) {
		this.expMonth = expMonth;
	}

	public int getExpYear() {
		return expYear;
	}

	public void setExpYear(int expYear) {
		this.expYear = expYear;
	}

	public BigDecimal getTotalCharged() {
		return totalCharged;
	}

	public void setTotalCharged(BigDecimal totalCharged) {
		this.totalCharged = totalCharged;
	}

	@Override
	public String toString() {
		return "CreditPayment";
	}

	@Override
	public boolean initiatePayment() {
		
		// charge card here
		if (this.cardNumber.equals("0000 0000 0000 0000")) {
			// use 0000 0000 0000 0000 card to test decline
			return (false);
		}
		
		// check the exp date against todays date
		Calendar calendar = Calendar.getInstance();
	    int monthNow = calendar.get(Calendar.MONTH);
	    monthNow++;  // increment to compensate for Jan == 0
	    int yearNow = calendar.get(Calendar.YEAR); 
		
	    if (expYear < yearNow) {
	    	return (false);
	    } else if ((expYear == yearNow) && (expMonth < monthNow)) {
	    	return (false);
	    }
	    	
		return true;
	}


	
	
}
