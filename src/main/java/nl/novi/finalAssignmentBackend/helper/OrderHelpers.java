package nl.novi.finalAssignmentBackend.helper;

import jakarta.persistence.EntityNotFoundException;
import nl.novi.finalAssignmentBackend.Repository.OrderRepository;

import nl.novi.finalAssignmentBackend.entities.Game;
import nl.novi.finalAssignmentBackend.entities.Order;
import nl.novi.finalAssignmentBackend.entities.Movie;
import nl.novi.finalAssignmentBackend.entities.ShoppingList;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class OrderHelpers {

    private final OrderRepository orderRepository;

    public OrderHelpers(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


    public void calculateProfit(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order with id " + id + " not found, the profit cannot be calculated"));

        double profit = 0.00;

        for (ShoppingList shoppingList : order.getShoppingList()) {
                for (Game game : shoppingList.getGames()) {
                    profit += calculateProfitGame(game);
                }
                for (Movie movie : shoppingList.getMovies()) {
                    profit += calculateProfitMovie(movie);
                }
                if(shoppingList.getDeliveryCost() == 0){
                    profit -= 10;
                }
            }

        order.setProfit(profit);
        orderRepository.save(order);
        }


    private Double calculateProfitGame(Game game) {
       return game.getSellingPrice() - game.getPurchasePrice();
    }

    private Double calculateProfitMovie(Movie movie) {
        return movie.getSellingPrice() - movie.getPurchasePrice();
    }



    public void calculateTotalPrice(Long id){
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order not found, the profit cannot be calculated"));

        double totalPrice = 0.00;

        for (ShoppingList shoppingList : order.getShoppingList()) {
            var subtotal = shoppingList.getSubtotal();
            var packaging = shoppingList.getPackagingCost();
            var delivery = shoppingList.getDeliveryCost();

            totalPrice += subtotal + packaging + delivery;
            order.setTotalPrice(totalPrice);
            orderRepository.save(order);

        }
    }

    // automatically sets the date ordered based on the orderConfirmations value being true;
    public void setOrderConfirmation(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Order not found"));


        if(order.getShoppingList().isEmpty()){
            throw new EntityNotFoundException("the order does not contain a shopping list and cant be set to true");
        }
        if (order.getOrderConfirmation()) {
            order.setDateOrdered(LocalDate.now());
        }
        orderRepository.save(order);
    }

    public void setDeliveryDate(Long id){
        Order order = orderRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Order not found"));


    }

}







