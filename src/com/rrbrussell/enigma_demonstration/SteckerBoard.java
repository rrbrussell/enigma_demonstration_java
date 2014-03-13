/**
 * 
 */
package com.rrbrussell.enigma_demonstration;

/**
 * This class implements the plugboard or Steckerboard used by the Enigma
 * machine to swap characters before and after encipherment by the rotors 
 * 
 * @author Robert R. Russell
 *
 */
public class SteckerBoard {
	
	private int[] Board = 
		{ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18,
			19, 20, 21, 22, 23, 24, 25 };
	
	/**
	 * Perform the Encipherment based on current SteckerBoard settings.
	 * 
	 * @param Plaintext
	 * @return Ciphertext
	 * @exception IllegalArgumentException if Plaintext is outside 0 &&
	 * Rotor.Ringsize - 1.
	 */
	int Encipher(int Plaintext) {
		if( Plaintext < 0 || Plaintext >= Rotor.RingSize) {
			throw new IllegalArgumentException(
					"Plaintext must between zero and Rotor.RingSize - 1.");
		}
		return Board[Plaintext];
	}
	
	/**
	 * @param First First Character in the swapping
	 * @param Second Second Character in the swapping
	 * @return True if one of the two characters in already in a pairing.
	 */
	boolean AddSwaping(int First, int Second) {
		boolean AlreadySwapped = false;
		if( Board[First] != Board[First] || Board[Second] != Board[Second]) {
			AlreadySwapped = true;
		} else {
			Board[First] = Second;
			Board[Second] = First;
		}
		return AlreadySwapped;
	}
}
