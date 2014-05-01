/**
 * Copyright (c) 2014 Robert R. Russell
 */
package com.rrbrussell.enigma_demonstration;

import java.util.EnumMap;
import java.util.Arrays;
import java.util.Vector;

/**
 * The Wermacht and Kriegsmarine default rotors.
 * 
 * @author Robert R. Russell
 * @author robert@rrbrussell.com
 * 
 */
public class Rotor {

	/**
	 * Static data for the default Wermacht and Kriegsmarine rotors.
	 * 
	 * @author Robert R. Russell
	 * @author robert@rrbrussell.com
	 */
	public enum Rotors {
		I("EKMFLGDQVZNTOWYHXUSPAIBRCJ", 'Q'),
		II("AJDKSIRUXBLHWTMCQGZNPYFVOE", 'E'),
		III("BDFHJLCPRTXVZNYEIWGAKMUSQO", 'V'),
		IV("ESOVPZJAYQUIRHXLNFTGKDCMWB", 'J'),
		V("VZBRGITYUPSDNHLXZWMJQOFECK", 'Z');

		private String Wiring;
		private char TurnoverWindow;

		Rotors(String w, char t) {
			this.Wiring = w;
			this.TurnoverWindow = t;
		}

		public String getWiring() {
			return this.Wiring;
		}

		public Characters getTurnoverWindow() {
			return Characters.fromChar(TurnoverWindow);
		}
	}

	/**
	 * Which position in the Wiring maps to 0 on the Indicator.
	 */
	private Characters rotorOffset = Characters.A;

	/**
	 * Which position on the Indicator ring is visible.
	 */
	private Characters Indicator = Characters.A;

	private EnumMap<Characters, Characters> rightToLeftWiringMap;
	private EnumMap<Characters, Characters> leftToRightWiringMap;

	/**
	 * The position on the indicator ring when the rotor steps the next rotor in
	 * sequence and itself.
	 */
	private Characters IndicatorTransferPosition;

	/**
	 * Create a new Rotor. Use Rotors.values to get a list of available
	 * rotors in this implementation and their identifiers. Pick one of those
	 * Rotors and a Ringstellung to create this Rotor.
	 */
	public Rotor(Rotors chosenRotor, int Ringstellung) {
		IndicatorTransferPosition = chosenRotor.getTurnoverWindow();
		rotorOffset = Characters.fromInt(Ringstellung);
		
		rightToLeftWiringMap =
				new EnumMap<Characters, Characters>(Characters.class);
		leftToRightWiringMap =
				new EnumMap<Characters, Characters>(Characters.class);
		
		Characters[] keyArray = Characters.values();
		Vector<Characters> rotorTable = new Vector<Characters>(Arrays.asList(
				Utility.stringToCharactersArray(chosenRotor.getWiring())));
		
		for(Characters keyValue: keyArray) {
			rightToLeftWiringMap.put(keyValue,
					rotorTable.get(keyValue.ordinal()));
		}
		
		for(Characters keyValue: keyArray) {
			leftToRightWiringMap.put(keyValue, Characters.fromInt(
					rotorTable.indexOf(keyValue)));
		}
	}

	/**
	 * Moves the Rotor on the spindle.
	 * 
	 * @return True if the Rotor instance has stepped past its turnover point.
	 */
	public boolean Step() {
		boolean ReturnValue = false;
		if (Indicator.equals(IndicatorTransferPosition)) {
			ReturnValue = true;
		}
		Indicator = Indicator.next();
		return ReturnValue;
	}

	/**
	 * Encipherment before the Reflector
	 * 
	 * @param Plaintext
	 * @return Ciphertext
	 */
	public char encipherRightToLeft(char Plaintext) {
		/*char Ciphertext;
		Ciphertext = Utility.intToChar(this.RightToLeftWiring[((this.Indicator
				+ this.Ringstellung + Utility.charToInt(Plaintext)) % Rotor.RingSize)]);
		Ciphertext = Utility.intToChar((Utility.charToInt(Ciphertext)
				- this.Indicator - this.Ringstellung + Rotor.RingSize)
				% Rotor.RingSize);
		return Ciphertext;*/
		Characters ciphertextIndex = Characters.fromChar(Plaintext);
		Characters ciphertext = encipher(ciphertextIndex,
				rightToLeftWiringMap);
		return ciphertext.toChar();
	}

	/**
	 * Encipherment after the Reflector
	 * 
	 * @param Plaintext
	 * @return Ciphertext
	 */
	public char encipherLeftToRight(char Plaintext) {
		Characters ciphertextIndex = Characters.fromChar(Plaintext);
		Characters ciphertext = encipher(ciphertextIndex,
				leftToRightWiringMap);
		
		/*Ciphertext = Utility.intToChar(this.LeftToRightWiring[(
				(this.Indicator	+ this.Ringstellung
						+ Utility.charToInt(Plaintext)) % Rotor.RingSize)]);
		Ciphertext = Utility.intToChar((Utility.charToInt(Ciphertext)
				- this.Indicator - this.Ringstellung + Rotor.RingSize)
				% Rotor.RingSize);*/
		return ciphertext.toChar();
	}

	/**
	 * @param ciphertextIndex
	 * @return
	 */
	private Characters encipher(Characters ciphertextIndex,
			EnumMap<Characters,Characters> direction) {
		//offset it by how far the rotor has stepped
		ciphertextIndex = ciphertextIndex.forwardBy(Indicator);
		//offset it by how far the wiring was shifted by
		ciphertextIndex = ciphertextIndex.forwardBy(rotorOffset);
		//find the ciphertext
		Characters ciphertext = direction.get(ciphertextIndex);
		//reverse the adjustments to the ciphertextIndex to the ciphertext
		ciphertext = ciphertext.backwardBy(rotorOffset);
		ciphertext = ciphertext.backwardBy(Indicator);
		return ciphertext;
	}

	/**
	 * The Grundstellung is the Indicator position for starting encipherment.
	 *  
	 * @param Grundstellung
	 *            The new Indicator position.
	 */
	public void SetGrundstellung(int Grundstellung) {
		Indicator = Characters.fromInt(Grundstellung);
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

	/**
	 * @param character The character to test.
	 * @return True if character is between A - Z or a - z inclusive.
	 */
	public static boolean SatisfiesRingConstraint(char character) {
		return SatisfiesRingConstraint(Utility.charToInt(character));
	}

	/**
	 * Just used a a testing tool.
	 */
	public String toString() {
		StringBuilder temp = new StringBuilder(200);
		for (int i = 0; Rotor.SatisfiesRingConstraint(i); i++) {
			temp.append(this.encipherRightToLeft(Utility.intToChar(i)));
		}
		temp.append(" = ");
		for (int i = 0; Rotor.SatisfiesRingConstraint(i); i++) {
			temp.append(this.encipherLeftToRight(Utility.intToChar(i)));
		}
		return temp.toString();
	}
}
