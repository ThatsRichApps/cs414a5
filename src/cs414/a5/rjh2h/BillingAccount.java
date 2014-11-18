package cs414.a5.rjh2h;

public class BillingAccount {
	
	private String firstName;
	private String lastName;
	private String address;
	private String licensePlate;
	private CreditCard creditCard;
	
	public BillingAccount() {
		super();
	}

	public BillingAccount(String licensePlate) {
		super();
		this.licensePlate = licensePlate;
	}

	@Override
	public String toString() {
		return "BillingAccount";
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getAddress() {
		return address;
	}

	public String getLicensePlate() {
		return licensePlate;
	}

	public CreditCard getCreditCard() {
		return creditCard;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}

	public void setCreditCard(CreditCard creditCard) {
		this.creditCard = creditCard;
	}
	
}
