package nl.novi.finalAssignmentBackend.Service;

import jakarta.persistence.EntityNotFoundException;
import nl.novi.finalAssignmentBackend.Repository.OrderRepository;
import nl.novi.finalAssignmentBackend.Repository.ShoppingListRepository;
import nl.novi.finalAssignmentBackend.entities.Order;
import nl.novi.finalAssignmentBackend.entities.ShoppingList;
import nl.novi.finalAssignmentBackend.exceptions.RecordNotFoundException;
import nl.novi.finalAssignmentBackend.helper.LoggedInCheck;
import nl.novi.finalAssignmentBackend.helper.OrderHelpers;
import nl.novi.finalAssignmentBackend.helper.PDFCreator.PdfFileOrder;
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

    public OrderService(OrderRepository orderRepository, OrderMapper orderMapper, ShoppingListRepository shoppingListRepository, OrderHelpers orderHelpers, LoggedInCheck loggedInCheck) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.shoppingListRepository = shoppingListRepository;
        this.orderHelpers = orderHelpers;
        this.loggedInCheck = loggedInCheck;
    }

//    public List<OrderModel>getAllOrders(String username) {
//        loggedInCheck.verifyLoggedInUser(username);
//
//        List<Order>orders=orderRepository.findAll();
//        for(Order order: orders){
//            loggedInCheck.verifyOwnerAuthorization(order.getUser().getUsername(), username, "order" );
//            if (order.getUser().getAuthorities().contains("ADMIN")){
//                orders.add(order);
//            }
//            if (order.getUser().getAuthorities().equals("USER")){
//                orders.add(order);
//            }
//            if (order.getUser().getUsername().isEmpty()){
//                throw new EntityNotFoundException("Orders cannot excist without users, please add a user");
//        }

//
//        {
//
//            List<Order>orderWithUsers = orders.stream()
//                .filter(order -> order.getUser() != null)
//                .collect(Collectors.toList());
//
//        }
//        return orderWithUsers.stream()
//                .map(orderMapper::fromEntity)
//                .collect(Collectors.toList());
//        }
//        else if (username.equals("USER")){
//            List<Order>orders=orderRepository.findOrderByUser(username);
//            if (orders.isEmpty()){
//                throw new EntityNotFoundException("No orders found with username " + username);
//            }
//            return orders.stream()
//                    .map(orderMapper::fromEntity)
//                    .collect(Collectors.toList());
//        }
//        throw new EntityNotFoundException("no orders found");
//    }
        public List<OrderModel> getAllOrders() {

            List<Order> orders = orderRepository.findAll();

            List<Order> orderWithUsers = orders.stream()
                    .filter(order -> order.getUser() != null)
                    .collect(Collectors.toList());
            if (orderWithUsers.isEmpty()) {
                throw new EntityNotFoundException("Orders cannot excist without users, please add a user");
            }

            return orderWithUsers.stream()
                    .map(orderMapper::fromEntity)
                    .collect(Collectors.toList());
        }


    public OrderModel getOrderById(Long id, String username){
        loggedInCheck.verifyLoggedInUser(username);

        Order order= orderRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Order not found with id " + id));
        if(order.getUser() == null){
            throw new EntityNotFoundException("please assign a user to the order");
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
            Order excistingOrder = orderFound.get();
            loggedInCheck.verifyOwnerAuthorization(excistingOrder.getUser().getUsername(), username, "order" );

            if(orderModel.getOrderConfirmation() != null){
                excistingOrder.setOrderConfirmation(orderModel.getOrderConfirmation());
                orderHelpers.setOrderConfirmation(id);
            }
            if(orderModel.getDeliveryDate() != null){
                excistingOrder.setDeliveryDate(orderModel.getDeliveryDate());
            }
            if (orderModel.isCreatePdf()){
                excistingOrder.setCreatePdf(orderModel.isCreatePdf());
            }
            if (orderModel.getOrderConfirmation() && orderModel.isCreatePdf()){
                createPdfOfOrder(excistingOrder);
            }
            excistingOrder = orderRepository.save(excistingOrder);
            return orderMapper.fromEntity(excistingOrder);
        } else {
            throw new RecordNotFoundException("Order model with " + id + " has not been found");
        }
    }

    public void addShoppingListToOrder(Long orderId,Long shoppingListId, String username){

        loggedInCheck.verifyLoggedInUser(username);

        Order order = orderRepository.findById(orderId).orElseThrow(()-> new EntityNotFoundException("Order with id " + orderId + " not found"));
        ShoppingList shoppingList = shoppingListRepository.findById(shoppingListId).orElseThrow(()-> new EntityNotFoundException("Shopping list with id " + shoppingListId + " not found"));
        loggedInCheck.verifyOwnerAuthorization(shoppingList.getUser().getUsername(), username, "order" );

        if(Objects.equals(shoppingList.getType(), "wishlist") || !Objects.equals(shoppingList.getType(), "shoppinglist")){
            throw new EntityNotFoundException("please change the type of the wishlist with id " + shoppingListId + " to shoppingList before adding it to the order");
        }
        if(order.getUser() == null){
            throw new EntityNotFoundException("please assign a user to the order before adding a shoppingList");
        }
        if(shoppingList.getUser() == null){
            throw new EntityNotFoundException("please add a user to the shoppingList before adding it to the order");
        }
        if(!order.getUser().getUsername().equalsIgnoreCase(shoppingList.getUser().getUsername())){
            throw new EntityNotFoundException("the user of the shoppingList with id " + shoppingList.getUser().getUsername() + " and the user of the order with id " + order.getUser().getUsername() + " do not match");
        }
        if(shoppingList.getGames().isEmpty() || shoppingList.getMovies().isEmpty()){
            throw new EntityNotFoundException("the shoppinglist cannot be added to the order when empty, please add a game or movie to the shoppingList before adding it to the order!");
        }
        order.getShoppingList().add(shoppingList);
        orderRepository.save(order);

    }

    public void deleteOrder(Long id, String username){
        loggedInCheck.verifyLoggedInUser(username);

        Optional<Order>optionalOrder = orderRepository.findById(id);
        if(optionalOrder.isEmpty()){
            throw new RecordNotFoundException("Order with id " + id + "does not exist!");
        }
        Order order = optionalOrder.get();
        loggedInCheck.verifyOwnerAuthorization(order.getUser().getUsername(),username,"order");

        orderRepository.deleteById(id);
    }

    public void createPdfOfOrder(Order order) {
        if (order.isCreatePdf()) {
            PdfFileOrder pdfFileOrder = new PdfFileOrder();
            try {
                pdfFileOrder.createPdf(order);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
