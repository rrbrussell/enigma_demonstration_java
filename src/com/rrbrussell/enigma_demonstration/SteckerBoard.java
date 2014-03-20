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
	 * Creates an empty SteckerBoard
	 */
	public SteckerBoard( ) {
		super();
	}
	
	/**
	 * Creates a SteckerBoard with pairs of characters connected.
	 * 
	 * @param Pairings A space delimited string of pairs of characters.
	 * @exception IllegalArgumentException May be thrown for:
	 * <ul>
	 * <li>Not following the definition of a pair
	 * <li>Adding a character to a second pair
	 * <li>Pairing a character with itself
	 * <li>Trying to add more than ten pairings.
	 * </ul>
	 */
	public SteckerBoard(String Pairings) {
		Pairings = Pairings.toUpperCase();
		String Pairing[] = Pairings.split(":");
		if( Pairing.length > 10 ) {
			throw new IllegalArgumentException(
					"Only 10 pairings are allowed.");
		}
		for( String Pair: Pairing) {
			if( Pair.length() != 2) {
				throw new IllegalArgumentException(
						"A pair is only two items.");
				}
			if( Pair.charAt(0) == Pair.charAt(1)) {
				throw new IllegalArgumentException(
						"A character already pairs itself.");
			}
			if( AddSwaping((int) Pair.charAt(0) - 65 ,
					(int) Pair.charAt(1) - 65)) {
				throw new IllegalArgumentException(
						"Cannot chain pairings.");
			}
		}
		
	}
	
	/**
	 * Perform the Encipherment based on current SteckerBoard settings.
	 * 
	 * @param Plaintext
	 * @return Ciphertext
	 * @exception IllegalArgumentException if Plaintext is outside 0 &&
	 * Rotor.Ringsize - 1.
	 */
	public int Encipher(int Plaintext) {
		if( !Rotor.SatisfiesRingConstraint(Plaintext)) {
			throw new RingSizeException();
		}
		return Board[Plaintext];
	}
	
	/**
	 * May not remain public:
	 * <p>
	 * Used to create a swapping between characters.
	 * @param First First Character in the swapping
	 * @param Second Second Character in the swapping
	 * @return True if one of the two characters in already in a pairing.
	 */
	public boolean AddSwaping(int First, int Second) {
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
