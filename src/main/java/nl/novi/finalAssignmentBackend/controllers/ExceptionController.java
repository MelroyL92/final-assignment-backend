package nl.novi.finalAssignmentBackend.controllers;


@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(value = RecordNotFoundException.class)
    public ResponseEntity<Object> exception(RecordNotFoundException exception) {

        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);

    }

    /*TODO maak exceptionHandlers voor de 2 nieuwe exceptions*/

}