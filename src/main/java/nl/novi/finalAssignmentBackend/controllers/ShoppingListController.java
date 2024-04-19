package nl.novi.finalAssignmentBackend.controllers;


import jakarta.servlet.http.HttpServletRequest;
import nl.novi.finalAssignmentBackend.Service.ShoppingListService;
import nl.novi.finalAssignmentBackend.dtos.ShoppingList.ShoppingListInputDto;
import nl.novi.finalAssignmentBackend.dtos.ShoppingList.ShoppingListResponseDto;
import nl.novi.finalAssignmentBackend.dtos.game.GameResponseDto;
import nl.novi.finalAssignmentBackend.dtos.movie.MovieResponseDto;
import nl.novi.finalAssignmentBackend.helper.UrlHelper;
import nl.novi.finalAssignmentBackend.mappers.ShoppingListMapper.ShoppingListDTOMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/shoppinglists")
public class ShoppingListController {

    private final ShoppingListDTOMapper shoppingListDTOMapper;
    private final ShoppingListService shoppingListService;
    private final HttpServletRequest request;

    public ShoppingListController(ShoppingListDTOMapper shoppingListDTOMapper, ShoppingListService shoppingListService, HttpServletRequest request) {
        this.shoppingListDTOMapper = shoppingListDTOMapper;
        this.shoppingListService = shoppingListService;
        this.request = request;
    }

    @GetMapping
    public ResponseEntity<List<ShoppingListResponseDto>> getAllShoppingLists() {
        var shoppingList = shoppingListService.getShoppingList();
        var shoppingListDTO = shoppingList.stream().map(shoppingListDTOMapper::toShoppingListDto).collect(Collectors.toList());

        return new ResponseEntity<>(shoppingListDTO, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShoppingListResponseDto> getShoppingListById(@PathVariable Long id) {
        var shoppingList = shoppingListService.getShoppingListById(id);
        if (shoppingList == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        var shoppingListDto = shoppingListDTOMapper.toShoppingListDto(shoppingList);
        return new ResponseEntity<>(shoppingListDto, HttpStatus.OK);
    }

        @GetMapping("/{shoppingListId}/games/{gameId}")
        public ResponseEntity<List<GameResponseDto>>searchGameFromShoppingList(@PathVariable Long shoppingListId, @PathVariable Long gameId){
            var gameInList = shoppingListService.getGameFromShoppingList(shoppingListId, gameId);
            if(gameInList == null){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(gameInList,HttpStatus.OK);
        }

        @GetMapping("{shoppingListId}/movies/{movieId}")
        public ResponseEntity<List<MovieResponseDto>>searchMovieFromShoppingList(@PathVariable Long shoppingListId, @PathVariable Long movieId){
            var movieInList = shoppingListService.getMovieFromShoppingList(shoppingListId, movieId);
             if (movieInList == null){
             return new ResponseEntity<>(HttpStatus.NOT_FOUND);
          }
             return new ResponseEntity<>(movieInList,HttpStatus.OK);
        }

    @PostMapping("")
    public ResponseEntity<ShoppingListResponseDto>createShoppingList(@RequestBody ShoppingListInputDto shoppingListInputDto){
        var shoppingListModel = shoppingListDTOMapper.createShoppingListModel(shoppingListInputDto);
        var newShoppingList = shoppingListService.createShoppingList(shoppingListModel);
        var shoppingListDto = shoppingListDTOMapper.toShoppingListDto(newShoppingList);
        return ResponseEntity.created(UrlHelper.getCurrentURLWithId(request, shoppingListDto.getId())).body(shoppingListDto);

    }


    @PutMapping("/{shoppingListId}/games/{gameId}")
    public ResponseEntity<String> addGameToShoppingList(@PathVariable Long shoppingListId, @PathVariable Long gameId) {
        shoppingListService.addGameToShoppingList(shoppingListId, gameId);
        return ResponseEntity.ok("Game successfully added to shopping list");
    }

    @PutMapping("/{shoppingListId}/movies/{movieId}")
    public ResponseEntity<String>addMovieToShoppingList(@PathVariable Long shoppingListId, @PathVariable Long movieId){
        shoppingListService.addMovieToShoppingList(shoppingListId,movieId);
        return ResponseEntity.ok("Movie successfully added to shopping list");
    }

    @PutMapping("/{id}")
    public ResponseEntity<ShoppingListResponseDto>UpdateShoppingList(@PathVariable Long id, @RequestBody ShoppingListInputDto shoppingListInputDto){
        var updateShoppingList = shoppingListService.updateShoppingList(id, shoppingListDTOMapper.createShoppingListModel(shoppingListInputDto));
        var shoppingListDto = shoppingListDTOMapper.toShoppingListDto(updateShoppingList);
        return new ResponseEntity<>(shoppingListDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object>deleteShoppingList(@PathVariable Long id){
        shoppingListService.deleteShoppingList(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{shoppingListId}/games/{gameId}")
    public ResponseEntity<Object>deleteGameFromShoppingList(@PathVariable Long shoppingListId,@PathVariable Long gameId){
        shoppingListService.deleteGameWithinShoppingList(gameId, shoppingListId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{shoppingListId}/movies/{movieId}")
    public ResponseEntity<Object>removieMovieFromWishlist(@PathVariable Long shoppingListId, @PathVariable Long movieId){
        shoppingListService.deleteMovieWithinShoppingList(shoppingListId,movieId);
        return ResponseEntity.noContent().build();
    }



}
