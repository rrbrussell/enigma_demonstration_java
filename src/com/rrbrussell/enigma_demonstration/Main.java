/**
 * 
 */
package com.rrbrussell.enigma_demonstration;

import org.apache.commons.cli.*;

/**
 * @author Robert R. Russell
 * @author robert@rrbrussell.com
 */
public class Main {
	private static WermachtMachine enigma;
	
	private static Options CommandLineOptions;
	
	//TODO Add java doc for main
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		buildCommandLineOptionList();
		CommandLineParser CLP = new GnuParser();
		CommandLine CL = null;
		try {
			CL = CLP.parse(CommandLineOptions, args);
		} catch(ParseException e) {
			System.err.println(e.getMessage());
		}
		/*
		System.out.println("stuff");
		Option[] ParsedArgsu = CL.getOptions();
		for( Option ArgOption : ParsedArgsu) {
			System.out.println(ArgOption.toString());
		}
		System.out.println("Remaining items");
		args = CL.getArgs();
		for( String arg : args) {
			System.out.println(arg);
		}
		System.out.println("\nParsing Objects");
		for( String x : CL.getOptionValues("s")) {
			System.out.println(x);
		}*/
		enigma = new WermachtMachine();
		enigma.loadRotors("WideB", CL.getOptionValues("w"),
				CL.getOptionValues("r"));
		enigma.SetSteckerBoard(CL.getOptionValue("s"));
		enigma.setGrundstellung(CL.getOptionValue("g"));
		char[] Plaintext = CL.getOptionValue("ms").toUpperCase().toCharArray();		
		char[] Ciphertext = new char[Plaintext.length];
		for(int i = 0; i < Plaintext.length; i++) {
			Ciphertext[i] = enigma.Encipher(Plaintext[i]);
		}
		System.out.println(new String(Plaintext) + "\t"
				+ new String(Ciphertext));
	}
	
	@SuppressWarnings("static-access")
	private static void buildCommandLineOptionList() {
		Option walzenlage = OptionBuilder.withArgName("walzenlage")
				.hasArgs(3)
				.withValueSeparator(':')
				.withDescription("3 rotor names sperated by :")
				.withLongOpt("walzenlage").create('w');
		Option ringstellung = OptionBuilder.withArgName("ringstellung")
				.hasArgs(3)
				.withValueSeparator(':')
				.withDescription("3 numbers or letters seperated by :")
				.withLongOpt("ringstellung").create('r');
		Option steckerverbindungen = OptionBuilder.withArgName("pairs")
				.hasArg()
				//.withValueSeparator(':')
				.withDescription("Up to 10 pairs of letters seperated by :")
				.withLongOpt("steckerverbindungen").create('s');
		Option grundstellung = OptionBuilder.withArgName("grundstellung")
				.hasArg()
				.withDescription("3 characters like ABC or ZYX")
				.withLongOpt("grundstellung").create('g');
		Option messageSetting = OptionBuilder.withArgName("message-setting")
				.hasArg()
				.withDescription("3 characters like ADE or ZIO")
				.withLongOpt("message-setting").create("ms");
		Option encode = new Option("encode", "Encode a message");
		Option decode = new Option("decode", "decode a message");
		
		CommandLineOptions = new Options();
		CommandLineOptions.addOption(decode);
		CommandLineOptions.addOption(encode);
		CommandLineOptions.addOption(messageSetting);
		CommandLineOptions.addOption(grundstellung);
		CommandLineOptions.addOption(steckerverbindungen);
		CommandLineOptions.addOption(ringstellung);
		CommandLineOptions.addOption(walzenlage);
	}
}
