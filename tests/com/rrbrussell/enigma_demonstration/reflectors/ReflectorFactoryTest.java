package com.rrbrussell.enigma_demonstration.reflectors;

import static org.junit.Assert.*;

import org.junit.Test;

import com.rrbrussell.enigma_demonstration.reflectors.ReflectorFactory;

public class ReflectorFactoryTest {

	@Test
	public void testSetupReflector() {
		try {
			ReflectorFactory.SetupReflector("");
			fail("Did not throw IllegalArgumentException");
		} catch (Exception e) {
			assertTrue(e.getClass().equals(IllegalArgumentException.class));
		}
		
		try {
			ReflectorFactory.SetupReflector("VI");
			fail("Did not throw IllegalArgumentException");
		} catch (Exception e) {
			assertTrue(e.getClass().equals(IllegalArgumentException.class));
		}
		
		assertTrue("Given WideB object",
				ReflectorFactory.SetupReflector("WideB").getClass().equals(
						WideB.class));
	}

}
