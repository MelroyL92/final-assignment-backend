package nl.novi.finalAssignmentBackend.Service;


import jakarta.persistence.EntityNotFoundException;
import nl.novi.finalAssignmentBackend.Repository.GameRepository;
import nl.novi.finalAssignmentBackend.entities.Game;
import nl.novi.finalAssignmentBackend.exceptions.RecordNotFoundException;
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

    public GameService(GameRepository gameRepository, GameMapper gameMapper) {
        this.gameRepository = gameRepository;
        this.gameMapper = gameMapper;
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

    public GameModel createGame (GameModel gameModel){
        Game game = gameMapper.toEntity(gameModel);
        game = gameRepository.save(game);
        return gameMapper.fromEntity(game);
    }

    public GameModel updateGame(Long id, GameModel gameModel) {
        Optional<Game> gameFound = gameRepository.findById(id);
        if(gameFound.isPresent()){

            Game existingGame = gameFound.get();
            existingGame.setOriginalStock(gameModel.getOriginalStock());
            existingGame.setPlatform(gameModel.getPlatform());
            existingGame.setName(gameModel.getName());
            existingGame.setPlayDuration(gameModel.getPlayDuration());
            existingGame.setPublisher(gameModel.getPublisher());
            existingGame.setDescription(gameModel.getDescription());
            existingGame.setAmountSold(gameModel.getAmountSold());
            existingGame.setPlayDuration(gameModel.getPlayDuration());
            existingGame.setSellingPrice(gameModel.getSellingPrice());
            existingGame.setPurchasePrice(gameModel.getPurchasePrice());
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
