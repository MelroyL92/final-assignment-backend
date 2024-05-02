package nl.novi.finalAssignmentBackend.mappers.GameMappers;

import nl.novi.finalAssignmentBackend.entities.Game;
import nl.novi.finalAssignmentBackend.mappers.EntityMapper;
import nl.novi.finalAssignmentBackend.model.GameModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class GameMapper implements EntityMapper<GameModel, Game> {


    public GameModel fromEntity(Game entity) {
        if (entity == null) {
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
        model.setPurchasePrice(entity.getPurchasePrice());
        model.setAmountSold(entity.getAmountSold());
        model.setCurrentStock(entity.getCurrentStock());
        return model;
    }

    public List<Game> toEntity(List<GameModel> gameModels) {
        List<Game> entityList = new ArrayList<>();
        for (GameModel model : gameModels) {
            entityList.add(toEntity(model));
        }
        return entityList;
    }

    public List<GameModel> fromEntity(List<Game>games){
        List<GameModel> modelList = new ArrayList<>();
        for(Game game: games){
            modelList.add(fromEntity(game));
        }
        return  modelList;
    }

//    public List<Game> toEntity(List<GameModel> gameModels) {
//        List<Game> entityList = new ArrayList<>();
//        for (GameModel model : gameModels) {
//            Game game = new Game();
//            game.setId(model.getId());
//            game.setName(model.getName());
//            game.setDescription(model.getDescription());
//            game.setSellingPrice(model.getSellingPrice());
//            game.setYearOfRelease(model.getYearOfRelease());
//            game.setOriginalStock(model.getOriginalStock());
//            game.setPlatform(model.getPlatform());
//            game.setPlayDurationInMin(model.getPlayDurationInMin());
//            game.setPublisher(model.getPublisher());
//            game.setPurchasePrice(model.getPurchasePrice());
//            game.setAmountSold(model.getAmountSold());
//            game.setCurrentStock(model.getCurrentStock());
//            entityList.add(game);
//        }
//        return entityList;
//    }

    public Game toEntity(GameModel gameModel) {
        Game game = new Game();
        game.setId(gameModel.getId());
        game.setName(gameModel.getName());
        game.setDescription(gameModel.getDescription());
        game.setSellingPrice(gameModel.getSellingPrice());
        game.setYearOfRelease(gameModel.getYearOfRelease());
        game.setOriginalStock(gameModel.getOriginalStock());
        game.setPlatform(gameModel.getPlatform());
        game.setPlayDurationInMin(gameModel.getPlayDurationInMin());
        game.setPublisher(gameModel.getPublisher());
        game.setPurchasePrice(gameModel.getPurchasePrice());
        game.setAmountSold(gameModel.getAmountSold());
        game.setCurrentStock(gameModel.getCurrentStock());
        return game;
    }

}
