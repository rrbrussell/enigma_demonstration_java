package com.rrbrussell.enigma_demonstration;

import static org.junit.Assert.*;

import org.junit.Test;

public class UtilityTest {

	@Test
	public void testCharToInt() {
		char[] testValues = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
		for(int i = 0; i < testValues.length; i++) {
			assertEquals(i, Utility.charToInt(testValues[i]));
		}
	}

	@Test
	public void testIntToChar() {
		char[] testValues = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
		for(int i = 0; i < testValues.length; i++) {
			assertEquals(testValues[i], Utility.intToChar(i));
		}
	}

}
