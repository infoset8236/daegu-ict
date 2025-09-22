package kr.co.sj.framework.exception;

public class AuthException extends Exception {

	private static final long serialVersionUID = 1L;

	public AuthException() {
		super("권한이 없습니다.");
	}
	
	public AuthException(String message) {
		super(message);
	}

}
