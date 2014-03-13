/**
 * 
 */
package com.rrbrussell.enigma_demonstration.rotors;

import com.rrbrussell.enigma_demonstration.Rotor;

/**
 * This class implements the IV Rotor for the Wermacht and Kriegsmarine Enigma
 * machines.
 * @see com.rrbrussell.enigma_demonstration.Rotor
 * @author Robert R. Russell
 * @author robert@rrbrussell.com
 */
public class RotorIV extends Rotor {
	
	public RotorIV(int Ringstellung) {
		super(Ringstellung);
		IndicatorTransferPosition = 9;
		Wiring = new int[] { 4, 18, 14, 21, 15, 25, 9, 0, 24, 16, 20, 8, 17,
				7, 23, 11, 13, 5, 19, 6, 10, 3, 2, 12, 22, 1};
	}
}
