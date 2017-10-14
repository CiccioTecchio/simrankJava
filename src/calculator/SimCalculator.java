package calculator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import struct.AdjacencyMatrix;

public class SimCalculator {

	private HashMap<String, Double> score;
	private AdjacencyMatrix matrix;
	private ArrayList<String> nomi;
	
	
	public SimCalculator() {
		this.score= new HashMap<String,Double>();
		this.nomi= new ArrayList<String>();
		this.matrix=new AdjacencyMatrix();
	}
	
	public void initStructures(String s) {
		initNomi(s);
		
		int n=nomi.size();//la matrice è quadrata;
		
		matrix.setNumRow(n);
		matrix.setNumCol(n);
		//ArrayList<String> k= new ArrayList<>();
		//k.addAll(nomi);
	}  
	
	
	
	private ArrayList<String> singleNode(String s) {
		ArrayList<String> nodes= new ArrayList<String>();
		char c=s.charAt(0);
		
		int i=0;
		while(c!=',') {
			c=s.charAt(i);
			i++;
		}
		String node1=s.substring(0,i-1);
		String node2=s.substring(i,s.length());
		nodes.add(node1);
		nodes.add(node2);
		return nodes;
	}
	
	private void initNomi(String s) {
		ArrayList<String> nodes= new ArrayList<String>();
		nodes=singleNode(s);
		
		if(!nomi.contains(nodes.get(0))) {
			nomi.add(nodes.get(0));
		}
		
		if(!nomi.contains(nodes.get(1))) {
			nomi.add(nodes.get(1));
		}
	}
}
