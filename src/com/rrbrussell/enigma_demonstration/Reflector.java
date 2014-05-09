/**
 * Reflector.java Copyright (c) 2014 Robert R. Russell
 */
package com.rrbrussell.enigma_demonstration;

import java.util.EnumMap;

/**
 * The main class used by all of the Enigma's reflectors.
 * 
 * @since v0.0
 * 
 * @author Robert R. Russell
 * @author robert@rrbrussell.com
 */
public class Reflector {
	
	/**
	 * 
	 * @author Robert R. Russell
	 * @author robert@rrbrussell.com
	 */
	public enum Reflectors {
		WideB("YRUHQSLDPXNGOKMIEBFZCWVJAT"),
		WideC("FVPJIAOYEDRZXWGCTKUQSBNMHL");
		
		private String wiringTable;
		
		Reflectors(String wt) {
			this.wiringTable = wt;
		}
		
		public String getWiringTable() {
			return this.wiringTable;
		}
	}

	private EnumMap<Characters, Characters> wiringMap;
	
	/**
	 * @since v0.0
	 * @param chosenReflector
	 */
	public Reflector(Reflectors chosenReflector) {
		this.wiringMap = new EnumMap<Characters, Characters>(Characters.class);
		
		Characters[] keyArray = Characters.values();
		Characters[] valuesArray = Utility.stringToCharactersArray(
				chosenReflector.getWiringTable());
		
		for(int i = 0; i < keyArray.length; i++) {
			this.wiringMap.put(keyArray[i], valuesArray[i]);
		}
		
	}
	
	/**
	 * @since v0.2
	 * 
	 * @param plaintext
	 * @return
	 */
	public Characters encipher(Characters plaintext) {
		return wiringMap.get(plaintext);
	}

}
