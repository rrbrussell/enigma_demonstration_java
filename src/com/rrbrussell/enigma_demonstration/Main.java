/**
 * 
 */
package com.rrbrussell.enigma_demonstration;

/**
 * @author Robert R. Russell
 * @author robert@rrbrussell.com
 */
public class Main {
	//TODO Add java doc for Argument enum
	/**
	 * @author Robert R. Russell
	 *
	 */
	private enum Argument {
		Mode(0),
		Walzenlage(1),
		Ringstellung(2),
		Steckerverbindugen(3),
		Grundstellung(4),
		Message_Setting(5),
		Message(6);
		
		public final int index;
		
		Argument(int x) {
			this.index = x;
		}
	}
	
	private static String EncipherUsageMessage =
			"Enigma Encipher Walzenlage Ringsetellung Steckerverbindungen"
					+ " Grundstellung Message-Setting Message";
	
	private static String DecipherUsageMessage =
			"Enigma Decipher Walzenlage Ringsetellung Steckerverbindungen"
					+ " Grundstellung Message-Setting Message";
	
	private static WermachtMachine enigma;
	
	//TODO Add java doc for main
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		char[] EncipheredMessageSetting;
		if( args.length != 7 ) {
			System.out.println("Encipher Usage: " + EncipherUsageMessage);
			System.out.println("Decipher Usage: " + DecipherUsageMessage);
		} else {
		
		enigma = new WermachtMachine();
		LoadRotors(args[Argument.Walzenlage.index],
				args[Argument.Ringstellung.index]);
		enigma.SetSteckerBoard(args[Argument.Steckerverbindugen.index]);
		enigma.setGrundstellung(args[Argument.Grundstellung.index]);
		
		if( args[Argument.Mode.index].equals("Encipher")) {
			EncipheredMessageSetting = encipherMessageSetting(
					args[Argument.Message_Setting.index]);
			System.out.print(args[Argument.Message_Setting.index]);
			System.out.println("\t" + new String(EncipheredMessageSetting));
			enigma.setGrundstellung(args[Argument.Message_Setting.index]);
		} else {
			if( args[Argument.Mode.index].equals("Decipher")) {
				EncipheredMessageSetting = encipherMessageSetting(
						args[Argument.Message_Setting.index]);
				System.out.print(args[Argument.Message_Setting.index]);
				System.out.println("\t" + new String(EncipheredMessageSetting));
				enigma.setGrundstellung(new String(EncipheredMessageSetting));
			} else {
				System.out.print("The only two valid modes are Encipher");
				System.out.println(" or Decipher");
				System.exit(1);
			}
		}
		
		
		
		char[] EncipheredMessage = encipherMessage(
				args[Argument.Message.index]);
		System.out.println(args[Argument.Message.index]);
		System.out.println(new String(EncipheredMessage));
		}

	}
	
	//TODO Add java doc for LoadRotors
	//TODO Rename LoadRotors to loadRotors
	/**
	 * @param str1
	 * @param str2
	 */
	private static void LoadRotors(String str1, String str2) {
		String str1ar[] = str1.split(":");
		String str2ar[] = str2.split(":");
		enigma.LoadRotors("WideB",
				str1ar[0] + ":" + str2ar[0],
				str1ar[1] + ":" + str2ar[1],
				str1ar[2] + ":" + str2ar[2]);
	}
	
	//TODO Add java doc for encipherMessageSetting
	/**
	 * @param ms
	 * @return
	 */
	private static char[] encipherMessageSetting(String ms) {
		char[] temp = ms.toUpperCase().toCharArray();
		char[] ciphertext = new char[temp.length];
		for( int i = 0; i < temp.length; i++) {
			ciphertext[i] = enigma.Encipher(temp[i]);
		}
		return ciphertext;
	}
	
	//TODO Add java doc for encipherMessage
	/**
	 * @param message
	 * @return
	 */
	private static char[] encipherMessage(String message) {
		char[] plaintext = message.toUpperCase().toCharArray();
		char[] ciphertext = new char[plaintext.length];
		for( int i = 0; i < plaintext.length; i++) {
			ciphertext[i] = enigma.Encipher(plaintext[i]);
		}
		return ciphertext;
	}

}
