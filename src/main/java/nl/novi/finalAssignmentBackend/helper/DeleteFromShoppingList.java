package nl.novi.finalAssignmentBackend.helper;

import nl.novi.finalAssignmentBackend.Repository.ShoppingListRepository;
import nl.novi.finalAssignmentBackend.entities.Game;
import nl.novi.finalAssignmentBackend.entities.Movie;
import nl.novi.finalAssignmentBackend.entities.ShoppingList;
import nl.novi.finalAssignmentBackend.exceptions.NoUserAssignedException;
import nl.novi.finalAssignmentBackend.exceptions.RecordNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class DeleteFromShoppingList {

    private final LoggedInCheck loggedInCheck;
    private final ShoppingListRepository shoppingListRepository;
    private final ShoppingListHelpers shoppingListHelpers;



    public DeleteFromShoppingList(LoggedInCheck loggedInCheck, ShoppingListRepository shoppingListRepository, ShoppingListHelpers shoppingListHelpers) {
        this.loggedInCheck = loggedInCheck;
        this.shoppingListRepository = shoppingListRepository;
        this.shoppingListHelpers = shoppingListHelpers;
    }

    public void deleteItemFromShoppingList(String username, Long shoppingListId, Long itemId, String itemType) {
        loggedInCheck.verifyLoggedInUser(username);

        Optional<ShoppingList> optionalShoppingList = shoppingListRepository.findById(shoppingListId);
        if (optionalShoppingList.isEmpty()) {
            throw new RecordNotFoundException("Shopping list with id " + shoppingListId + " does not exist");
        }
        ShoppingList shoppingList = optionalShoppingList.get();

        if (shoppingList.getUser() == null || shoppingList.getUser().getUsername() == null || shoppingList.getUser().getUsername().isEmpty()) {
            throw new NoUserAssignedException("shopping list", username);
        }

        loggedInCheck.verifyOwnerAuthorization(shoppingList.getUser().getUsername(), username, "shopping list");

        if ("game".equals(itemType)) {
            List<Game> gameInList = shoppingList.getGames();
            Optional<Game> optionalGame = gameInList.stream().filter(game -> game.getId().equals(itemId)).findFirst();

            if (optionalGame.isPresent()) {
                Game gameToDelete = optionalGame.get();
                gameInList.remove(gameToDelete);
            } else {
                throw new RecordNotFoundException("The game you requested to delete with id " + itemId + " does not exist");
            }
        } else if ("movie".equals(itemType)) {
            List<Movie> movieInList = shoppingList.getMovies();
            Optional<Movie> optionalMovie = movieInList.stream().filter(movie -> movie.getId().equals(itemId)).findFirst();

            if (optionalMovie.isPresent()) {
                Movie movieToDelete = optionalMovie.get();
                movieInList.remove(movieToDelete);
            } else {
                throw new RecordNotFoundException("The movie you requested to delete with id " + itemId + " does not exist");
            }
        }

        shoppingListHelpers.updateSubtotal(shoppingListId);
        shoppingListRepository.save(shoppingList);
    }
}
