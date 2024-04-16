package nl.novi.finalAssignmentBackend.controllers;


import nl.novi.finalAssignmentBackend.Service.GameService;
import nl.novi.finalAssignmentBackend.dtos.game.GameInputDto;
import nl.novi.finalAssignmentBackend.dtos.game.GameResponseDto;
import nl.novi.finalAssignmentBackend.mappers.GameMappers.GameDTOMapper;
import nl.novi.finalAssignmentBackend.model.GameModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/games")
@RestController

public class GameController {

    private final GameDTOMapper gameDTOMapper;
    private final GameService gameService;


    public GameController(GameDTOMapper gameDTOMapper, GameService gameService) {
        this.gameDTOMapper = gameDTOMapper;
        this.gameService = gameService;
    }

    @GetMapping
    public ResponseEntity<List<GameResponseDto>>getAllGames(){
        var games = gameService.getGames();
        var gameDTO = games.stream().map(gameDTOMapper::toGameDto).collect(Collectors.toList());
        return new ResponseEntity<>(gameDTO, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GameResponseDto>getGameById(@PathVariable Long id){
        var game = gameService.getGameById(id);
        if (game == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        var gameDto =gameDTOMapper.toGameDto(game);
        return new ResponseEntity<>(gameDto,HttpStatus.OK);
    }

    // what to return when a non valid param is entered?
    @GetMapping("/platform")
    public List<GameModel>getGamesByGenre(@RequestParam String platform){
        List<GameModel>games=gameService.getGamesByPlatform(platform);
        return ResponseEntity.ok(games).getBody();
    }

    @PostMapping("")
    public ResponseEntity<GameResponseDto>CreateGame(@RequestBody GameInputDto gameInputDto){
        var gameModel = gameDTOMapper.createGameModel(gameInputDto);
        var newGame = gameService.createGame(gameModel);
        var gameDto = gameDTOMapper.toGameDto(newGame);
        return ResponseEntity.created(URI.create("/movies/" + newGame.getId()))
                .body(gameDto);
    }

    @PutMapping("{id}")
    public ResponseEntity<GameResponseDto>updateGame(@PathVariable Long id, @RequestBody GameInputDto gameInputDto){
        var updateGame = gameService.updateGame(id, gameDTOMapper.createGameModel(gameInputDto));
        var gameDto = gameDTOMapper.toGameDto(updateGame);
        return new ResponseEntity<>(gameDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object>deleteGame(@PathVariable Long id){
        gameService.deleteGame(id);
        return ResponseEntity.noContent().build();
    }


}
