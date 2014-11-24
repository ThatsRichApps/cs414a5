package cs414.a5.rjh2h.common;

import java.io.Serializable;

public class Vehicle implements Serializable {
	
	private static final long serialVersionUID = 1L;
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

	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}
	
}
