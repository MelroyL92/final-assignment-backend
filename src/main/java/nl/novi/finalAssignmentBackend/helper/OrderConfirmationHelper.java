package nl.novi.finalAssignmentBackend.helper;

import jakarta.persistence.EntityNotFoundException;
import nl.novi.finalAssignmentBackend.Repository.OrderRepository;
import nl.novi.finalAssignmentBackend.Repository.ShoppingListRepository;
import nl.novi.finalAssignmentBackend.entities.Order;
import nl.novi.finalAssignmentBackend.entities.ShoppingList;
import org.springframework.stereotype.Component;


@Component
public class OrderConfirmationHelper {

    private final OrderRepository orderRepository;
    private final ShoppingListRepository shoppingListRepository;

    public OrderConfirmationHelper(OrderRepository orderRepository, ShoppingListRepository shoppingListRepository) {
        this.orderRepository = orderRepository;
        this.shoppingListRepository = shoppingListRepository;
    }

//    public boolean isShoppingListConnectedToOrder(Long shoppingListId) {
//        ShoppingList shoppingList = shoppingListRepository.findById(shoppingListId)
//                .orElseThrow(() -> new EntityNotFoundException("Shopping list not found with id: " + shoppingListId));
//
//        return orderRepository.findAll().stream()
//                .filter(order -> order.getOrderConfirmation())
//                .flatMap(order -> order.getShoppingList().stream())
//                .anyMatch(list -> list.getId().equals(shoppingList.getId()));
//    }

    public boolean isShoppingListConnectedToOrder(Long shoppingListId) {
        ShoppingList shoppingList = shoppingListRepository.findById(shoppingListId)
                .orElseThrow(() -> new EntityNotFoundException("Shopping list not found with id: " + shoppingListId));

        return orderRepository.findAll().stream()
                .filter(Order::getOrderConfirmation)
                .anyMatch(order -> order.getShoppingList().getId().equals(shoppingList.getId()));
    }
}
