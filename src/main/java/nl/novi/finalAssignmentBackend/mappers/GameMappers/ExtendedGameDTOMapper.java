package nl.novi.finalAssignmentBackend.mappers.GameMappers;


import nl.novi.finalAssignmentBackend.dtos.game.ExtendedGameResponseDTO;
import nl.novi.finalAssignmentBackend.model.GameModel;
import org.springframework.stereotype.Component;

@Component
public class ExtendedGameDTOMapper extends GameDTOMapper {

        public ExtendedGameResponseDTO toExtendedGameDto(GameModel game){
            return toExtendedGameDto(game, new ExtendedGameResponseDTO());
        }


        public <D extends ExtendedGameResponseDTO> D toExtendedGameDto(GameModel game, D target) {
            target = super.toGameDto(game, target);
            target.setPurchasePrice(game.getPurchasePrice());
            return target;
        }

}