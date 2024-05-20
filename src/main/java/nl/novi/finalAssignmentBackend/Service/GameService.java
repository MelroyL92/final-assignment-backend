package nl.novi.finalAssignmentBackend.Service;


import jakarta.persistence.EntityNotFoundException;
import nl.novi.finalAssignmentBackend.Repository.GameRepository;
import nl.novi.finalAssignmentBackend.entities.Game;
import nl.novi.finalAssignmentBackend.exceptions.RecordNotFoundException;
import nl.novi.finalAssignmentBackend.helper.MaxPurchasePrice;
import nl.novi.finalAssignmentBackend.mappers.GameMappers.GameMapper;
import nl.novi.finalAssignmentBackend.model.GameModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GameService {


    private final GameRepository gameRepository;
    private final GameMapper gameMapper;
    private final MaxPurchasePrice maxPurchasePrice;

    public GameService(GameRepository gameRepository, GameMapper gameMapper, MaxPurchasePrice maxPurchasePrice) {
        this.gameRepository = gameRepository;
        this.gameMapper = gameMapper;
        this.maxPurchasePrice = maxPurchasePrice;
    }


    public List<GameModel> getGames(){
    return gameRepository.findAll().stream().map(gameMapper::fromEntity).collect(Collectors.toList());
    }

    public GameModel getGameById(Long id) {
        Game game = gameRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Movie not found with id: " + id));
        return gameMapper.fromEntity(game);
    }

    public List<GameModel>getGamesByPlatform(String platform){
        List<Game>games = gameRepository.findByPlatformContainingIgnoreCase(platform);

        if (games.isEmpty()) {
            throw new RecordNotFoundException("No games found for platform: " + platform);
        }
        return games.stream().map(gameMapper::fromEntity).collect(Collectors.toList());
    }

    public List<GameModel>getGameByName(String name){
        List<Game>games = gameRepository.findByNameContainingIgnoreCase(name);
        if (games.isEmpty()){
            throw new RecordNotFoundException("No games were found containing " + name);
        }

        return  games.stream().map(gameMapper::fromEntity).collect(Collectors.toList());
    }

    public GameModel createGame(GameModel gameModel) {
        Game game = gameMapper.toEntity(gameModel);

        double purchasePrice = game.getPurchasePrice();
        double sellingPrice = game.getSellingPrice();
        double validatedPurchasePrice = maxPurchasePrice.isPurchasePriceValid(purchasePrice, sellingPrice);

        game.setPurchasePrice(validatedPurchasePrice);
        game = gameRepository.save(game);

        return gameMapper.fromEntity(game);
    }


    public GameModel updateGame(Long id, GameModel gameModel) {
        Optional<Game> gameFound = gameRepository.findById(id);

        if (gameFound.isPresent()) {
            Game existingGame = gameFound.get();

            existingGame.setOriginalStock(gameModel.getOriginalStock());
            existingGame.setPlatform(gameModel.getPlatform());
            existingGame.setName(gameModel.getName());
            existingGame.setPlayDurationInMin(gameModel.getPlayDurationInMin());
            existingGame.setPublisher(gameModel.getPublisher());
            existingGame.setDescription(gameModel.getDescription());
            existingGame.setAmountSold(gameModel.getAmountSold());
            existingGame.setPurchasePrice(maxPurchasePrice.isPurchasePriceValid(gameModel.getPurchasePrice(),gameModel.getSellingPrice()));
            existingGame.setSellingPrice(gameModel.getSellingPrice());
            existingGame.setYearOfRelease(gameModel.getYearOfRelease());

            existingGame = gameRepository.save(existingGame);
            return gameMapper.fromEntity(existingGame);

        } else {
            throw new RecordNotFoundException("Game with ID " + id + " does not exist");
        }
    }

    public void deleteGame(Long id){
        gameRepository.deleteById(id);
    }

}
