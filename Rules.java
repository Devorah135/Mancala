package mancala;

public class Rules {

	@Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Mancala Game Rules:\n")
          .append("1. Players take turns picking stones from one of their pits (1-6) and distribute them counterclockwise.\n")
          .append("2. Stones are placed one by one into each subsequent pit, including the player's store, but skipping the opponent's store.\n")
          .append("3. If the last stone lands in your store, you get an extra turn.\n")
          .append("4. If the last stone lands in an empty pit on your side, capture that stone + all stones from the opposite pit.\n"
          		+ "   You capture your own piece even when the opposite side is empty.\n")
          .append("5. The game ends when one player's pits are empty. Remaining stones in the other player's pits are added to their store.\n")
          .append("6. The player with the most stones in their store wins!\n");
        
        return sb.toString();
    }
}
