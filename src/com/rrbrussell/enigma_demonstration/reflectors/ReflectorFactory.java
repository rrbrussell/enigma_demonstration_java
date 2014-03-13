/**
 * 
 */
package com.rrbrussell.enigma_demonstration.reflectors;

import com.rrbrussell.enigma_demonstration.Reflector;

/**
 * @author Robert R. Russell
 *
 */
public final class ReflectorFactory {

	private enum Reflectors {
		B, C
	}

	public static final Reflector SetupReflector(String ReflectorDescription) {
		Reflector FinishedReflector = null;
		Reflectors WantedReflector = Reflectors.valueOf(ReflectorDescription);
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
