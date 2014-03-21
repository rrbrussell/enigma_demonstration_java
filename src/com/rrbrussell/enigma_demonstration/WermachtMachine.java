/**
 * 
 */
package com.rrbrussell.enigma_demonstration;

import com.rrbrussell.enigma_demonstration.reflectors.ReflectorFactory;
import com.rrbrussell.enigma_demonstration.rotors.RotorFactory;

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
		int Ciphercode = (int) Plaintext - 65;
		StepRotors();
		Ciphercode = SteckerBoard.Encipher(Ciphercode);
		Ciphercode = FastRotor.Encipher(Ciphercode);
		Ciphercode = MediumRotor.Encipher(Ciphercode);
		Ciphercode = SlowRotor.Encipher(Ciphercode);
		Ciphercode = Reflector.Encipher(Ciphercode);
		Ciphercode = SlowRotor.Encipher(Ciphercode);
		Ciphercode = MediumRotor.Encipher(Ciphercode);
		Ciphercode = FastRotor.Encipher(Ciphercode);
		Ciphercode = SteckerBoard.Encipher(Ciphercode);
		Ciphertext = (char) (Ciphercode + 65);
		return Ciphertext;
	}
	
	/**
	 * 
	 */
	private void StepRotors() {
		System.out.println("--StepRotors--");
		printRotors();
		if(FastRotor.Step()) {
			if(MediumRotor.Step()) {
				SlowRotor.Step();
			}
		}
		printRotors();
		System.out.println("--StepRotors--");
	}
	
	/**
	 * @param ReflectorDescription {@link ReflectorFactory#SetupReflector(String)}
	 * @param SlowRotorDescription {@link RotorFactory#SetupRotor(String)}
	 * @param MediumRotorDescription {@link RotorFactory#SetupRotor(String)}
	 * @param FastRotorDescription {@link RotorFactory#SetupRotor(String)}
	 */
	public void loadRotors(String ReflectorDescription,
			String SlowRotorDescription,
			String MediumRotorDescription,
			String FastRotorDescription) {
		SlowRotor = RotorFactory.SetupRotor(SlowRotorDescription);
		MediumRotor = RotorFactory.SetupRotor(MediumRotorDescription);
		FastRotor = RotorFactory.SetupRotor(FastRotorDescription);
		Reflector = ReflectorFactory.SetupReflector(ReflectorDescription);
	}
	
	public void loadRotors(String ReflectorChoice, String[] RotorTable,
			String[] RingstellungTable) {
		Reflector = ReflectorFactory.SetupReflector(ReflectorChoice);
		SlowRotor = RotorFactory.SetupRotor(RotorTable[0] + ":"
				+ RingstellungTable[0]);
		MediumRotor = RotorFactory.SetupRotor(RotorTable[1] + ":"
				+ RingstellungTable[1]);
		FastRotor = RotorFactory.SetupRotor(RotorTable[2] + ":"
				+ RingstellungTable[2]);
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
		System.out.println("--setGrundstellung--" + Grund);
		printRotors();
		char[] x = Grund.toCharArray();
		System.out.println(x);
		SlowRotor.SetGrundstellung(Utility.charToInt(x[0]));
		MediumRotor.SetGrundstellung(Utility.charToInt(x[1]));
		FastRotor.SetGrundstellung(Utility.charToInt(x[2]));
		printRotors();
	}
	
	public void printRotors() {
		System.out.println("Slow:\t"+SlowRotor);
		System.out.println("Medium:\t"+MediumRotor);
		System.out.println("Fast:\t"+FastRotor);
	}

}
