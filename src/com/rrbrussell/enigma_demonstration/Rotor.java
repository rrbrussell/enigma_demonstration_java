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
	
	public enum Rotors {
		I("EKMFLGDQVZNTOWYHXUSPAIBRCJ",'Q'),
		II("AJDKSIRUXBLHWTMCQGZNPYFVOE",'E'),
		III("BDFHJLCPRTXVZNYEIWGAKMUSQO",'V'),
		IV("ESOVPZJAYQUIRHXLNFTGKDCMWB",'J'),
		V("VZBRGITYUPSDNHLXZWMJQOFECK",'Z');
		
		private String Wiring;
		private char TurnoverWindow;
		
		Rotors(String w, char t) {
			this.Wiring = w;
			this.TurnoverWindow = t;
		}
		
		public String getWiring() { return this.Wiring; }
		
		public char getTurnoverWindow() { return this.TurnoverWindow; }
	}
	
	/**
	 * Which position in the Wiring maps to 0 on the Indicator.
	 */
	private int Ringstellung = 0;
	
	/**
	 * Which position on the Indicator ring is visible.
	 */
	private int Indicator = 0;
	
	private char[] RightToLeftWiring;
	private char[] LeftToRightWiring;
	
	public static Rotors[] getValidRotors() { return Rotors.values(); } 
	
	/**
	 * The position on the indicator ring when the rotor steps the next rotor
	 * in sequence and itself.
	 */
	private int IndicatorTransferPosition;
	
	public Rotor( Rotors Chosen, int Ringstellung) {
		this.RightToLeftWiring = Chosen.getWiring().toCharArray();
		this.LeftToRightWiring = new char[RightToLeftWiring.length];
		for( int i = 0; Rotor.SatisfiesRingConstraint(i); i++) {
			this.LeftToRightWiring[i] = Utility.intToChar(Chosen.getWiring()
					.indexOf(Utility.intToChar(i)));
		}
		if(Rotor.SatisfiesRingConstraint(Ringstellung)) {
			this.Ringstellung = Ringstellung;
		} else {
			throw new RingSizeException();
		}
		this.IndicatorTransferPosition = Chosen.getTurnoverWindow();
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
		Indicator = (Indicator + 1) % Rotor.RingSize;
		return ReturnValue;
	}
	
	public char encipherRightToLeft(char Plaintext) {
		char Ciphertext;
		Ciphertext = this.RightToLeftWiring[((this.Indicator
				+ this.Ringstellung + Utility.charToInt(Plaintext))
				% Rotor.RingSize)];
		Ciphertext = Utility.intToChar((Utility.charToInt(Ciphertext)
				- this.Indicator - this.Ringstellung + Rotor.RingSize)
				% Rotor.RingSize);
		return Ciphertext;
	}
	
	public char encipherLeftToRight(char Plaintext) {
		char Ciphertext;
		Ciphertext = this.LeftToRightWiring[(
			        (this.Indicator + this.Ringstellung +
			        		Utility.charToInt(Plaintext)) % Rotor.RingSize)];
		Ciphertext = Utility.intToChar((Utility.charToInt(Ciphertext)
				- this.Indicator - this.Ringstellung + Rotor.RingSize)
				% Rotor.RingSize);
		return Ciphertext;
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
		StringBuilder temp = new StringBuilder(200);
		for( int i = 0; Rotor.SatisfiesRingConstraint(i); i++ ) {
			temp.append(this.encipherRightToLeft(Utility.intToChar(i)));
		}
		temp.append(" = ");
		for( int i = 0; Rotor.SatisfiesRingConstraint(i); i++ ) {
			temp.append(this.encipherLeftToRight(Utility.intToChar(i)));
		}
		return temp.toString();
	}
}
