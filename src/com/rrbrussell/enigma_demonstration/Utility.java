/**
 * Utility.java Copyright (c) 2014 Robert R. Russell
 */
package com.rrbrussell.enigma_demonstration;

/**
 * A simple utility class for over duplicated code.
 * 
 * @since v0.1
 * 
 * @author Robert R. Russell
 * @author robert@rrbrussell.com
 */
public final class Utility {
	
	public static final String Alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	
	/**
	 * @param c A-Z or a-z
	 * 
	 * @return 0-25
	 */
	public static int charToInt(char c) {
		return Character.getNumericValue(c) - 10;
	}
	
	/**
	 * @param i 0-25
	 * 
	 * @return A-Z
	 */
	public static char intToChar(int i) {
		return (char) (i + 65);
	}
	
	/**
	 * @since v0.2
	 * 
	 * @param input a string containing only A-Z
	 * 
	 * @return an array of Characters.
	 */
	public static Characters[] stringToCharactersArray(String input) {
		if(input == null || input.length() == 0) {
			throw new IllegalArgumentException("input length is not valid");
		}
		Characters[] output = new Characters[input.length()];
		input = input.toUpperCase();
		for(int i=0; i<input.length(); i++) {
			output[i] = Characters.valueOf(input.substring(i, i + 1));
		}
		return output;
	}
	
}
