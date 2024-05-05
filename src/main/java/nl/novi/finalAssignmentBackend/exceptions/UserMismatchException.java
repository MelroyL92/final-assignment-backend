package nl.novi.finalAssignmentBackend.exceptions;

import java.io.Serial;

public class UserMismatchException extends RuntimeException {
        @Serial
        private static final long serialVersionUID = 1L;

        public UserMismatchException(String usernameOrder, String type, String usernameshoppingList) {
            super("the user : " + usernameOrder + " you are trying to assign to the  " + type + " with username " + usernameshoppingList + " have to match!");
        }

        public UserMismatchException(String type, Long id){
            super (type + " with id " + id +  " already has a user assigned to it");
        }

        public UserMismatchException(String username){
            super ("Mismatch in usernames: " + username + " does not match the logged-in user.");
        }
    }

