/**
 * Copyright (c) 2014 Robert R. Russell
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
	
	/** Read README.md for usage instructions
	 * @param args The command line arguments.
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
		
		enigma = new WermachtMachine();
		enigma.loadRotors("WideB", CL.getOptionValues("w"),
				CL.getOptionValues("r"));
		if(CL.hasOption("s")) {
			enigma.SetSteckerBoard(CL.getOptionValue("s"));
		}
		enigma.setIndicators(CL.getOptionValue("g"));
		char[] Plaintext = CL.getOptionValue("ms").toUpperCase().toCharArray();		
		char[] Ciphertext = new char[Plaintext.length];
		for(int i = 0; i < Plaintext.length; i++) {
			Ciphertext[i] = enigma.Encipher(Plaintext[i]);
		}
		//System.out.println(new String(Plaintext) + "\t"
		//		+ new String(Ciphertext));
		
		if(CL.hasOption("encode")) {
			enigma.setIndicators(new String(Plaintext));
		}
		if(CL.hasOption("decode")) {
			enigma.setIndicators(new String(Ciphertext));
		}
		String[] LeftOverArgs = CL.getArgs();
		if(LeftOverArgs.length == 0) {
			HelpFormatter Help = new HelpFormatter();
			Help.printHelp("enigma [options] message", CommandLineOptions);
			System.exit(1);
		}
		for( String LeftOverArg : LeftOverArgs) {
			Plaintext = LeftOverArg.toCharArray();
			Ciphertext = new char[Plaintext.length];
			for(int i = 0; i < Plaintext.length; i++) {
				Ciphertext[i] = enigma.Encipher(Plaintext[i]);
			}
			System.out.println(new String(Ciphertext));
		}
	}
	
	
	/**
	 * Big ugly thing to build command line argument descriptions for
	 * command line parser. Knowledge of the Enigma machine is assumed.
	 */
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
				.withDescription("3 numbers seperated by :")
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
		OptionGroup ed = new OptionGroup();
		ed.addOption(decode);
		ed.addOption(encode);
		ed.setRequired(true);
		
		CommandLineOptions = new Options();
		CommandLineOptions.addOptionGroup(ed);
		CommandLineOptions.addOption(messageSetting);
		CommandLineOptions.addOption(grundstellung);
		CommandLineOptions.addOption(steckerverbindungen);
		CommandLineOptions.addOption(ringstellung);
		CommandLineOptions.addOption(walzenlage);
	}
}
