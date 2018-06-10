package de.omikron.main;

public class Schueler {
	
	private Klasse klasse;
	private String name;
	private int points;
	
	public Schueler(String name, Klasse klasse) {
		this.setName(name);
		this.points = 0;
		this.setKlasse(klasse);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPoints() {
		return points;
	}

	public void addPoint() {
		this.points++;
	}
	
	public void removePoint() {
		if(this.points > 0) {
			this.points--;
		}
	}

	public Klasse getKlasse() {
		return klasse;
	}

	public void setKlasse(Klasse klasse) {
		this.klasse = klasse;
	}
}
