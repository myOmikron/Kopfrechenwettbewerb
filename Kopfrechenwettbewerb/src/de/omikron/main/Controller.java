package de.omikron.main;

public class Controller {

	public static boolean checkClassInput(String in) {
		if(in.matches("\\d[a-zA-Z]")) {
			return true;
		}
		WarningFrame frame = new WarningFrame();
		frame.setWarning("Bitte eine gültige Klasse eingeben!");
		return false;
	}
	
	public static boolean isNotEmpty(String in) {
		if(in == "" || in == null || in.equals(null) || in.equalsIgnoreCase("")) {
			WarningFrame frame = new WarningFrame();
			frame.setWarning("Bitte geben Sie etwas ein!");
			return false;
		}
		return true;
	}
}
