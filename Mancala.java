package mancala;
/**
 * The Mancala class represents a mancala store that holds stones for a player in the game.
 */
public class Mancala {
    private int numStones; // The number of stones currently held in the store.

    /**
     * Constructs a Mancala object with an initial number of stones set to 0.
     */
    public Mancala() {
        numStones = 0;
    }

    /**
     * Adds a specified number of stones to the store.
     * @param stones the number of stones to add to the store.
     */
    public void addStones(int stones) {
        this.numStones += stones;
    }

    /**
     * Increments the number of stones in the store by 1. 
     * This method is similar to addStone().
     */
    public void increment() {
        numStones++;
    }

    /**
     * Retrieves the current number of stones in the store.
     * @return the number of stones in the store.
     */
    public int getNumStones() {
        return numStones;
    }

    /**
     * Sets the number of stones in the store to a specified value.
     * @param numStones the new number of stones to be set in the store.
     */
    public void setNumStones(int numStones) {
        this.numStones = numStones;
    }

    /**
     * @return a string 
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Mancala [numStones=");
        builder.append(numStones);
        builder.append("]");
        return builder.toString();
    }
}