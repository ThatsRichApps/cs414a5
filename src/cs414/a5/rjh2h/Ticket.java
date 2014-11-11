package cs414.a5.rjh2h;

import java.math.BigDecimal;
import java.util.Date;

public class Ticket {
	
	private Date timeIn;
	private Vehicle vehicle;
	private int ticketNumber;
	private BigDecimal rate;
	
	public Ticket() {
		this.ticketNumber = 999999;
		setTimeIn();
		vehicle = new Vehicle();
		rate = new BigDecimal(0);
	}
	
	public Ticket(int ticketNumber, BigDecimal rate) {
		// set unique ticket number
		this.ticketNumber = ticketNumber;
		// get the current time
		setTimeIn();
		// get the info for the current automobile
		vehicle = new Vehicle();
		this.rate = rate;
	}
	
	@Override
	public String toString() {
		return "Ticket [" + ticketNumber + "]";
	}
	
	public Vehicle getVehicle() {
		return vehicle;
	}

	public int getTicketNumber() {
		return this.ticketNumber;
	}
	
	public Date getTimeIn() {
		return timeIn;
	}

	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	private void setTimeIn () {
		this.timeIn = new Date();
	}
	
}
