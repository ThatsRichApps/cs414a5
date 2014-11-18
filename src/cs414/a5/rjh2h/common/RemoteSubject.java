package cs414.a5.rjh2h.common;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteSubject extends Remote {
	
	public void addObserver(RemoteObserver o) throws RemoteException;
	public void remoteObserver(RemoteObserver o) throws RemoteException;
	

}
