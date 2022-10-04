package danek.exceptions;

/**
 * Třída reprezentující výjimku při překladu (neplatného) JSON
 * 
 * @author Kevin Daněk
 *
 */
public class NotJsonObjectException extends Exception {

	private static final long serialVersionUID = 1497996809315572115L;
	
	public NotJsonObjectException(String message) {
		super(message);
	}
	
	public NotJsonObjectException() {
		super();
	}
}
