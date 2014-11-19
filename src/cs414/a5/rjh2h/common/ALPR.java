package cs414.a5.rjh2h.common;

import java.util.Random;

public class ALPR {
	
	private String licensePlate;
	// license plate is the combination of the state code
	// plus the license number with dashes: CO-AAA-123
	
	public ALPR () {
		// ALPR is instantiated once per vehicle
		// and used to determine licensePlate
	}
	
	public ALPR(String licensePlate) {
		
		this.licensePlate = licensePlate;
		
	}
	
	public String getLicensePlate() {
		
		if (this.licensePlate == null) {
			this.licensePlate = getStateCode() + "-" + getLicenseNumber();
		}
	
		return this.licensePlate;
	}
	
	public String getLicenseNumber() {
		
		// this would query the camera and the ocr software 
		// to determine the actual license number in real case
		// until then, generate a random license plate
		
		Random r;
			
		// generate a random plate number
		r = new Random();
		char c1 = (char)(r.nextInt(26) + 'A');
		r = new Random();
		char c2 = (char)(r.nextInt(26) + 'A');
		r = new Random();
		char c3 = (char)(r.nextInt(26) + 'A');
		r = new Random();
		char c4 = (char)(r.nextInt(10) + '0');
		r = new Random();
		char c5 = (char)(r.nextInt(10) + '0');
		r = new Random();
		char c6 = (char)(r.nextInt(10) + '0');
			
		String alphaPlate = new String(new char[]{ c1, c2, c3, '-', c4, c5, c6});
		this.licensePlate = alphaPlate;
			
		return alphaPlate;
	}

	public String getStateCode() {
	
		// return Colorado as the state code
		// this would also query the camera and ocr software
		// to determine action statecode in real case
		return ("CO");
	}
	
}
