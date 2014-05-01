/**
 * Copyright (c) 2014 Robert R. Russell
 */
package com.rrbrussell.enigma_demonstration;

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

	private char[] wiring;
	
	
	public Reflector(Reflectors chosenReflector) {
		this.wiring = chosenReflector.getWiringTable().toCharArray();
	}
	
	/*/**
	 * Encipher the input value 
	 * @param Plaintext
	 * @return Ciphertext
	 */
	/*public char Encipher(int Plaintext) {
		if (!Rotor.SatisfiesRingConstraint(Plaintext)) {
			throw new RingSizeException();
		}
		return Utility.intToChar(wiring[Plaintext]);
	}*/
	
	/**
	 * Encipher the input value 
	 * @param Plaintext
	 * @return Ciphertext
	 */
	public char Encipher(char Plaintext) {
		if (!Rotor.SatisfiesRingConstraint(Plaintext)) {
			throw new RingSizeException();
		}
		return wiring[Utility.charToInt(Plaintext)];
	}

}
