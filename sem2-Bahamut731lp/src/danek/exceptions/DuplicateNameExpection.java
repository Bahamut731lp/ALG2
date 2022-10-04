package danek.exceptions;

/**
 * Třída reprezentující výjimku při vytváření entity s duplicitním identifikátorem
 * 
 * @author Kevin Daněk
 *
 */
public class DuplicateNameExpection extends Exception {

	private static final long serialVersionUID = -3416023346101189486L;

	public DuplicateNameExpection() {
		// TODO Auto-generated constructor stub
	}

	public DuplicateNameExpection(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public DuplicateNameExpection(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public DuplicateNameExpection(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public DuplicateNameExpection(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
