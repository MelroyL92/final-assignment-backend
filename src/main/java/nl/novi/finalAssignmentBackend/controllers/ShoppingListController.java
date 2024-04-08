package nl.novi.finalAssignmentBackend.controllers;


import jakarta.servlet.http.HttpServletRequest;
import nl.novi.finalAssignmentBackend.Service.ShoppingListService;
import nl.novi.finalAssignmentBackend.dtos.ShoppingList.ShoppingListResponseDto;
import nl.novi.finalAssignmentBackend.mappers.ShoppingListMapper.ShoppingListDTOMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/shoppinglists")
public class ShoppingListController{

    private final ShoppingListDTOMapper shoppingListDTOMapper;
    private final ShoppingListService shoppingListService;
    private final HttpServletRequest request;

    public ShoppingListController(ShoppingListDTOMapper shoppingListDTOMapper, ShoppingListService shoppingListService, HttpServletRequest request) {
        this.shoppingListDTOMapper = shoppingListDTOMapper;
        this.shoppingListService = shoppingListService;
        this.request = request;
    }

    @GetMapping
    public ResponseEntity<List<ShoppingListResponseDto>>getAllShoppinglists(){
        var shoppingList = shoppingListService.getShoppingList();
        var shoppingListDTO = shoppingList.stream().map(shoppingListDTOMapper::toShoppingListDto).collect(Collectors.toList());
        return new ResponseEntity<>(shoppingListDTO, HttpStatus.OK);
    }
}
