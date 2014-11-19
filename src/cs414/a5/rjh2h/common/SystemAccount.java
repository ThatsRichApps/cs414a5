package cs414.a5.rjh2h.common;

public class SystemAccount {
	
	private String username;
	private String password;
	private String role;
	
	public SystemAccount(){
	}

	public SystemAccount(String username, String password, String role) {
		super();
		this.username = username;
		this.password = password;
		this.role = role;
	}
	
	@Override
	public String toString() {
		return "SystemAccount";
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}
	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
