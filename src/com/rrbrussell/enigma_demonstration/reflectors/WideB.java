/**
 * 
 */
package com.rrbrussell.enigma_demonstration.reflectors;

import com.rrbrussell.enigma_demonstration.Reflector;

/**
 * This class implements the Wide B Reflector for the Wermacht and Kriegsmarine
 * Enigma machines.
 * @see com.rrbrussell.enigma_demonstration.Reflector
 * @author Robert R. Russell
 * @author robert@rrbrussell.com
 */
public class WideB extends Reflector {
	/**
	 * 
	 */
	public WideB() {
		Wiring = new int[] { 24, 17, 20, 7, 16, 18, 11, 3, 15, 23, 13, 6, 14,
				10, 12, 8, 4, 1, 5, 25, 2, 22, 21, 9, 0, 19 };
	}

}
