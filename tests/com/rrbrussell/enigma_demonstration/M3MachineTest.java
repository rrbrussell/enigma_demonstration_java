package com.rrbrussell.enigma_demonstration;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.rrbrussell.enigma_demonstration.Reflector.Reflectors;
import com.rrbrussell.enigma_demonstration.Rotor.Rotors;

public class M3MachineTest {
	
	private M3Machine M3;

	@Before
	public void setUp() {
		M3 = new M3Machine();
		Rotors[] RotorTa = {Rotors.III, Rotors.II, Rotors.I};
		Characters[] Grend ={Characters.A, Characters.A, Characters.A};
		M3.loadRotors(Reflectors.WideB, RotorTa , Grend );
		M3.setIndicators(Utility.stringToCharactersArray("AAZ"));
	}
	
	@After
	public void tearDown() {
		this.M3 = null;
	}

	@Test
	public void testEncipher() {
		Characters[] plaintext = Utility.stringToCharactersArray("AAA");
		Characters[] ciphertext = new Characters[plaintext.length];
		for(int i = 0; i < plaintext.length; i++) {
			ciphertext[i] = M3.Encipher(plaintext[i]);
		}
		M3.setIndicators(Utility.stringToCharactersArray("AAZ"));
		Characters[] plaintext2 = new Characters[plaintext.length];
		for(int j = 0; j < plaintext.length; j++) {
			plaintext2[j] = M3.Encipher(ciphertext[j]);
		}
		//System.out.println("plaintext1\t" + new String(plaintext));
		//System.out.println("ciphertext\t" + new String(ciphertext));
		//System.out.println("plaintext2\t" + new String(plaintext2));
		assertArrayEquals("AAA must encipher and deciphered to itself",
				plaintext, plaintext2);
	}
}
