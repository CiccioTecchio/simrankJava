package struct;

public class AdjacencyMatrix {

	private int row;
	private int col;
	private byte matrix[][] ;
	
	public AdjacencyMatrix(int row,int col) {
		this.row=row;
		this.col=col;
		matrix= new byte [this.row][this.col];
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
	
	
}
