package com.studyhelper.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.studyhelper.domain.board.exception.BoardException;
import com.studyhelper.domain.member.exception.LoginException;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(BoardException.class)
	public String handleBoardException(Exception exception, Model model) {
		model.addAttribute("exception", exception);

		return "/error/errorpage";
	}
	
	@ExceptionHandler(LoginException.class)
	public String handleSignupException(Exception exception, Model model) {
		model.addAttribute("exception", exception);

		return "/error/errorpage";
	}

	@ExceptionHandler(Exception.class)
	public String handleException(Exception exception, Model model) {
		model.addAttribute("exception", exception);

		return "/error/errorpage";
	}
}