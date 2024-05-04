package nl.novi.finalAssignmentBackend.controllers;

import jakarta.servlet.http.HttpServletRequest;
import nl.novi.finalAssignmentBackend.Service.GameService;
import nl.novi.finalAssignmentBackend.dtos.game.ExtendedGameResponseDTO;
import nl.novi.finalAssignmentBackend.dtos.game.GameInputDTO;
import nl.novi.finalAssignmentBackend.dtos.game.GameResponseDTO;
import nl.novi.finalAssignmentBackend.helper.UrlHelper;
import nl.novi.finalAssignmentBackend.mappers.GameMappers.ExtendedGameDTOMapper;
import nl.novi.finalAssignmentBackend.mappers.GameMappers.GameDTOMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/games")
@RestController
public class GameController {

    private final GameDTOMapper gameDTOMapper;
    private final ExtendedGameDTOMapper extendedGameDTOMapper;
    private final GameService gameService;
    private final HttpServletRequest request;


    public GameController(GameDTOMapper gameDTOMapper, ExtendedGameDTOMapper extendedGameDTOMapper, GameService gameService, HttpServletRequest request) {
        this.gameDTOMapper = gameDTOMapper;
        this.extendedGameDTOMapper = extendedGameDTOMapper;
        this.gameService = gameService;
        this.request = request;
    }

    @GetMapping
    public ResponseEntity<List<GameResponseDTO>>getAllGames(){
        var games = gameService.getGames();
        var gameDTO = games.stream().map(gameDTOMapper::toGameDto).collect(Collectors.toList());
        return new ResponseEntity<>(gameDTO, HttpStatus.OK);
    }

    @GetMapping("/admin")
    public ResponseEntity<List<ExtendedGameResponseDTO>>getAllGamesAdmin(){
        var games = gameService.getGames();
        var extendedGameDto = games.stream().map(extendedGameDTOMapper::toExtendedGameDto).collect(Collectors.toList());
        return new ResponseEntity<>(extendedGameDto, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GameResponseDTO>getGameById(@PathVariable Long id){
        var game = gameService.getGameById(id);
        if (game == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        var gameDto =gameDTOMapper.toGameDto(game);
        return new ResponseEntity<>(gameDto,HttpStatus.OK);
    }

    @GetMapping("/admin/{id}")
    public ResponseEntity<ExtendedGameResponseDTO>getGameByIdAdmin(@PathVariable Long id){
        var game = gameService.getGameById(id);
        var extendedGameDTO = extendedGameDTOMapper.toExtendedGameDto(game);
        return new ResponseEntity<>(extendedGameDTO, HttpStatus.OK);
    }

    @GetMapping("/platform")
    public ResponseEntity<List<GameResponseDTO>>getGamesByGenre(@RequestParam String platform){
        var game= gameService.getGamesByPlatform(platform);
        var gameDto = gameDTOMapper.toGameDTOs(game);
        return new ResponseEntity<>(gameDto, HttpStatus.OK);
    }

    @GetMapping("/name")
    public ResponseEntity <List<GameResponseDTO>>getGamesByName(@RequestParam String name){
       var game = gameService.getGameByName(name);
       var gameDto = gameDTOMapper.toGameDTOs(game);
        return  new ResponseEntity<>(gameDto, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<GameResponseDTO>CreateGame(@RequestBody GameInputDTO gameInputDto){
        var gameModel = gameDTOMapper.createGameModel(gameInputDto);
        var newGame = gameService.createGame(gameModel);
        var gameDto = gameDTOMapper.toGameDto(newGame);
        return ResponseEntity.created(UrlHelper.getCurrentURLWithId(request, gameDto.getId())).body(gameDto);
    }

    @PutMapping("{id}")
    public ResponseEntity<GameResponseDTO>updateGame(@PathVariable Long id, @RequestBody GameInputDTO gameInputDto){
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