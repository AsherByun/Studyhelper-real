package com.studyhelper.exception.login;

public class SameIdOrNickNameException extends LoginException {
	private static final long serialVersionUID = 1L;

	public SameIdOrNickNameException(String message) {
		super(message);
	}

}
