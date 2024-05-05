package nl.novi.finalAssignmentBackend.controllers;

import jakarta.servlet.http.HttpServletRequest;
import nl.novi.finalAssignmentBackend.Service.OrderService;
import nl.novi.finalAssignmentBackend.dtos.order.ExtendedOrderResponseDTO;
import nl.novi.finalAssignmentBackend.dtos.order.OrderInputDTO;
import nl.novi.finalAssignmentBackend.dtos.order.OrderResponseDTO;
import nl.novi.finalAssignmentBackend.helper.UrlHelper;
import nl.novi.finalAssignmentBackend.mappers.OrderMapper.ExtendeOrderDTOMapper;
import nl.novi.finalAssignmentBackend.mappers.OrderMapper.OrderDTOMapper;
import nl.novi.finalAssignmentBackend.model.OrderModel;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RequestMapping("/orders")
@RestController
public class OrderController {

    private final OrderDTOMapper orderDtoMapper;
    private final ExtendeOrderDTOMapper extendeOrderDTOMapper;
    private final OrderService orderService;
    private final HttpServletRequest request;


    public OrderController(@Qualifier("orderDTOMapper") OrderDTOMapper orderDtoMapper, ExtendeOrderDTOMapper extendeOrderDTOMapper, OrderService orderService, HttpServletRequest request) {
        this.orderDtoMapper = orderDtoMapper;
        this.extendeOrderDTOMapper = extendeOrderDTOMapper;
        this.orderService = orderService;
        this.request = request;
    }


    @GetMapping("/admin")
    public ResponseEntity<List<ExtendedOrderResponseDTO>>getAllOrdersAdmin(){
        var orders = orderService.getAllOrders();
        var extendedOrderDTO = orders.stream().map(extendeOrderDTOMapper::toExtendedOrderDTO).collect(Collectors.toList());
        return new ResponseEntity<>(extendedOrderDTO, HttpStatus.OK);
    }

    @GetMapping("/{username}")
    public ResponseEntity<List<OrderResponseDTO>>getAllOrders(@PathVariable String username){
        var orders = orderService.getAllOrdersForUser(username);
        var orderDto = orders.stream().map(orderDtoMapper::toOrderDTO).collect(Collectors.toList());
        return new ResponseEntity<>(orderDto, HttpStatus.OK);
    }

    @GetMapping("/{id}/user/{username}")
    public ResponseEntity<OrderResponseDTO>getOrderById(@PathVariable Long id, @PathVariable String username){

        var orders = orderService.getOrderById(id, username);
        if(orders == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        var orderDto = orderDtoMapper.toOrderDTO(orders);
        return new ResponseEntity<>(orderDto, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<OrderModel>createOrder(){
        var newOrder = orderService.createOrder();
        return ResponseEntity.created(UrlHelper.getCurrentURLWithId(request, newOrder.getOrderNumber())).body(newOrder);
    }



    @PutMapping("/{id}/user/{username}")
    public ResponseEntity<OrderResponseDTO>updateOrder(@PathVariable Long id, @PathVariable String username, @RequestBody OrderInputDTO orderInputDto){
        var updateOrder = orderService.updateOrder(id, username,  orderDtoMapper.createOrderModel(orderInputDto));
        var invoiceDto = orderDtoMapper.toOrderDTO(updateOrder);
        return new ResponseEntity<>(invoiceDto, HttpStatus.OK);
    }

    @PostMapping("/{orderId}/shoppinglists/{shoppingListId}/user/{username}")
    public ResponseEntity<String>addShoppingListToOrder(@PathVariable Long orderId, @PathVariable Long shoppingListId, @PathVariable String username){
        orderService.addShoppingListToOrder(orderId,shoppingListId, username);
        return ResponseEntity.ok("the shopping list has been added to the order");
    }

    @DeleteMapping("/{id}/user/{username}")
    public ResponseEntity<Object>deleteOrder(@PathVariable Long id, @PathVariable String username){
        orderService.deleteOrder(id, username);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}/admin")
    public ResponseEntity<Object>deleteOrderAdmin(@PathVariable Long id){
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }

}
