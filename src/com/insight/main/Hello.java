package com.insight.main;

import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

public class Hello {
	static Hashtable<String, LinkedList<String>> hashTable;
	public static void main (String args[]) throws IOException {
		hashTable = new Hashtable<>();
		Feature1_add();
		Feature1_find();
		
			
	}//main
	
	public static void Feature1_add() throws IOException {
		FileInputStream inputStream = null;
		Scanner sc = null;
		
		String giver;
		String getter;
		
				
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
		        
		        //System.out.println(line);
		        //System.out.println(getGiver(line));
		        //System.out.println(getGetter(line));
		        
		        giver = getGiver(line);
	    			getter = getGetter(line);
	    			
	    			LinkedList<String> list = hashTable.get(giver);
	    			
		    		if (list == null) {
		    			hashTable.put(giver, new LinkedList<String>());	
		    			list = hashTable.get(giver);
		    			list.add(getter);
		    		} else {
		    			list.add(getter);
		    		} 	
		    		
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
	}
	
	public static void Feature1_find() throws IOException{
		FileInputStream inputStream = null;
		Scanner sc = null;
		
		String giver;
		String getter;
		
		//write to file
			/*	
		Writer writer = null;		
		try {
		    writer = new BufferedWriter(new OutputStreamWriter(
		          new FileOutputStream("./paymo_output/output1.txt"), "utf-8"));
		    //writer.write(batch_payment);
		    LinkedList<String> list = hashTable.get(giver);
		    if (list != null && list.contains(getter)) {
	    			//out.println("trusted");
	    			//System.out.println("trusted");
	    			writer.write("trusted");
    			} else {
	    			//out.println("unverified");
	    			//System.out.println("unverified");
    				writer.write("unverified");
    			}
		    
		} catch (IOException ex) {
		  // report
		} finally {
		   try {
			   writer.close();
		   } catch (Exception ex) {
			   //
		   }
		}*/
			///////////////////////////////
		Writer writer = null;	
		try {
				
			writer = new BufferedWriter(new OutputStreamWriter(
			          new FileOutputStream("./paymo_output/output1.txt"), "utf-8"));
			
		    inputStream = new FileInputStream("./paymo_input/stream_payment.txt");
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
		        giver = getGiver(line);
	    			getter = getGetter(line);
	    			//append
	    			LinkedList<String> list = hashTable.get(giver);
	    		    if (list != null && list.contains(getter)) {
	    	    			//out.println("trusted");
	    	    			//System.out.println("trusted");
	    	    			writer.write("trusted/n");
	        			} else {
	    	    			//out.println("unverified");
	    	    			//System.out.println("unverified");
	        				writer.write("unverified\n");
	        			}
		    }//while
		    
		    // note that Scanner suppresses exceptions
		 /*   if (sc.ioException() != null) {
		        throw sc.ioException();
		    }*/
		    
			} //try
			finally {
				writer.close();
			    if (inputStream != null) {
			        inputStream.close();
			    }
			    if (sc != null) {
			        sc.close();
			    }
			    
			}
	}
	
	public static String getGiver (String input) {
		try {
			int indexComma1 = input.indexOf(",");
			int indexComma2 = input.indexOf(",", indexComma1 + 1);	
			//System.out.println(indexComma1);
			//System.out.println(indexComma2);
			//System.out.println(indexComma3);		
			String giver = input.substring(indexComma1 + 2, indexComma2);
			return giver;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "";

	}
	
	public static String getGetter (String input) {
		try {
			int indexComma1 = input.indexOf(",");
			int indexComma2 = input.indexOf(",", indexComma1 + 1);
			int indexComma3 = input.indexOf(",", indexComma2 + 1);
					
			String getter = input.substring(indexComma2 + 2, indexComma3);
			return getter;
		} catch (Exception e){
			
		}
		return "";

	}
	public static boolean hasValidDate(String input) {
		String regex = "^((19|20)\\d\\d)-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])$";
		if (Pattern.matches(regex, input)) {
			return true;
		}
		return false;
	}
	
}
