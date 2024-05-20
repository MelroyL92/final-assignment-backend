package nl.novi.finalAssignmentBackend.helpers;

import nl.novi.finalAssignmentBackend.entities.Game;
import nl.novi.finalAssignmentBackend.model.GameModel;

public class ServiceHelperGameCreation {

    public static Game createGame(
            Long id,
            String platform,
            String publisher,
            Integer playDurationInMin,
            String name,
            Integer originalStock,
            Integer amountSold,
            Integer currentStock,
            Double sellingPrice,
            String description,
            Double purchasePrice,
            Integer yearOfRelease
            ){
        Game game = new Game();
            game.setId(id);
            game.setPlatform(platform);
            game.setPublisher(publisher);
            game.setPlayDurationInMin(playDurationInMin);
            game.setName(name);
            game.setOriginalStock(originalStock);
            game.setAmountSold(amountSold);
            game.setCurrentStock(currentStock);
            game.setSellingPrice(sellingPrice);
            game.setDescription(description);
            game.setPurchasePrice(purchasePrice);
            game.setYearOfRelease(yearOfRelease);
            return game;
    }


    public static GameModel createGameModel(
            String platform,
            String publisher,
            Integer playDurationInMin,
            String name,
            Integer originalStock,
            Integer amountSold,
            Integer currentStock,
            Double sellingPrice,
            String description,
            Double purchasePrice,
            Integer yearOfRelease
    ){
        GameModel gameModel = new GameModel();
        gameModel.setPlatform(platform);
        gameModel.setPublisher(publisher);
        gameModel.setPlayDurationInMin(playDurationInMin);
        gameModel.setName(name);
        gameModel.setOriginalStock(originalStock);
        gameModel.setAmountSold(amountSold);
        gameModel.setCurrentStock(currentStock);
        gameModel.setSellingPrice(sellingPrice);
        gameModel.setDescription(description);
        gameModel.setPurchasePrice(purchasePrice);
        gameModel.setYearOfRelease(yearOfRelease);
        return gameModel;
    }
}
