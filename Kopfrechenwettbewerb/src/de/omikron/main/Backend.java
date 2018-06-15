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
	
	public void addPoint(Schueler schueler) {
		schueler.addPoint();
	}
	
	public boolean removePoint(Schueler schueler) {
		boolean tmp = schueler.removePoint();
		return tmp;
	}
	
	public boolean addSchueler(String newSchueler, String klasse) {
		klasse = klasse.toUpperCase();
		Klasse kl = addKlasse(klasse);
		if(!appendSchueler(newSchueler, kl)) {
			return false;
		}
		front.updateList(schueler.get(schueler.size()-1), schueler.size()-1);
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
	
	protected void rmSchueler(int rmSchueler) {
		
		for(int i = 0; i < schueler.size(); i++) {
			if(schueler.get(rmSchueler).getKlasse().equals(schueler.get(i).getKlasse())) {
				schueler.remove(rmSchueler);
				front.updateDeleteOperation(rmSchueler);
				return;
			}
		}
		removeKlasse(schueler.get(rmSchueler).getKlasse().getName());
		schueler.remove(rmSchueler);
		
		front.updateDeleteOperation(rmSchueler);
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
	
	public void editSchueler(String name, String klasse, int index) {
		int points = schueler.get(index).getPoints();
		Schueler s = new Schueler(name, new Klasse(klasse));
		s.setPoint(points);
		schueler.set(index, s);
		front.updateList(s, index);
	}
	
	public void reset() {
		klassen = new ArrayList<>();
		schueler = new ArrayList<>();
		front.reset();
		front = new InitFrame(this);
		front.init();
	}

}
