/**
 * 
 */
package com.rrbrussell.enigma_demonstration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.rrbrussell.enigma_demonstration.rotors.RotorII;

/**
 * @author Robert R. Russell
 *
 */
public class RotorIITest {
	RotorII TestableRotor;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		TestableRotor = new RotorII(0);
	}

	/**
	 * Test method for {@link com.rrbrussell.enigma_demonstration.Rotor#Step()}.
	 */
	@Test
	public void testStep() {
		assertEquals("0 should encipher as 0",
				0, this.TestableRotor.Encipher(0));
		assertFalse("Stepping from 0 to 1 should not flip next Rotor",
				this.TestableRotor.Step());
		assertEquals("0 should now encipher as 9",
				9, this.TestableRotor.Encipher(0));
		this.TestableRotor.SetGrundstellung(4);
		assertTrue("Stepping from 4 to 5 should flip next Rotor",
				this.TestableRotor.Step());
		assertEquals("0 should encipher as 8", 8,
				this.TestableRotor.Encipher(0));
	}

	/**
	 * Test method for {@link com.rrbrussell.enigma_demonstration.Rotor#Encipher(int)}.
	 */
	@Test
	public void testEncipher() {
		assertEquals("0 should encipher as 0",
				0, this.TestableRotor.Encipher(0));
	}

	/**
	 * Test method for {@link com.rrbrussell.enigma_demonstration.Rotor#SetGrundstellung(int)}.
	 */
	@Test
	public void testSetGrundstellung() {
		this.TestableRotor.SetGrundstellung(1);
		assertEquals("0 should encipher as 9",
				9, this.TestableRotor.Encipher(0));
	}

}
