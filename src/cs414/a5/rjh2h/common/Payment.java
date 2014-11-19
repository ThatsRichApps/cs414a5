package cs414.a5.rjh2h.common;

import java.io.Serializable;
import java.math.BigDecimal;

public abstract class Payment implements Serializable {
	
	private static final long serialVersionUID = 1L;
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
