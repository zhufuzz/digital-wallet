package com.insight.main;

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
import java.io.Writer;

public class HelloWorld {
	public static void main(String args[]) throws FileNotFoundException, IOException {
		
		//read from file
		String batch_payment;
		try(BufferedReader br = new BufferedReader(new FileReader("./paymo_input/batch_payment.txt"))) {
		    StringBuilder sb = new StringBuilder();
		    String line = br.readLine();

		    while (line != null) {
		        sb.append(line);
		        sb.append(System.lineSeparator());
		        line = br.readLine();
		    }
		    batch_payment = sb.toString();
		}
		System.out.println(batch_payment);
		
		//write to file
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
		
		//append
		try(FileWriter fw = new FileWriter("./paymo_output/output1.txt", true);
			    BufferedWriter bw = new BufferedWriter(fw);
			    PrintWriter out = new PrintWriter(bw))
			{
				out.println(batch_payment);
			
			    out.println("the text");
			    //more code
			    out.println("more text");
			    //more code
			} catch (IOException e) {
			    //exception handling left as an exercise for the reader
		}
		
	}
}
