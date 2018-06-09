package de.omikron.main;

import java.util.ArrayList;

public class Backend {
	
	private ArrayList<Klasse> klassen;
	
	public Backend() {
		klassen = new ArrayList<>();
	}
	
	public void init() {
		Frontend front = new Frontend();
		front.init();
	}

}
