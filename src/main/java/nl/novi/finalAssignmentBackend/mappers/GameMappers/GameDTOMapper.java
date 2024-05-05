package nl.novi.finalAssignmentBackend.mappers.GameMappers;


import nl.novi.finalAssignmentBackend.dtos.game.GameInputDTO;
import nl.novi.finalAssignmentBackend.dtos.game.GameResponseDTO;
import nl.novi.finalAssignmentBackend.model.GameModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GameDTOMapper {


    public GameResponseDTO toGameDto(GameModel game){
            return toGameDto(game, new GameResponseDTO());
        }

        public <D extends GameResponseDTO> D toGameDto(GameModel game, D target) {
            target.setId(game.getId());
            target.setName(game.getName());
            target.setDescription(game.getDescription());
            target.setSellingPrice(game.getSellingPrice());
            target.setYearOfRelease(game.getYearOfRelease());
            target.setOriginalStock(game.getOriginalStock());
            target.setPlatform(game.getPlatform());
            target.setPlayDurationInMin(game.getPlayDurationInMin());
            target.setPublisher(game.getPublisher());
            target.setAmountSold(game.getAmountSold());
            target.setCurrentStock(game.getCurrentStock());
            return target;
        }

        public List<GameResponseDTO> toGameDTOs(List<GameModel>gameModels){
            List<GameResponseDTO> result = new ArrayList<>();
            for (GameModel gameModel: gameModels){
                result.add(toGameDto(gameModel));
            }
            return result;
        }

        public GameModel createGameModel(GameInputDTO dto) {
            var game = new GameModel();
            game.setId(dto.getId());
            game.setName(dto.getName());
            game.setSellingPrice(dto.getSellingPrice());
            game.setPurchasePrice(dto.getPurchasePrice());
            game.setYearOfRelease(dto.getYearOfRelease());
            game.setOriginalStock(dto.getOriginalStock());
            game.setAmountSold(dto.getAmountSold());
            game.setDescription(dto.getDescription());
            game.setPlatform(dto.getPlatform());
            game.setPlayDurationInMin(dto.getPlayDurationInMin());
            game.setPublisher(dto.getPublisher());
            game.setCurrentStock(dto.getCurrentStock());


            return game;
        }


}

