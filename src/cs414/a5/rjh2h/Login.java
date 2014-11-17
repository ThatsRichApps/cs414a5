package cs414.a5.rjh2h;

public class Login {
	
	public static boolean authenticate(String username, String password) {
		// hardcoded username and password
		if (username.equals("rich") && password.equals("password")) {
			return true;
		}

		return false;
	    
	}
	
}
