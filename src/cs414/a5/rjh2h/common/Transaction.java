package cs414.a5.rjh2h.common;

import java.math.BigDecimal;
import java.util.Date;

public class Transaction {
	
	private Date timeOut;
	private BigDecimal amount;
	private Ticket ticket;
	private Payment payment;
	private boolean paid;
	
	public Transaction () {
	}
	
	public Transaction(Ticket ticket) {
		this.ticket = ticket;
		// set timeOut to creation time
		setTimeOut();
		// determine fees based upon ticket
		this.amount = calculateFees();
		this.paid = false;
	}
	
	public Transaction(BigDecimal amount) {
		// set fee explicitly
		this.amount = amount;
		this.paid = false;
	}
	
	@Override
	public String toString() {
		return "Transaction";
	}

	public BigDecimal getAmount() {
		return amount;
	}
	
	public Payment getPayment() {
		return payment;
	}

	public boolean isPaid() {
		return paid;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public void setPaid(boolean paid) {
		this.paid = paid;
	}

	public Date getTimeOut() {
		return this.timeOut;
	}
	
	public void setTimeOut() {
		this.timeOut = new Date();
	}

	public BigDecimal calculateFees (){
	
		BigDecimal rate = ticket.getRate();
		
		Date timeIn = ticket.getTimeIn();
		
		// determine the number of hours in the garage
		double timeDifference = timeOut.getTime() - timeIn.getTime(); 
		double diffHours = timeDifference / (60 * 60 * 1000) % 24;
		
		// now round up to the next hour
		diffHours = Math.ceil(diffHours);
		
		BigDecimal amountOwed = rate.multiply(new BigDecimal(diffHours));
		
		return (amountOwed);
	
	}
	
	public void createCashPayment(){
		payment = new CashPayment(amount);
	}
	
	public void createCreditPayment(String creditCardNumber){
		payment = new CreditPayment(amount, creditCardNumber);
	}
	
	public void createAccountPayment(){
		payment = new AccountPayment(amount);
	}
	
	
}
