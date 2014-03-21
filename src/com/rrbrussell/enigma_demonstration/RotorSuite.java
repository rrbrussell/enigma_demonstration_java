/**
 * 
 */
package com.rrbrussell.enigma_demonstration;

import com.rrbrussell.enigma_demonstration.rotors.RotorFactory;

/**
 * @author Robert R. Russell
 *
 */
public class RotorSuite {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Rotor TestRotor = RotorFactory.SetupRotor("I");
		for( String string : stepRotor(TestRotor)) {
			System.out.println(string);
		}
		TestRotor = RotorFactory.SetupRotor("I:1");
		for( String string : stepRotor(TestRotor)) {
			System.out.println(string);
		}
	}

	private static String[] stepRotor(Rotor r) {
		String[] strs = new String[Rotor.RingSize];
		for( int i = 0; Rotor.SatisfiesRingConstraint(i); i++) {
			strs[i] = r.toString();
			r.Step();
		}
		return strs;
	}
}
