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
		Ciphertext = Integer.toString(Ciphercode + 65).charAt(0);
		return Ciphertext;
	}
	
	/**
	 * 
	 */
	private void StepRotors() {
		if(FastRotor.Step()) {
			if(MediumRotor.Step()) {
				MediumRotor.Step();
				SlowRotor.Step();
			}
		}
	}
	
	/**
	 * @param ReflectorDescription {@link ReflectorFactory#SetupReflector(String)}
	 * @param SlowRotorDescription {@link RotorFactory#SetupRotor(String)}
	 * @param MediumRotorDescription {@link RotorFactory#SetupRotor(String)}
	 * @param FastRotorDescription {@link RotorFactory#SetupRotor(String)}
	 */
	public void LoadRotors(String ReflectorDescription,
			String SlowRotorDescription,
			String MediumRotorDescription,
			String FastRotorDescription) {
		SlowRotor = RotorFactory.SetupRotor(SlowRotorDescription);
		MediumRotor = RotorFactory.SetupRotor(MediumRotorDescription);
		FastRotor = RotorFactory.SetupRotor(FastRotorDescription);
		Reflector = ReflectorFactory.SetupReflector(ReflectorDescription);
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
		char[] x = Grund.toUpperCase().toCharArray();
		SlowRotor.SetGrundstellung(x[0] - 65);
		MediumRotor.SetGrundstellung(x[1] - 65);
		FastRotor.SetGrundstellung(x[2] - 65);
	}

}
