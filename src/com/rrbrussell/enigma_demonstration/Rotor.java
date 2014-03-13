/**
 * 
 */
package com.rrbrussell.enigma_demonstration;

/**
 * The main class used by all of the Enigma's rotors.
 * <p>
 * This class is not usable by itself. The subclasses must be used because
 * Java Enumerations cannot store arrays.
 * 
 * @author Robert R. Russell
 * @author robert@rrbrussell.com
 *
 */
public class Rotor {
	
	/**
	 * Which position in the Wiring maps to 0 on the Indicator.
	 */
	protected int Ringstellung = 0;
	
	/**
	 * Which position on the Indicator ring is visible.
	 */
	protected int Indicator = 0;
	
	protected int[] Wiring;
	
	protected int IndicatorTransferPosition;
	
	/**
	 * @param Ringstellung Which position on the Wiring matches 0 on the
	 * Indicator.
	 * @exception RingSizeException Ringstellung must comply with
	 * Rotor.RingSize constraint.
	 */
	public Rotor(int Ringstellung) {
		if (Ringstellung < 0 || Ringstellung >= RingSize) {
			throw new RingSizeException();
		}
		this.Ringstellung = Ringstellung;
	}
	
	/**
	 * Moves the Rotor on the spindle.
	 * @return True if the Rotor instance has stepped past its turnover point. 
	 */
	public boolean Step() {
		boolean ReturnValue = false;
		if (Indicator == IndicatorTransferPosition) {
			ReturnValue = true;
		}
		Indicator++;
		return ReturnValue;
	}
	
	/**
	 * Encipher the input value 
	 * @param input Plaintext
	 * @return Ciphertext
	 */
	public int Encipher(int input) {
		return Wiring[(Indicator + Ringstellung + input)%Rotor.RingSize];
	}
	
	/**
	 * The Grundstellung is the Indicator position for starting encipherment.
	 * Calling this after encipherment has begun is undefined behavior.
	 * @param Grundstellung The new Indicator position.
	 */
	public void SetGrundstellung(int Grundstellung) {
		Indicator = Grundstellung;
	}
	
	/**
	 * Value for the Rotor.RingSize constraint.
	 * <p>
	 * The constraint is all values mapping into or out of a rotor must be
	 * between 0 and Rotor.RingSize - 1. 
	 */
	public static final int RingSize = 26;
}
