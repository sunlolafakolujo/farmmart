package com.logicgate.userrole.exception;


import com.logicgate.erromessage.ValidateErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@RestControllerAdvice
@ResponseStatus
public class UserRoleRestControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ResponseStatus
    @ExceptionHandler(UserRoleNotFoundException.class)
    public ResponseEntity<ValidateErrorMessage> userRoleExceptionNotFound(UserRoleNotFoundException userRoleNotFoundException,
                                                                          WebRequest request){

        ValidateErrorMessage validateErrorMessage=new ValidateErrorMessage(HttpStatus.NOT_FOUND,
                userRoleNotFoundException.getMessage(),
                request.getDescription(false),
                new Date());

        return new ResponseEntity<>(validateErrorMessage,HttpStatus.BAD_REQUEST);
    }
}