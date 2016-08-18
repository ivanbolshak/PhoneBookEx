package PhoneBooks.exceptions;

public class WriteInFileException extends RuntimeException {
	
	private static final long serialVersionUID = 2L;

	public WriteInFileException(String message) {
		super(message);
	}

}
