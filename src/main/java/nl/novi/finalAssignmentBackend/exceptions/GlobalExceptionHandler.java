package nl.novi.finalAssignmentBackend.exceptions;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ResponseBody
    public String handleGenericException(Exception exception, HttpServletRequest request){
        return "We have to look into this error and we will fix it as soon as we can";
    }

    @ExceptionHandler(value = RecordNotFoundException.class)
    public ResponseEntity<Object> exception(RecordNotFoundException exception) {

        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(value = UsernameNotFoundException.class)
    public ResponseEntity<Object> exception(UsernameNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage() , HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public String handleEntityNotFoundException(EntityNotFoundException exception, HttpServletRequest request){
        return exception.getMessage().replace("nl.novi.finalAssignmentBackend.entities", "");
    }

    @ExceptionHandler(NoUserAssignedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public String handleUserNotAssignedException(NoUserAssignedException exception, HttpServletRequest request){
        return exception.getMessage();
    }

    @ExceptionHandler(NothingFoundWithinListException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public String handleNothingFoundWithinList(NothingFoundWithinListException exception, HttpServletRequest request){
        return  exception.getMessage();
    }

    @ExceptionHandler(UserMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public String handleUserMismatchException(UserMismatchException exception, HttpServletRequest request){
        return exception.getMessage();
    }

}
