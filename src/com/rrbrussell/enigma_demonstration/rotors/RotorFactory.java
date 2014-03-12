package com.rrbrussell.enigma_demonstration.rotors;
import com.rrbrussell.enigma_demonstration.Rotor;

public final class RotorFactory {
	public enum Rotors {
		I, II, III, IV, V
	}
	
	public enum Reflectors {
		B, C
	}

	public static final Rotor SetupRotor(RotorFactory.Rotors WantedRotor, int Ringstellung) {
		Rotor FinishedRotor = null;
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
	
	public static final Rotor SetupReflector(RotorFactory.Reflectors WantedReflector ) {
		Rotor FinishedReflector = null;
		switch(WantedReflector) {
		case B:
			FinishedReflector = new WideB();
			break;
		case C:
			FinishedReflector = new WideC();
			break;
		}
		return FinishedReflector;
	}
}
