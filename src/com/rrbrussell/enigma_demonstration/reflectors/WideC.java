/**
 * Copyright (c) 2014 Robert R. Russell
 */
package com.rrbrussell.enigma_demonstration.reflectors;

import com.rrbrussell.enigma_demonstration.Reflector;

/**
 * This class implements the Wide C Reflector for the Wermacht and Kriegsmarine
 * Enigma machines.
 * @see com.rrbrussell.enigma_demonstration.Reflector
 * @author Robert R. Russell
 * @author robert@rrbrussell.com
 */
public class WideC extends Reflector {

	public WideC() {
		Wiring = new int[] { 5, 21, 15, 9, 8, 0, 14, 24, 4, 3, 17, 25, 23, 22,
				6, 2, 19, 10, 20, 16, 18, 1, 13, 12, 7, 11 };
	}

}

