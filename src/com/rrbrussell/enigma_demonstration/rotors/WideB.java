/**
 * 
 */
package com.rrbrussell.enigma_demonstration.rotors;

import com.rrbrussell.enigma_demonstration.Rotor;

/**
 * @author Robert R. Russell
 *
 */
public class WideB implements Rotor {
	private static final int[] Wiring =
		{ 24, 17, 20, 7, 16, 18, 11, 3, 15, 23, 13, 6, 14, 10, 12, 8, 4, 1, 5, 25, 2, 22, 21, 9, 0, 19 };
	
	private static final int IndicatorTransferStepFromPosition = 0;
	
	private int Indicator = 0;
	
	private int Ringstellung = 0;

	/**
	 * @see com.rrbrussell.enigma_demonstration.Rotor#Step()
	 */
	@Override
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
	@Override
	public int Encipher(int input) {
		return Wiring[(Indicator + Ringstellung + input)%Rotor.RingSize];
	}

	/**
	 * @see com.rrbrussell.enigma_demonstration.Rotor#SetRingstellung(int)
	 */
	@Override
	public void SetRingstellung(int offset) {
		if (offset >= Rotor.RingSize) { Ringstellung = 0; }
		else {Ringstellung = offset; }
	}

	/**
	 * @see com.rrbrussell.enigma_demonstration.Rotor#SetGrundstellung(int)
	 */
	@Override
	public void SetGrundstellung(int indicator) {
		if (indicator >= Rotor.RingSize) {Indicator = 0;}
		else {Indicator = indicator; }
	}

}
