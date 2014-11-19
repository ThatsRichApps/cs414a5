package cs414.a5.rjh2h.common;

import java.math.BigDecimal;

public abstract class Payment {
	
	protected BigDecimal amountDue;
	
	public abstract boolean initiatePayment();
	
	public Payment(){
	}

	public Payment(BigDecimal amountDue) {
		super();
		this.amountDue = amountDue;
	}

	public BigDecimal getAmountDue() {
		return amountDue;
	}

	public void setAmountDue(BigDecimal amountDue) {
		this.amountDue = amountDue;
	}
	
}
