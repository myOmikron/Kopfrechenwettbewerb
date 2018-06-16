package de.omikron.main;

public abstract class VarSettings {
	
	private static int gameLength = 15;

	public static int getGameLength() {
		return gameLength;
	}

	public static void setGameLength(int gameLength) {
		VarSettings.gameLength = gameLength;
	}

}
