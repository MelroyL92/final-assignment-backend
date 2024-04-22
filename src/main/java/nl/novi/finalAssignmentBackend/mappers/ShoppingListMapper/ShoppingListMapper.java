package nl.novi.finalAssignmentBackend.mappers.ShoppingListMapper;

import nl.novi.finalAssignmentBackend.entities.ShoppingList;
import nl.novi.finalAssignmentBackend.mappers.EntityMapper;
import nl.novi.finalAssignmentBackend.mappers.GameMappers.GameMapper;
import nl.novi.finalAssignmentBackend.mappers.MovieMappers.MovieMapper;
import nl.novi.finalAssignmentBackend.model.ShoppingListModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class ShoppingListMapper implements EntityMapper<ShoppingListModel, ShoppingList> {

    private final GameMapper gameMapper;
    private final MovieMapper movieMapper;


    public ShoppingListMapper(GameMapper gameMapper, MovieMapper movieMapper) {
        this.gameMapper = gameMapper;
        this.movieMapper = movieMapper;
    }


    @Override
    public ShoppingListModel fromEntity(ShoppingList shoppingList) {
       if(shoppingList == null) {
           return null;

       }
       ShoppingListModel model = new ShoppingListModel();
       model.setSubtotal(shoppingList.getSubtotal());
       model.setId(shoppingList.getId());
       model.setType(shoppingList.getType());
       model.setGames(gameMapper.fromEntity(shoppingList.getGames()));
       model.setMovies(movieMapper.fromEntity(shoppingList.getMovies()));
       model.setDeliverCost(shoppingList.getDeliveryCost());
       model.setPackaging(shoppingList.getPackaging());
       model.setAtHomeDelivery(shoppingList.getAtHomeDelivery());
       model.setPackagingCost(shoppingList.getPackagingCost());
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
        entity.setGames(gameMapper.toEntity(shoppingListModel.getGames()));
        entity.setMovies(movieMapper.toEntityList(shoppingListModel.getMovies()));
        entity.setDeliveryCost(shoppingListModel.getDeliverCost());
        entity.setPackaging(shoppingListModel.getPackaging());
        entity.setAtHomeDelivery(shoppingListModel.getAtHomeDelivery());
        entity.setPackagingCost(shoppingListModel.getPackagingCost());
        return entity;
    }


    public List<ShoppingList> toEntity(List<ShoppingListModel> shoppingListModels) {
        List<ShoppingList> entityList = new ArrayList<>();
        for (ShoppingListModel model : shoppingListModels) {
            entityList.add(toEntity(model));
        }
        return entityList;
    }

    public List<ShoppingListModel>fromEntity(List<ShoppingList>shoppingLists){
        List<ShoppingListModel>modelList= new ArrayList<>();
            for (ShoppingList shoppingList : shoppingLists){
                modelList.add(fromEntity(shoppingList));
        }
            return  modelList;
    }
}
