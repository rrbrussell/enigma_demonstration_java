/**
 * Copyright (c) 2014 Robert R. Russell
 */
package com.rrbrussell.enigma_demonstration;

/**
 * A simple utility class for over duplicated code.
 * @author Robert R. Russell
 * @author robert@rrbrussell.com
 */
public final class Utility {
	/**
	 * @param c A-Z or a-z
	 * @return 0-25
	 */
	public static int charToInt(char c) {
		return Character.getNumericValue(c) - 10;
	}
	
	/**
	 * @param i 0-25
	 * @return A-Z
	 */
	public static char intToChar(int i) {
		return (char) (i + 65);
	}
}
