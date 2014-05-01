/**
 * Copyright (c) 2014 Robert R. Russell
 */
package com.rrbrussell.enigma_demonstration;

import com.rrbrussell.enigma_demonstration.Rotor.Rotors;
import com.rrbrussell.enigma_demonstration.reflectors.ReflectorFactory;

/**
 * The Wermacht Enigma machine
 * @author Robert R. Russell
 * @author robert@rrbrussell.com
 */
public class WermachtMachine {

	private Rotor FastRotor;
	
	private Rotor MediumRotor;
	
	private Rotor SlowRotor;
	
	private Reflector Reflector;
	
	private SteckerBoard SteckerBoard;
	
	public WermachtMachine() {
		SteckerBoard = new SteckerBoard();
	}
	
	/**
	 * Perform the encipherment of a character.
	 * @param Plaintext 
	 * @return The ciphertext.
	 */
	public char Encipher(char Plaintext) {
		char Ciphertext;
		StepRotors();
		//System.out.print("P: " + Character.toString(Plaintext) + " > ");
		Ciphertext = SteckerBoard.Encipher(Plaintext);
		//System.out.print("SB: " + Character.toString(Ciphertext) + " > ");
		Ciphertext = FastRotor.encipherRightToLeft(Ciphertext);
		//System.out.print("FR: " + Character.toString(Ciphertext) + " > ");
		Ciphertext = MediumRotor.encipherRightToLeft(Ciphertext);
		//System.out.print("MR: " + Ciphertext + " > ");
		Ciphertext = SlowRotor.encipherRightToLeft(Ciphertext);
		//System.out.print("SR: " + Ciphertext + " > ");
		Ciphertext = Reflector.Encipher(Ciphertext);
		//System.out.print("R: " + Ciphertext + " > ");
		Ciphertext = SlowRotor.encipherLeftToRight(Ciphertext);
		//System.out.print("SR: " + Ciphertext + " > ");
		Ciphertext = MediumRotor.encipherLeftToRight(Ciphertext);
		//System.out.print("MR: " + Ciphertext + " > ");
		Ciphertext = FastRotor.encipherLeftToRight(Ciphertext);
		//System.out.print("FR: " + Ciphertext + " > ");
		Ciphertext = SteckerBoard.Encipher(Ciphertext);
		//System.out.println("SB: " + Ciphertext);
		return Ciphertext;
	}
	
	/**
	 * Rotates the rotors.
	 */
	private void StepRotors() {
		//System.out.println("--StepRotors--");
		//printRotors();
		if(FastRotor.Step()) {
			if(MediumRotor.Step()) {
				SlowRotor.Step();
			}
		}
		//printRotors();
		//System.out.println("--StepRotors--");
	}
	
	/**
	 * @throws IllegalArgumentException On normal parsing errors.
	 * @throws RotorSizeException On bad Ringstellung values.
	 * @param ReflectorChoice "WideB" or "WideC"
	 * @param RotorTable A 3 item array of Strings found in Rotors.getValues().
	 * @param RingstellungTable A 3 item array of ints as strings. 
	 */
	public void loadRotors(String ReflectorChoice, String[] RotorTable,
			String[] RingstellungTable) {
		Reflector = ReflectorFactory.SetupReflector(ReflectorChoice);
		SlowRotor = new Rotor(Rotors.valueOf(RotorTable[0]),
				Integer.parseInt(RingstellungTable[0]));
		MediumRotor = new Rotor(Rotors.valueOf(RotorTable[1]),
				Integer.parseInt(RingstellungTable[1]));
		FastRotor = new Rotor(Rotors.valueOf(RotorTable[2]),
				Integer.parseInt(RingstellungTable[2]));
	}
	
	/**
	 * @param SteckerBoardPairs
	 */
	public void SetSteckerBoard(String SteckerBoardPairs) {
		SteckerBoard = new SteckerBoard(SteckerBoardPairs);
	}
	
	/**
	 * @param Grund
	 */
	public void setGrundstellung(String Grund) {
		//System.out.println("--setGrundstellung--" + Grund);
		//printRotors();
		char[] x = Grund.toCharArray();
		//System.out.println(x);
		SlowRotor.SetGrundstellung(Utility.charToInt(x[0]));
		MediumRotor.SetGrundstellung(Utility.charToInt(x[1]));
		FastRotor.SetGrundstellung(Utility.charToInt(x[2]));
		//printRotors();
	}
	
	/**
	 * Support code for testing only.
	 */
	public void printRotors() {
		System.out.println("Slow:\t"+SlowRotor);
		System.out.println("Medium:\t"+MediumRotor);
		System.out.println("Fast:\t"+FastRotor);
	}

}
