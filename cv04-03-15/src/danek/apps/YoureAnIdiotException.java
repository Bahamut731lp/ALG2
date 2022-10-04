package danek.apps;

public class YoureAnIdiotException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1786591633962656434L;

	public YoureAnIdiotException(String message) {
		System.err.printf("%s %s", message, getStackTrace());
	}
}
