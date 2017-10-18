package calculator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


import struct.AdjacencyMatrix;
import struct.Score;
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
		ScoreTable app;
		String[] pair =new String [2];
		ArrayList<Integer>indicesA,indicesB;
		double simScore=0;
		Score s;
		double k;
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
				/*if(coeff.get(key)==Double.POSITIVE_INFINITY){coeff.replace(key, 1.000);
															 score.put(key,0.000);}
				*/
				
				if(a.equals(b)) {
					s=new Score(1.0,true);
					score.put(key,s);
				}
				
				else {
					indicesA=indexIn(a);
					indicesB=indexIn(b);
					if(indicesA.size() == 0 || indicesB.size() == 0) {
						s=new Score(0.0,true);
						score.put(key,s);
					}
					
					else {
					inA=indicesA.size();
					inB=indicesB.size();
				
				for(i=0;i<inA;i++) {
					for(j=0;j<inB;j++) {
						s=app.get(nomi.get(indicesA.get(i))+","+nomi.get(indicesB.get(j)));
						//sumScore+=app.get(nomi.get(indicesA.get(i))+","+nomi.get(indicesB.get(j)));
					sumScore+=s.getScore();
					}
				}
				k=coeff.get(key).getScore();
				simScore=k*sumScore;
				s=new Score(simScore);
				score.put(key, s);
				score.put(b+","+a, s);
				simScore=0;
				sumScore=0;
				}//fine else
				}//else esterno
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
				if(!matrix.containsIndex(i, j)) matrix.addElement(i, j,0);
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
		String strI,strJ,key;
		Score s,put;
		for(i=0;i<size;i++) {
			for(j=0;j<size;j++) {
				strI=nomi.get(i);
				strJ=nomi.get(j);
				if(!score.containsKey(strI+","+strJ)) {
					 key=strI+","+strJ;
					 s=new Score(assignValue(strI,strJ));
				//score.put(key,s);
				inA=checkIngresso(strI);
				inB=checkIngresso(strJ);
				if(inA==0||inB==0) {
					s=new Score(0.0,true);
					score.put(key, s);
				}
				coef=c/(inA*inB);
				put=new Score(coef);
				coeff.put(key, put);
				score.put(key, s);
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
		matrix.addElement(i, j,1);
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
