package nl.novi.finalAssignmentBackend.Service;
import nl.novi.finalAssignmentBackend.Repository.GameRepository;
import nl.novi.finalAssignmentBackend.Repository.UserRepository;
import nl.novi.finalAssignmentBackend.entities.Game;
import nl.novi.finalAssignmentBackend.mappers.GameMappers.GameMapper;
import nl.novi.finalAssignmentBackend.model.GameModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GameServiceTest {

    @Mock
    private GameRepository gameRepository;

    @Mock
    private GameMapper gameMapper;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private GameService gameService;


    @BeforeEach
    public void setUp() {
        gameRepository = Mockito.mock();
        gameService = Mockito.mock();
        gameMapper = Mockito.mock();
    }


    @Test
    @DisplayName("get all games")
    public void testGetGames() {
        // Setup
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

        //        Mockito.when(gameMapper.fromEntity((mockGames))).thenAnswer(invocation -> {
        Mockito.when(gameMapper.fromEntity((Game) ArgumentMatchers.any())).thenAnswer(invocation -> {
            Game game = invocation.getArgument(0);
            if (game == null) {
                return null;
            }
            GameModel gameModel = new GameModel();
            gameModel.setId(game.getId());
            gameModel.setPlatform(game.getPlatform());
            gameModel.setPublisher(game.getPublisher());
            gameModel.setPlayDurationInMin(game.getPlayDurationInMin());
            gameModel.setOriginalStock(game.getOriginalStock());
            gameModel.setName(game.getName());
            gameModel.setDescription(game.getDescription());
            gameModel.setCurrentStock(game.getCurrentStock());
            gameModel.setAmountSold(game.getAmountSold());
            gameModel.setPurchasePrice(game.getPurchasePrice());
            gameModel.setSellingPrice(game.getSellingPrice());
            gameModel.setYearOfRelease(game.getYearOfRelease());
            return gameModel;
        });


        List<GameModel> result = gameService.getGames();

        // Assertions
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
        // Mocking the behavior of gameRepository.findById()
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

        // Mocking the behavior of gameMapper.fromEntity()
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

        // Call the method under test
        GameModel result = gameService.getGameById(1L);

        // Verify the result
        assertEquals(1L, result.getId());
        assertEquals("Platform 1", result.getPlatform());
        assertEquals("Publisher 1", result.getPublisher());
        assertEquals(60, result.getPlayDurationInMin());
    }


}
//    @DisplayName("get games bij platform")
//    public void testGetGamesByPlatform() {
//        // Define mockGames with some sample games
//        List<Game> mockGames = Arrays.asList(
//                new Game1(1L, "Platform 1"),
//                new Game(2L, "Platform 2")
//        );
//
//        // Configure behavior of mock repository
//        when(gameRepository.findByPlatformContainingIgnoreCase("platform")).thenReturn(mockGames);
//
//        // Call the method under test
//        List<GameModel> result = gameService.getGamesByPlatform("platform");
//
//        // Assert the result
//        assertEquals(2, result.size()); // Ensure that the correct number of games is returned
//        // Add more assertions as needed to verify the properties of the returned game
//    }
//}
//
//        @Test
//        @DisplayName("create game")
//        public void testCreateGame() {
//
//        }
//
//        @Test
//        @DisplayName("test update Game")
//        public void testUpdateGame() {
//
//        }
//
//        @Test
//        @DisplayName("test delete game")
//        public void testDeleteGame() {
//            Long gameId = 1L;
//            // No need to mock behavior for delete method
//
//            assertDoesNotThrow(() -> gameService.deleteGame(gameId));
//        }
//    }