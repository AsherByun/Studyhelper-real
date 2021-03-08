package com.studyhelper.domain.board.exception;

public class BoardNotSatisfiyInsert extends BoardException{
	private static final long serialVersionUID = 1L;
	private final static String MESSAGE = "게시판에 빈칸이 있습니다";
	
	public BoardNotSatisfiyInsert(String message) {
		super(MESSAGE);
	}

}
