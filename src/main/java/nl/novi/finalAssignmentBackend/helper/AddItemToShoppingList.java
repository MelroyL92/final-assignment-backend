package nl.novi.finalAssignmentBackend.helper;

import jakarta.persistence.EntityNotFoundException;
import nl.novi.finalAssignmentBackend.Repository.GameRepository;
import nl.novi.finalAssignmentBackend.Repository.MovieRepository;
import nl.novi.finalAssignmentBackend.Repository.ShoppingListRepository;
import nl.novi.finalAssignmentBackend.entities.Game;
import nl.novi.finalAssignmentBackend.entities.Movie;
import nl.novi.finalAssignmentBackend.entities.ShoppingList;
import nl.novi.finalAssignmentBackend.exceptions.NoUserAssignedException;
import org.springframework.stereotype.Component;

@Component
public class AddItemToShoppingList {

    private final LoggedInCheck loggedInCheck;
    private final ShoppingListRepository shoppingListRepository;
    private final OrderConfirmationHelper orderConfirmationHelper;
    private final GameRepository gameRepository;
    private final MovieRepository movieRepository;
    private final ShoppingListHelpers shoppingListHelpers;

    public AddItemToShoppingList(LoggedInCheck loggedInCheck, ShoppingListRepository shoppingListRepository, OrderConfirmationHelper orderConfirmationHelper, GameRepository gameRepository, MovieRepository movieRepository, ShoppingListHelpers shoppingListHelpers) {
        this.loggedInCheck = loggedInCheck;
        this.shoppingListRepository = shoppingListRepository;
        this.orderConfirmationHelper = orderConfirmationHelper;
        this.gameRepository = gameRepository;
        this.movieRepository = movieRepository;
        this.shoppingListHelpers = shoppingListHelpers;
    }

    public void addItemToShoppingList(Long shoppingListId, String username, Long itemId, String itemType) {
        loggedInCheck.verifyLoggedInUser(username);

        ShoppingList shoppingList = shoppingListRepository.findById(shoppingListId)
                .orElseThrow(() -> new EntityNotFoundException("Shopping list not found"));
        if (shoppingList.getUser() == null) {
            throw new NoUserAssignedException("shopping list");
        }
        loggedInCheck.verifyOwnerAuthorization(shoppingList.getUser().getUsername(), username, "shopping list");
        if (orderConfirmationHelper.isShoppingListConnectedToOrder(shoppingListId)) {
            throw new EntityNotFoundException("It is not allowed to adjust fields of a shopping list within an order with the confirmation set to true");
        }

        if ("game".equals(itemType)) {
            Game game = gameRepository.findById(itemId)
                    .orElseThrow(() -> new EntityNotFoundException("Game not found"));
            shoppingList.getGames().add(game);
        } else if ("movie".equals(itemType)) {
            Movie movie = movieRepository.findById(itemId)
                    .orElseThrow(() -> new EntityNotFoundException("Movie not found"));
            shoppingList.getMovies().add(movie);
        }

        shoppingListHelpers.updateSubtotal(shoppingListId);
        shoppingListHelpers.calculateDeliveryCost(shoppingListId);
        shoppingListHelpers.calculatePackagingCost(shoppingListId);

        shoppingListRepository.save(shoppingList);
    }
}
