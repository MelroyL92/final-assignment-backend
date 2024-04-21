package nl.novi.finalAssignmentBackend.mappers.GameMappers;

import nl.novi.finalAssignmentBackend.entities.Game;
import nl.novi.finalAssignmentBackend.mappers.EntityMapper;
import nl.novi.finalAssignmentBackend.model.GameModel;
import org.springframework.stereotype.Component;


@Component
public class GameMapper implements EntityMapper<GameModel, Game> {


    @Override
    public GameModel fromEntity(Game entity) {
        if (entity == null){
            return null;
        }
        GameModel model = new GameModel();
        model.setId(entity.getId());
        model.setName(entity.getName());
        model.setDescription(entity.getDescription());
        model.setSellingPrice(entity.getSellingPrice());
        model.setYearOfRelease(entity.getYearOfRelease());
        model.setOriginalStock(entity.getOriginalStock());
        model.setPlatform(entity.getPlatform());
        model.setPlayDurationInMin(entity.getPlayDurationInMin());
        model.setPublisher(entity.getPublisher());
        model.setAmountSold(entity.getAmountSold());
        model.setPurchasePrice(entity.getPurchasePrice());
        model.setCurrentStock(entity.getCurrentStock());
        return model;
    }


    @Override
    public Game toEntity(GameModel model) {
        if (model == null){
            return null;
        }
        Game entity = new Game();
        entity.setId(model.getId());
        entity.setName(model.getName());
        entity.setDescription(model.getDescription());
        entity.setSellingPrice(model.getSellingPrice());
        entity.setYearOfRelease(model.getYearOfRelease());
        entity.setOriginalStock(model.getOriginalStock());
        entity.setPlatform(model.getPlatform());
        entity.setPlayDurationInMin(model.getPlayDurationInMin());
        entity.setPublisher(model.getPublisher());
        entity.setPurchasePrice(model.getPurchasePrice());
        entity.setAmountSold(model.getAmountSold());
        entity.setCurrentStock(model.getCurrentStock());
        return entity;
    }
}
