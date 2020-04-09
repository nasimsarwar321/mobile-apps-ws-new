package com.appsdeveloperblogs.service.exception;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.appsdeveloperblogs.ui.model.response.ErrorMessage;
import com.appsdeveloperblogs.ui.model.response.ErrorMessages;

@ControllerAdvice
public class AppExceptionHandler {
	@ExceptionHandler(value = {UserServiceException.class})
	public ResponseEntity<Object> hendleUserServiceException(UserServiceException ex, WebRequest request) {
        ErrorMessage errorMessage = new ErrorMessage(new Date(), ex.getMessage());    
		
		return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
