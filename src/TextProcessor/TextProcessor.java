package TextProcessor;

//Commands:
//cd eclipse-workspace
//cd RegularExpression_JLK_EAF
//cd src
//javac TextProcessor/TextProcessor.java
//java TextProcessor.TextProcessor

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.*;

/**
 * Patterns used for this code:
 * 1.[^a-zA-Z]a[^a-zA-Z’]|[^a-zA-Z]an[^a-zA-Z’]|[^a-zA-Z]the[^a-zA-Z]
 * 
 * 2.\sMina\sHarker|\sMrs\.\sHarker
 * 
 * 3.\w+\sTransylvania
 * 
 * 4.\sto\s\w*
 * 
 * 5.\b(?!Godalming|Helsing)\b\w*ing[^a-zA-Z]
 */

/**
 * TextProcessor.java
 * This program will search the text of Dracula using Regex patterns. It will
 * accept two two inputs from the command line for the file name and the 
 * Regex patterns. It should search for the pattern passed and print each 
 * occurrence as well the count.
 * @author Jaylon Kiper,Elizabeth Fultz
 * @version 1.0
 * Compiler Project 3
 * CS322 - Compiler Construction
 * Fall 2021
 */

public class TextProcessor {

	private static Scanner readFile;

	public static void main(String[] args) {
		
		String phrase;
		String file;
		String text;
		int count = 0;
		Matcher matcher;
		
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		
		try {
			//Enter file name of what is being parsed.
			System.out.println("Input text file: ");
			file = scan.nextLine();
			File textFile = new File(file);
			
			//Enter the Regex pattern.
			System.out.println("Type regex pattern: ");
			phrase = scan.nextLine();
		
			//Compiles Regex pattern
			Pattern pattern = Pattern.compile(phrase,Pattern.CASE_INSENSITIVE);
			
			//Scans the text file that was entered.
			readFile = new Scanner(textFile);
			
			//Runs two while loops to go line by line and match
			//what's in the text and the corresponding Regex pattern.
			while(readFile.hasNextLine()) {
				text = (readFile.nextLine() + " ");
				matcher = pattern.matcher(text);
				
				while(matcher.find()) {
					
					count++;
					System.out.println(matcher.group());
					
				}//end inner while loop
				
				System.out.println(count);
				
			}//end outer while loop
		
		}//end try clause
		catch(Exception e){
			
			System.out.println("ERROR!");
			e.printStackTrace();
			
		}//end catch clause
		
	}//end main

}//end class
