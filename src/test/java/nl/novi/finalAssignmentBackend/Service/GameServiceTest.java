package nl.novi.finalAssignmentBackend.Service;
import nl.novi.finalAssignmentBackend.Repository.GameRepository;
import nl.novi.finalAssignmentBackend.Repository.UserRepository;
import nl.novi.finalAssignmentBackend.entities.Game;
import nl.novi.finalAssignmentBackend.exceptions.RecordNotFoundException;
import nl.novi.finalAssignmentBackend.helper.MaxPurchasePrice;
import nl.novi.finalAssignmentBackend.mappers.GameMappers.GameMapper;
import nl.novi.finalAssignmentBackend.model.GameModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GameServiceTest {

    @Mock
    private GameRepository gameRepository;

    @Mock
    private GameMapper gameMapper;

    @Mock
    private UserRepository userRepository;

    @Mock
    MaxPurchasePrice maxPurchasePrice;


    @InjectMocks
    private GameService gameService;


    @BeforeEach
    public void setUp() {

    }


    @Test
    @DisplayName("get all games")
    public void testGetGames() {
        List<Game> mockGames = new ArrayList<>();

        Game game1 = new Game();
        game1.setId(1L);
        game1.setPlatform("Platform 1");
        game1.setPublisher("Publisher 1");
        game1.setPlayDurationInMin(60);
        game1.setName("name");
        game1.setOriginalStock(100);
        game1.setAmountSold(50);
        game1.setCurrentStock(50);
        game1.setSellingPrice(40.0);
        game1.setDescription("dfafafafafasasfafsasf");
        game1.setPurchasePrice(10.00);
        game1.setYearOfRelease(2002);
        mockGames.add(game1);

        Game game2 = new Game();
        game2.setId(2L);
        game2.setPlatform("Platform 2");
        game2.setPublisher("Publisher 2");
        game2.setPlayDurationInMin(60);
        game2.setName("name2");
        game2.setOriginalStock(100);
        game2.setAmountSold(50);
        game2.setCurrentStock(50);
        game2.setSellingPrice(40.0);
        game2.setDescription("wehlfbsdgbhsldgskdglsdgsdg");
        game2.setPurchasePrice(20.0);
        game2.setYearOfRelease(2001);
        mockGames.add(game2);

        Mockito.when(gameRepository.findAll()).thenReturn(mockGames);

        Mockito.when(gameMapper.fromEntity((Game) ArgumentMatchers.any())).thenAnswer(invocation -> {
            Game game = invocation.getArgument(0);
            if (game == null) {
                return null;
            }
            GameModel gameModel = new GameModel();
            gameModel.setId(game.getId());
            gameModel.setName(game.getName());
            gameModel.setDescription(game.getDescription());
            gameModel.setSellingPrice(game.getSellingPrice());
            gameModel.setYearOfRelease(game.getYearOfRelease());
            gameModel.setOriginalStock(game.getOriginalStock());
            gameModel.setPlatform(game.getPlatform());
            gameModel.setPlayDurationInMin(game.getPlayDurationInMin());
            gameModel.setPublisher(game.getPublisher());
            gameModel.setPurchasePrice(game.getPurchasePrice());

            Integer amountSold = game.getAmountSold();
            if (amountSold != null) {
                gameModel.setAmountSold(amountSold);
                gameModel.setCurrentStock(game.getOriginalStock() - amountSold);
            } else {
                gameModel.setAmountSold(0);
                gameModel.setCurrentStock(game.getOriginalStock());
            }

            return gameModel;
        });

        List<GameModel> result = gameService.getGames();


        assertEquals(2, result.size());
        assertEquals("Platform 1", result.get(0).getPlatform());
        assertEquals("Publisher 1", result.get(0).getPublisher());
        assertEquals(60, result.get(0).getPlayDurationInMin());
        assertEquals("name", result.get(0).getName());
        assertEquals("dfafafafafasasfafsasf", result.get(0).getDescription());
        assertEquals(10.00, result.get(0).getPurchasePrice());
        assertEquals(50, result.get(0).getCurrentStock());
        assertEquals(50, result.get(0).getAmountSold());
        assertEquals(40.0, result.get(0).getSellingPrice());

        assertEquals("Platform 2", result.get(1).getPlatform());
        assertEquals("Publisher 2", result.get(1).getPublisher());
        assertEquals(60, result.get(1).getPlayDurationInMin());
        assertEquals("name2", result.get(1).getName());
        assertEquals("wehlfbsdgbhsldgskdglsdgsdg", result.get(1).getDescription());
        assertEquals(20.0, result.get(1).getPurchasePrice());
        assertEquals(50, result.get(1).getCurrentStock());
        assertEquals(50, result.get(1).getAmountSold());
        assertEquals(40.0, result.get(1).getSellingPrice());
    }


    @Test
    @DisplayName("get gamebyID")
    public void testGetGameById() {
        Game game1 = new Game();
        game1.setId(1L);
        game1.setPlatform("Platform 1");
        game1.setPublisher("Publisher 1");
        game1.setYearOfRelease(2007);
        game1.setSellingPrice(60.00);
        game1.setCurrentStock(100);
        game1.setName("some game");
        game1.setPlayDurationInMin(100);
        game1.setPurchasePrice(100.00);
        game1.setAmountSold(100);

        game1.setPlayDurationInMin(60);

        when(gameRepository.findById(1L)).thenReturn(Optional.of(game1));

        Mockito.when(gameMapper.fromEntity((Game) ArgumentMatchers.any())).thenAnswer(invocation -> {
            Game game = invocation.getArgument(0);
            GameModel gameModel = new GameModel();
            gameModel.setId(game.getId());
            gameModel.setPlatform(game.getPlatform());
            gameModel.setPublisher(game.getPublisher());
            gameModel.setPlayDurationInMin(game.getPlayDurationInMin());
            gameModel.setName(game.getName());
            return gameModel;
        });

        GameModel result = gameService.getGameById(1L);

        assertEquals(1L, result.getId());
        assertEquals("Platform 1", result.getPlatform());
        assertEquals("Publisher 1", result.getPublisher());
        assertEquals(60, result.getPlayDurationInMin());
    }


    @Test
    @DisplayName("platform Exception test")
    public void testGamesByPlatformNoGamesFound() {

        List<Game> mockGames = new ArrayList<>();
        Game game1 = new Game();
        game1.setPlatform("pc");
        mockGames.add(game1);

        Mockito.when(gameRepository.findByPlatformContainingIgnoreCase(Mockito.anyString())).thenAnswer(invocation -> {
            String platform = invocation.getArgument(0);
            if ("playstation".equalsIgnoreCase(platform)) {
                return Collections.emptyList();
            } else {
                return mockGames;
            }
        });


        assertThrows(RecordNotFoundException.class, () -> {
            gameService.getGamesByPlatform("playstation");
        });
    }


    @Test
    @DisplayName("get games bij platform")
    public void testGetGamesByPlatform() {
        List<Game> mockGames = new ArrayList<>();
        Game game1 = new Game();
        game1.setPlatform("pc");
        mockGames.add(game1);

        Game game2 = new Game();
        game2.setPlatform("Playstation");
        mockGames.add(game2);

        Mockito.when(gameRepository.findByPlatformContainingIgnoreCase("pc")).thenReturn(mockGames);

        List<GameModel> result = gameService.getGamesByPlatform("pc");

        assertFalse(result.isEmpty());
        assertEquals(mockGames.size(), result.size());
        verify(gameRepository).findByPlatformContainingIgnoreCase(argThat(arg -> arg.equalsIgnoreCase("pc")));
        verifyNoMoreInteractions(gameRepository);

    }

    @Test
    @DisplayName("find game by name no games found")
    public void testFindGamesByNameNoFound(){
        List<Game>mockGames = new ArrayList<>();
        Game game1 = new Game();
        game1.setName("pokemon");
        mockGames.add(game1);

        Mockito.when(gameRepository.findByNameContainingIgnoreCase(Mockito.anyString())).thenAnswer(invocation -> {
            String name = invocation.getArgument(0);
            if ("Star trek".equalsIgnoreCase(name)) {
                return Collections.emptyList();
            } else {
                return mockGames;
            }
        });
        assertThrows(RecordNotFoundException.class, () -> {
            gameService.getGameByName("Star Trek");
        });
    }

    @Test
    @DisplayName("find game by name")
    public void testFindGamesByName(){
        List<Game>mockGames = new ArrayList<>();
        Game game1 = new Game();
        game1.setName("pokemon");
        mockGames.add(game1);

        Game game2 = new Game();
        game2.setName("pokemon1");
        mockGames.add(game2);

        Game game3 = new Game();
        game3.setName("pokemon2");
        mockGames.add(game3);

        Game game4 = new Game();
        game2.setName("pokemon3");
        mockGames.add(game4);

        Mockito.when(gameRepository.findByNameContainingIgnoreCase("pokemon")).thenReturn(mockGames);

        List<GameModel> result = gameService.getGameByName("pokemon");

        assertFalse(result.isEmpty());
        assertEquals(mockGames.size(), result.size());
        verify(gameRepository).findByNameContainingIgnoreCase(argThat(arg -> arg.equalsIgnoreCase("pokemon")));
        verifyNoMoreInteractions(gameRepository);
    }


    @Test
    @DisplayName("create game")
    public void testCreateGame() {
        GameModel gameModel = new GameModel();
        gameModel.setPlatform("pc");
        gameModel.setDescription("the best game ever");
        gameModel.setPurchasePrice(100.0);
        gameModel.setName("game of thrones");
        gameModel.setYearOfRelease(2015);
        gameModel.setOriginalStock(100);
        gameModel.setAmountSold(50);
        gameModel.setPlayDurationInMin(200);
        gameModel.setPublisher("Whatever works");
        gameModel.setSellingPrice(120.0);

        Mockito.when(gameMapper.fromEntity(Mockito.any(Game.class))).thenAnswer(invocation -> {
            Game gameArgument = invocation.getArgument(0);
            return gameModel;
        });

        Mockito.when(maxPurchasePrice.isPurchasePriceValid(Mockito.anyDouble(), Mockito.anyDouble()))
                .thenAnswer(invocation -> {
                    double purchasePrice = invocation.getArgument(0);
                    double sellingPrice = invocation.getArgument(1);
                    return (purchasePrice < sellingPrice * 0.8) ? sellingPrice * 0.75 : purchasePrice;
                });

        Mockito.when(gameMapper.toEntity(Mockito.any(GameModel.class))).thenAnswer(invocation -> {
            GameModel gameModelArgument = invocation.getArgument(0);
            Game gameEntity = new Game();
            gameEntity.setPlatform(gameModelArgument.getPlatform());
            gameEntity.setDescription(gameModelArgument.getDescription());
            gameEntity.setPurchasePrice(gameModelArgument.getPurchasePrice());
            gameEntity.setSellingPrice(gameModelArgument.getSellingPrice());
            return gameEntity;
        });

        Game savedGameEntity = new Game();
        savedGameEntity.setId(1L);
        Mockito.when(gameRepository.save(Mockito.any(Game.class))).thenReturn(savedGameEntity);

        GameModel result = gameService.createGame(gameModel);

        assertNotNull(result);

        // Verify interactions with mocks
        verify(gameMapper).toEntity(gameModel);
        verify(gameRepository).save(Mockito.any(Game.class));
        verify(gameMapper).fromEntity(savedGameEntity);
        verifyNoMoreInteractions(gameMapper, gameRepository);
    }

    @Test
    @DisplayName("no games found while updating")
    public void testUpdateGameNoGamesFound(){

            Mockito.when(gameRepository.findById(1L)).thenReturn(Optional.empty());

            GameModel updatedGameModel = new GameModel();
            updatedGameModel.setDescription("hello this is a game");
            updatedGameModel.setAmountSold(100);
            updatedGameModel.setYearOfRelease(1950);

            assertThrows(RecordNotFoundException.class, () -> gameService.updateGame(1L, updatedGameModel));
        }



    @Test
    @DisplayName("test update Game")
    public void testUpdateGame() {
        Game existingGame = new Game();
        existingGame.setId(1L);
        existingGame.setOriginalStock(100);
        existingGame.setPlatform("pc");
        existingGame.setName("star wars");
        existingGame.setPlayDurationInMin(200);
        existingGame.setPublisher("dont care");
        existingGame.setDescription("worst game");
        existingGame.setAmountSold(1);
        existingGame.setSellingPrice(150.0);
        existingGame.setPurchasePrice(25.0);
        existingGame.setYearOfRelease(2000);

        Mockito.when(gameRepository.findById(1L)).thenReturn(Optional.of(existingGame));

        GameModel updatedGameModel = new GameModel();
        updatedGameModel.setId(existingGame.getId());
        updatedGameModel.setOriginalStock(200);
        updatedGameModel.setPlatform("PS5");
        updatedGameModel.setName("Lord of the rings");
        updatedGameModel.setPlayDurationInMin(100);
        updatedGameModel.setPublisher("WETA");
        updatedGameModel.setDescription("the best game ever");
        updatedGameModel.setAmountSold(100);
        updatedGameModel.setSellingPrice(150.0);
        updatedGameModel.setPurchasePrice(50.0);
        updatedGameModel.setYearOfRelease(2001);


        Mockito.when(maxPurchasePrice.isPurchasePriceValid(Mockito.anyDouble(), Mockito.anyDouble()))
                .thenAnswer(invocation -> {
                    double purchasePrice = invocation.getArgument(0);
                    double sellingPrice = invocation.getArgument(1);
                    return (purchasePrice < sellingPrice * 0.8) ? sellingPrice * 0.75 : purchasePrice;
                });
;
        Mockito.when(gameRepository.save(Mockito.any(Game.class))).thenAnswer(invocation -> {
            Game game = invocation.getArgument(0);
            return game;
        });

        Mockito.when(gameMapper.fromEntity(Mockito.any(Game.class))).thenReturn(updatedGameModel);

        GameModel returnedUpdatedGameModel = gameService.updateGame(1L, updatedGameModel);

        assertEquals(updatedGameModel.getPlatform(), returnedUpdatedGameModel.getPlatform());
    }

    @Test
    @DisplayName("update, game not found")
    public void testUpdateRecordNotFound(){
        GameModel model = new GameModel();
        model.setId(1L);

        RecordNotFoundException exception = assertThrows(RecordNotFoundException.class, () -> {
            gameService.updateGame(2L, model);
        });

        assertEquals("Game with ID 2 does not exist", exception.getMessage());
    }

        @Test
        @DisplayName("test delete game")
        public void testDeleteGame() {
            Long gameId = 1L;

            assertDoesNotThrow(() -> gameService.deleteGame(gameId));
        }
    }