package exceptions;

public class InvalidPlayerException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5546494573614479178L;

	public InvalidPlayerException() {
		// TODO Auto-generated constructor stub
	}

	public InvalidPlayerException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public InvalidPlayerException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public InvalidPlayerException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public InvalidPlayerException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
