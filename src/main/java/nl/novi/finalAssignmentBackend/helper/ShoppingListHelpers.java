package nl.novi.finalAssignmentBackend.helper;

import jakarta.persistence.EntityNotFoundException;
import nl.novi.finalAssignmentBackend.Repository.ShoppingListRepository;
import nl.novi.finalAssignmentBackend.entities.Game;
import nl.novi.finalAssignmentBackend.entities.Movie;
import nl.novi.finalAssignmentBackend.entities.ShoppingList;
import org.springframework.stereotype.Component;

@Component
public class ShoppingListHelpers {

    private final ShoppingListRepository shoppingListRepository;

    public ShoppingListHelpers(ShoppingListRepository shoppingListRepository) {
        this.shoppingListRepository = shoppingListRepository;
    }


    public void updateSubtotal(Long shoppingListId){
        ShoppingList shoppingList = shoppingListRepository.findById(shoppingListId)
                .orElseThrow(() -> new EntityNotFoundException("Shopping list not found, the subtotal cannot be calculated"));

        Double subtotal = 0.00;

        for (Game game : shoppingList.getGames()){
            subtotal += game.getSellingPrice();
        }

        for (Movie movie: shoppingList.getMovies()){
            subtotal += movie.getSellingPrice();
        }

        shoppingList.setSubtotal(subtotal);
        shoppingListRepository.save(shoppingList);
    }

    public void calculateDeliveryCost(Long shoppingListId) {
        ShoppingList shoppingList = shoppingListRepository.findById(shoppingListId)
                .orElseThrow(() -> new EntityNotFoundException("Shopping list not found, the delivery cost cannot be calculated"));

        int movieAmount = shoppingList.getMovies().size();
        int gameAmount = shoppingList.getGames().size();
        int totalAmount = movieAmount + gameAmount;

        if (shoppingList.getAtHomeDelivery()) {
            int deliveryCost = 0;

            if (totalAmount < 5) {
                deliveryCost = 5;
            } else if (totalAmount < 10) {
                deliveryCost = 10;
            } else {
                deliveryCost = 0;
                // think of a way to let the person know that its free because of the amount they buy
            }
            shoppingList.setDeliveryCost(deliveryCost);
        }
        shoppingListRepository.save(shoppingList);
    }

    public void calculatePackagingCost(Long shoppingListId) {
        ShoppingList shoppingList = shoppingListRepository.findById(shoppingListId).orElseThrow(() -> new EntityNotFoundException("Shopping list not found, so the packaging cant be calculated"));
        int amount = shoppingList.getGames().size() + shoppingList.getMovies().size();
        double packagingCost = 0.40 * amount;
        shoppingList.setPackagingCost(packagingCost);
        shoppingListRepository.save(shoppingList);
    }
}
