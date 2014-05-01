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
	 * Test method for {@link com.rrbrussell.enigma_demonstration.Reflector#Encipher(char)}.
	 */
	@Test
	public void testEncipher() {
		String plaintext = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String ciphertext = Reflectors.WideC.getWiringTable();	
		for(int i=0; i < plaintext.length(); i++) {
			//System.out.println(String.format("%1$c encodes as %2$c",
			//		plaintext.charAt(i), ciphertext.charAt(i)));
			assertEquals(ciphertext.charAt(i),tR.Encipher(Utility.intToChar(i)));
		}
	}

}
