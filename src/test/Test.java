package test;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import calculator.SimCalculator;
import struct.ScoreTable;

public class Test {
	
	private static SimCalculator calculator;
	private static ScoreTable score;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			float c=Float.parseFloat(args[1]);
			if(c<=0.0 || c>=1.00) throw new RuntimeException();
			FileReader file= new FileReader(args[0]);
			BufferedReader b= new BufferedReader(file);
			String s=b.readLine();
			calculator=new SimCalculator();
			
			while(s!=null) {
				calculator.initStructures(s);
				s=b.readLine();
			}
			calculator.initMatrix();
			calculator.initScore(c);
			//acquisizione info terminata
			//gli stessi risultati del paper di Windom,Jeh si trovano con 7 iterazioni
			int numIter=Integer.parseInt(args[2]);
			if(numIter<=0) throw new RuntimeException();
			int size=calculator.getNomi().size();
			printInstance(size);
			score=calculator.simScore(numIter);
			System.out.println("----------SCORE FINALI----------\n");
			System.out.println(calculator.getScore().toString(size)+"\n");
			System.out.println("----------SCORE FINALI----------\n");
		
		}catch(IOException e) {e.getMessage();
							  e.printStackTrace();}
		catch(RuntimeException e) {e.getMessage();
		  					      e.printStackTrace();}
		
	}
	
	public static void printInstance(int sizeCol) {
		System.out.println("----------NOMI----------\n");
		System.out.println(calculator.getNomi()+"\n");
		System.out.println("----------NOMI----------\n");
		System.out.println("----------MATRICE DI ADIACENZA----------\n");
		System.out.println(calculator.getMatrix().toString(sizeCol)+"\n");
		System.out.println("----------MATRICE DI ADIACENZA----------\n");
		System.out.println("----------SCORE INIZIALI----------\n");
		System.out.println(calculator.getScore().toString(sizeCol)+"\n");
		System.out.println("----------SCORE INIZIALI----------\n");
		System.out.println("----------COEFF----------\n");
		System.out.println(calculator.getCoeff().toString(sizeCol)+"\n");
		System.out.println("----------COEFF----------\n");
		
	}

}
