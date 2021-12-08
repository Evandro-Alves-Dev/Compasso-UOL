package com.uol.compasso.API.exceptions.handle;

import com.uol.compasso.API.exceptions.DescriptionException;
import com.uol.compasso.API.exceptions.NameException;
import com.uol.compasso.API.exceptions.PriceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class DefaultExceptionHandle {

    @ExceptionHandler(NameException.class)
    public ResponseEntity<ResponseException> handle(NameException nameException){
        ResponseException responseException = new ResponseException();
        responseException.setStatus_code(HttpStatus.BAD_REQUEST.value());
        responseException.setMessage(nameException.getMessage());
        return ResponseEntity.status(responseException.getStatus_code()).body(responseException);
    }

    @ExceptionHandler(DescriptionException.class)
    public ResponseEntity<ResponseException> handle(DescriptionException descriptionException){
        ResponseException responseException = new ResponseException();
        responseException.setStatus_code(HttpStatus.BAD_REQUEST.value());
        responseException.setMessage(descriptionException.getMessage());
        return ResponseEntity.status(responseException.getStatus_code()).body(responseException);
    }

    @ExceptionHandler(PriceException.class)
    public ResponseEntity<ResponseException> handle(PriceException priceException){
        ResponseException responseException = new ResponseException();
        responseException.setStatus_code(HttpStatus.BAD_REQUEST.value());
        responseException.setMessage(priceException.getMessage());
        return ResponseEntity.status(responseException.getStatus_code()).body(responseException);
    }
}
