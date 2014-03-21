/**
 * 
 */
package com.rrbrussell.enigma_demonstration;

/**
 * @author Robert R. Russell
 * @author robert@rrbrussell.com
 */
public final class Utility {
	public static int charToInt(char c) {
		return Character.getNumericValue(c) - 10;
	}
	
	public static char intToChar(int i) {
		return (char) (i + 65);
	}
}
