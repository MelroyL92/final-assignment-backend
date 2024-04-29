package nl.novi.finalAssignmentBackend.Service;

import jakarta.persistence.EntityNotFoundException;
import nl.novi.finalAssignmentBackend.Repository.OrderRepository;
import nl.novi.finalAssignmentBackend.Repository.ShoppingListRepository;
import nl.novi.finalAssignmentBackend.entities.Order;
import nl.novi.finalAssignmentBackend.entities.ShoppingList;
import nl.novi.finalAssignmentBackend.exceptions.RecordNotFoundException;
import nl.novi.finalAssignmentBackend.helper.OrderHelpers;
import nl.novi.finalAssignmentBackend.helper.PDFCreator.PdfFile;
import nl.novi.finalAssignmentBackend.mappers.OrderMapper.OrderMapper;
import nl.novi.finalAssignmentBackend.model.OrderModel;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
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




    public List<OrderModel> getOrders(){
        return orderRepository.findAll().stream().map(orderMapper::fromEntity).collect(Collectors.toList());
    }

    public OrderModel getOrderById(Long id){
       Order order= orderRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Invoice not found with id " + id));
       orderHelpers.calculateTotalPrice(id);
       orderRepository.save(order);
       createOrder(order);
       return orderMapper.fromEntity(order);
    }

    public OrderModel createOrder(OrderModel orderModel){
        Order order = orderMapper.toEntity(orderModel);
        order = orderRepository.save(order);
        return orderMapper.fromEntity(order);
    }

    public OrderModel updateOrderModel(Long id, OrderModel orderModel){
        Optional<Order> orderFound = orderRepository.findById(id);
        if(orderFound.isPresent()){
            Order excistingOrder = orderFound.get();
            excistingOrder.setDateOrdered(orderModel.getDateOrdered());
            excistingOrder.setProfit(orderModel.getProfit());
            excistingOrder.setStatus(orderModel.getStatus());
            excistingOrder = orderRepository.save(excistingOrder);
            return orderMapper.fromEntity(excistingOrder);
        } else {
            throw new RecordNotFoundException("Order model with " + id + " has not been found");
        }
    }

    public void addShoppingListToOrder(Long orderId, Long shoppingListId){
        Order order = orderRepository.findById(orderId).orElseThrow(()-> new EntityNotFoundException("Order with id " + orderId + " not found"));
        ShoppingList shoppingList = shoppingListRepository.findById(shoppingListId).orElseThrow(()-> new EntityNotFoundException("Shopping list with id " + shoppingListId + " not found"));

        if(order.getUser() == null){
            throw new EntityNotFoundException("please assign a user to the order before adding a shoppingList");
        }
        if(shoppingList.getUser() == null){
            throw new EntityNotFoundException("please add a user to the shoppingList before adding it to the order");
        }
        if(order.getUser().getUsername().equalsIgnoreCase(shoppingList.getUser().getUsername())){
            order.getShoppingList().add(shoppingList);
            orderRepository.save(order);
        }
        throw new EntityNotFoundException("the user of the shoppingList with id " + shoppingList.getUser().getUsername() + " and the user of the order with id " + order.getUser().getUsername() + " do not match");

    }

    public void deleteOrder(Long id){
        orderRepository.deleteById(id);
    }

    public void createOrder(Order order) {
        if (order.isCreatePdf()) {
            PdfFile pdfFile = new PdfFile();
            try {
                pdfFile.createPdf(order);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
