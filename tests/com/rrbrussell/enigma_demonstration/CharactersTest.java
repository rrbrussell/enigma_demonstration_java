/**
 * CharactersTest.java Copyright (C) 2014 Robert R. Russell
 */
package com.rrbrussell.enigma_demonstration;

import static org.junit.Assert.*;

import org.junit.Test;

public class CharactersTest {
	
	@Test
	public void testConstraint() {
		char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
		char[] constructedAlphabet = new char[alphabet.length];
		for(Characters charc: Characters.values()) {
			constructedAlphabet[charc.ordinal()] = charc.toString().charAt(0);
		}
		assertArrayEquals("A major constraint failed.", alphabet,
				constructedAlphabet);
	}

	@Test
	public void testNext() {
		Characters currentChar = Characters.A;
		for(int i=0; i < 26; i++) {
			assertEquals(String.valueOf(i) + " does not equal the ordinal value"
				+ " of " + currentChar.toString(), i, currentChar.ordinal());
			currentChar = currentChar.next();
		}
		assertEquals("currentChar should have an ordinal of 0", 0,
				currentChar.ordinal());
	}

	@Test
	public void testPrevious() {		
		Characters currentChar = Characters.Z;
		for(int i=25; i >= 0; i--) {
			assertEquals(String.valueOf(i) + " does not equal the ordinal value"
				+ " of " + currentChar.toString(), i, currentChar.ordinal());
			currentChar = currentChar.previous();
		}
		assertEquals("currentChar should have an ordinal of 25", 25,
				currentChar.ordinal());
	}

	@Test
	public void testForwardBy() {
		Characters nextingChar = Characters.X;
		Characters forwardingChar = Characters.X;
		int jumpingForwardBy = 4;
		forwardingChar = forwardingChar.forwardBy((
				Characters.fromInt(jumpingForwardBy)));
		for(int i=0; i < jumpingForwardBy; i++) {
			nextingChar = nextingChar.next();
		}
		assertEquals(nextingChar, forwardingChar);
	}

	@Test
	public void testBackwardBy() {
		Characters previousingChar = Characters.E;
		Characters backwardingChar = Characters.E;
		int jumpingBackwardsBy = 7;
		backwardingChar = backwardingChar.forwardBy((
				Characters.fromInt(jumpingBackwardsBy)));
		for(int i=0; i < jumpingBackwardsBy; i++) {
			previousingChar = previousingChar.next();
		}
		assertEquals(previousingChar, backwardingChar);
	}

}
