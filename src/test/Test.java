package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import calculator.SimCalculator;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			FileReader file= new FileReader(args[0]);
			BufferedReader b= new BufferedReader(file);
			String s=b.readLine();
			SimCalculator calculator=new SimCalculator();
			while(s!=null) {
				calculator.initStructures(s);
				s=b.readLine();
			}
			
		}catch(IOException e) {e.getMessage();
										 e.printStackTrace();}
	}

}
