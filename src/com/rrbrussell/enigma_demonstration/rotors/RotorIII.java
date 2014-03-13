/**
 * 
 */
package com.rrbrussell.enigma_demonstration.rotors;

import com.rrbrussell.enigma_demonstration.Rotor;

/**
 * This class implements the III Rotor for the Wermacht and Kriegsmarine Enigma
 * machines.
 * @see com.rrbrussell.enigma_demonstration.Rotor
 * @author Robert R. Russell
 * @author robert@rrbrussell.com
 */
public class RotorIII extends Rotor {

	public RotorIII(int Ringstellung) {
		super(Ringstellung);
		IndicatorTransferPosition = 21;
		Wiring = new int[] { 1, 3, 5, 7, 9, 11, 2, 15, 17, 19, 23, 21, 25, 13,
				24, 4, 8, 22, 6, 0, 10, 12, 20, 18, 16, 14 };
	}
}
