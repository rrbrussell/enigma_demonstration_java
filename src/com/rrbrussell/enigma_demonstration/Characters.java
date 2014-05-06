/**
 * Copyright (c) 2014 Robert R. Russell
 */
package com.rrbrussell.enigma_demonstration;

/**
 * @author Robert R. Russell
 *
 */
public enum Characters {
	A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z;
	
	public static Characters valueOf(char input) {
		return Characters.valueOf(String.valueOf(input));
	}
	
	/**
	 * @return
	 */
	Characters next() {
		int plusOne = (this.ordinal() + 1) % Rotor.RingSize;
		return Characters.valueOf(String.valueOf(Utility.intToChar(plusOne)));
	}
	
	/**
	 * @return
	 */
	Characters previous() {
		int minusOne = (this.ordinal() - 1 + Rotor.RingSize) % Rotor.RingSize;
		return Characters.valueOf(String.valueOf(
				Utility.intToChar(minusOne)));
	}
	
	/**
	 * @param offset
	 * @return
	 */
	Characters forwardBy(Characters offset) {
		int adjustedOffset = (this.ordinal() + offset.ordinal())
				% Rotor.RingSize;
		return Characters.valueOf(String.valueOf(
				Utility.intToChar(adjustedOffset)));
	}
	
	/**
	 * @param offset
	 * @return
	 */
	Characters backwardBy(Characters offset) {
		int adjustedOffset = (this.ordinal() - offset.ordinal()
				+ Rotor.RingSize) % Rotor.RingSize;
		return Characters.valueOf(String.valueOf(
				Utility.intToChar(adjustedOffset)));
	}
	
	/**
	 * @param possible
	 * @return
	 */
	static Characters fromInt(int possible) {
		return Characters.valueOf(String.valueOf(
				Utility.intToChar(possible)));
	}
	
	/**
	 * @param possible
	 * @return
	 */
	static Characters fromChar(char possible) {
		return Characters.valueOf(String.valueOf(possible));
	}
	
	/**
	 * @return
	 */
	int toInt() {
		return this.ordinal();
	}
	
	/**
	 * @return
	 */
	char toChar() {
		return this.toString().charAt(0);
	}
}
