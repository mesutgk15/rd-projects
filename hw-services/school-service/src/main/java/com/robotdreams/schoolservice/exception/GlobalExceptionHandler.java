package com.robotdreams.schoolservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({StudentNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<AppResponse> handleException(StudentNotFoundException e) {
        AppResponse appResponse = prepareErrorResponse(HttpStatus.NOT_FOUND, e.getMessage());
        return new ResponseEntity<>(appResponse, HttpStatusCode.valueOf(appResponse.getStatus()));

    }

    @ExceptionHandler({CourseNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<AppResponse> handleException(CourseNotFoundException e) {
        AppResponse appResponse = prepareErrorResponse(HttpStatus.NOT_FOUND, e.getMessage());
        return new ResponseEntity<>(appResponse, HttpStatusCode.valueOf(appResponse.getStatus()));

    }

    @ExceptionHandler({InstructorNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<AppResponse> handleException(InstructorNotFoundException e) {
        AppResponse appResponse = prepareErrorResponse(HttpStatus.NOT_FOUND, e.getMessage());
        return new ResponseEntity<>(appResponse, HttpStatusCode.valueOf(appResponse.getStatus()));

    }

    private AppResponse prepareErrorResponse(HttpStatus httpStatus, String message) {
        AppResponse appResponse = new AppResponse();
        appResponse.setStatus(httpStatus.value());
        appResponse.setMessage(message);
        appResponse.setTimeStamp(System.currentTimeMillis());
        return appResponse;
    }
}
