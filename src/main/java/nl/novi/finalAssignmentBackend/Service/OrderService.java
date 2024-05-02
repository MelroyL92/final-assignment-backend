package nl.novi.finalAssignmentBackend.Service;

import jakarta.persistence.EntityNotFoundException;
import nl.novi.finalAssignmentBackend.Repository.OrderRepository;
import nl.novi.finalAssignmentBackend.Repository.ShoppingListRepository;
import nl.novi.finalAssignmentBackend.entities.Order;
import nl.novi.finalAssignmentBackend.entities.ShoppingList;
import nl.novi.finalAssignmentBackend.exceptions.RecordNotFoundException;
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

    public OrderService(OrderRepository orderRepository, OrderMapper orderMapper, ShoppingListRepository shoppingListRepository, OrderHelpers orderHelpers) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.shoppingListRepository = shoppingListRepository;
        this.orderHelpers = orderHelpers;
    }

//    public List<OrderModel> getOrders(){
//        return orderRepository.findAll().stream().map(orderMapper::fromEntity).collect(Collectors.toList());
//    }

    public List<OrderModel>getAllOrders(){
        List<Order>orders = orderRepository.findAll();

        List<Order>orderWithUsers = orders.stream()
                .filter(order -> order.getUser() != null)
                .collect(Collectors.toList());
        if (orderWithUsers.isEmpty()){
            throw new EntityNotFoundException("Orders cannot excist without users, please add a user");
        }

        return orderWithUsers.stream()
                .map(orderMapper::fromEntity)
                .collect(Collectors.toList());
    }

    public OrderModel getOrderById(Long id){
       Order order= orderRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Order not found with id " + id));
        if(order.getUser() == null){
            throw new EntityNotFoundException("please assign a user to the order");
        }
       orderHelpers.calculateTotalPrice(id);
       orderRepository.save(order);
       return orderMapper.fromEntity(order);
    }

    public OrderModel createOrder(OrderModel orderModel){
//        Order order = orderMapper.toEntity(orderModel);
//        order = orderRepository.save(order);
//        return orderMapper.fromEntity(order);
        Order order = new Order();
        order.setCreatePdf(orderModel.isCreatePdf());
        order.setOrderConfirmation(orderModel.getOrderConfirmation());

        order = orderRepository.save(order);
        return orderMapper.fromEntity(order);
    }

    public OrderModel updateOrder(Long id, OrderModel orderModel){
        Optional<Order> orderFound = orderRepository.findById(id);
        if(orderFound.isPresent()){
            Order excistingOrder = orderFound.get();

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
                createOrder(excistingOrder);
            }
            excistingOrder = orderRepository.save(excistingOrder);
            return orderMapper.fromEntity(excistingOrder);
        } else {
            throw new RecordNotFoundException("Order model with " + id + " has not been found");
        }
    }

    public void addShoppingListToOrder(Long orderId, Long shoppingListId){
        Order order = orderRepository.findById(orderId).orElseThrow(()-> new EntityNotFoundException("Order with id " + orderId + " not found"));
        ShoppingList shoppingList = shoppingListRepository.findById(shoppingListId).orElseThrow(()-> new EntityNotFoundException("Shopping list with id " + shoppingListId + " not found"));
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
        }// when the orderConfirmation is true this doesnt work, how to prevent the orderConfirmation to be set to true before a shoppingList has been added upon creation of a new order?
        order.getShoppingList().add(shoppingList);
        orderRepository.save(order);

    }

    public void deleteOrder(Long id){
        orderRepository.deleteById(id);
    }

    public void createOrder(Order order) {
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
