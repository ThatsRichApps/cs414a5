package cs414.a5.rjh2h.common;

import java.math.BigDecimal;

public class CashPayment extends Payment {

	private BigDecimal cashTendered;
	private BigDecimal change;
	
	public CashPayment(){
	}
	
	public CashPayment(BigDecimal amountDue) {
		super(amountDue);
	}
	
	@Override
	public String toString() {
		return "CashPayment";
	}

	public boolean initiatePayment() {

		return (true);
		
	}

	public BigDecimal getCashTendered() {
		return cashTendered;
	}

	public BigDecimal getChange() {
		this.change = cashTendered.subtract(amountDue);
		return change;
	}

	public void setCashTendered(BigDecimal cashTendered) {
		this.cashTendered = cashTendered;
	}

	public void setChange(BigDecimal change) {
		this.change = change;
	}
	
	
	
	
}
