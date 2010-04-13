package finders;

// is thrown whenever finder cannot solve function
public class InvalidFuncException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public InvalidFuncException(String message) {
		super(message);
	}
}
