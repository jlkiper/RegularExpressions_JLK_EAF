package LogFileProcessor;

import java.util.Scanner;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


//Commands:
//cd eclipse-workspace
//cd RegularExpression_JLK_EAF
//javac -d bin -classpath bin src/LogFileProcessor/LogFileProcessor.java
//java -classpath bin LogFileProcessor.LogFileProcessor

/**
 * LogFileProcessor.java
 * This program will .
 * @author Jaylon Kiper,Elizabeth Fultz
 * @version 1.0
 * Compiler Project 3
 * CS322 - Compiler Construction
 * Fall 2021
 */

public class LogFileProcessor {
	
	private static Scanner readFile;
	public static HashMap<String, Integer> ipAddresses = new HashMap<String, Integer>();
	public static HashMap<String, Integer> users = new HashMap<String, Integer>();
	private static BufferedReader br;
		 
	public static void main(String[] args) {
		
		String ipPhrase;
		String userPhrase;
		String file;
		int printArg;
		String text;
		Matcher matcher1;
		Matcher matcher2;
		String ip;
		String user;

		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		
		try {
			
			//Enter csv file name of what is being parsed.
			System.out.println("Input csv file: ");
			file = scan.nextLine();
			File textFile = new File(file);
			
			// Use FileReader to red CSV file
			FileReader fr = new FileReader(textFile);
			br = new BufferedReader(fr);
			String line = "";

			String[] tempArr;
			// User FileWriter to write content to text file
			FileWriter writer = new FileWriter("output.txt");
			// Use while loop to check when file contains data
			while ((line = br.readLine()) != null) {
				tempArr = line.split(",");
				// User for loop to iterate String Array and write data to text file
				for (String str : tempArr) {
					writer.write(str + " ");
				}//end for loop
				// Write each line of CSV file to multiple lines
				writer.write("\n");
			}//end while loop
			writer.close();
			
			BufferedReader csvfile = new BufferedReader(new FileReader("output.txt"));
			
			//Enter argument from switch statement
			System.out.print("Enter Print Argument: ");
			printArg = scan.nextInt();
			
			//Pattern to extract IP Addresses and Usernames
			ipPhrase = "\\d+\\.\\d+\\.\\d+\\.\\d+";
			userPhrase = "user\\s\\w*";
				
		    //Compiles Regex pattern
			Pattern ipPattern = Pattern.compile(ipPhrase);
			Pattern userPattern = Pattern.compile(userPhrase);
				
			//Scans the text file that was entered.
			readFile = new Scanner(csvfile);
				
			int count = 0;
				
			//Runs two while loops to go line by line and match
			//what's in the text and the corresponding Regex pattern.
			while(readFile.hasNextLine()) {
				count++;
				text = (readFile.nextLine() + " ");
				matcher1 = ipPattern.matcher(text);
				matcher2 = userPattern.matcher(text);
				
				while(matcher1.find()) {
					
					ip = matcher1.group();
					
					Integer num = ipAddresses.get(ip);
					
					if(num == null) {
						ipAddresses.put(ip, 1);
					}//end if statement
					else {
						ipAddresses.put(ip, num+1);
					}//end else statement
					
				}//end while loop
					
				while(matcher2.find()) {	
					user = matcher2.group();
						
					String name = user.substring(5);
						
					Integer number = users.get(name);
						
					if(number == null) {
							users.put(name, 1);
					}//end if statement
					else {
							users.put(name, count+1);
					}//end else statement
					
				}//end while loop
					
			}//end while loop
			
			//Chooses argument to be printed.
			switch(printArg) {
				case 0:
					arg0(count);
					break;
				case 1:
					arg1(count);
					break;
				case 2:
					arg2(count);
					break;
				default:
					System.out.println("NO");
					break;
			}//end switch statement
			
		}//end try clause
		catch(Exception e){
			
			System.out.println("ERROR!");
			e.printStackTrace();
			
		}//end catch clause
		
	}//end main
	
	public static void arg0(int lines) throws IOException { 
		
		System.out.println(lines + " lines in the log were parsed.");
		System.out.println("There are " + ipAddresses.size() + " unique IP addresses in the log.");
		System.out.println("There are " + users.size() + " unique users in the log.");
		
	}//end arg0
	
	public static void arg1(int lines) throws IOException{
		
		for(String i : ipAddresses.keySet()) {
			
			System.out.println(i + ": " + ipAddresses.get(i));
			
		}
		
		arg0(lines);
		
	}//end arg1
	
	public static void arg2(int lines) throws IOException{
		
		for(String i : users.keySet()) {
			
			System.out.println(i + ": " + users.get(i));
			
		}
		
		arg0(lines);
		
	}//end arg2

}//end class