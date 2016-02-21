package in.sg.rpc.common.exception;

public class UserExistsException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public UserExistsException() {
	}
	public UserExistsException(String msg) {
		super(msg);
	}

}
