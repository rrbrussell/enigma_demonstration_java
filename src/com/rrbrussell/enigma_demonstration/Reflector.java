/**
 * Copyright (c) 2014 Robert R. Russell
 */
package com.rrbrussell.enigma_demonstration;

import java.util.EnumMap;

/**
 * The main class used by all of the Enigma's reflectors.
 * <p>
 * This class is not usable by itself. The subclasses must be used because
 * Java Enumerations cannot store arrays.
 * 
 * @author Robert R. Russell
 * @author robert@rrbrussell.com
 * 
 */
public class Reflector {
	
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

	//private char[] wiring;
	
	private EnumMap<Characters, Characters> wiringMap;
	
	
	public Reflector(Reflectors chosenReflector) {
		//this.wiring = chosenReflector.getWiringTable().toCharArray();
		this.wiringMap = new EnumMap<Characters, Characters>(Characters.class);
		
		Characters[] keyArray = Characters.values();
		Characters[] valuesArray = Utility.stringToCharactersArray(
				chosenReflector.getWiringTable());
		
		for(int i = 0; i < keyArray.length; i++) {
			this.wiringMap.put(keyArray[i], valuesArray[i]);
		}
		
	}
	
	/**
	 * Encipher the input value 
	 * @param Plaintext
	 * @return Ciphertext
	 */
	/*public char Encipher(char Plaintext) {
		if (!Rotor.SatisfiesRingConstraint(Plaintext)) {
			throw new RingSizeException();
		}

		Characters ciphertext =	encipher(Characters.fromChar(Plaintext));
		return ciphertext.toChar();
	}*/
	
	/**
	 * @param plaintext
	 * @return
	 */
	public Characters encipher(Characters plaintext) {
		return this.wiringMap.get(plaintext);
	}

}
