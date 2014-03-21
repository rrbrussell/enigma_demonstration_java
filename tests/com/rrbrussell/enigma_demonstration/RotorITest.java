/**
 * 
 */
package com.rrbrussell.enigma_demonstration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.rrbrussell.enigma_demonstration.rotors.RotorI;

/**
 * @author Robert R. Russell
 *
 */
public class RotorITest {
	RotorI TestableRotor;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		TestableRotor = new RotorI(0);
	}

	/**
	 * Test method for {@link com.rrbrussell.enigma_demonstration.Rotor#Step()}.
	 */
	@Test
	public void testStep() {
		assertEquals("0 should encipher as 4",
				4, this.TestableRotor.Encipher(0));
		assertFalse("Stepping from 0 to 1 should not flip next Rotor",
				this.TestableRotor.Step());
		assertEquals("0 should encipher as 10",
				10, this.TestableRotor.Encipher(0));
		this.TestableRotor.SetGrundstellung(16);
		assertTrue("Stepping from 16 to 17 should flip next Rotor",
				this.TestableRotor.Step());
		assertEquals("0 should encipher as 20", 20,
				this.TestableRotor.Encipher(0));
	}

	/**
	 * Test method for {@link com.rrbrussell.enigma_demonstration.Rotor#Encipher(int)}.
	 */
	@Test
	public void testEncipher() {
		assertEquals("0 should encipher as 4",
				4, this.TestableRotor.Encipher(0));
	}

	/**
	 * Test method for {@link com.rrbrussell.enigma_demonstration.Rotor#SetGrundstellung(int)}.
	 */
	@Test
	public void testSetGrundstellung() {
		this.TestableRotor.SetGrundstellung(1);
		assertEquals("0 should encipher as 10",
				10, this.TestableRotor.Encipher(0));
	}

}
