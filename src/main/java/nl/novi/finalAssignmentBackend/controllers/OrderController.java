package nl.novi.finalAssignmentBackend.controllers;

import jakarta.servlet.http.HttpServletRequest;
import nl.novi.finalAssignmentBackend.Service.OrderService;
import nl.novi.finalAssignmentBackend.dtos.order.OrderInputDto;
import nl.novi.finalAssignmentBackend.dtos.order.OrderResponseDto;
import nl.novi.finalAssignmentBackend.helper.LoggedInCheck;
import nl.novi.finalAssignmentBackend.helper.UrlHelper;
import nl.novi.finalAssignmentBackend.mappers.OrderMapper.OrderDtoMapper;
import nl.novi.finalAssignmentBackend.model.OrderModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RequestMapping("/orders")
@RestController
public class OrderController {

    private final OrderDtoMapper orderDtoMapper;
    private final OrderService orderService;
    private final HttpServletRequest request;
    private final LoggedInCheck loggedInCheck;

    public OrderController(OrderDtoMapper orderDtoMapper, OrderService orderService, HttpServletRequest request, LoggedInCheck loggedInCheck) {
        this.orderDtoMapper = orderDtoMapper;
        this.orderService = orderService;
        this.request = request;
        this.loggedInCheck = loggedInCheck;
    }


    @GetMapping("/admin") // just added!
    public ResponseEntity<List<OrderModel>>getAllOrdersAdmin(){
        var orders = orderService.getAllOrders();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/{username}")
    public ResponseEntity<List<OrderResponseDto>>getAllOrders(@PathVariable String username){
        var orders = orderService.getAllOrdersForUser(username);
        var orderDto = orders.stream().map(orderDtoMapper::toOrderDto).collect(Collectors.toList());
        return new ResponseEntity<>(orderDto, HttpStatus.OK);
    }

    @GetMapping("/{id}/user/{username}")
    public ResponseEntity<OrderResponseDto>getOrderById(@PathVariable Long id, @PathVariable String username){

        var orders = orderService.getOrderById(id, username);
        if(orders == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        var orderDto = orderDtoMapper.toOrderDto(orders);
        return new ResponseEntity<>(orderDto, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<OrderModel>createOrder(@RequestBody OrderInputDto orderInputDto){
        var newOrder = orderService.createOrder();
        return ResponseEntity.created(UrlHelper.getCurrentURLWithId(request, newOrder.getOrderNumber())).body(newOrder);
    }



    @PutMapping("/{id}/user/{username}")
    public ResponseEntity<OrderResponseDto>updateOrder(@PathVariable Long id, @PathVariable String username, @RequestBody OrderInputDto orderInputDto){
        var updateOrder = orderService.updateOrder(id, username,  orderDtoMapper.createOrderModel(orderInputDto));
        var invoiceDto = orderDtoMapper.toOrderDto(updateOrder);
        return new ResponseEntity<>(invoiceDto, HttpStatus.OK);
    }

    @PutMapping("/{orderId}/shoppinglists/{shoppingListId}/user/{username}")
    public ResponseEntity<String>addShoppingListToOrder(@PathVariable Long orderId, @PathVariable Long shoppingListId, @PathVariable String username){
        orderService.addShoppingListToOrder(orderId,shoppingListId, username);
        return ResponseEntity.ok("the shopping list has been added to the order");
    }

    @DeleteMapping("/{id}/user/{username}")
    public ResponseEntity<Object>deleteOrder(@PathVariable Long id, @PathVariable String username){
        orderService.deleteOrder(id, username);
        return ResponseEntity.noContent().build();
    }

}
