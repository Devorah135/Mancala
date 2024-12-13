package mancala;

import java.util.Arrays;

public class Player {
	
	private String name;
	Pit[] pits = new Pit[6];
	private Mancala store;
	
	public Player(String name) {
		this.name = name;
		 pits[0] = new Pit();
		 pits[1] = new Pit();
		 pits[2] = new Pit();
		 pits[3] = new Pit();
		 pits[4] = new Pit();
		 pits[5] = new Pit();
		 store = new Mancala();
	}

	public Player() {
		this.name = null;
		 pits[0] = new Pit();
		 pits[1] = new Pit();
		 pits[2] = new Pit();
		 pits[3] = new Pit();
		 pits[4] = new Pit();
		 pits[5] = new Pit();
		 store = new Mancala();
	}
	
	public boolean pitsAreEmpty() {
		for (int i = 0; i < pits.length; i++) {
			if(pits[i].getNumStones() > 0)
				return false;
		}
		return true;
	}
	
	public Pit[] getPits() {
		return pits;
	}
	
	public Pit getPit1() {
		return pits[0];
	}

	public void setPit1(Pit pit1) {
		this.pits[0] = pit1;
	}

	public Pit getPit2() {
		return pits[1];
	}

	public void setPit2(Pit pit2) {
		this.pits[1] = pit2;
	}

	public Pit getPit3() {
		return pits[2];
	}

	public void setPit3(Pit pit3) {
		this.pits[2] = pit3;
	}

	public Pit getPit4() {
		return pits[3];
	}

	public void setPit4(Pit pit4) {
		this.pits[3] = pit4;
	}

	public Pit getPit5() {
		return pits[4];
	}

	public void setPit5(Pit pit5) {
		this.pits[4] = pit5;
	}

	public Pit getPit6() {
		return pits[5];
	}

	public void setPit6(Pit pit6) {
		this.pits[5] = pit6;
	}

	public Mancala getStore() {
		return store;
	}

	public void setStore(Mancala store) {
		this.store = store;
	}
	
	public void incrementStore() {
		this.store.increment();
	}
	
	public void addToStore(int n) {
		this.store.addStones(n);
	}
	
	public void addAllStonesToStore() {
		for (int i = 0; i < pits.length; i++) {
			store.addStones(pits[i].getNumStones());
			pits[i].emptyPit();
		}
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Player [name=");
		builder.append(name);
		builder.append(", store=");
		builder.append(store);
		builder.append("]");
		return builder.toString();
	}

}
