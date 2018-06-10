package de.omikron.main;

import java.util.ArrayList;

public class Backend {
	
	private ArrayList<Klasse> klassen;
	private ArrayList<Schueler> schueler;
	
	private InitFrame front;
	
	public Backend() {
		klassen = new ArrayList<>();
		schueler = new ArrayList<>();
	}
	
	public void init() {
		front = new InitFrame(this);
		front.init();
	}
	
	public boolean removeSchueler(String name) {
		return rmSchueler(name);
	}
	
	public boolean addSchueler(String newSchueler, String klasse) {
		klasse = klasse.toUpperCase();
		Klasse kl = addKlasse(klasse);
		if(!appendSchueler(newSchueler, kl)) {
			return false;
		}
		front.updateList(schueler);
		return true;
	}

	private boolean appendSchueler(String newSchueler, Klasse klasse) {
		for(int i = 0; i < this.schueler.size(); i++) {
			if(this.schueler.get(i).getName() == newSchueler) {
				return false;
			}
		}
		this.schueler.add(new Schueler(newSchueler, klasse));
		return true;
	}
	
	private boolean rmSchueler(String rmSchueler) {
		for(int i = 0; i < schueler.size(); i++) {
			if(schueler.get(i).getName().equalsIgnoreCase(rmSchueler)) {
				Klasse tmp = schueler.get(i).getKlasse();
				schueler.remove(i);
				for(int j = 0; j < schueler.size(); j++) {
					if(schueler.get(i).getKlasse().equals(tmp)) {
						return true;
					}
				}
				removeKlasse(tmp.getName());
				return true;
			}
		}
		return false;
	}

	public ArrayList<String> getKlassen() {
		ArrayList<String> tmp = new ArrayList<>();
		for(int i = 0; i < klassen.size(); i++) {
			tmp.add(klassen.get(i).getName());
		}
		return tmp;
	}
	
	public ArrayList<Schueler> getSchuelerlist() {
		return schueler;
	}
	
	public ArrayList<String> getSchueler() {
		ArrayList<String> tmp = new ArrayList<>();
		for(int i = 0; i < schueler.size(); i++) {
			tmp.add(schueler.get(i).getName());
		}
		return tmp;
	}

	private Klasse addKlasse(String name) {
		for(int i = 0; i < klassen.size(); i++) {
			if(klassen.get(i).getName().equalsIgnoreCase(name)) {
				return klassen.get(i);
			}
		}
		Klasse newKlasse = new Klasse(name);
		klassen.add(newKlasse);
		return newKlasse;
	}
	
	private boolean removeKlasse(String name) {
		for(int i = 0; i < klassen.size(); i++) {
			if(klassen.get(i).getName().equalsIgnoreCase(name)) {
				klassen.remove(i);
				return true;
			}
		}
		return false;
	}

}
