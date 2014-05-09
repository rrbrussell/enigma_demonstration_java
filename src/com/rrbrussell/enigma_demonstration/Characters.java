/**
 * Characters.java Copyright (c) 2014 Robert R. Russell
 */
package com.rrbrussell.enigma_demonstration;

/**
 * An Enumeration used to constrain the possible valid inputs and outputs of
 * the internal mechanisms in the Enigma Machine.
 * 
 * @since v0.2
 * 
 * @author Robert R. Russell
 * @author robert@rrbrussell.com
 */
public enum Characters {
	A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z;
	
	public static Characters valueOf(char input) {
		return Characters.valueOf(String.valueOf(input));
	}
	
	/**
	 * @return the next constant with wrapping.
	 * 
	 * @since v0.2
	 */
	Characters next() {
		int plusOne = (this.ordinal() + 1) % Rotor.RingSize;
		return Characters.valueOf(Utility.intToChar(plusOne));
	}
	
	/**
	 * @return the previous constant with wrapping.
	 * 
	 * @since v0.2
	 */
	Characters previous() {
		int minusOne = (this.ordinal() - 1 + Rotor.RingSize) % Rotor.RingSize;
		return Characters.valueOf(Utility.intToChar(minusOne));
	}
	
	/**
	 * @param offset The current indicator or ring offset value
	 * @return the adjusted Character
	 * 
	 * @since v0.2
	 */
	Characters forwardBy(Characters offset) {
		int adjustedOffset = (this.ordinal() + offset.ordinal())
				% Rotor.RingSize;
		return Characters.valueOf(Utility.intToChar(adjustedOffset));
	}
	
	/**
	 * @param offset The current indicator or ring offset value
	 * @return the adjusted Character
	 * 
	 * @since v0.2
	 */
	Characters backwardBy(Characters offset) {
		int adjustedOffset = (this.ordinal() - offset.ordinal()
				+ Rotor.RingSize) % Rotor.RingSize;
		return Characters.valueOf(Utility.intToChar(adjustedOffset));
	}
	
	/**
	 * @since v0.2
	 */
	static Characters fromInt(int input) {
		return Characters.valueOf(Utility.intToChar(input));
	}
	
	/**
	 * @since v0.2
	 */
	static Characters fromChar(char input) {
		return Characters.valueOf(String.valueOf(input));
	}
	
	/**
	 * @since v0.2
	 */
	int toInt() {
		return this.ordinal();
	}
	
	/**
	 * @since v0.2
	 */
	char toChar() {
		return this.toString().charAt(0);
	}
}
