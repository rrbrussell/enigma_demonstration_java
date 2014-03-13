/**
 * 
 */
package com.rrbrussell.enigma_demonstration.rotors;

import com.rrbrussell.enigma_demonstration.Rotor;

/**
 * This class implements the I Rotor for the Wermacht and Kriegsmarine Enigma
 * machines.
 * @see com.rrbrussell.enigma_demonstration.Rotor
 * @author Robert R. Russell
 * @author robert@rrbrussell.com
 */
public class RotorI extends Rotor {
	
	public RotorI(int Ringstellung) {
		super(Ringstellung);
		IndicatorTransferPosition = 16;
		Wiring = new int[] { 4, 10, 12, 5, 11, 6, 3, 16, 21, 25, 13, 19, 14,
				22, 24, 7, 23, 20, 18, 15, 0, 8, 1, 17, 2, 9 };
	}

}
