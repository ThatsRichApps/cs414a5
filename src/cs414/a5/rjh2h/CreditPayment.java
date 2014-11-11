package cs414.a5.rjh2h;

import java.math.BigDecimal;

public class CreditPayment extends Payment {
	
	private CreditCard creditCard;
	
	public CreditPayment(){
	}
	
	public CreditPayment(BigDecimal amountDue, String cardNumber) {
		super(amountDue);
		creditCard = new CreditCard();
		creditCard.setCardNumber(cardNumber);
	}
	
	@Override
	public String toString() {
		return "CreditPayment";
	}

	@Override
	public boolean initiatePayment() {
		
		// charge card here
		if (creditCard.getCardNumber() == "1111 1111 1111 1111") {
			// use 1111 1111 1111 1111 card to test decline
			return (false);
		}
		
		return true;
	}

	public CreditCard getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(CreditCard creditCard) {
		this.creditCard = creditCard;
	}

}
