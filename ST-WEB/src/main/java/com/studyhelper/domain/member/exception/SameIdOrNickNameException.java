package com.studyhelper.domain.member.exception;

public class SameIdOrNickNameException extends LoginException {
	private static final long serialVersionUID = 1L;

	public SameIdOrNickNameException(String message) {
		super(message);
	}

}
