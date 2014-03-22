/**
 * Copyright (c) 2014 Robert R. Russell
 */
package com.rrbrussell.enigma_demonstration;

import static org.junit.Assert.*;

import org.junit.Test;

public class SteckerBoardTest {
	
	@Test
	public void testSteckerBoardString() {
		try {
			new SteckerBoard("");
			fail("Did not throw IllegalArgumentException");
		} catch (Exception e) {
			assertTrue(e.getClass().equals(IllegalArgumentException.class));
		}
		
		try {
			new SteckerBoard("AA");
			fail("Did not throw IllegalArgumentException");
		} catch (Exception e) {
			assertTrue(e.getClass().equals(IllegalArgumentException.class));
		}
		
		try {
			new SteckerBoard("AB:BC");
			fail("Did not throw IllegalArgumentException");
		} catch (Exception e) {
			assertTrue(e.getClass().equals(IllegalArgumentException.class));
		}
		
		try {
			new SteckerBoard("AB:CD:EF:GH:IJ:KL:MN:OP:QR:ST:UV");
			fail("Did not throw IllegalArgumentException");
		} catch (Exception e) {
			assertTrue(e.getClass().equals(IllegalArgumentException.class));
		}
	}

	@Test
	public void testEncipher() {
		SteckerBoard TestableSB = new SteckerBoard();
		for( int i = 0; Rotor.SatisfiesRingConstraint(i); i++) {
			assertEquals("i should encode as i", i, TestableSB.Encipher(i));
		}
		try {
			TestableSB.Encipher(26);
			fail("Did not throw exception");
		} catch (Exception e) {
			assertTrue(e.getClass().equals(RingSizeException.class));
		}
	}

	@Test
	public void testAddSwaping() {
		SteckerBoard TestableSB = new SteckerBoard();
		assertFalse("2 and 5 are not swapped", TestableSB.AddSwaping(2, 5));
		for (int i = 0; Rotor.SatisfiesRingConstraint(i); i++) {
			if (i == 2) {
				assertEquals("2 should encode as 5", 5, TestableSB.Encipher(2));
			} else {
				if (i == 5) {
					assertEquals("5 should encode as 2", 2,
							TestableSB.Encipher(5));
				} else {
					assertEquals("i should encode as i", i,
							TestableSB.Encipher(i));
				}
			}

		}
		
		assertTrue("2 is already swapped", TestableSB.AddSwaping(2, 3));
		assertTrue("5 is already swapped", TestableSB.AddSwaping(5, 4));
		for (int i = 0; Rotor.SatisfiesRingConstraint(i); i++) {
			if (i == 2) {
				assertEquals("2 should encode as 5", 5, TestableSB.Encipher(2));
			} else {
				if (i == 5) {
					assertEquals("5 should encode as 2", 2,
							TestableSB.Encipher(5));
				} else {
					assertEquals("i should encode as i", i,
							TestableSB.Encipher(i));
				}
			}

		}		
	}

}
