package de.omikron.main;

import java.util.ArrayList;

public class Klasse {

	private ArrayList<Schueler> schueler;
	private String name;
	
	public Klasse(String name) {
		this.setName(name);
	}

	public ArrayList<Schueler> getSchueler() {
		return schueler;
	}
	
	public boolean appendSchueler(String newSchueler) {
		for(int i = 0; i < this.schueler.size(); i++) {
			if(this.schueler.get(i).getName() == newSchueler) {
				return false;
			}
		}
		this.schueler.add(new Schueler(newSchueler));
		return true;
	}
	
	public boolean removeSchueler(Schueler rmSchueler) {
		if(schueler.contains(rmSchueler)) {
			schueler.remove(rmSchueler);
			return true;
		}
		return false;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
