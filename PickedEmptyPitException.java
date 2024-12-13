package mancala;

public class PickedEmptyPitException extends RuntimeException {
	
	public PickedEmptyPitException() {
		super("You can't pick an empty pit.");
	}
	
	public PickedEmptyPitException(String m) {
		super(m);
	}

}
