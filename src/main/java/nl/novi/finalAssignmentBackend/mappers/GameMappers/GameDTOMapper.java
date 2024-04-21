package nl.novi.finalAssignmentBackend.mappers.GameMappers;


import nl.novi.finalAssignmentBackend.dtos.game.GameInputDto;
import nl.novi.finalAssignmentBackend.dtos.game.GameResponseDto;
import nl.novi.finalAssignmentBackend.model.GameModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GameDTOMapper {

        public GameResponseDto toGameDto(GameModel game){
            return toGameDto(game, new GameResponseDto());
        }

        public <D extends GameResponseDto> D toGameDto(GameModel game, D target) {
            target.setId(game.getId());
            target.setName(game.getName());
            target.setDescription(game.getDescription());
            target.setSellingPrice(game.getSellingPrice());
            target.setYearOfRelease(game.getYearOfRelease());
            target.setOriginalStock(game.getOriginalStock());
            target.setPlatform(game.getPlatform());
            target.setPlayDurationInMin(game.getPlayDurationInMin());
            target.setPublisher(game.getPublisher());
            target.setCurrentStock(game.getCurrentStock());
            return target;
        }

        public List<GameResponseDto> toGameDTOs(List<GameModel>gameModels){
            List<GameResponseDto> result = new ArrayList<>();
            for (GameModel gameModel: gameModels){
                result.add(toGameDto(gameModel));
            }

            return result;
        }


        public GameModel createGameModel(GameInputDto dto) {
            var game = new GameModel();
            game.setId(dto.getId());
            game.setName(dto.getName());
            game.setSellingPrice(dto.getSellingPrice());
            game.setPurchasePrice(dto.getSellingPrice());
            game.setYearOfRelease(dto.getYearOfRelease());
            game.setAmountSold(dto.getAmountSold());
            game.setDescription(dto.getDescription());
//            game.setOriginalStock(dto.getOriginalStock());
            game.setPlatform(dto.getPlatform());
            game.setPlayDurationInMin(dto.getPlayDurationInMin());
            game.setPublisher(dto.getPublisher());
            game.setCurrentStock(dto.getCurrentStock());
            return game;
        }


}

