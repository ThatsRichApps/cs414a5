package cs414.a5.rjh2h;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;

import cs414.a5.rjh2h.ui.EntryKioskUI;
import cs414.a5.rjh2h.ui.PhysicalTicketUI;

public class EntryKiosk extends Observable implements Observer, ActionListener {

	// EntryKiosk is a client implementation that connects to the ParkingGarageServer
	
	private static final int TICKET_LEVEL_WARNING = 10;
	private EntryKioskUI entryUI;
	private boolean isGarageOpen;
	private Gate entryGate;
	private Ticket currentTicket;
	private int ticketNumber = 0; 
	private int ticketLevel = 1000;
	
	private ParkingGarage garage;
	
	/*
	public static void main(String[] args) {
		
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				
			}
		});

	}
	*/
	
	public EntryKiosk(ParkingGarage garage) {
	
		// set the garage as observer, notify as cars enter
		this.garage = garage;
		this.addObserver(garage);
		
		// create the entry ui and listen for it's button
		entryUI = new EntryKioskUI();
		entryUI.addButtonActionListener(this);
		
		// create the entry Gate and make sure it starts closed
		entryGate = new Gate();
		
		entryGate.addObserver(this);
		
		entryGate.closeGate();
		
	}

	@Override
	public String toString() {
		return "EntryKiosk";
	}

	public void update(Observable o, Object arg) {
		// Entry observes the ParkingGarage.  If garage is closed, entry is not allowed.
		
		//System.out.println("Update called:" + o + ":" + arg);
		
		// should get the state from the garage here
		
		
		
		
		
		String statusMessage = (String) arg;
		
		switch (statusMessage) {
			
			case ("GarageFull"):
				isGarageOpen = false;
		    	entryUI.setMessage1("Garage is Full");
				entryUI.setMessage2("");
				entryUI.enableEnterButton(isGarageOpen);
	    		break;
			case ("GarageOpen"):
				isGarageOpen = true;
		    	entryUI.setMessage1("Press Top Button to Enter");
				entryUI.setMessage2("");
				entryUI.enableEnterButton(isGarageOpen);
				break;
			case ("GateOpen"):
				entryUI.setGateStatus(true);
				entryUI.enableEnterButton(false);
				break;
			case ("GateClosed"):	
				entryUI.setGateStatus(false);
				entryUI.enableEnterButton(false);
				entryUI.enableEnterButton(isGarageOpen);
				break;

		}
				
		
	}
	
	public void actionPerformed(ActionEvent e) {
    
		String cmd = e.getActionCommand();
	    
		switch (cmd) {
		
		case "EnterButton": 
			
			// first the driver presses the enter button, this creates a ticket
			ticketNumber++;
			
			// if the rate changes, this driver only has to pay the rate at the time
			// that the ticket is tendered, therefore, we store this on / with the ticket
			BigDecimal currentRate = garage.getSystemPreferences().getHourlyFee();
			
			currentTicket = new Ticket(ticketNumber, currentRate);
			
			Date timeIn = currentTicket.getTimeIn();
			SimpleDateFormat dateFormatter = new SimpleDateFormat("MMM yyyy HH:mm z");

			String dateOut = dateFormatter.format(timeIn);

			String entryMessage = "Time is " + dateOut;
			
			String entryMessage2 = " License: " + 
					currentTicket.getVehicle().getLicensePlate();
			
			entryUI.setMessage1(entryMessage);
			entryUI.setMessage2(entryMessage2);
			
			entryUI.enableTicketButtons(true);
			entryUI.enableEnterButton(false);
			
			break;
			
		case "DispenseTicketButton":
			// this dispenses an actual ticket, shows in the printedTicket UI

			entryUI.enableTicketButtons(false);
			entryUI.enableEnterButton(true);

			ticketLevel--;
			@SuppressWarnings("unused")
			PhysicalTicketUI printedTicket = new PhysicalTicketUI(currentTicket);	
			ticketLevel--;

			if (ticketLevel < TICKET_LEVEL_WARNING) {
				// warn someone that the physical ticket level is low
				// maybe with a notification
			}
			
			// add to the list of physical tickets current out (by number)
			garage.addPhysicalTicket(currentTicket);
			// also track it as a virtual ticket if possible
			garage.addVirtualTicket(currentTicket);
			
			entryUI.setMessage1("Press Top Button to Enter");
	    	entryUI.setMessage2("");
	    	
			setChanged();
			notifyObservers("entry");
			
			entryGate.openGateForCar();

			break;
			
		case "VirtualTicketButton":

			entryUI.enableTicketButtons(false);
			entryUI.enableEnterButton(true);

			// just track it as a virtual ticket (by license plate)
			garage.addVirtualTicket(currentTicket);
		
			entryUI.setMessage1("Press Top Button to Enter");
	    	entryUI.setMessage2("");
	    	
			setChanged();
			notifyObservers("entry");

			entryGate.openGateForCar();

			break;
			
		
		}
			
		
	}
	
	
	
	
}