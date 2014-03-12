/**
 * 
 */
package com.rrbrussell.enigma_demonstration;

/**
 * The API used by all of the Enigma's rotors.
 * 
 * @author Robert R. Russell
 *
 */
public abstract class Rotor {
	/**
	 * Moves the Rotor on the spindle.
	 * @return Returns true if the Rotor instance has stepped past its turnover point. 
	 */
	public abstract boolean Step();
	
	/**
	 * Encipher the input value 
	 * @param input Plaintext
	 * @return Ciphertext
	 */
	public abstract int Encipher(int input);
	
	/**
	 * The Ringstellung is the offset between 0 the 0 on the wiring and 0 on the indicator ring.
	 * @param offset The amount to offset the wiring.
	 */
	public abstract void SetRingstellung(int offset);
	
	/**
	 * The Grundstellung is the Indicator position for starting encipherment
	 * @param indicator The new Indicator value.
	 */
	public abstract void SetGrundstellung(int indicator);
	
	protected static final int RingSize = 26;
}
