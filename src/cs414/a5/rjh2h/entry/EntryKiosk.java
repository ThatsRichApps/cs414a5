package cs414.a5.rjh2h.entry;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;

import cs414.a5.rjh2h.common.Garage;
import cs414.a5.rjh2h.common.Gate;
import cs414.a5.rjh2h.common.RemoteObserver;
import cs414.a5.rjh2h.common.Sign;
import cs414.a5.rjh2h.common.Ticket;
import cs414.a5.rjh2h.ui.EntryKioskUI;
import cs414.a5.rjh2h.ui.PhysicalTicketUI;

public class EntryKiosk extends UnicastRemoteObject implements RemoteObserver, ActionListener, Serializable, Observer {

	// EntryKiosk is a client implementation that connects to the ParkingGarageServer
	
	private static final long serialVersionUID = 1L;
	private static final int TICKET_LEVEL_WARNING = 10;
	
	private EntryKioskUI entryUI;
	private Sign entrySign;
	private boolean isGarageOpen;
	private Gate entryGate;
	private Ticket currentTicket;
	private int ticketNumber = 0; 
	private int ticketLevel = 1000;
	
	private Garage garage;
		
	public static void main(String[] args) {
		Garage garage = null;
		try {
			garage = (Garage) 
					Naming.lookup("rmi://" + args[0] + ":" + args[1]  + "/GarageService");
			
			@SuppressWarnings("unused")
			EntryKiosk entryKiosk = new EntryKiosk(garage);
			
		} catch (MalformedURLException murle) {
			System.out.println("MalformedURLException");
			System.out.println(murle);
			System.exit(-1);
		} catch (RemoteException re) {
			System.out.println("RemoteException - Make sure server is started"); 
			System.out.println(re);
			System.exit(-1);
		} catch (NotBoundException nbe) {
			System.out.println("NotBoundException");
			System.out.println(nbe);
			System.exit(-1);
		}
		
		
	}
	
	public EntryKiosk(Garage garage) throws RemoteException {
		
		this.garage = garage;
		
		// create the entry ui and listen for it's buttons
		entryUI = new EntryKioskUI();
		entryUI.addButtonActionListener(this);
		
		// create the entry Gate and make sure it starts closed
		entryGate = new Gate();
		entryGate.addObserver(this);
		
		entryGate.closeGate();
		
		entrySign = new Sign();
		
		try {
			this.garage.addObserver(this);
			setGarageOpen(this.garage.isOpen());
		} catch (RemoteException re) {
			// do something
		}	
		
	}

	@Override
	public String toString() {
		return "EntryKiosk";
	}

	public void setGarageOpen(boolean isGarageOpen) {
		this.isGarageOpen = isGarageOpen;
	
		if (isGarageOpen) {
			
	    	entryUI.setMessage1("Press Top Button to Enter");
			entryUI.setMessage2("");
			entrySign.setMessage("Garage is Open");
			entryUI.enableEnterButton(isGarageOpen);
			
		} else {
		
			entryUI.setMessage1("Garage is Full");
			entryUI.setMessage2("");
			entrySign.setMessage("Garage is Full");
			entryUI.enableEnterButton(isGarageOpen);
		
		}

	}

	public void update(Object o) throws RemoteException {
		// Entry observes the ParkingGarage.  If garage is closed, entry is not allowed.
		//System.out.println("garage state change");
		
		// get the state from the garage
		String status = "";
		
		if (o.equals(garage)) {
			try {
				status = garage.getStatus();
			} catch (RemoteException re) {
				// do something
			}	
		} 	
			
		switch (status) {
			
			case ("GarageFull"):
				setGarageOpen(false);
	    		break;
			case ("GarageOpen"):
				setGarageOpen(true);
				break;

		}
		
	}
	
	public void actionPerformed(ActionEvent e) {
    
		String cmd = e.getActionCommand();
	    
		switch (cmd) {
		
		case "EnterButton": 
			
			// first the driver presses the enter button, this creates a ticket
			ticketNumber++;
			// need to get the next ticket from the garage, not increment here
			
			// if the rate changes, this driver only has to pay the rate at the time
			// that the ticket is tendered, therefore, we store this on / with the ticket
			BigDecimal currentRate;
			try {
				currentRate = garage.getRate();
			} catch (RemoteException e2) {
				e2.printStackTrace();
				currentRate = new BigDecimal("2.50");
			}
			
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

			entryUI.setMessage1("Press Top Button to Enter");
	    	entryUI.setMessage2("");
	    	
			entryGate.openGateForCar();
			
			// add to the list of tickets current outstanding
			try {
				garage.addTicket(currentTicket);
				garage.updateOccupancy("entry");
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			break;
			
		case "VirtualTicketButton":

			entryUI.enableTicketButtons(false);
			entryUI.enableEnterButton(true);
			
			entryUI.setMessage1("Press Top Button to Enter");
	    	entryUI.setMessage2("");
			entryGate.openGateForCar();
			
			// add to the list of tickets current outstanding
			try {
				garage.addTicket(currentTicket);
				garage.updateOccupancy("entry");
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			break;
		
		}
		
	}

	@Override
	public void update(Observable o, Object arg) {
		
		// Entry observes the gate with a standard java observable / observer 
		
		// get the state
		String status = "";
		
		if (o.equals(entryGate)) {
			status = entryGate.getStatus();
		} 	
		
		switch (status) {
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

	
}