package nl.novi.finalAssignmentBackend.exceptions;


import java.io.Serial;

public class NoUserAssignedException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    public NoUserAssignedException (String type) {
        super("please assign a user to the " + type );
    }
}


