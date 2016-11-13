package com.insight.main;

import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

public class Hello {

	public static void main (String args[]) throws IOException {
		FileInputStream inputStream = null;
		Scanner sc = null;
		
		String giver;
		String getter;
		Hashtable<String, LinkedList<String>> hashTable = new Hashtable<>();
				
		try {
		    inputStream = new FileInputStream("./paymo_input/batch_payment.txt");
		    sc = new Scanner(inputStream, "UTF-8");
		    while (sc.hasNextLine()) {
		        String line = sc.nextLine();
		        if (!line.contains(",") || line.length() < 10) {
		        		continue;
		        }
		        String date = line.substring(0, line.indexOf(","));
		        
		        if (hasValidDate(line)) {
		        		continue;
		        }
		        
		        System.out.println(line);
		        //System.out.println(getGiver(line));
		        //System.out.println(getGetter(line));
		        
		        giver = getGiver(line);
	    			getter = getGetter(line);
	    		
		    		/*if (!hashTable.containsKey(giver)) {
		    			hashTable.put(giver, new LinkedList<String>());
		    			hashTable.get(giver).add(getter);
		    		} else {
	    				hashTable.get(giver).add(getter);
		    		}	*/    	
		    }
		    // note that Scanner suppresses exceptions
		    if (sc.ioException() != null) {
		        throw sc.ioException();
		    }
			} finally {
			    if (inputStream != null) {
			        inputStream.close();
			    }
			    if (sc != null) {
			        sc.close();
			    }
			}
		//System.out.println(hashTable.get("33884"));

		
			
		}//main
	
	
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
	public static boolean hasValidDate(String input) {
		String regex = "^((19|20)\\d\\d)-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])$";
		if (Pattern.matches(regex, input)) {
			return true;
		}
		return false;
	}
	
}
