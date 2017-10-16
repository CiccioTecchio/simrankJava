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
	
	@Override
	public String toString() {
		String str="";
		
		Set entrySet=matrix.entrySet();
		Iterator it= entrySet.iterator();
		int i=0;
		while(it.hasNext()) {
			if(i==5) {i=0;
					  str+="\n";}
			Map.Entry me = (Map.Entry)it.next(); 
			str+=me.getValue()+"\t";
			i++;
		}
		return str;
		
		
		
	}
	
	
}
