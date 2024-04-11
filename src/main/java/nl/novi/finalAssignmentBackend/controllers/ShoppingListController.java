package nl.novi.finalAssignmentBackend.controllers;


import jakarta.servlet.http.HttpServletRequest;
import nl.novi.finalAssignmentBackend.Service.ShoppingListService;
import nl.novi.finalAssignmentBackend.dtos.ShoppingList.ShoppingListInputDto;
import nl.novi.finalAssignmentBackend.dtos.ShoppingList.ShoppingListResponseDto;
import nl.novi.finalAssignmentBackend.helper.UrlHelper;
import nl.novi.finalAssignmentBackend.mappers.ShoppingListMapper.ShoppingListDTOMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    public ResponseEntity<ShoppingListResponseDto>getShoppingListById(@PathVariable Long id) {
        var shoppingList = shoppingListService.getShoppingListById(id);
        if (shoppingList == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        var shoppingListDto = shoppingListDTOMapper.toShoppingListDto(shoppingList);
        return new ResponseEntity<>(shoppingListDto, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<ShoppingListResponseDto>createShoppingList(@RequestBody ShoppingListInputDto shoppingListInputDto){
        var shoppingListModel = shoppingListDTOMapper.createShoppingListModel(shoppingListInputDto);
        var newShoppingList = shoppingListService.createShoppingList(shoppingListModel);
        var shoppingListDto = shoppingListDTOMapper.toShoppingListDto(newShoppingList);
        return ResponseEntity.created(UrlHelper.getCurrentURLWithId(request, shoppingListDto.getId())).body(shoppingListDto);

    }

    // game wordt wel toegevoegd, maar is niet te zien in postman dus ergens gaat er iets fout
    @PostMapping("/{shoppingListId}/games/{gameId}")
    public ResponseEntity<String> addGameToShoppingList(@PathVariable Long shoppingListId, @PathVariable Long gameId) {
        shoppingListService.addGameToShoppingList(shoppingListId, gameId);
        return ResponseEntity.ok("Game successfully added to shopping list");
    }
}
