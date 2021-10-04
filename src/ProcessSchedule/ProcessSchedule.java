package ProcessSchedule;

import java.util.Scanner;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

//Commands:
//cd eclipse-workspace
//cd RegularExpressions_JLK_EAF
//cd src
//javac ProcessSchedule/ProcessSchedule.java
//java ProcessSchedule.ProcessSchedule

/**
 * ProcessScheduler.java
 * This program will be used to search the newly created text file using Regex 
 * patterns and perform certian actions.
 * @author Jaylon Kiper,Elizabeth Fultz
 * @version 1.0
 * Compiler Project 3
 * CS322 - Compiler Construction
 * Fall 2021
 */

public class ProcessSchedule {

	private static Scanner readFile;
	
	public static void main(String[] args) throws IOException{
		
		try {
			
			courseInfo();
			openClosed();
			uniqueClasses();
			
		}//end try clause
		
		catch(Exception e){
			
			System.out.println("ERROR!");
			e.printStackTrace();
			
		}//end catch clause
				
	}//end main
	
	public static void courseInfo() throws IOException{
		
		String phrase;
		String text;
		Matcher matcher;
		
		try {
			//Loading an existing document
			BufferedReader file1 = new BufferedReader(new FileReader("2021FA.txt"));
			 
			//Pattern to extract course information and titles
			phrase = "\\w+\\-\\w+\\-\\w+\\D*";
			
	      	//Compiles Regex pattern
			Pattern pattern = Pattern.compile(phrase);
			
			//Scans the text file that was entered.
			readFile = new Scanner(file1);
			
			//PrintWriter Object creates 
			PrintWriter out = new PrintWriter("Matches.txt");
			
			//Runs while loop to go line by line and matches
			//what's in the text and the corresponding Regex pattern.
			while(readFile.hasNextLine()) {
				
				text = (readFile.nextLine() + " ");
				matcher = pattern.matcher(text);
				
				while(matcher.find()) {
					
					out.println(matcher.group());
					
				}//end inner while loop
			     
			}//end outer while loop

			out.close();
			
		}//end try clause
		catch(Exception e){
			
			System.out.println("ERROR!");
			e.printStackTrace();
			
		}//end catch clause
		
	}//end courseInfo
	
	public static void openClosed() throws IOException{
		
		String phrase;
		String text;
		Matcher matcher;
		
		try {
			//Loading an existing document
			File file1 = new File("2021FA.txt");
			 
			//Pattern to extract open/closed coures and seat numbers
			phrase = "\\b(?!Exp\\-Bldg\\-Level)\\b\\w+\\-\\w+\\-\\w+|Open\\s\\d*\\s\\d*|CLOSED\\s\\d*\\s\\-*\\d*";
			
	      	//Compiles Regex pattern
			Pattern pattern = Pattern.compile(phrase);
			
			//Scans the text file that was entered.
			readFile = new Scanner(file1);
			
			int count = 1;
			
			//Runs two while loops to go line by line and match
			//what's in the text and the corresponding Regex pattern.
			while(readFile.hasNextLine()) {
				text = (readFile.nextLine() + " ");
				matcher = pattern.matcher(text);
				
				while(matcher.find()) {
					
					count++;
					
					if(count%2 == 0) {
						System.out.println();
					}
			
					System.out.print(matcher.group() + " ");
					
				}//end inner while loop
				
			}//end outer while loop

			System.out.println();
			
		}//end try clause
		catch(Exception e){
			
			System.out.println("ERROR!");
			e.printStackTrace();
			
		}//end catch clause
		
			
	}//end openClosed
	
	public static void uniqueClasses() throws IOException{
		
		HashMap<String, Integer> classCodes = new HashMap<String, Integer>();
		
		String classId;
		String classCode = "";
		String text;
		Matcher matcher;
		
		try {
			//Loading an existing document
			BufferedReader file = new BufferedReader(new FileReader("2021FA.txt"));
			 
			//Pattern to extract the number of unique class
			classId = "\\w+\\-\\w+\\-01";
			
	      	//Compiles Regex pattern
			Pattern idPattern = Pattern.compile(classId);
			
			//Scans the text file that was entered.
			readFile = new Scanner(file);
			
			Integer count;
			
			//Runs while loop to go line by line and match
			//what's in the text and the corresponding Regex pattern.
			while(readFile.hasNextLine()) {
				text = (readFile.nextLine() + " ");
				matcher = idPattern.matcher(text);
				
				while(matcher.find()) {
					
					String matchedId = matcher.group();
					int end = matchedId.indexOf("-");
					
					if(end != -1) {
						classCode = matchedId.substring(0, end);
					}
					
					count = classCodes.get(classCode);
					
					if(count == null) {
						classCodes.put(classCode, 1);
					}
					else {
						classCodes.put(classCode, count+1);
					}//end else statement
				
				}//end inner while loop
						
			}//end outer while loop
			
			for(String i : classCodes.keySet()) {
				
				System.out.println("Class Code: " + i + ", Number of Classes: " + classCodes.get(i));
				
			}//end for loop

		}//end try clause
		catch(Exception e){
			
			System.out.println("ERROR!");
			e.printStackTrace();
			
		}//end catch clause
		
	}//end uniqueClasses

}//end class
