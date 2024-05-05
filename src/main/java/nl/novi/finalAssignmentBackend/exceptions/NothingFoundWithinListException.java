package nl.novi.finalAssignmentBackend.exceptions;

import java.io.Serial;

public class NothingFoundWithinListException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public NothingFoundWithinListException(String type) {
        super("There are no " + type + " within this list or there is no user assigned" );
    }
}

