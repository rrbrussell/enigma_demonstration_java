package com.rrbrussell.enigma_demonstration.reflectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import com.rrbrussell.enigma_demonstration.Reflector;
import com.rrbrussell.enigma_demonstration.RingSizeException;

public class WideBTest {
	private Reflector TestableReflector;

	@Before
	public void setUp() throws Exception {
		this.TestableReflector = new WideB();
	}

	@Test
	public void testEncipher() {
		assertEquals("0 enciphers as 24", 24,
				this.TestableReflector.Encipher(0));
		assertEquals("25 enciphers as 19", 19,
				this.TestableReflector.Encipher(25));
		try {
			this.TestableReflector.Encipher(26);
			fail("RingSizeException test did not throw and exception.");
		} catch (Exception e) {
			assertTrue("Did not throw a RingSizeException",
					e.getClass().equals(RingSizeException.class));
		}
	}

}
