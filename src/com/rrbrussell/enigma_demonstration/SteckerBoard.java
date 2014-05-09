/**
 * SteckerBoard.java Copyright (c) 2014 Robert R. Russell
 */
package com.rrbrussell.enigma_demonstration;

import java.util.EnumMap;
import java.util.EnumSet;

/**
 * This class implements the plugboard or Steckerboard used by the Enigma
 * machine to swap characters before and after encipherment by the rotors 
 * 
 * @author Robert R. Russell
 * @author robert@rrbrussell.com
 */
public class SteckerBoard {
	
	private EnumMap<Characters, Characters> board;
	
	/**
	 * Creates an empty SteckerBoard
	 */
	public SteckerBoard() {
		board = new EnumMap<Characters, Characters>(Characters.class);
		for(Characters value:
			Utility.stringToCharactersArray(Utility.Alphabet)) {
			board.put(value, value);
		}
	}
	
	/**
	 * Creates a SteckerBoard with pairs of characters connected.
	 * 
	 * @param Pairings A : delimited string of pairs of characters.
	 * @exception IllegalArgumentException May be thrown for:
	 * <ul>
	 * <li>Not following the definition of a pair
	 * <li>Adding a character to a second pair
	 * <li>Pairing a character with itself
	 * <li>Trying to add more than ten pairings.
	 * </ul>
	 */
	public SteckerBoard(String Pairings) {
		if(Pairings == null || Pairings.length() == 0 ) {
			// Do nothing
		} else {
			board = new EnumMap<Characters, Characters>(Characters.class);
			for(Characters value:
				Utility.stringToCharactersArray(Utility.Alphabet)) {
				board.put(value, value);
			}
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
			if( AddSwaping(Pair.charAt(0), Pair.charAt(1))) {
				throw new IllegalArgumentException(
						"Cannot chain pairings.");
			}
		}
		}
		
	}
	
	/**
	 * Perform the Encipherment based on current SteckerBoard settings.
	 * 
	 * @param plaintext
	 * @return Ciphertext
	 */
	public Characters encipher(Characters plaintext) {
		return board.get(plaintext);
	}
	
	/**
	 * May not remain public:
	 * <p>
	 * Used to create a swapping between characters.
	 * @param First First Character in the swapping
	 * @param Second Second Character in the swapping
	 * @return True if one of the two characters in already in a pairing.
	 */
	public boolean AddSwaping(char First, char Second) {
		Characters firstCharacter = Characters.fromChar(First);
		Characters secondCharacter = Characters.fromChar(Second);
		boolean AlreadySwapped = false;
		if( !(board.get(firstCharacter).equals(firstCharacter)) ||
				!(board.get(secondCharacter).equals(secondCharacter))) {
			AlreadySwapped = true;
		} else {
			board.put(firstCharacter, secondCharacter);
			board.put(secondCharacter, firstCharacter);
		}
		return AlreadySwapped;
	}
	
	/**
	 * Returns true if all of the following conditions are met:
	 * <ul>
	 * <li>Not following the definition of a pair
	 * <li>Adding a character to a second pair
	 * <li>Pairing a character with itself
	 * <li>Trying to add more than ten pairings.
	 * </ul>
	 * @since 0.3
	 */
	public static boolean validatePairings(String input) {
		boolean valid = true;
		EnumSet<Characters> foundItems = EnumSet.noneOf(Characters.class);
		input = input.toUpperCase();
		String[] subStrings = input.split(":");
		if(subStrings.length == 10) { 
			try {
				for(String subString: subStrings) {
					for(Characters tempChar:
						Utility.stringToCharactersArray(subString)) {
						if(foundItems.contains(tempChar)) {
							valid = false;
						}
						foundItems.add(tempChar);
					}

				}
			}
			catch(IllegalArgumentException exception) {
				return false;
			}
		} else {
			valid = false;
		}
		return valid;
	}

}
