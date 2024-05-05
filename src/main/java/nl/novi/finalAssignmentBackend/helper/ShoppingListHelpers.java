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

        int deliveryCost = 0;

        if (shoppingList.getAtHomeDelivery()) {
            if (totalAmount < 5) {
                deliveryCost = 5;
            } else if (totalAmount < 10) {
                deliveryCost = 10;
            }

        }

        shoppingList.setDeliveryCost(deliveryCost);
        shoppingListRepository.save(shoppingList);
    }

    public void calculatePackagingCost(Long shoppingListId) {

        ShoppingList shoppingList = shoppingListRepository.findById(shoppingListId).orElseThrow(() -> new EntityNotFoundException("Shopping list not found. The packaging cant be calculated"));
        double packagingCost = 0;

        if(shoppingList.getPackaging()) {
            int amount = shoppingList.getGames().size() + shoppingList.getMovies().size();
            packagingCost = 0.40 * amount;

            packagingCost = Math.round(packagingCost * 100.0) / 100.0;
        }

        shoppingList.setPackagingCost(packagingCost);
        shoppingListRepository.save(shoppingList);
    }
}
