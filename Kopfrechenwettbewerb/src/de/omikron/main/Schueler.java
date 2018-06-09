package de.omikron.main;

public class Schueler {
	
	private String name;
	private int points;
	
	public Schueler(String name) {
		this.setName(name);
		this.points = 0;
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
	
	

}
