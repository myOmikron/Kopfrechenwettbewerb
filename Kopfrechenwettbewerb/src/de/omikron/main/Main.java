package de.omikron.main;

import java.awt.EventQueue;

public class Main {
	
	/**
	 * 
	 * @author Niklas Pfister
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				Backend back = new Backend();
				back.init();
			}
		});
	}

}
