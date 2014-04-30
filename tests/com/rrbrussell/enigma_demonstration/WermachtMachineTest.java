package com.rrbrussell.enigma_demonstration;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class WermachtMachineTest {
	
	private WermachtMachine M3;

	@Before
	public void setUp() {
		this.M3 = new WermachtMachine();
		String[] RotorTa = {"III","II","I"};
		String[] Grend ={"0","0","0"};
		this.M3.loadRotors("WideB", RotorTa , Grend );
		this.M3.setGrundstellung("AAZ");
		//this.M3.SetSteckerBoard(null);
	}
	
	@After
	public void tearDown() {
		this.M3 = null;
	}

	@Test
	public void testEncipher() {
		char[] plaintext = "AAA".toCharArray();
		char[] ciphertext = new char[plaintext.length];
		for(int i = 0; i < plaintext.length; i++) {
			ciphertext[i] = M3.Encipher(plaintext[i]);
		}
		M3.setGrundstellung("AAZ");
		char[] plaintext2 = new char[plaintext.length];
		for(int j = 0; j < plaintext.length; j++) {
			plaintext2[j] = M3.Encipher(ciphertext[j]);
		}
		System.out.println("plaintext1\t" + new String(plaintext));
		System.out.println("ciphertext\t" + new String(ciphertext));
		System.out.println("plaintext2\t" + new String(plaintext2));
		assertArrayEquals("AAA must encipher and deciphered to itself",
				plaintext, plaintext2);
	}

}
