package nl.novi.finalAssignmentBackend.exceptions;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ResponseBody
    public String handleGenericException(Exception exception, HttpServletRequest request){
        return "We have to look into this error and we will fix it as soon as we can";
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public String handleDataIntegrityViolation(DataIntegrityViolationException exception, HttpServletRequest request ){
        return exception.getRootCause().getMessage().toString();
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public List<String> handleConstraintViolationException(ConstraintViolationException exception, HttpServletRequest request){
        return exception.getConstraintViolations()
                .stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toList());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public List<String>handleMethodArgumentNotValidException(MethodArgumentNotValidException exception, HttpServletRequest request){
        return exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.toList());
    }

    private static String getUserFriendlyErrorMessage(String message, String defaultMessage){
        String[] parts = message.split(":" , 2);
        if (parts.length > 0){
            return parts[0];
        } else {
            return  defaultMessage; // Hier nog naar kijken, wat is het default bericht?
        }
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public String handleMessageNotReadable(Exception exception, HttpServletRequest request){
      return getUserFriendlyErrorMessage(exception.getMessage(), "Error processing Request");
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public String handleEntityNotFoundException(EntityNotFoundException exception, HttpServletRequest request){
        return exception.getMessage().replace("nl.novi.finalAssignmentBackend.entities", "");
    }

    @ExceptionHandler(IOException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public String handleIOException(IOException exception, HttpServletRequest request){
        return "Error handling the file, the file is not valid";
    }





}
