/**
 * 
 */
package com.rrbrussell.enigma_demonstration.rotors;

import com.rrbrussell.enigma_demonstration.Rotor;

/**
 * This class implements the II Rotor for the Wermacht and Kriegsmarine Enigma
 * machines.
 * @see com.rrbrussell.enigma_demonstration.Rotor
 * @author Robert R. Russell
 * @author robert@rrbrussell.com
 */
public class RotorII extends Rotor {

	public RotorII(int Ringstellung) {
		super(Ringstellung);
		IndicatorTransferPosition = 4;
		Wiring = new int[] { 0, 9, 3, 10, 18, 8, 17, 20, 23, 1, 11, 7, 22, 19,
				12, 2, 16, 6, 25, 13, 15, 24, 5, 21, 14, 4 };
	}
	
}
