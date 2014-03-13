/**
 * 
 */
package com.rrbrussell.enigma_demonstration.rotors;

import com.rrbrussell.enigma_demonstration.RingSizeException;
import com.rrbrussell.enigma_demonstration.Rotor;

public final class RotorFactory {
	
	private enum Rotors {
		I, II, III, IV, V
	}
	
	public static final Rotor SetupRotor(String RotorDescription) {
		Rotor FinishedRotor = null;
		Rotors WantedRotor;
		int Ringstellung = 0;
		String Pieces[] = RotorDescription.split(":");
		if (Pieces.length > 2 || Pieces.length == 0) {
			throw new IllegalArgumentException(
					"The proper format of a RotorDescription is".concat(
							" RotorName:Rinstellung."));
		} else {
			WantedRotor = Rotors.valueOf(Pieces[0]);
			if (Pieces.length == 2) {
				Ringstellung = Integer.parseInt(Pieces[1]);
				if(!Rotor.SatisfiesRingConstraint(Ringstellung)) {
					throw new RingSizeException();
				}
			}
		}
		switch(WantedRotor){
		case I:
			FinishedRotor = new RotorI(Ringstellung);
			break;
		case II:
			FinishedRotor = new RotorII(Ringstellung);
			break;
		case III:
			FinishedRotor = new RotorIII(Ringstellung);
			break;
		case IV:
			FinishedRotor = new RotorIV(Ringstellung);
			break;
		case V: FinishedRotor = new RotorV(Ringstellung);
			break;
		}
		return FinishedRotor;
	}
	
	
}
