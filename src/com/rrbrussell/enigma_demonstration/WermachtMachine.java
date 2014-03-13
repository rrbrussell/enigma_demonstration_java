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
	
	private void StepRotors() {
		if(FastRotor.Step()) {
			if(MediumRotor.Step()) {
				MediumRotor.Step();
				SlowRotor.Step();
			}
		}
	}
	
	public void LoadRotors(String ReflectorDescription,
			String SlowRotorDescription,
			String MediumRotorDescription,
			String FastRotorDescription) {
		SlowRotor = RotorFactory.SetupRotor(SlowRotorDescription);
		MediumRotor = RotorFactory.SetupRotor(MediumRotorDescription);
		FastRotor = RotorFactory.SetupRotor(FastRotorDescription);
		Reflector = ReflectorFactory.SetupReflector(ReflectorDescription);
	}
	
	public void SetSteckerBoard(String SteckerBoardPairs) {
		
	}

}
