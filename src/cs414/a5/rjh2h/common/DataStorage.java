package cs414.a5.rjh2h.common;

import java.util.Date;
import java.util.HashMap;

public class DataStorage {
	
	private HashMap<String, Ticket> virtualTickets = new HashMap<String, Ticket>();
	private HashMap<Integer, Ticket> physicalTickets = new HashMap<Integer, Ticket>();
	private HashMap<Date, Integer> occupancyData = new HashMap<Date, Integer>();
	private HashMap<Integer, Transaction> transactionRecords = new HashMap<Integer, Transaction>();
	private int transactionID = 0;
	
	private HashMap<String, SystemAccount> systemAccounts = new HashMap<String, SystemAccount>();
	
	public DataStorage() {
		
		// initialize any pre startup test data here
		initializeData();
	
	}
	
	@Override
	public String toString() {
		return "DataStorage";
	}
	
	public HashMap<Date, Integer> getOccupancyData() {
		return occupancyData;
	}

	public HashMap<String, Ticket> getVirtualTicketMap() {
		return virtualTickets;
	}

	public HashMap<Integer, Ticket> getPhysicalTicketMap() {
		return physicalTickets;
	}

	public HashMap<String, SystemAccount> getSystemAccountList() {
		return systemAccounts;
	}

	public void setSystemAccountList(HashMap<String, SystemAccount> systemAccountList) {
		this.systemAccounts = systemAccountList;
	}

	public void setVirtualTicketMap(HashMap<String, Ticket> virtualTicketMap) {
		this.virtualTickets = virtualTicketMap;
	}

	public void setPhysicalTicketMap(HashMap<Integer, Ticket> physicalTicketMap) {
		this.physicalTickets = physicalTicketMap;
	}
	
	public void addVirtualTicket(Ticket ticket) {
		String key = ticket.getVehicle().getLicensePlate();
		virtualTickets.put(key, ticket);
	}
	
	public void addPhysicalTicket(Ticket ticket) {
		int key = ticket.getTicketNumber();
		physicalTickets.put(key, ticket);
	}

	public void addSystemAccount(SystemAccount systemAccount) {
		String key = systemAccount.getUsername();
		systemAccounts.put(key, systemAccount);
	}
	
	public SystemAccount getSystemAccount(String username) {
		return systemAccounts.get(username);
	}
	
	public Ticket getTicketByNumber (int ticketNumber) {
		// lookup the ticket by number 
		Ticket ticket = physicalTickets.get(ticketNumber);
		// ticket can only be used once, so remove it
		physicalTickets.remove(ticketNumber);
		return ticket;
	}

	public Ticket getTicketForLicensePlate (String licensePlate) {
		Ticket ticket = virtualTickets.get(licensePlate);
		// ticket can only be used once, so remove it
		virtualTickets.remove(licensePlate);
		return ticket;
	}
	
	public void saveTransaction (Transaction transaction) {
		// increment the transaction id and use it as a key
		transactionID++;
		transactionRecords.put(transactionID, transaction);
	}
	
	
	public void updateOccupancyData (Date timestamp, int occupancy) {
		
		//System.out.println("Occupancy: " + timestamp + " : " + occupancy);
		
		occupancyData.put(timestamp,  occupancy);
		
	}

	private void initializeData() {
		
		// loop through a few hundred entries and exits
		
		//Date monthsBack = Date() - 100000;
		
	}
	

}
