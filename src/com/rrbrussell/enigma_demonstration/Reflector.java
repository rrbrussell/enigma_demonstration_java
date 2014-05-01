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

	protected int[] Wiring;
	
	/**
	 * Encipher the input value 
	 * @param Plaintext
	 * @return Ciphertext
	 */
	public char Encipher(int Plaintext) {
		if (!Rotor.SatisfiesRingConstraint(Plaintext)) {
			throw new RingSizeException();
		}
		return Utility.intToChar(Wiring[Plaintext]);
	}
	
	/**
	 * Encipher the input value 
	 * @param Plaintext
	 * @return Ciphertext
	 */
	public char Encipher(char Plaintext) {
		if (!Rotor.SatisfiesRingConstraint(Plaintext)) {
			throw new RingSizeException();
		}
		return Utility.intToChar(Wiring[Utility.charToInt(Plaintext)]);
	}

}
