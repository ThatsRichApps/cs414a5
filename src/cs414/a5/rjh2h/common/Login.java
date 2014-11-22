package cs414.a5.rjh2h.common;

import cs414.a5.rjh2h.ui.LoginUI;

public class Login {
	
	private LoginUI loginUI;
	
	public Login(){
		
		loginUI = new LoginUI();
		
	}
	
	
	public static boolean authenticate(String username, String password) {
		// hardcoded username and password
		if (username.equals("rich") && password.equals("password")) {
			return true;
		}

		return false;
	    
	}
	
}
