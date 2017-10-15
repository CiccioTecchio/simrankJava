package calculator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import struct.AdjacencyMatrix;

public class SimCalculator {

	private HashMap<String, Double> score;
	private HashMap<String, Double> coeff;
	private AdjacencyMatrix matrix;
	private ArrayList<String> nomi;
	
	public SimCalculator() {
		this.score= new HashMap<String,Double>();
		this.coeff= new HashMap<String,Double>();
		this.nomi= new ArrayList<String>();
		this.matrix=new AdjacencyMatrix();
	}
	
	//converge verso la quinta iterazione
	public HashMap<String, Double> simScore(int iteration){
		int i,j,inA,inB,it=0;
		String key,a,b;
		HashMap<String,Double> app;
		ArrayList<String> pair;
		ArrayList<Integer>indicesA,indicesB;
		double simScore=0;
		double sumScore=0;
		int indexA,indexB;
		//app mappa di appoggio che contiene gli score calcolati all'iterazione precedente
		
		
		while(it<iteration) {
			app=score;
			Set entrySet=app.entrySet();
			Iterator iterator= entrySet.iterator();
			while(iterator.hasNext()) {
				Map.Entry me = (Map.Entry)iterator.next();
				key=""+me.getKey();
				
				pair=singleNode(key);
				
				
				
				a=pair.get(0);
				b=pair.get(1);
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

	
	public ArrayList<Integer> indexIn(String a){
		ArrayList<Integer> index=new ArrayList<>();
		
		int col= nomi.indexOf(a);
		int size=nomi.size();
		int i,j;
		for( i=0;i<size;i++) {
			if(matrix.getElement(i, col)==1) index.add(i);
		}
		return index;
	}
	
	public void initStructures(String s) {
		initNomi(s);
		
		int n=nomi.size();
		
		matrix.setNumRow(n);
		matrix.setNumCol(n);
		
		initAdjacenzyMatrix(s);
		
		// in initScore è presente anche l'inizializzazione di coeff
		
	} 
	protected int checkIngresso(String a){
		int index=nomi.indexOf(a);
		int size=nomi.size();
		int i,num=0;
		for(i=0;i<size;i++) {
			if(matrix.getElement(i,index)==1) num++;
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

	public HashMap<String, Double> getCoeff() {
		return coeff;
	}
	public void setCoeff(HashMap coeff) {
		this.coeff = coeff;
	}
	

	
	public String toStringMap(HashMap<String,Double> map) {
		Set entrySet=map.entrySet();
		Iterator it= entrySet.iterator();
		int i=0;
		String str="";
		while(it.hasNext()) {
			if(i==5) {i=0;
					  str+="\n";}
			Map.Entry me = (Map.Entry)it.next();
			String troncato= String.format ("%.3f", me.getValue()); 
			str+="\t"+me.getKey()+": "+troncato+"\t";
			i++;
		}
		return str;
	}
	
}
