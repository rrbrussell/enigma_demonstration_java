/**
 * 
 */
package com.rrbrussell.engima_demonstration.rotors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.rrbrussell.enigma_demonstration.rotors.RotorV;

/**
 * @author Robert R. Russell
 *
 */
public class RotorVTest {
	RotorV TestableRotor;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		TestableRotor = new RotorV(0);
	}

	/**
	 * Test method for {@link com.rrbrussell.enigma_demonstration.Rotor#Step()}.
	 */
	@Test
	public void testStep() {
		assertEquals("0 should encipher as 21", 21,
				this.TestableRotor.Encipher(0));
		assertFalse("Stepping from 0 to 1 should not flip next Rotor",
				this.TestableRotor.Step());
		assertEquals("0 should encipher as 25", 25,
				this.TestableRotor.Encipher(0));
		this.TestableRotor.SetGrundstellung(25);
		assertTrue("Stepping from 25 to 0 should flip next Rotor",
				this.TestableRotor.Step());
		assertEquals("0 should encipher as 21", 21,
				this.TestableRotor.Encipher(0));
	}

	/**
	 * Test method for {@link com.rrbrussell.enigma_demonstration.Rotor#Encipher(int)}.
	 */
	@Test
	public void testEncipher() {
		assertEquals("0 should encipher as 21", 21,
				this.TestableRotor.Encipher(0));
	}

	/**
	 * Test method for {@link com.rrbrussell.enigma_demonstration.Rotor#SetGrundstellung(int)}.
	 */
	@Test
	public void testSetGrundstellung() {
		this.TestableRotor.SetGrundstellung(1);
		assertEquals("0 should encipher as 25",	25,
				this.TestableRotor.Encipher(0));
	}

}
