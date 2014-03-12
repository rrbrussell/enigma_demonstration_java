/**
 * 
 */
package com.rrbrussell.enigma_demonstration.rotors;

import com.rrbrussell.enigma_demonstration.Rotor;

/**
 * @author Robert R. Russell
 *
 */
public class RotorIII implements Rotor {
	private static final int[] Wiring =
		{ 1, 3, 5, 7, 9, 11, 2, 15, 17, 19, 23, 21, 25, 13, 24, 4, 8, 22, 6, 0, 10, 12, 20, 18, 16, 14 };
	
	private static final int IndicatorTransferStepFromPosition = 21;
	
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
