package nl.novi.finalAssignmentBackend.Service;


import jakarta.persistence.EntityNotFoundException;
import nl.novi.finalAssignmentBackend.Repository.ShoppingListRepository;
import nl.novi.finalAssignmentBackend.entities.ShoppingList;
import nl.novi.finalAssignmentBackend.mappers.ShoppingListMapper.ShoppingListMapper;
import nl.novi.finalAssignmentBackend.model.ShoppingListModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShoppingListService {

    private final ShoppingListMapper shoppingListMapper;
    private final ShoppingListRepository shoppingListRepository;


    public ShoppingListService(ShoppingListMapper shoppingListMapper, ShoppingListRepository shoppingListRepository) {
        this.shoppingListMapper = shoppingListMapper;
        this.shoppingListRepository = shoppingListRepository;
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









}
