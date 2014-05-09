/**
 * SteckerBoardTest.java Copyright (c) 2014 Robert R. Russell
 */
package com.rrbrussell.enigma_demonstration;

import static org.junit.Assert.*;

import org.junit.Test;

public class SteckerBoardTest {
	
	@Test
	public void testSteckerBoardString() {
		/*
		 * Tests that that steckerboard can handle and empty string
		 */
		try {
			new SteckerBoard("");
		} catch (Exception e) {
			fail("Threw IllegalArgumentException on an empty string");
		}
		/*
		 * Tests that SteckerBoard fails when passed an identity pair.
		 */
		try {
			new SteckerBoard("AA");
			fail("Did not throw IllegalArgumentException");
		} catch (Exception e) {
			assertTrue("Tested Identity pair", 
					e.getClass().equals(IllegalArgumentException.class));
		}
		
		/*
		 * Tests that SteckerBoard will not accept a chained set of pairings.
		 */
		try {
			new SteckerBoard("AB:BC");
			fail("Did not throw IllegalArgumentException");
		} catch (Exception e) {
			assertTrue("Tested chain pairings",
					e.getClass().equals(IllegalArgumentException.class));
		}
		
		/*
		 * Tests that SteckerBoard only accepts a maximum of 10 Pairings as
		 * input.
		 */
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
			assertEquals("i should encode as i", Characters.fromInt(i),
					TestableSB.encipher(Characters.fromInt(i)));
		}
	}

	@Test
	public void testAddSwaping() {
		SteckerBoard TestableSB = new SteckerBoard();
		assertFalse("2 and 5 are not swapped",
				TestableSB.AddSwaping(Utility.intToChar(2),
						Utility.intToChar(5)));
		for (int i = 0; Rotor.SatisfiesRingConstraint(i); i++) {
			if (i == 2) {
				assertEquals("2 should encode as 5", Characters.fromInt(5),
						TestableSB.encipher(Characters.fromInt(i)));
			} else {
				if (i == 5) {
					assertEquals("5 should encode as 2", Characters.fromInt(2),
							TestableSB.encipher(Characters.fromInt(i)));
				} else {
					assertEquals("i should encode as i", Characters.fromInt(i),
							TestableSB.encipher(Characters.fromInt(i)));
				}
			}

		}
		
		assertTrue("2 is already swapped", TestableSB.AddSwaping(
				Utility.intToChar(2), Utility.intToChar(3)));
		assertTrue("5 is already swapped", TestableSB.AddSwaping(
				Utility.intToChar(5), Utility.intToChar(4)));
		for (int i = 0; Rotor.SatisfiesRingConstraint(i); i++) {
			if (i == 2) {
				assertEquals("2 should encode as 5", Characters.fromInt(5),
						TestableSB.encipher(Characters.fromInt(i)));
			} else {
				if (i == 5) {
					assertEquals("5 should encode as 2", Characters.fromInt(2),
							TestableSB.encipher(Characters.fromInt(i)));
				} else {
					assertEquals("i should encode as i", Characters.fromInt(i),
							TestableSB.encipher(Characters.fromInt(i)));
				}
			}

		}		
	}

}
