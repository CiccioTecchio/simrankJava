package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import calculator.SimCalculator;

public class Test {
	
	private static SimCalculator calculator;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			double c=Double.parseDouble(args[1]);
			if(c<=0.0 || c>=1.00) throw new RuntimeException();
			FileReader file= new FileReader(args[0]);
			BufferedReader b= new BufferedReader(file);
			String s=b.readLine();
			calculator=new SimCalculator();
			
			while(s!=null) {
				calculator.initStructures(s);
				s=b.readLine();
			}
			calculator.initScore(c);
			//acquisizione info terminata
			printInstance();
			
		}catch(IOException e) {e.getMessage();
							  e.printStackTrace();}
		catch(RuntimeException e) {e.getMessage();
		  					      e.printStackTrace();}
		
	}
	
	public static void printInstance() {
		System.out.println("----------NOMI----------\n");
		System.out.println(calculator.getNomi()+"\n");
		System.out.println("----------NOMI----------\n");
		System.out.println("----------MATRICE DI ADIACENZA----------\n");
		System.out.println(calculator.getMatrix());
		System.out.println("----------MATRICE DI ADIACENZA----------\n");
		System.out.println("----------SCORE----------\n");
		System.out.println(calculator.toStringScore()+"\n");
		System.out.println("----------SCORE----------\n");
		System.out.println("----------COEFF----------\n");
		System.out.println(calculator.toStringCoeff()+"\n");
		System.out.println("----------COEFF----------\n");
	}

}
