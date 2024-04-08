package nl.novi.finalAssignmentBackend.mappers.ShoppingListMapper;


import nl.novi.finalAssignmentBackend.entities.Game;
import nl.novi.finalAssignmentBackend.entities.Movie;
import nl.novi.finalAssignmentBackend.entities.ShoppingList;
import nl.novi.finalAssignmentBackend.mappers.EntityMapper;
import nl.novi.finalAssignmentBackend.mappers.GameMappers.GameMapper;
import nl.novi.finalAssignmentBackend.mappers.MovieMappers.MovieMapper;
import nl.novi.finalAssignmentBackend.model.GameModel;
import nl.novi.finalAssignmentBackend.model.MovieModel;
import nl.novi.finalAssignmentBackend.model.ShoppingListModel;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class ShoppingListMapper implements EntityMapper<ShoppingListModel, ShoppingList> {


    private GameMapper gameMapper;
    private MovieMapper movieMapper;


    @Override
    public ShoppingListModel fromEntity(ShoppingList shoppingList) {
       if(shoppingList == null) {
           return null;

       }
       ShoppingListModel model = new ShoppingListModel();
       model.setSubtotal(shoppingList.getSubtotal());
       model.setId(shoppingList.getId());
       model.setType(shoppingList.getType());
       return model;
    }

    @Override
    public ShoppingList toEntity(ShoppingListModel shoppingListModel) {
        if (shoppingListModel == null) {
            return null;
        }
        ShoppingList entity = new ShoppingList();
        entity.setSubtotal(shoppingListModel.getSubtotal());
        entity.setId(shoppingListModel.getId());
        entity.setType(shoppingListModel.getType());
        return entity;
    }
}
