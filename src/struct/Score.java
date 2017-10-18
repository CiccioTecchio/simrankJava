package struct;

public class Score {

	private double score;
	private boolean noCompute;
	
	public Score(double score, boolean noCompute) {
		this.score = score;
		this.noCompute = noCompute;
	}
	
	public Score(double score) {
		this.score = score;
		this.noCompute = false;
	}
	
	public Score() {
		this.score = 0.0;
		this.noCompute = false;
	}

	
	
	@Override
	public String toString() {
		return ""+score;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public boolean isNoCompute() {
		return noCompute;
	}

	public void setNoCompute(boolean noCompute) {
		this.noCompute = noCompute;
	}
	
	
	
	
	
}
