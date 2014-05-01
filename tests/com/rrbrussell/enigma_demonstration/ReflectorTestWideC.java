/**
 * Copyright (c) 2014 Robert R. Russell
 */
package com.rrbrussell.enigma_demonstration;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.rrbrussell.enigma_demonstration.Reflector.Reflectors;

/**
 * @author Robert R. Russell
 *
 */
public class ReflectorTestWideC {
	
	private Reflector tR;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		tR = new Reflector(Reflectors.WideC);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		tR = null;
	}

	/**
	 * Test method for {@link com.rrbrussell.enigma_demonstration.Reflector#encipher(Characters)}.
	 */
	@Test
	public void testEncipher() {
		Characters[] plaintext = Utility.stringToCharactersArray(
				Utility.Alphabet);
		Characters[] ciphertext = Utility.stringToCharactersArray(
				Reflectors.WideC.getWiringTable());	
		for(int i=0; i < plaintext.length; i++) {
			assertEquals(ciphertext[i],tR.encipher(plaintext[i]));
		}
	}

}
