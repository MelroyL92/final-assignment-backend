package nl.novi.finalAssignmentBackend.Service;


import jakarta.persistence.EntityNotFoundException;
import nl.novi.finalAssignmentBackend.Repository.GameRepository;
import nl.novi.finalAssignmentBackend.Repository.ShoppingListRepository;
import nl.novi.finalAssignmentBackend.entities.Game;
import nl.novi.finalAssignmentBackend.entities.ShoppingList;
import nl.novi.finalAssignmentBackend.mappers.GameMappers.GameMapper;
import nl.novi.finalAssignmentBackend.mappers.ShoppingListMapper.ShoppingListMapper;
import nl.novi.finalAssignmentBackend.model.ShoppingListModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShoppingListService {

    private final ShoppingListMapper shoppingListMapper;
    private final ShoppingListRepository shoppingListRepository;
    private final GameMapper gameMapper;
    private final GameRepository gameRepository;


    public ShoppingListService(ShoppingListMapper shoppingListMapper, ShoppingListRepository shoppingListRepository, GameMapper gameMapper, GameRepository gameRepository) {
        this.shoppingListMapper = shoppingListMapper;
        this.shoppingListRepository = shoppingListRepository;
        this.gameMapper = gameMapper;
        this.gameRepository = gameRepository;
    }


    public List<ShoppingListModel> getShoppingList(){
        return shoppingListRepository.findAll().stream().map(shoppingListMapper::fromEntity).collect(Collectors.toList());
    }

    public ShoppingListModel getShoppingListById(Long id){
        ShoppingList shoppingList = shoppingListRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Shoppinglist not found with id: " + id));
        return shoppingListMapper.fromEntity(shoppingList);
    }

    public ShoppingListModel createShoppingList(ShoppingListModel shoppingListModel){
        ShoppingList shoppingList = shoppingListMapper.toEntity(shoppingListModel);
        shoppingList = shoppingListRepository.save(shoppingList);
        return shoppingListMapper.fromEntity(shoppingList);
    }



    // stil a big questionmark? Doesnt make much sense at the moment/ also after it works make sure it goes through the dto to not give unwanted information back to the client!
    public void addGameToShoppingList(Long shoppingListId, Long gameId) {
        ShoppingList shoppingList = shoppingListRepository.findById(shoppingListId)
                .orElseThrow(() -> new EntityNotFoundException("Shopping list not found"));

        Game game = gameRepository.findById(gameId)
                .orElseThrow(() -> new EntityNotFoundException("Game not found"));

        shoppingList.getGames().add(game);

        shoppingListRepository.save(shoppingList);
    }



}










