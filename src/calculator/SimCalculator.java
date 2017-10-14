package calculator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
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
		
		initAdjacenzyMatrix(s);
		
		initScore();
	}  
	
	protected void initScore() {
		// ricorda g2 ha esattamente n^2 coppie quindi conta le ripetizioni
		int size=nomi.size();
		int i,j;
		String strI,strJ;
		
		for(i=0;i<size;i++) {
			for(j=0;j<size;j++) {
				strI=nomi.get(i);
				strJ=nomi.get(j);
			if(!score.containsKey(strI+""+strJ)) {
				score.put(strI+""+strJ, assignValue(strI, strJ));
			}	
			}
		}
		
		
	}
	protected double assignValue(String a,String b) {
		
		if(a.equals(b)) return 1; else return 0;
	}
	protected void initAdjacenzyMatrix(String s) {
		ArrayList <String>app= singleNode(s); 
		int i=nomi.indexOf(app.get(0));
		int j=nomi.indexOf(app.get(1));
		matrix.insert(i, j, (byte)1);
	}
	protected ArrayList<String> singleNode(String s) {
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
	protected void initNomi(String s) {
		ArrayList<String> nodes= new ArrayList<String>();
		nodes=singleNode(s);
		
		if(!nomi.contains(nodes.get(0))) {
			nomi.add(nodes.get(0));
		}
		
		if(!nomi.contains(nodes.get(1))) {
			nomi.add(nodes.get(1));
		}
	}

	public HashMap<String, Double> getScore() {
		return score;
	}

	public void setScore(HashMap<String, Double> score) {
		this.score = score;
	}

	public AdjacencyMatrix getMatrix() {
		return matrix;
	}

	public void setMatrix(AdjacencyMatrix matrix) {
		this.matrix = matrix;
	}

	public ArrayList<String> getNomi() {
		return nomi;
	}

	public void setNomi(ArrayList<String> nomi) {
		this.nomi = nomi;
	}

	public String toStringScore() {
		Set entrySet=score.entrySet();
		Iterator it= entrySet.iterator();
		int i=0;
		String str="";
		while(it.hasNext()) {
			if(i==5) {i=0;
					  str+="\n";}
			Map.Entry me = (Map.Entry)it.next();
			str+=me.getKey()+": "+me.getValue()+"\t";
			i++;
		}
		return str;
	}


}
