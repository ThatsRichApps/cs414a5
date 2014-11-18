package cs414.a5.rjh2h.server;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

import cs414.a5.rjh2h.common.Garage;

public class GarageServer {
	
	@SuppressWarnings("unused")
	private String url;
	
	public GarageServer(String url) {
		this.url = url;
		try {
			LocateRegistry.createRegistry(2000);  
			Garage garage = new GarageImpl();
				Naming.rebind(url, garage);
				System.out.println("Garage server running...");
		} catch (Exception e) {
				System.out.println("Trouble: " + e);
		}
	}

	// run the program using 
	// java GarageServer <host> <port>

	public static void main(String args[]) {
		String url = new String("rmi://" + args[0] + ":" + args[1] + "/GarageService");
		new GarageServer(url);
	}

}

