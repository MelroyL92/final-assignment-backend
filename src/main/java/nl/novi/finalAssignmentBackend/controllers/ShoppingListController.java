package nl.novi.finalAssignmentBackend.controllers;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import nl.novi.finalAssignmentBackend.Service.ShoppingListService;
import nl.novi.finalAssignmentBackend.dtos.shoppingList.ShoppingListInputDTO;
import nl.novi.finalAssignmentBackend.dtos.shoppingList.ShoppingListResponseDTO;
import nl.novi.finalAssignmentBackend.dtos.game.GameResponseDTO;
import nl.novi.finalAssignmentBackend.dtos.movie.MovieResponseDTO;
import nl.novi.finalAssignmentBackend.helper.UrlHelper;
import nl.novi.finalAssignmentBackend.mappers.ShoppingListMapper.ShoppingListDTOMapper;
import nl.novi.finalAssignmentBackend.model.ShoppingListModel;
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

    @GetMapping("/admin")
    public ResponseEntity<List<ShoppingListResponseDTO>> getAllShoppingLists() {
        var shoppingList = shoppingListService.getAllShoppingLists();
        var shoppingListDTO = shoppingList.stream().map(shoppingListDTOMapper::toShoppingListDto).collect(Collectors.toList());
        return new ResponseEntity<>(shoppingListDTO, HttpStatus.OK);
    }

    @GetMapping("/{id}/user/{username}")
    public ResponseEntity<ShoppingListResponseDTO> getShoppingListById(@PathVariable Long id, @PathVariable String username) {
        var shoppingList = shoppingListService.getShoppingListById(id, username);
        if (shoppingList == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        var shoppingListDto = shoppingListDTOMapper.toShoppingListDto(shoppingList);
        return new ResponseEntity<>(shoppingListDto, HttpStatus.OK);
    }

        @GetMapping("/{shoppingListId}/user/{username}/games/{gameId}")
        public ResponseEntity<List<GameResponseDTO>>searchGameFromShoppingList(@PathVariable Long shoppingListId, @PathVariable String username, @PathVariable Long gameId){
            var gameInList = shoppingListService.getGameFromShoppingList(shoppingListId, username, gameId);
            if(gameInList == null){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(gameInList,HttpStatus.OK);
        }

        @GetMapping("{shoppingListId}/user/{username}/movies/{movieId}")
        public ResponseEntity<List<MovieResponseDTO>>searchMovieFromShoppingList(@PathVariable Long shoppingListId, @PathVariable String username, @PathVariable Long movieId){
            var movieInList = shoppingListService.getMovieFromShoppingList(shoppingListId, username, movieId);
             if (movieInList == null){
             return new ResponseEntity<>(HttpStatus.NOT_FOUND);
          }
             return new ResponseEntity<>(movieInList,HttpStatus.OK);
        }

    @PostMapping("")
    public ResponseEntity<ShoppingListModel>createShoppingList(@Valid @RequestBody ShoppingListInputDTO shoppingListInputDto){
        var shoppingListModel = shoppingListDTOMapper.createShoppingListModel(shoppingListInputDto);
        var newShoppingList = shoppingListService.createShoppingList(shoppingListModel);
        return ResponseEntity.created(UrlHelper.getCurrentURLWithId(request, newShoppingList.getId())).body(newShoppingList);

    }


    @PostMapping("/{shoppingListId}/user/{username}/games/{gameId}")
    public ResponseEntity<String> addGameToShoppingList(@PathVariable Long shoppingListId, @PathVariable String username, @PathVariable Long gameId) {
        shoppingListService.addGameToShoppingList(shoppingListId, username, gameId);
        return ResponseEntity.ok("Game successfully added to shopping list");
    }


    @PostMapping("/{shoppingListId}/user/{username}/movies/{movieId}")
    public ResponseEntity<String>addMovieToShoppingList(@PathVariable Long shoppingListId,@PathVariable String username, @PathVariable Long movieId){
        shoppingListService.addMovieToShoppingList(shoppingListId,username, movieId);
        return ResponseEntity.ok("Movie successfully added to shopping list");
    }

    @PutMapping("/{id}/user/{username}")
    public ResponseEntity<ShoppingListResponseDTO>UpdateShoppingList(@PathVariable Long id, @PathVariable String username, @Valid @RequestBody ShoppingListInputDTO shoppingListInputDto){
        var updateShoppingList = shoppingListService.updateShoppingList(id, username, shoppingListDTOMapper.createShoppingListModel(shoppingListInputDto));
        var shoppingListDto = shoppingListDTOMapper.toShoppingListDto(updateShoppingList);
        return new ResponseEntity<>(shoppingListDto, HttpStatus.OK);
    }


    @DeleteMapping("/{shoppingListId}/user/{username}/games/{gameId}")
    public ResponseEntity<Object>deleteGameFromShoppingList(@PathVariable Long shoppingListId, @PathVariable String username, @PathVariable Long gameId){
        shoppingListService.deleteGameWithinShoppingList(username, shoppingListId, gameId);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{shoppingListId}/user/{username}/movies/{movieId}")
    public ResponseEntity<Object>removeMovieFromWishlist(@PathVariable String username, @PathVariable Long shoppingListId, @PathVariable Long movieId){
        shoppingListService.deleteMovieWithinShoppingList(username, shoppingListId,movieId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}/user/{username}")
    public ResponseEntity<Object>deleteShoppingList(@PathVariable Long id, @PathVariable String username){
        shoppingListService.deleteShoppingList(id, username);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}/admin")
    public ResponseEntity<Object>deleteShoppingList(@PathVariable Long id){
        shoppingListService.deleteShoppingList(id);
        return ResponseEntity.noContent().build();
    }



}
