package nl.novi.finalAssignmentBackend.helper;

import jakarta.persistence.EntityNotFoundException;
import nl.novi.finalAssignmentBackend.Repository.OrderRepository;
import nl.novi.finalAssignmentBackend.Repository.ShoppingListRepository;
import nl.novi.finalAssignmentBackend.entities.Order;
import nl.novi.finalAssignmentBackend.entities.ShoppingList;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderConfirmationHelper {

    private final OrderRepository orderRepository;
    private final ShoppingListRepository shoppingListRepository;

    public OrderConfirmationHelper(OrderRepository orderRepository, ShoppingListRepository shoppingListRepository) {
        this.orderRepository = orderRepository;
        this.shoppingListRepository = shoppingListRepository;
    }

    public boolean isShoppingListConnectedToOrder(Long shoppingListId){
        List<Order> orders = orderRepository.findAll();

        ShoppingList shoppingList = shoppingListRepository.findById(shoppingListId).orElseThrow(() -> new EntityNotFoundException("Shopping list not found with id: " + shoppingListId));

        for(Order order: orders) {
            for (ShoppingList list : order.getShoppingList()) {
                if (list.getId().equals(shoppingList.getId())) {
                    return true;
                }
            }
        }
        return false;
    }
}
