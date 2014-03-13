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

	public enum Reflectors {
		B, C
	}

	public static final Reflector SetupReflector(ReflectorFactory.Reflectors WantedReflector ) {
		Reflector FinishedReflector = null;
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
