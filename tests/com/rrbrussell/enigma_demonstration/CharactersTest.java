/**
 * package com.rrbrussell.enigma_demonstration;
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
		//System.out.println("\n\ntestNext()---");
		Characters currentChar = Characters.A;
		for(int i=0; i < 26; i++) {
			//System.out.println(String.valueOf(i) + " equals the ordinal value"
			//	+ " of " + currentChar.toString());
			assertEquals(String.valueOf(i) + " does not equal the ordinal value"
				+ " of " + currentChar.toString(), i, currentChar.ordinal());
			currentChar = currentChar.next();
		}
		//System.out.println("The loop ended on " + currentChar.toString());
		assertEquals("currentChar should have an ordinal of 0", 0,
				currentChar.ordinal());
	}

	@Test
	public void testPrevious() {
		//System.out.println("\n\ntestPrevious()---");		
		Characters currentChar = Characters.Z;
		for(int i=25; i >= 0; i--) {
		//	System.out.println(String.valueOf(i) + " equals the ordinal value"
		//		+ " of " + currentChar.toString());
			assertEquals(String.valueOf(i) + " does not equal the ordinal value"
				+ " of " + currentChar.toString(), i, currentChar.ordinal());
			currentChar = currentChar.previous();
		}
		//System.out.println("The loop ended on " + currentChar.toString());
		assertEquals("currentChar should have an ordinal of 25", 25,
				currentChar.ordinal());
	}

	@Test
	public void testForwardBy() {
		//System.out.println("\n\ntestForwardBy()---");
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
		//System.out.println("\n\ntestBackwardBy()---");
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
