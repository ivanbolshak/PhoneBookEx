package PhoneBooks.exceptions;

public class NoDataToReadException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public NoDataToReadException(String message) {
		super(message);
	}

}
