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
	
	/**
	 * The internal wiring table.
	 */
	protected int[] Wiring;
	
	/**
	 * The position on the indicator ring when the rotor steps the next rotor
	 * in sequence and itself.
	 */
	protected int IndicatorTransferPosition;
	
	/**
	 * @param Ringstellung Which position in the Wiring maps to 0 on the
	 * Indicator.
	 * @exception RingSizeException Ringstellung must comply with
	 * Rotor.RingSize constraint.
	 */
	public Rotor(int Ringstellung) {
		if (!Rotor.SatisfiesRingConstraint(Ringstellung)) {
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
	
	/**
	 * @param Number The number to test.
	 * @return True if Number is between 0 and Rotor.RingSize - 1 inclusive.
	 */
	public static boolean SatisfiesRingConstraint(int Number) {
		if (Number < 0 || Number >= Rotor.RingSize) {
			return false;
		}
		return true;
	}
	
	public String toString() {
		StringBuilder temp = new StringBuilder(100);
		for (int i = 0; Rotor.SatisfiesRingConstraint(i); i++) {
			if (i == this.Indicator) {
				temp.append("[");
			}
			temp.append(Utility.intToChar(i));
			if (i == this.Indicator) {
				temp.append("]");
			}
		}
		temp.append("=>");
		for (int i = 0; Rotor.SatisfiesRingConstraint(i); i++) {
			if (i == this.Indicator) {
				temp.append("[");
			}
			temp.append(Utility.intToChar(
					this.Wiring[(Ringstellung + i)%Rotor.RingSize]));
			if (i == this.Indicator) {
				temp.append("]");
			}
		}
		return temp.toString();
	}
}
