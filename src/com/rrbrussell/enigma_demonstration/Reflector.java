/**
 * 
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
	public int Encipher(int Plaintext) {
		if ( Plaintext < 0 || Plaintext >= Rotor.RingSize) {
			throw new RingSizeException();
		}
		return Wiring[Plaintext];
	}

}
