package cs414.a5.rjh2h;

import java.math.BigDecimal;

public class CreditCard {

	private String cardNumber;
	private int expMonth;
	private int expYear;
	private BigDecimal totalCharged;

	// if called with no fields, just initate with bogus data
	public CreditCard() {
		this.cardNumber = "1111 1111 1111 1111";
		this.totalCharged = new BigDecimal(0);
		this.expMonth = 10;
		this.expYear = 2015;
	}
	
	public CreditCard(String cardNumber, int expMonth, int expYear, int cvv) {
		
		if (cardNumber == null) {
			this.cardNumber = "1111 1111 1111 1111";
		} else {
			this.cardNumber = cardNumber;
		}
		
		this.expMonth = expMonth;
		this.expYear = expYear;
		this.totalCharged = new BigDecimal(0);
	}
	
	@Override
	public String toString() {
		return "CreditCard [" + cardNumber + "]";
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public int getExpMonth() {
		return expMonth;
	}

	public int getExpYear() {
		return expYear;
	}

	public BigDecimal getTotalCharged() {
		return totalCharged;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public void setExpMonth(int expMonth) {
		this.expMonth = expMonth;
	}

	public void setExpYear(int expYear) {
		this.expYear = expYear;
	}

	public void setTotalCharged(BigDecimal totalCharged) {
		this.totalCharged = totalCharged;
	}

	public boolean chargeCard(BigDecimal amount) {
		
		if (amount == null) {
			return (false);
		}
		
		// assume that this method connects to
		// verification system and charges the card
		
		// set bogus card number for false tests
		if (this.cardNumber == "0000 0000 0000 0000") {
			return (false);
		}
		
		// track the total amount charged on this card
		this.totalCharged = this.totalCharged.add(amount);
		
		return (true);
	}
	
	
	
}
