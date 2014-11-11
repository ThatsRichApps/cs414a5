package cs414.a5.rjh2h;


public class GarageMain {
	
	private static ParkingGarage garage;

	public static void main(String[] args) {
		
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
	    
				garage = new ParkingGarage();
				garage.setOpen(true);
				
			}
		});

	}

}
