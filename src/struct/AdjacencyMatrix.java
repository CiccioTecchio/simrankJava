package struct;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class AdjacencyMatrix {

	private HashMap<String,Byte>matrix;
	
	public AdjacencyMatrix() {
		matrix=new HashMap<String,Byte>();
	}
	


	public void addElement (int r,int c, byte e) {
		String key=r+","+c;
		matrix.put(key, e);
		
	}
	
	public byte getElement(int r,int c) {
		String key=r+","+c;
		return matrix.get(key);
	}
	
	public boolean containsIndex(int r,int c) {
		String key=r+","+c;
		return matrix.containsKey(key);
	}
	
	
	public String toString(int sizeCol) {
		String str="";
		int i,j=0;
		byte value;
		for(i=0;i<sizeCol;i++) {
			if(j==sizeCol)str+="\n";
			for(j=0;j<sizeCol;j++) {
				value=this.getElement(i, j);
				str+="\t"+value+"\t";
			
			}
		}		
		return str;
	}
	
	
}
