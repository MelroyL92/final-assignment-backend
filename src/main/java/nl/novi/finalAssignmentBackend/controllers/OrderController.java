package nl.novi.finalAssignmentBackend.controllers;

import jakarta.servlet.http.HttpServletRequest;
import nl.novi.finalAssignmentBackend.Service.OrderService;
import nl.novi.finalAssignmentBackend.dtos.order.OrderInputDto;
import nl.novi.finalAssignmentBackend.dtos.order.OrderResponseDto;
import nl.novi.finalAssignmentBackend.helper.UrlHelper;
import nl.novi.finalAssignmentBackend.mappers.OrderMapper.OrderDtoMapper;
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

    public OrderController(nl.novi.finalAssignmentBackend.mappers.OrderMapper.OrderDtoMapper orderDtoMapper, nl.novi.finalAssignmentBackend.Service.OrderService orderService, HttpServletRequest request) {
        this.orderDtoMapper = orderDtoMapper;
        this.orderService = orderService;
        this.request = request;
    }


    @GetMapping
    public ResponseEntity<List<OrderResponseDto>>getAllOrders(){
        var orders = orderService.getOrders();
        var orderDto = orders.stream().map(orderDtoMapper::toOrderDto).collect(Collectors.toList());
        return new ResponseEntity<>(orderDto, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDto>getInvoiceById(@PathVariable Long id){
        var orders = orderService.getOrderById(id);
        if(orders == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        var orderDto = orderDtoMapper.toOrderDto(orders);
        return new ResponseEntity<>(orderDto, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<OrderResponseDto>createOrder(@RequestBody OrderInputDto orderInputDto){
        var orderModel = orderDtoMapper.createOrderModel(orderInputDto);
        var newOrder = orderService.createOrder(orderModel);
        var orderDto = orderDtoMapper.toOrderDto(newOrder);
        return ResponseEntity.created(UrlHelper.getCurrentURLWithId(request, orderDto.getOrderNumber())).body(orderDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderResponseDto>UpdateInvoice(@RequestParam Long id, @RequestBody OrderInputDto orderInputDto){
        var updateOrder = orderService.updateOrderModel(id, orderDtoMapper.createOrderModel(orderInputDto));
        var invoiceDto = orderDtoMapper.toOrderDto(updateOrder);
        return new ResponseEntity<>(invoiceDto, HttpStatus.OK);
    }

    @PutMapping("/{orderId}/shoppinglists/{shoppingListId}")
    public ResponseEntity<String>addShoppingListToOrder(@PathVariable Long orderId, @PathVariable Long shoppingListId){
        orderService.addShoppingListToOrder(orderId,shoppingListId);
        return ResponseEntity.ok("the shopping list has been added to the invoice");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object>deleteOrder(@PathVariable Long id){
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }



}
