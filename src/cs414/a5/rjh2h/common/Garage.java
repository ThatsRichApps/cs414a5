package cs414.a5.rjh2h.common;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Garage extends Remote {

	public String getStatus() throws RemoteException;

	public void addObserver(RemoteObserver o) throws RemoteException;
	public void addTicket(Ticket currentTicket) throws RemoteException;

	public void updateOccupancy(String string) throws RemoteException;
	
}
