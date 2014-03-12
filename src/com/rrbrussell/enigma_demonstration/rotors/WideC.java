/**
 * 
 */
package com.rrbrussell.enigma_demonstration.rotors;

import com.rrbrussell.enigma_demonstration.Rotor;

/**
 * @author Robert R. Russell
 *
 */
public class WideC extends Rotor {
	private static final int[] Wiring =
		{ 5, 21, 15, 9, 8, 0, 14, 24, 4, 3, 17, 25, 23, 22, 6, 2, 19, 10, 20, 16, 18, 1, 13, 12, 7, 11 };
	
	private static final int IndicatorTransferStepFromPosition = 0;
	
	private int Indicator = 0;
	
	private int Ringstellung = 0;

	/**
	 * @see com.rrbrussell.enigma_demonstration.Rotor#Step()
	 */
	public boolean Step() {
		boolean ReturnValue = false;
		if (Indicator == IndicatorTransferStepFromPosition) {
			ReturnValue = true;
		}
		Indicator++;
		return ReturnValue;
	}

	/** 
	 * @see com.rrbrussell.enigma_demonstration.Rotor#Encipher(int)
	 */
	public int Encipher(int input) {
		return Wiring[(Indicator + Ringstellung + input)%Rotor.RingSize];
	}

	/**
	 * @see com.rrbrussell.enigma_demonstration.Rotor#SetRingstellung(int)
	 */
	public void SetRingstellung(int offset) {
		if (offset >= Rotor.RingSize) { Ringstellung = 0; }
		else {Ringstellung = offset; }
	}

	/**
	 * @see com.rrbrussell.enigma_demonstration.Rotor#SetGrundstellung(int)
	 */
	public void SetGrundstellung(int indicator) {
		if (indicator >= Rotor.RingSize) {Indicator = 0;}
		else {Indicator = indicator; }
	}

}
