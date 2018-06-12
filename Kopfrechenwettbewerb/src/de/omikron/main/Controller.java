package de.omikron.main;

public class Controller {

	public static boolean checkClassInput(String in) {
		if(in.matches("\\d[a-zA-Z]")) {
			return true;
		}
		return false;
	}
	
	public static boolean isNotEmpty(String in) {
		if(in == "" || in == null || in.equals(null) || in.equalsIgnoreCase("")) {
			return false;
		}
		return true;
	}
}
