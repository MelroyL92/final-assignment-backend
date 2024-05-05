package nl.novi.finalAssignmentBackend.exceptions;

import java.io.Serial;

public class InsufficientStockException extends  RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public InsufficientStockException(String type, String name){
        super("not enough games in stock for"+ type + " with the name " + name);
    }
}
