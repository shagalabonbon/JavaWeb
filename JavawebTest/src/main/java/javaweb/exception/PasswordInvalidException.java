package javaweb.exception;

public class PasswordInvalidException extends CertException {
	
	public PasswordInvalidException() {
	}
	
	public PasswordInvalidException(String message) {
		super(message);
	}

	public PasswordInvalidException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public PasswordInvalidException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public PasswordInvalidException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
	

}
