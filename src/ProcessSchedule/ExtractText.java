package ProcessSchedule;

//Commands:
//cd eclipse-workspace
//cd RegularExpressions_JLK_EAF
//cd src
//javac ProcessSchedule/ExtractText.java
//java ProcessSchedule.ExtractText


import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

/**
 * ExtractText.java
 * This program will use the PDFBox Library from Apache Foundation to extract 
 * the data for the PDF that you can download at bellarmine.edu as a text file.
 * @author Jaylon Kiper,Elizabeth Fultz
 * @version 1.0
 * Compiler Project 3
 * CS322 - Compiler Construction
 * Fall 2021
 */

public class ExtractText {
	
	 public static void main(String args[]) throws IOException {

	      //Loading an existing document
	      File file1 = new File("2021FA_Class_Schedule_Daily.pdf");
	      PDDocument document1 = PDDocument.load(file1);
	      
	      //Loading an existing document
	      File file2 = new File("2021SU_Class_Schedule_Daily.pdf");
	      PDDocument document2 = PDDocument.load(file2);

	      //Instantiate PDFTextStripper class
	      PDFTextStripper pdfStripper = new PDFTextStripper();

	      //Retrieving text from PDF document
	      String text1 = pdfStripper.getText(document1);
	      System.out.println(text1);
	      
	      //Retrieving text from PDF document
	      String text2 = pdfStripper.getText(document2);
	      System.out.println(text2);

	      //Prints the written text files to src folder.
	      try (PrintWriter out = new PrintWriter("2021FA.txt")) {
	    	  
	    	    out.println(text1);
	    	    
	      }//end try clause
	      catch(Exception e){
	    	  
	    	  System.out.println("ERROR!");
				e.printStackTrace();
				
	      }//end catch clause
	      
	      try (PrintWriter out = new PrintWriter("2021SU.txt")) {
	    	  
	    	    out.println(text2);
	    	    
	    	}//end try clause
	      catch(Exception e){
	    	  
	    	  System.out.println("ERROR!");
				e.printStackTrace();
				
	      }//end catch clause

	}//end main

}//end class