
package mancala;

import java.util.*;

public class PlayMancala {

	public static void main(String[] args) {

		Scanner kb = new Scanner(System.in);
		
		Rules mancalaRules = new Rules();
        System.out.println(mancalaRules.toString());

		System.out.print("Enter player 1 name:\t");
			String name1 = kb.nextLine();
		System.out.print("Enter player 2 name:\t");
			String name2 = kb.nextLine();
		MancalaGame game = new MancalaGame(name1, name2);

		do {
			game = turn(game);
		} while (game.getWinner() == null);
	}

	/**
	 * @param game
  	This method manages each turn in the game.
	 */
	public static MancalaGame turn(MancalaGame game) {
		Scanner kb = new Scanner(System.in);

		setCurrentPlayer(game);
		game.displayBoard();
		int pitNum = getPitChoice(kb,game);
		int numInPit = getNumInPitAndEmptyPit(game, pitNum);
		
		boolean opponentSide = false;
		boolean lastMove = false;
		boolean empty = false;

		// loop for amt of moves (according to number of stones in pit)
		for (int moves = 0; moves < numInPit; moves++) {
			// game.displayBoard(); // for testing purposes
			pitNum--;
			lastMove = checkIfLastMove(numInPit, lastMove, moves);
			empty = checkIfEmpty(game, pitNum);

			// If there are stones left and we are on the current player's side
			if (pitNum >= 1 && !opponentSide) {
				game.getCurrentPlayer().getPits()[pitNum - 1].incrementStones();
				if (lastMove) {
					if (!empty) {
						numInPit = captureOnLanding(game, pitNum);
						moves = 0;
						emptyCurrentPit(game, pitNum);
					} else {
						captureOppIfEmpty(game, pitNum);
						//emptyCurrentPit(game, pitNum);
					}
					lastMove = false;
				}
			}

			// If we reach the current player's store (pitNum == 0)
			else if (pitNum == 0 && !opponentSide) {
				game.getCurrentPlayer().getStore().increment();
				if (lastMove) {
					endOfTurn(game);
					System.out.println(game.getCurrentPlayer().getName() + " gets another turn!");
					return game;
				}
			}
			// If pitNum is less than 0, meaning we’ve passed all pits on the current player's side
			else if (pitNum < 0 && !opponentSide) {
				pitNum = 6;
				opponentSide = true;
			}
			// If switchSide is true, meaning we are now on the opponent's side
			if (opponentSide) {
				if (pitNum >= 1) {
					game.getOtherPlayer().getPits()[pitNum - 1].incrementStones();
				}
				if (pitNum == 0) {
					opponentSide = false;
					pitNum = 6;
					game.getCurrentPlayer().getPits()[pitNum - 1].incrementStones();
				}
			}
		}
		endOfTurn(game);
		game.changeTurn();
		return game;
	}

	/**
	 * @param game
  	This method manages instructions for the end of a turn
	 */
	public static void endOfTurn(MancalaGame game) {
		savePlayers(game);
		checkForWinner(game);
	}

	/**
	 * @param game
	 * @param pitNum
  	This method empties the current pit
	 */
	public static void emptyCurrentPit(MancalaGame game, int pitNum) {
		game.getCurrentPlayer().getPits()[pitNum - 1].emptyPit();
	}

	/**
	 * @param numInPit
	 * @param lastMove
	 * @param moves
	 * @return
  	This method checks if this is the last move, returning a boolean
	 */
	public static boolean checkIfLastMove(int numInPit, boolean lastMove, int moves) {
		if (moves == (numInPit - 1)) 
			return true;
		return false;
	}

	/**
	 * @param game
	 * @param pitNum
	 * @param empty
	 * @return
  	This method checks if the pit is empty, returning a boolean
	 */
	public static boolean checkIfEmpty(MancalaGame game, int pitNum) {
		if (pitNum > 0 && game.getCurrentPlayer().getPits()[pitNum - 1].getNumStones() == 0)
			return true;
		else 
			return false;
	}

	/**
	 * @param game
	 * @param pitNum
	 * @param numInPit
	 * @param lastMove
	 * @return
  	This method captures the stones in a pit when player lands in a pit on their side.
	 */
	public static int captureOnLanding(MancalaGame game, int pitNum) {
		return (game.getCurrentPlayer().getPits()[pitNum - 1].getNumStones()+1);
	}

	/**
	 * @param game
	 * @param pitNum
	 * @param lastMove
	 * @param empty
  	This method captures from the opposite pit if landing on an empty pit on their side
	 */
	public static void captureOppIfEmpty(MancalaGame game, int pitNum) {
		Pit opp = game.getOppositePit(pitNum);
		game.getCurrentPlayer().addToStore(opp.getNumStones() + 1);
		opp.emptyPit();
		emptyCurrentPit(game, pitNum);
	}

	/**
	 * @param game
  	This method checks if the game should end and then checks for winner
	 */
	public static void checkForWinner(MancalaGame game) {
		if (game.getCurrentPlayer().pitsAreEmpty() || game.getOtherPlayer().pitsAreEmpty()) {
			resetBoardOnWin(game);
			game.displayBoard();
			setAndAnnounceWinner(game);
		}
	}

	/**
	 * @param game
	 * @param p1Stones
	 * @return
  	This method sets the winner to the winning player and announces the winner
	 */
	public static void setAndAnnounceWinner(MancalaGame game) {
		int p1Stones = game.getPlayer1().getStore().getNumStones();
		int p2Stones = game.getPlayer2().getStore().getNumStones();

		if (p1Stones > p2Stones) {
			game.setWinner(game.getPlayer1());
		} else if (p2Stones > p1Stones) {
			game.setWinner(game.getPlayer2());
		}
		if (p2Stones == p1Stones) {
			System.out.println("Wow! It's a tie!");
		} else {
			System.out.println("Mazel tov! " + game.getWinner().getName() + " won with "
					+ game.getWinner().getStore().getNumStones() + " stones. Good game! Play again!");
		}
	}

	/**
	 * @param game
  	This method resets the board when there is a winner and adds all stones to store
	 */
	public static void resetBoardOnWin(MancalaGame game) {
		game.displayBoard();
		// If the current player's pits are empty, add all stones from the other player's pits to their store
		if (game.getCurrentPlayer().pitsAreEmpty()) {
			System.out.println("Game over! " + game.getCurrentPlayer().getName() + "'s pits are empty.\n"
					+ "Placing all stones in " + game.getOtherPlayer().getName() + "'s mancala.");
			game.getOtherPlayer().addAllStonesToStore();
		}
		// If the other player's pits are empty, add all stones from the current player's pits to their store
		else if (game.getOtherPlayer().pitsAreEmpty()) {
			System.out.println("Game over! " + game.getOtherPlayer().getName() + "'s pits are empty.\n"
					+ "Placing all stones in " + game.getCurrentPlayer().getName() + "'s mancala.");
			game.getCurrentPlayer().addAllStonesToStore();
		}
	}

	/**
	 * @param game
  	This method saves players info at end of turn
	 */
	public static void savePlayers(MancalaGame game) {
		if (game.getTurn() == 1) {
			game.setPlayer1(game.getCurrentPlayer());
			game.setPlayer2(game.getOtherPlayer());
		} else if (game.getTurn() == 2) {
			game.setPlayer2(game.getCurrentPlayer());
			game.setPlayer1(game.getOtherPlayer());
		}
	}

	/**
	 * @param game
  	This method sets "currentPlayer" according to whose turn it is
	 */
	public static void setCurrentPlayer(MancalaGame game) {
		// set current player
		if (game.getTurn() == 1) {
			game.setCurrentPlayer(game.getPlayer1());
			game.setOtherPlayer(game.getPlayer2());
		} else if (game.getTurn() == 2) {
			game.setCurrentPlayer(game.getPlayer2());
			game.setOtherPlayer(game.getPlayer1());
		}
		System.out.println(game.getCurrentPlayer().getName() + "'s turn");
	}

	/**
	 * @param kb
	 * @param pitNum
	 * @return
  	This method gets the pit choice from user
	 */
	public static int getPitChoice(Scanner kb, MancalaGame game) {
		int pitNum = 0;
		boolean valid = true;
		do {
			System.out.print("Enter pit number you choose: ");
			try {
				pitNum = kb.nextInt();
				if (game.getCurrentPlayer().getPits()[pitNum - 1].getNumStones() == 0) {
					throw new PickedEmptyPitException();
				}
				if (pitNum < 1 || pitNum > 6) {
				    System.out.println("Pit number must be between 1 and 6.");
				    valid = false;
				}
				else {
					valid = true;
				}
			} catch (InputMismatchException e) {
				System.out.println("INVALID: Input must be a number (from 1-6).");
				kb.next();
				valid = false;
			} catch (PickedEmptyPitException e) {
				System.out.println(e.getMessage());
				valid = false;
			} catch (ArrayIndexOutOfBoundsException e) {
				System.out.println("Invalid choice. Pit number must be between 1 and 6.");
				valid = false;
			}

		} while (!valid);

		kb.nextLine();
		System.out.println();
		return pitNum;
	}

	/**
	 * @param game
	 * @param pitNum
	 * @param numInPit
	 * @return
  	This method gets how many stones are in a pit and empties the pit
	 */
	public static int getNumInPitAndEmptyPit(MancalaGame game, int pitNum) {
		int num = game.getCurrentPlayer().getPits()[pitNum - 1].getNumStones();
		emptyCurrentPit(game, pitNum);
		return num;
	}

}
