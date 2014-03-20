/**
 * 
 */
package com.rrbrussell.enigma_demonstration;

/**
 * @author Robert R. Russell
 * @author robert@rrbrussell.com
 */
public class Main {
	private enum Argument {
		Walzenlage(0),
		Ringstellung(1),
		Steckerverbindugen(2),
		Grundstellung(3),
		Message_Setting(4),
		Message(5);
		
		public final int index;
		
		Argument(int x) {
			this.index = x;
		}
	}
	
	private static WermachtMachine enigma;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		if( args.length != 6 ) {
			System.out.print("Usage: Enigma Walzenlage Ringstellung");
			System.out.print(" Steckerverbindungen Grundstellung");
			System.out.print(" Message-Setting");
			System.out.print(" Message" + System.lineSeparator());
		}
		
		enigma = new WermachtMachine();
		LoadRotors(args[Argument.Walzenlage.index],
				args[Argument.Ringstellung.index]);
		enigma.SetSteckerBoard(args[Argument.Steckerverbindugen.index]);
		enigma.setGrundstellung(args[Argument.Grundstellung.index]);
		char[] temp = 
			args[Argument.Message_Setting.index].toUpperCase().toCharArray();
		char[] ciphertext = new char[temp.length];
		for( int i = 0; i < temp.length; i++) {
			ciphertext[i] = enigma.Encipher(temp[i]) + 65;
		}
		System.out.println(new String(temp) + "\t" +
				new String(ciphertext));

		
		
		System.out.println(args.length);
		for( String arg : args) {
			System.out.println(arg);
		}

	}
	
	private static void LoadRotors(String str1, String str2) {
		String str1ar[] = str1.split(":");
		String str2ar[] = str2.split(":");
		enigma.LoadRotors("WideB",
				str1ar[0] + ":" + str2ar[0],
				str1ar[1] + ":" + str2ar[1],
				str1ar[2] + ":" + str2ar[2]);
	}

}
