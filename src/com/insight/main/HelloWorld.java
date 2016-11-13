package com.insight.main;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
/*
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;*/
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.regex.Pattern;

import org.junit.Assert;
import org.junit.Test;

public class HelloWorld {
	public static void main(String args[]) throws FileNotFoundException, IOException {
		
		//read from file
		String batch_payment;
		String giver;
		String getter;
		Hashtable<String, LinkedList<String>> hashTable = new Hashtable<>();
		
		try(BufferedReader br = new BufferedReader(new FileReader("./paymo_input/batch_payment.txt"))) {
		    StringBuilder sb = new StringBuilder();
		    String line = br.readLine();
		    if (line.equals("time, id1, id2, amount, message")) {
		    		line = br.readLine();
		    		
		    }
		    while (line != null) {
			   /* 	if (!hasValidDate(line)) {
			    	    line = br.readLine();
			    	    continue;
			    	}*/
		    		line = line.substring(0, line.lastIndexOf(","));
		    		giver = getGiver(line);
		    		getter = getGetter(line);
		    		
		    		System.out.println(giver);
		    		
		    		if (!hashTable.containsKey(giver)) {
		    			hashTable.put(giver, new LinkedList<String>());
		    			hashTable.get(giver).add(getter);
		    		} else {
	    				hashTable.get(giver).add(getter);
		    		}	    	
		    		
		       // sb.append(line);
		        //sb.append(System.lineSeparator());
		        line = br.readLine();
		        
		    }
		    //batch_payment = sb.toString();
		}
		
		
		
		System.out.println(hashTable);
		//System.out.println(getGiver(batch_payment));
		//System.out.println(getGetter(batch_payment));
		
		//write to file
		/*
		Writer writer = null;		
		try {
		    writer = new BufferedWriter(new OutputStreamWriter(
		          new FileOutputStream("./paymo_output/output1.txt"), "utf-8"));
		    writer.write(batch_payment);
		} catch (IOException ex) {
		  // report
		} finally {
		   try {
			   writer.close();
		   } catch (Exception ex) {
			   //
		   }
		}
		*/
		/*
		//append
		try(FileWriter fw = new FileWriter("./paymo_output/output1.txt", true);
			    BufferedWriter bw = new BufferedWriter(fw);
			    PrintWriter out = new PrintWriter(bw))
			{
				out.println(batch_payment);
			
			    out.println(giver);
			    //more code
			   // out.println(getter);
			    //more code
			} catch (IOException e) {
			    //exception handling left as an exercise for the reader
		}
		*/
		
	}
	
	public static String getGiver (String input) {
		int indexComma1 = input.indexOf(",");
		int indexComma2 = input.indexOf(",", indexComma1 + 1);	
		//System.out.println(indexComma1);
		//System.out.println(indexComma2);
		//System.out.println(indexComma3);		
		String giver = input.substring(indexComma1 + 2, indexComma2);
		return giver;
	}
	
	public static String getGetter (String input) {
		int indexComma1 = input.indexOf(",");
		int indexComma2 = input.indexOf(",", indexComma1 + 1);
		int indexComma3 = input.indexOf(",", indexComma2 + 1);
				
		String getter = input.substring(indexComma2 + 2, indexComma3);
		return getter;
	}
	
	
	static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	static boolean isValidDate(String input) {
	     try {
	          format.parse(input);
	          return true;
	     }
	     catch(ParseException e){
	          return false;
	     }
	}
	
	static boolean hasValidDate(String input) {
		String regex = "^((19|20)\\d\\d)-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])$";
		if (Pattern.matches(regex, input)) {
			return true;
		}
		return false;
	}
	
	@Test
	public void testRex() {
		String line = "2016-11-32 09:49:29, 36746, 8634, 15.77, 🖖 ";
		if (hasValidDate(line)) {
    	    		System.out.println("True");
    		}
			
		//String regex = "^((19|20)\\d\\d)-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])$";

		//Assert.assertTrue("Date: matched.", Pattern.matches(regex, "2011-1-1"));
		//Assert.assertFalse("Date (month): not matched.", Pattern.matches(regex, "2011-13-1"));
	}
	
}
