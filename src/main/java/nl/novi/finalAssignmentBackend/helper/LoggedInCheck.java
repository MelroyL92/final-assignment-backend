package nl.novi.finalAssignmentBackend.helper;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class LoggedInCheck {

        public void verifyLoggedInUser(String username) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String loggedInUsername = authentication.getName();

            if (!username.equals(loggedInUsername)) {
                throw new EntityNotFoundException("Mismatch in usernames: " + username + " does not match the logged-in user.");
            }
        }

         public void verifyOwnerAuthorization(String expectedOwnerUsername, String loggedInUsername, String entityType) {
        if (!expectedOwnerUsername.equals(loggedInUsername)) {
            throw new EntityNotFoundException("You are not authorized to perform operations on this " + entityType + ".");
            }
    }

    }


//

