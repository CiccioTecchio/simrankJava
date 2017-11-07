package calculator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.jgrapht.DirectedGraph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;

import struct.ScoreTable;

public class SimCalculator {

	private DirectedGraph<String,DefaultEdge> graph;
	private ArrayList<String> nomi;
	private ScoreTable coeff;
	private ScoreTable score;
	private ScoreTable vecchia;
	private int size;
	
	public SimCalculator() {
		graph= new DefaultDirectedGraph<>(DefaultEdge.class);
		nomi=new ArrayList<>();
		coeff=new ScoreTable();
		score=new ScoreTable();
		vecchia=new ScoreTable();
		size=0;
	}
	
	
	public void initNomi(String s) {
		String [] nodes= new String[2];
		nodes=s.split(",");
		if(!nomi.contains(nodes[0])) {
			graph.addVertex(nodes[0]);
			nomi.add(nodes[0]);
		}
		if(!nomi.contains(nodes[1])) {
			graph.addVertex(nodes[1]);
			nomi.add(nodes[1]);
		}
		size=nomi.size();
		graph.addEdge(nodes[0],nodes[1]);	
	}
	
	
	public void initScore(float c) {
		int i,j=0;
		float coef=0.0f;
		String key="";
		Set<DefaultEdge> indexA;
		Set<DefaultEdge> indexB;
		String strA,strB;
		int cardA=0,cardB=0;
		for(i=0;i<size;i++) {
			for(j=0;j<size;j++) {
						key=nomi.get(i)+","+nomi.get(j);
					score.put(key, assignValue(nomi.get(i),nomi.get(j)));
					indexA=graph.incomingEdgesOf(nomi.get(i));
					indexB=graph.incomingEdgesOf(nomi.get(j));
					coef=c/(indexA.size()*indexB.size());
					coeff.put(key, coef);
			}
		}
	}
	
	
	public ScoreTable simScore(int numIter) {
		int i,j,it=0;
		float simScore=0;
		float sumScore=0;
		String a,b,key,newkey;
		Set<DefaultEdge> indexA,indexB;
		String[] pair =new String [2];
		HashMap<String,Float> app;
		int inA,inB;
		while(it<numIter) {
		app=score;
			Set entrySet=app.entrySet();
			Iterator iterator= entrySet.iterator();
			while(iterator.hasNext()) {
				Map.Entry me = (Map.Entry)iterator.next();
				key=""+me.getKey();
				pair=key.split(",");
				a=pair[0];
				b=pair[1];
				
				if(a.equals(b)) score.put(key, 1.0f);
				
				else {
					indexA=graph.incomingEdgesOf(a);
					indexB=graph.incomingEdgesOf(b);
					 inA=indexA.size();
					 inB=indexB.size();
					if(inA==0 || inB==0) {score.put(key, 0.0f);}
					else {
						int xA=0,xB=0;
						String v1,v2="";
						for(DefaultEdge itA:indexA) {
							v1   = graph.getEdgeSource(itA);
							for(DefaultEdge itB:indexB) {
								v2=graph.getEdgeSource(itB);
								newkey=v1+","+v2;
							    sumScore+=app.get(newkey);
							}
							
						}
						simScore=coeff.get(key)*sumScore;
						score.put(key, simScore);	
						}
					simScore=0;
					sumScore=0;
					
				}//fine top else

			}
			it++;
		}
		
		
		
		return score;
	}
	
	
	
	protected float assignValue(String a,String b) {
		float toReturn=0;
		if(a.equals(b)) toReturn=1;
		return toReturn;
	}


	public DirectedGraph<String, DefaultEdge> getGraph() {
		return graph;
	}


	public void setGraph(DirectedGraph<String, DefaultEdge> graph) {
		this.graph = graph;
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


	public ScoreTable getScore() {
		return score;
	}


	public void setScore(ScoreTable score) {
		this.score = score;
	}


	public int getSize() {
		return size;
	}


	public void setSize(int size) {
		this.size = size;
	}
	
	
	
	
}
