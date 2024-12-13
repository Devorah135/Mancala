package mancala;

public class Pit {
	
	private int numStones;
	
	public Pit(int numStones) {
		this.numStones = numStones;
	}
	
	public Pit() {
		this.numStones = 4;
	}

	public int getNumStones() {
		return numStones;
	}

	public void setNumStones(int numStones) {
		this.numStones = numStones;
	}
	
	public void incrementStones() {
		this.numStones ++;
	}
	
	public void emptyPit() {
		this.numStones = 0;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Pit [numStones=");
		builder.append(numStones);
		builder.append("]");
		return builder.toString();
	}

}
