package struct;

import java.util.Arrays;

public class AdjacencyMatrix {

	private int row;
	private int col;
	private byte matrix[][] ;
	private static final int N=5;
	private static final int M=5;
	public AdjacencyMatrix(int row,int col) {
		this.row=row;
		this.col=col;
		matrix= new byte [this.row][this.col];
		init(row,col);
	}
	
	protected void init() {
		int r,c;
		for(r=0;r<N;r++) {
			for(c=0;c<M;c++) {
				insert(r, c,(byte)0);
			}
		}		
	}
	
	protected void init(int row,int col) {
		int r,c;
		for(r=0;r<N;r++) {
			for(c=0;c<M;c++) {
				insert(r, c,(byte)0);
			}
		}		
	}

	public AdjacencyMatrix() {
		matrix= new byte [N][M];
		init();
	}



	public int getNumRow() {
		return row;
	}

	public void setNumRow(int row) {
		this.row = row;
	}

	public int getNumCol() {
		return col;
	}

	public void setNumCol(int col) {
		this.col = col;
	}

	public byte[][] getMatrix() {
		return matrix;
	}

	public void setMatrix(byte[][] matrix) {
		this.matrix = matrix;
	}
	
	public void insert(int i,int j,byte value){
		matrix[i][j]=value;
	}

	public int getElement(int r, int c) {
		return matrix[r][c];
	}
	
	public String toString() {
		int i,j;
		String str="";
		for(i=0;i<row;i++){
			for(j=0;j<col;j++){
				str=str+" "+matrix[i][j]+" ";
			}
			str=str+"\n";
		}
		
		return str;
	}
	
	
	
}
