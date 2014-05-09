/**
 * RotorTest.java Copyright (c) 2014 Robert R. Russell
 */
package com.rrbrussell.enigma_demonstration;

import static org.junit.Assert.*;

import org.junit.Test;

public class RotorTest {

	@Test
	public void testSatisfiesRingConstraint() {
		for(int i = 0; i < Rotor.RingSize; i++) {
			assertTrue(i + " statisfiesRingConstraint",
					Rotor.SatisfiesRingConstraint(i));
		}
	}

}
