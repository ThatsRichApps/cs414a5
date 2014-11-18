package cs414.a5.rjh2h;

public class Vehicle {
	
	private String licensePlate;
	
	public Vehicle() {
		
		// if no license or plate is given, we use the ALPR System to determine it
		ALPR licenseSystem = new ALPR();
		this.licensePlate = licenseSystem.getLicensePlate(); ;

	}
	
	@Override
	public String toString() {
		return "Vehicle";
	}

	public Vehicle(String licensePlate) {
		this.licensePlate = licensePlate;
	}

	public String getLicensePlate() {
		return licensePlate;
	}
	
}
