package factory;

public class FinderNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public FinderNotFoundException(String message) {
		super(message);
	}
}
