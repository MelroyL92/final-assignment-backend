package nl.novi.finalAssignmentBackend.exceptions;


import java.io.Serial;

public class NoUserAssignedException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    public NoUserAssignedException (String type) {
        super("please assign a user to the " + type );
    }

    public NoUserAssignedException(String type, String username){
        super( "the list of type : " + type + ", the user with username " + username + " is trying to delete does not contain a user and therefore cannot be deleted");
    }
}


