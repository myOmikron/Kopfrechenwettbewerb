package de.omikron.main;

public class Controller {

	public static boolean checkClassInput(String in) {
		if(in.matches("\\d[a-zA-Z]")) {
			return true;
		}
		return false;
	}
}
