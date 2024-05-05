package nl.novi.finalAssignmentBackend.helper;

import nl.novi.finalAssignmentBackend.exceptions.NoUserAssignedException;
import nl.novi.finalAssignmentBackend.exceptions.UserMismatchException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class LoggedInCheck {

        public void verifyLoggedInUser(String username) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String loggedInUsername = authentication.getName();

            if (!username.equals(loggedInUsername)) {
                throw new UserMismatchException(username);
            }
        }

         public void verifyOwnerAuthorization(String expectedOwnerUsername, String loggedInUsername, String entityType) {
        if (!expectedOwnerUsername.equals(loggedInUsername)) {
            throw new NoUserAssignedException("You are not authorized to perform operations on this " + entityType + ".");
            }
    }

    }


//

