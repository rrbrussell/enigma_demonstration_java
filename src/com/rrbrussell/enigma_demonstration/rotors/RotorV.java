/**
 * 
 */
package com.rrbrussell.enigma_demonstration.rotors;

import com.rrbrussell.enigma_demonstration.Rotor;

/**
 * This class implements the V Rotor for the Wermacht and Kriegsmarine Enigma
 * machines.
 * @see com.rrbrussell.enigma_demonstration.Rotor
 * @author Robert R. Russell
 * @author robert@rrbrussell.com
 */
public class RotorV extends Rotor {
	
	public RotorV(int Ringstellung) {
		super(Ringstellung);
		IndicatorTransferPosition = 25;
		Wiring = new int[] { 21, 25, 1, 17, 6, 8, 19, 24, 20, 15, 18, 3, 13,
				7, 11, 23, 0, 22, 12, 9, 16, 14, 5, 4, 2, 10 };
	}
}
