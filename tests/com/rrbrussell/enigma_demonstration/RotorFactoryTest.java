/**
 * 
 */
package com.rrbrussell.enigma_demonstration;

import static org.junit.Assert.*;

import org.junit.Test;

import com.rrbrussell.enigma_demonstration.rotors.*;
import com.rrbrussell.enigma_demonstration.RingSizeException;

/**
 * @author Robert R. Russell
 *
 */
public class RotorFactoryTest {

	/**
	 * Test method for {@link com.rrbrussell.enigma_demonstration.rotors.RotorFactory#SetupRotor(java.lang.String)}.
	 */
	@Test
	public void testSetupRotor() {
		try {
			RotorFactory.SetupRotor("");
			fail("Did not throw IllegalArgumentException");
		} catch (Exception e) {
			assertTrue(e.getClass().equals(IllegalArgumentException.class));
		}
		
		try {
			RotorFactory.SetupRotor("VI");
			fail("Did not throw IllegalArgumentException");
		} catch (Exception e) {
			assertTrue(e.getClass().equals(IllegalArgumentException.class));
		}
		
		try {
			RotorFactory.SetupRotor("V:9:0");
			fail("Did not throw IllegalArgumentException");
		} catch (Exception e) {
			assertTrue(e.getClass().equals(IllegalArgumentException.class));
		}
		
		try {
			RotorFactory.SetupRotor("V:26");
			fail("Did not throw IllegalArgumentException");
		} catch (Exception e) {
			assertTrue("Did not throw a RingSizeException",
					e.getClass().equals(RingSizeException.class));
		}
		
		assertTrue("Given RotorI object",
				RotorFactory.SetupRotor("I").getClass().equals(RotorI.class));
		
		
		//fail("Not yet implemented"); //TODO
	}

}
