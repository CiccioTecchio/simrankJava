package calculator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


import struct.AdjacencyMatrix;
import struct.ScoreTable;

public class SimCalculator {
	
	private ScoreTable score;
	private ScoreTable coeff;
	private ArrayList<String> nomi;
	private AdjacencyMatrix matrix; 
	
	public SimCalculator() {
		
		this.score= new ScoreTable();
		this.coeff= new ScoreTable();
		this.nomi= new ArrayList<String>();
		this.matrix=new AdjacencyMatrix();
	}
	
	//converge verso la quinta iterazione
	public ScoreTable simScore(int iteration){
		int i,j,inA,inB,it=0;
		String key,a,b;
		HashMap<String,Double> app;
		String[] pair =new String [2];
		ArrayList<Integer>indicesA,indicesB;
		double simScore=0;
		double sumScore=0;
		//app mappa di appoggio che contiene gli score calcolati all'iterazione precedente
		
		
		while(it<iteration) {
			app=score;
			Set entrySet=app.entrySet();
			Iterator iterator= entrySet.iterator();
			while(iterator.hasNext()) {
				Map.Entry me = (Map.Entry)iterator.next();
				key=""+me.getKey();
				
				pair=singleNode(key);
				
				
				
				a=pair[0];
				b=pair[1];
				if(a.equals(b)) score.put(key, 1.0);
				
				else {
					indicesA=indexIn(a);
					indicesB=indexIn(b);
					inA=indicesA.size();
					inB=indicesB.size();
				
				for(i=0;i<inA;i++) {
					for(j=0;j<inB;j++) {
						sumScore+=app.get(nomi.get(indicesA.get(i))+","+nomi.get(indicesB.get(j)));
					
					}
				}
				simScore=coeff.get(key)*sumScore;
				score.put(key, simScore);
				
				simScore=0;
				sumScore=0;
				}//fine else
				
			}//fine iterator map
				
			it++;
		}
		
		return score;
	}

	
	protected ArrayList<Integer> indexIn(String a){
		ArrayList<Integer> index=new ArrayList<>();
		
		int col= nomi.indexOf(a);
		int size=nomi.size();
		int i,j;
		for( i=0;i<size;i++) {
			if(matrix.getElement(i, col)==1)index.add(i);
		}
		return index;
	}
	
	public void initStructures(String s) {
		initNomi(s);
		initAdjacenzyMatrix(s);
		
		// in initScore è presente anche l'inizializzazione di coeff
		
	} 
	
	public void initMatrix() {
		int i,j;
		int size=nomi.size();
		for(i=0;i<size;i++) {
			for(j=0;j<size;j++) {
				if(!matrix.containsIndex(i, j)) matrix.addElement(i, j,(byte) 0);
			}
		}
	}
	
	protected int checkIngresso(String a){
		int index=nomi.indexOf(a);
		int size=nomi.size();
		int i,num=0;
		for(i=0;i<size;i++) {
			if(matrix.getElement(i, index)==1)num++;
		}
		return num;
	}
	
	public void initScore(double c) {
		int size=nomi.size();
		int i,j,inA,inB;
		double coef;
		String strI,strJ;
		
		for(i=0;i<size;i++) {
			for(j=0;j<size;j++) {
				strI=nomi.get(i);
				strJ=nomi.get(j);
				if(!score.containsKey(strI+","+strJ)) {
				score.put(strI+","+strJ, assignValue(strI, strJ));
				inA=checkIngresso(strI);
				inB=checkIngresso(strJ);
				coef=c/(inA*inB);
				coeff.put(strI+","+strJ, coef);
				}	
			}
		}	
	}
	
	protected double assignValue(String a,String b) {	
		if(a.equals(b)) return 1; else return 0;
	}
	
	protected void initAdjacenzyMatrix(String s) {
		String[]app= singleNode(s); 
		int i=nomi.indexOf(app[0]);
		int j=nomi.indexOf(app[1]);
		System.out.println(i+","+j);
		matrix.addElement(i, j, (byte)1);
	}
	
	protected String[] singleNode(String s) {
		String[] nodes= new String [2];
		nodes=s.split(",");
		return nodes;
	}
	
	protected void initNomi(String s) {
		String[] nodes= new String [2];
		nodes=singleNode(s);
		
		if(!nomi.contains(nodes[0])) {
			nomi.add(nodes[0]);
		}
		
		if(!nomi.contains(nodes[1])) {
			nomi.add(nodes[1]);
		}
	}
	
	public ScoreTable getScore() {
		return score;
	}
	
	public void setScore(ScoreTable score) {
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
	
	public ScoreTable getCoeff() {
		return coeff;
	}
	
	public void setCoeff(ScoreTable coeff) {
		this.coeff = coeff;
	}
	
	
}
