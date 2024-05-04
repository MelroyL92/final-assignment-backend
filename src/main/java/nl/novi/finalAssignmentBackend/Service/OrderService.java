package nl.novi.finalAssignmentBackend.Service;

import jakarta.persistence.EntityNotFoundException;
import nl.novi.finalAssignmentBackend.Repository.OrderRepository;
import nl.novi.finalAssignmentBackend.Repository.ShoppingListRepository;
import nl.novi.finalAssignmentBackend.entities.Order;
import nl.novi.finalAssignmentBackend.entities.ShoppingList;
import nl.novi.finalAssignmentBackend.entities.User;
import nl.novi.finalAssignmentBackend.exceptions.*;
import nl.novi.finalAssignmentBackend.helper.LoggedInCheck;
import nl.novi.finalAssignmentBackend.helper.OrderHelpers;
import nl.novi.finalAssignmentBackend.helper.PDFCreator.PdfFileOrder;
import nl.novi.finalAssignmentBackend.helper.deliveryHelpers.DeliveryTimeCalculator;
import nl.novi.finalAssignmentBackend.mappers.OrderMapper.OrderMapper;
import nl.novi.finalAssignmentBackend.model.OrderModel;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final ShoppingListRepository shoppingListRepository;
    private final OrderHelpers orderHelpers;
    private final LoggedInCheck loggedInCheck;
    private final DeliveryTimeCalculator deliveryTimeCalculator;

    public OrderService(OrderRepository orderRepository, OrderMapper orderMapper, ShoppingListRepository shoppingListRepository, OrderHelpers orderHelpers, LoggedInCheck loggedInCheck, DeliveryTimeCalculator deliveryTimeCalculator) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.shoppingListRepository = shoppingListRepository;
        this.orderHelpers = orderHelpers;
        this.loggedInCheck = loggedInCheck;
        this.deliveryTimeCalculator = deliveryTimeCalculator;
    }




    private List<OrderModel> convertOrder(List<Order> orders) {

        List<Order> orderWithUsers = orders.stream()
                .filter(order -> order.getUser() != null)
                .collect(Collectors.toList());
        if (orderWithUsers.isEmpty()) {
            throw new NothingFoundWithinListException("orders");
        }

        return orderWithUsers.stream()
                .map(order -> {
                    double profit = orderHelpers.calculateProfit(order);
                    OrderModel orderModel = orderMapper.fromEntity(order);
                    orderModel.setProfit(profit);
                    orderModel.setTotalPrice(orderHelpers.calculateTotalPrice(order.getOrderNumber()));
                    return orderModel;
                })
                .collect(Collectors.toList());
    }

    public List<OrderModel> getAllOrders() {

        List<Order> orders = orderRepository.findAll();
        return convertOrder(orders);
    }


    public List<OrderModel> getAllOrdersForUser(String username) {
        loggedInCheck.verifyLoggedInUser(username);
        List<Order> orders = orderRepository.findAll();

        List<Order> ordersForUser = orders.stream()
                .filter(order -> {
                    User user = order.getUser();
                    return user != null && user.getUsername().equals(username);
                })
                .collect(Collectors.toList());

        ordersForUser.forEach(order -> loggedInCheck.verifyOwnerAuthorization(order.getUser().getUsername(), username, "order"));

        return convertOrder(ordersForUser);
    }

    public OrderModel getOrderById(Long id, String username){
        loggedInCheck.verifyLoggedInUser(username);

        Order order= orderRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Order not found with id " + id));
        if(order.getUser() == null){
            throw new NoUserAssignedException("order");
        }
        loggedInCheck.verifyOwnerAuthorization(order.getUser().getUsername(), username, "order" );

        orderHelpers.calculateTotalPrice(id);
        orderRepository.save(order);
        return orderMapper.fromEntity(order);
    }

    public OrderModel createOrder(){
        Order order = new Order();
        order = orderRepository.save(order);
        return orderMapper.fromEntity(order);
    }

    public OrderModel updateOrder(Long id, String username, OrderModel orderModel){

        loggedInCheck.verifyLoggedInUser(username);

        Optional<Order> orderFound = orderRepository.findById(id);

        if(orderFound.isPresent()){
            Order existingOrder = orderFound.get();
            User user = existingOrder.getUser();
            if (user == null || user.getUsername().isEmpty()) {
                throw new NoUserAssignedException("order");
            }
            loggedInCheck.verifyOwnerAuthorization(existingOrder.getUser().getUsername(), username, "order" );

            if(existingOrder.getOrderConfirmation()){
                throw new EntityNotFoundException("you cannot adjust the orderConfirmation anymore! please contact the admin if you want to make changes");
            }
            if(orderModel.getOrderConfirmation() != null){
                existingOrder.setOrderConfirmation(orderModel.getOrderConfirmation());
                orderHelpers.setOrderConfirmation(id);
                deliveryTimeCalculator.setDeliveryDate(id);
            }
            if(orderModel.getDeliveryDate() != null){
                existingOrder.setDeliveryDate(orderModel.getDeliveryDate());
            }
            if (orderModel.getCreatePdf()){
                existingOrder.setCreatePdf(orderModel.getCreatePdf());
            }
            if (orderModel.getOrderConfirmation() && orderModel.getCreatePdf()){
                createPdfOfOrder(existingOrder);
            }
            existingOrder = orderRepository.save(existingOrder);
            return orderMapper.fromEntity(existingOrder);
        } else {
            throw new RecordNotFoundException("Order with " + id + " has not been found");
        }
    }

        public void addShoppingListToOrder(Long orderId,Long shoppingListId, String username){

        loggedInCheck.verifyLoggedInUser(username);

        Order order = orderRepository.findById(orderId).orElseThrow(()-> new EntityNotFoundException("Order with id " + orderId + " not found"));
        ShoppingList shoppingList = shoppingListRepository.findById(shoppingListId).orElseThrow(()-> new EntityNotFoundException("Shopping list with id " + shoppingListId + " not found"));
            if (!order.getShoppingList().isEmpty()) {
                throw new EntityNotFoundException("Order already contains a shopping list.");
            }
        if(Objects.equals(shoppingList.getType(), "wishlist") || !Objects.equals(shoppingList.getType(), "shoppinglist")){
            throw new EntityNotFoundException("please change the type of the wishlist with id " + shoppingListId + " to shoppinglist before adding it to the order");
        }
        if(order.getUser() == null){
            throw new NoUserAssignedException("order");
        }
        if(shoppingList.getUser() == null){
            throw new NoUserAssignedException("shopping list");
        }
        if(!order.getUser().getUsername().equalsIgnoreCase(shoppingList.getUser().getUsername())){
            throw new UserMismatchException(shoppingList.getUser().getUsername(), "order", order.getUser().getUsername());
        }
        if(shoppingList.getGames().isEmpty() && shoppingList.getMovies().isEmpty()){
            throw new EntityNotFoundException("the shoppinglist cannot be added to the order when empty, please add a game or movie to the shoppingList before adding it to the order!");
        }
        loggedInCheck.verifyOwnerAuthorization(shoppingList.getUser().getUsername(), username, "order" );

        order.getShoppingList().add(shoppingList);
        orderRepository.save(order);


    }

    public void deleteOrder(Long id, String username){
        loggedInCheck.verifyLoggedInUser(username);

        Optional<Order>optionalOrder = orderRepository.findById(id);
        if(optionalOrder.isEmpty()){
            throw new RecordNotFoundException("Order with id " + id + " does not exist!");
        }
        Order order = optionalOrder.get();
        loggedInCheck.verifyOwnerAuthorization(order.getUser().getUsername(),username,"order");

        if(order.getOrderConfirmation().equals(false)){
            orderRepository.deleteById(id);
        } else {
            throw new BadRequestException("when you already confirmed the order you cannot delete it");
        }
    }

    public void createPdfOfOrder(Order order) {
        if (order.getCreatePdf()) {
            PdfFileOrder pdfFileOrder = new PdfFileOrder();
            try {
                pdfFileOrder.createPdf(order);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
