package cs414.a5.rjh2h.server;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Random;

import cs414.a5.rjh2h.common.*;
import cs414.a5.rjh2h.ui.*;

public class GarageImpl extends UnicastRemoteObject implements Garage, RemoteSubject, ActionListener {
	
	// Parking Garage occupancy is observable.  Also, the garage observes the entryKiosk
	// in order to know when cars are entering
	
	private static final long serialVersionUID = 1L;
	private boolean isOpen;
	private int currentOccupancy;
	private GarageUI garageUI;
	private SystemPreferences systemPreferences;
	@SuppressWarnings("unused")
	private UsageReports usageReports;
	private DataStorage dataStorage;
	private int ticketID = 100;
	private LoginUI loginUI;
	private boolean loggedIn;
	
	private ArrayList<RemoteObserver> observers = new ArrayList<RemoteObserver>();
	
	public GarageImpl() throws java.rmi.RemoteException {
		super();
		
		// create the main gui here
		garageUI = new GarageUI();
		systemPreferences = new SystemPreferences(this);
		dataStorage = new DataStorage();

		garageUI.addActionListeners(this);
		
		this.setOpen(true);
		
		this.currentOccupancy = 0;
		
		simulateData();
		
	}
	
	@Override
	public String toString() {
		return "ParkingGarage";
	}

	public boolean isOpen() {
		return isOpen;
	}

	public void setOpen(boolean isOpen) {
		this.isOpen = isOpen;
		notifyObservers();
	}

	
	public DataStorage getDataStorage() {
		return dataStorage;
	}

	public void setDataStorage(DataStorage dataStorage) {
		this.dataStorage = dataStorage;
	}

	public SystemPreferences getSystemPreferences() {
		return systemPreferences;
	}

	public void updateOccupancy(String entryOrExit) {
		// successful entry or exit, update occupancy and observers
		
		//System.out.println("occupancy update for: " + entryOrExit);
		
		if (entryOrExit.equals("entry")) {
			//System.out.println("occupancy = " + currentOccupancy);
			currentOccupancy++;
		} else if (entryOrExit.equals("exit")) {
			currentOccupancy--;
			if (currentOccupancy < 0 ) {currentOccupancy = 0;};
		}
		
		garageUI.setMessage("Current Occupancy: " + currentOccupancy);
		garageUI.setVisible(true);
		// disable buttons until login is verified
		garageUI.enableButtons(false);

		Date timeNow = new Date();
		dataStorage.updateOccupancyData(timeNow, currentOccupancy);
				
		// If the garage reaches max occupancy, close it down by 
		// notifying the sign and the entryKiosk
		if (currentOccupancy >= systemPreferences.getMaxOccupancy()) {
			this.isOpen = false;		
			notifyObservers();
		} else if (this.isOpen == false) {
			this.isOpen = true;		
			notifyObservers();
		}
			
	}
	
	public BigDecimal getRate() {
		
		return getSystemPreferences().getRate();
		
	}
	
	public BigDecimal getMaxFee() {
		
		return getSystemPreferences().getMaxFee();
		
	}
	
	public void actionPerformed(ActionEvent event) {
		
		// this is the action listener for the buttons on the garageUI
		// loginUI is a dialog for verifying admins
		// launches an adminUI for changing preferences and a usageReportsUI for 
		// viewing using data
		
		String eventName = event.getActionCommand();
		
		switch (eventName) {
		
		case "SysAdmin":
			systemPreferences.showAdminUI();
			break;
		case "ShowUsage":
			usageReports = new UsageReports(dataStorage);
			break;
		case "Login":
			loginUI = new LoginUI();
			loginUI.addActionListeners(this);
			break;
		case "Authenticate":
			loggedIn = authenticate(loginUI.getUsername(), loginUI.getPassword());
			
			if (loggedIn) {
				garageUI.enableButtons(true);
			}
			break;
		case "Cancel":
			// close the login window
			loginUI.dispose();
			break;
			
		}
	
	}
	
	private boolean authenticate (String username, String password) {

		//System.out.println("check for user: " + username + " pass: " + password);
		return (dataStorage.validateSystemAccount(username, password));
		
	}
	
	public void addVirtualTicket(Ticket ticket) {
		
		dataStorage.addVirtualTicket(ticket);
			
	}
	
	public void addPhysicalTicket(Ticket ticket) {
		
		dataStorage.addPhysicalTicket(ticket);
		
	}
	
	public Ticket getTicketNumber (int ticketNumber) {
		
		return dataStorage.getTicketByNumber(ticketNumber);
		
	}
	
	public Ticket getTicketForLicensePlate(String licensePlate) {
		
		return dataStorage.getTicketForLicensePlate(licensePlate);
		
	}
	
	public void saveTransaction(Transaction transaction) {
		
		dataStorage.saveTransaction(transaction);
	
	}

	@Override
	public void addObserver(RemoteObserver o) throws RemoteException {
		observers.add(o);
	}


	@Override
	public void remoteObserver(RemoteObserver o) throws RemoteException {
		observers.remove(o);
	}
	
	private void notifyObservers() {
		Iterator<RemoteObserver> i = observers.iterator();
		while ( i.hasNext() ) {
			RemoteObserver o = (RemoteObserver) i.next();
			try {
				o.update( this );
			} catch (RemoteException re) {
				// do something
			}
		}	
	}
	
	public String getStatus() throws RemoteException {
		if (isOpen) {
			return ("GarageOpen");
		} else {
			return ("GarageFull");
		}
	}

	@Override
	public void addTicket(Ticket currentTicket) throws RemoteException {
		//System.out.println("adding ticket to dataStorage");
		dataStorage.addPhysicalTicket(currentTicket);
		dataStorage.addVirtualTicket(currentTicket);
	}
	
	@Override
	public int getNextTicketID() throws RemoteException {
		ticketID++;
		return ticketID;
	}
	
	private void simulateData() {
		
	    Calendar calendar = Calendar.getInstance();
	    calendar.add(Calendar.MONTH, -10);   //Go to date, x months ago 
    	Date simulatedTime = calendar.getTime(); 
    	
    	Date timeNow = new Date();
    	
		// simulate 1000 entries and exits
		int occupancy = 0;
		int maxOccupancy = 300;
		
		int randomEntryPercent = 80;
		// 80% will be entries at first, then switch to 80% exits when capacity is 250
		
		// track occupancy so that cars can exit if there are none
		for (int i = 0; i < 100000; i++) {
		
				Random rand = new Random();
		    
				// change the probability so that occupancy fluctuates
				
				if (occupancy == 20) {
					randomEntryPercent = 60; 
				} else if (occupancy == 250) {
					randomEntryPercent = 40;
				}
				
				
				if (rand.nextInt(100) <= randomEntryPercent) {
				
					// simulate entry is less than max
					if (occupancy <= maxOccupancy) {
						occupancy++;
					}
					
				} else {
					
					// simulate exit unless already empty
					if (occupancy > 0) {
						occupancy--;
					}
				
				}
				
				//System.out.println(simulatedTime + " : " + occupancy);
				dataStorage.updateOccupancyData(simulatedTime, occupancy);
				
				// simulate time passing
				calendar.add(Calendar.MINUTE, rand.nextInt(60));   //add up to 60 minutes 
				simulatedTime = calendar.getTime(); 
				
				// stop when we get to now
				if (simulatedTime.after(timeNow)) break;
		    	
		}
		
		
	}

	@Override
	public BillingAccount getBillingAccount(String licensePlate) throws RemoteException {
		
		return dataStorage.getBillingAccount(licensePlate);

	}

	@Override
	public void addBillingAccount(BillingAccount newAccount) throws RemoteException {
		dataStorage.addBillingAccount(newAccount);
	}

	
	
	
	
}

