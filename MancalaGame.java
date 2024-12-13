package mancala;

public class MancalaGame {
	
	private Player player1;
	private Player player2;
	private int turn;
	private Player winner;
	private Player currentPlayer;
	private Player otherPlayer;
	
	public MancalaGame(String name1, String name2) {
		this.player1 = new Player(name1);
		this.player2 = new Player(name2);
		this.turn = 1;
		this.currentPlayer = player1;
		this.otherPlayer = player2;
	}
	
	public MancalaGame() {
		this.player1 = new Player();
		this.player2 = new Player();
		this.turn = 1;
		this.currentPlayer = player1;
		this.otherPlayer = player2;

	}
	
	public void displayBoard() {
	System.out.println();
		//header
		System.out.println(player1.getName() + "\t\tPit 1\tPit 2\tPit 3\tPit 4\tPit 5\tPit 6\t\t" + player2.getName());
		
		System.out.print("  " + player1.getStore().getNumStones() + "\t\t");
			for (int i = 0; i < 6; i++)
				System.out.print("  " + player1.getPits()[i].getNumStones() + "\t");
			System.out.print("\t  " + player2.getStore().getNumStones());
			
			System.out.println();
			System.out.print("\t\t");
			for (int i = 5; i >= 0; i--)
				System.out.print("  " + player2.getPits()[i].getNumStones() + "\t");
			
			
		System.out.print("\n" + player1.getName() + "\t\tPit 6\tPit 5\tPit 4\tPit 3\tPit 2\tPit 1\t\t" + player2.getName());	
				
			
		System.out.println("\n");
	}
	
	public void changeTurn() {
		if (turn == 1) {
			turn = 2;
			currentPlayer = player2;
			this.otherPlayer = player1;

		}
		else if (turn == 2) {
			turn = 1;
			currentPlayer = player1;
			this.otherPlayer = player2;

		}
	}
	
	public Pit getOppositePit(int i) {
		switch (i) {
		case 1:
			return otherPlayer.getPit6();
		case 2:
			return otherPlayer.getPit5();
		case 3:
			return otherPlayer.getPit4();
		case 4:
			return otherPlayer.getPit3();
		case 5:
			return otherPlayer.getPit2();		
		case 6:
			return otherPlayer.getPit1();
		}
		
		return null;
		
	}

	public Player getPlayer1() {
		return player1;
	}

	public void setPlayer1(Player player1) {
		this.player1 = player1;
	}

	public Player getPlayer2() {
		return player2;
	}

	public void setPlayer2(Player player2) {
		this.player2 = player2;
	}

	public int getTurn() {
		return turn;
	}

	public void setTurn(int turn) {
		this.turn = turn;
	}

	public Player getWinner() {
		return winner;
	}

	public void setWinner(Player winner) {
		this.winner = winner;
	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	public void setCurrentPlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	public Player getOtherPlayer() {
		return otherPlayer;
	}

	public void setOtherPlayer(Player otherPlayer) {
		this.otherPlayer = otherPlayer;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MancalaGame [player1=");
		builder.append(player1);
		builder.append(", player2=");
		builder.append(player2);
		builder.append(", turn=");
		builder.append(turn);
		builder.append(", winner=");
		builder.append(winner);
		builder.append(", currentPlayer=");
		builder.append(currentPlayer);
		builder.append(", otherPlayer=");
		builder.append(otherPlayer);
		builder.append("]");
		return builder.toString();
	}
	
	
	
}
