/**
 * Copyright (c) 2014 Robert R. Russell
 */
package com.rrbrussell.enigma_demonstration;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Properties;

import com.rrbrussell.enigma_demonstration.Reflector.Reflectors;
import com.rrbrussell.enigma_demonstration.Rotor.Rotors;

/**
 * The Wermacht Enigma machine
 * @author Robert R. Russell
 * @author robert@rrbrussell.com
 */
public class M3Machine {

	private Rotor FastRotor;
	
	private Rotor MediumRotor;
	
	private Rotor SlowRotor;
	
	private Reflector Reflector;
	
	private SteckerBoard SteckerBoard;
	
	public M3Machine() {
		SteckerBoard = new SteckerBoard();
	}
	
	/**
	 * Perform the encipherment of a character.
	 * @param Plaintext 
	 * @return The ciphertext.
	 */
	public Characters Encipher(Characters Plaintext) {
		Characters Ciphertext;
		StepRotors();
		//System.out.print("P: " + Character.toString(Plaintext) + " > ");
		Ciphertext = SteckerBoard.encipher(Plaintext);
		//System.out.print("SB: " + Character.toString(Ciphertext) + " > ");
		Ciphertext = FastRotor.encipherRightToLeft(Ciphertext);
		//System.out.print("FR: " + Character.toString(Ciphertext) + " > ");
		Ciphertext = MediumRotor.encipherRightToLeft(Ciphertext);
		//System.out.print("MR: " + Ciphertext + " > ");
		Ciphertext = SlowRotor.encipherRightToLeft(Ciphertext);
		//System.out.print("SR: " + Ciphertext + " > ");
		Ciphertext = Reflector.encipher(Ciphertext);
		//System.out.print("R: " + Ciphertext + " > ");
		Ciphertext = SlowRotor.encipherLeftToRight(Ciphertext);
		//System.out.print("SR: " + Ciphertext + " > ");
		Ciphertext = MediumRotor.encipherLeftToRight(Ciphertext);
		//System.out.print("MR: " + Ciphertext + " > ");
		Ciphertext = FastRotor.encipherLeftToRight(Ciphertext);
		//System.out.print("FR: " + Ciphertext + " > ");
		Ciphertext = SteckerBoard.encipher(Ciphertext);
		//System.out.println("SB: " + Ciphertext);
		return Ciphertext;
	}
	
	public Iterator<Characters> encipher(Iterator<Characters> plaintext) {
		LinkedList<Characters> fullCiphertext = new LinkedList<Characters>();
		while(plaintext.hasNext()) {
			fullCiphertext.add(Encipher(plaintext.next()));
		}
		return fullCiphertext.iterator();
	}
	
	/**
	 * Rotates the rotors.
	 */
	private void StepRotors() {
		if(FastRotor.Step()) {
			if(MediumRotor.Step()) {
				SlowRotor.Step();
			}
		}
	}
	
	/**
	 * @throws IllegalArgumentException On normal parsing errors.
	 * @param ReflectorChoice "WideB" or "WideC"
	 * @param RotorTable A 3 item array of Strings found in Rotors.getValues().
	 * @param ringOffsetTable A 3 item array of ints as strings. 
	 */
	public void loadRotors(Reflectors ReflectorChoice, Rotors[] RotorTable,
			Characters[] ringOffsetTable) {
		if(RotorTable.length != 3 || ringOffsetTable.length != 3) {
			throw new IllegalArgumentException("RotorTable and "
					+ "RingstellungTable must be 3 items long");
		}
		Reflector = new Reflector(ReflectorChoice);
		SlowRotor = new Rotor(RotorTable[0], ringOffsetTable[0]);
		MediumRotor = new Rotor(RotorTable[1], ringOffsetTable[1]);
		FastRotor = new Rotor(RotorTable[2], ringOffsetTable[2]);
	}
	
	/**
	 * @param SteckerBoardPairs
	 */
	public void SetSteckerBoard(String SteckerBoardPairs) {
		SteckerBoard = new SteckerBoard(SteckerBoardPairs);
	}
	
	/**
	 * @param newIndicators
	 */
	public void setIndicators(Characters[] newIndicators) {
		if(newIndicators.length != 3) {
			throw new IllegalArgumentException("Must have 3 "
					+ "new Indicator settings");
		}
		SlowRotor.setIndicator(newIndicators[0]);
		MediumRotor.setIndicator(newIndicators[1]);
		FastRotor.setIndicator(newIndicators[2]);
	}
	
	/**
	 * Support code for testing only.
	 */
	public void printRotors() {
		System.out.println("Slow:\t"+SlowRotor);
		System.out.println("Medium:\t"+MediumRotor);
		System.out.println("Fast:\t"+FastRotor);
	}
	
	/**
	 * 
	 * @param inProp Null if these properties are to be separate from other
	 * Properties.
	 * @return <b><i>machineType.</i></b><dl>
	 *  <dt><b>numberOfRotors</b></dt>
	 *  <dd>The number of simultaneously usable Rotors.</dd>
	 *  <dt><b>validRotors</b></dt>
	 *  <dd>A space delimited string of valid Rotor Names</dd>
	 *  <dt><b>numberOfReflectors</b></dt>
	 *  <dd>The number of simultaneously usable Reflectors.</dd>
	 *  <dt><b>validReflectors</b></dt>
	 *  <dd>A space delimited string of valid Reflector Names</dd>
	 *  </dl>
	 */
	public static Properties machineDescription(Properties inProp) {
		String machineType = "M3";
		if(inProp == null) {
			inProp = new Properties();
		}
		inProp.setProperty(machineType + ".numberOfRotors", "3");
		inProp.setProperty(machineType + ".validRotors", "I II III IV V");
		inProp.setProperty(machineType + ".numberOfReflectors", "1");
		inProp.setProperty(machineType + ".validReflectors", "WideB WideC");
		return inProp;
	}
}
