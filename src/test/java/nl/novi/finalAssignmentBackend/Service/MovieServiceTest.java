package nl.novi.finalAssignmentBackend.Service;


import nl.novi.finalAssignmentBackend.Repository.MovieRepository;
import nl.novi.finalAssignmentBackend.entities.Movie;
import nl.novi.finalAssignmentBackend.exceptions.RecordNotFoundException;
import nl.novi.finalAssignmentBackend.helper.MaxPurchasePrice;
import nl.novi.finalAssignmentBackend.mappers.MovieMappers.MovieMapper;
import nl.novi.finalAssignmentBackend.model.MovieModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static nl.novi.finalAssignmentBackend.helpers.ServiceHelperMovieCreation.createMovie;
import static nl.novi.finalAssignmentBackend.helpers.ServiceHelperMovieCreation.createMovieModel;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class MovieServiceTest {

    @Mock
    private MovieRepository movieRepository;

    @Mock
    private MovieMapper movieMapper;

    @Mock
    private MaxPurchasePrice maxPurchasePrice;

    @InjectMocks
    MovieService movieService;


    @BeforeEach
    public void Setup() {

    }


    @Test
    @DisplayName("get all movies")
    public void testGetAllMovies() {
        List<Movie> mockMovies = new ArrayList<>();
        mockMovies.add(createMovie(1L,50.0,20.0,100,50,50,
                "the hobbit", "Peter Jackson", 2016,"worst of the lotr movies but still great",
                "blu ray", "fantasy", 200));

        mockMovies.add(createMovie(2L, 80.0, 30.0, 200, 100, 100,
                "the hobbit 2", "Peter Jackson", 2017, "worst of the lotr movies but still great",
                "blu ray", "fantasy",300));


        when(movieRepository.findAll()).thenReturn(mockMovies);

        when(movieMapper.fromEntity((Movie) ArgumentMatchers.any())).thenAnswer(invocation -> {
            Movie movie = invocation.getArgument(0);
            if (movie == null) {
                return null;
            }
            MovieModel movieModel = new MovieModel();
            movieModel.setId(movie.getId());
            movieModel.setPurchasePrice(movie.getPurchasePrice());
            movieModel.setSellingPrice(movie.getSellingPrice());
            movieModel.setOriginalStock(movie.getOriginalStock());
            movieModel.setAmountSold(movie.getAmountSold());
            movieModel.setCurrentStock(movie.getCurrentStock());
            movieModel.setName(movie.getName());
            movieModel.setDirector(movie.getDirector());
            movieModel.setYearOfRelease(movie.getYearOfRelease());
            movieModel.setDescription(movie.getDescription());
            movieModel.setType(movie.getType());
            movieModel.setGenre(movie.getGenre());
            movieModel.setWatchTimeInMin(movie.getWatchTimeInMin());

            return movieModel;
        });

        List<MovieModel> result = movieService.getMovies();

        assertEquals(2, result.size());
        assertEquals(50, result.get(0).getPurchasePrice());
        assertEquals(20, result.get(0).getSellingPrice());
        assertEquals(50, result.get(0).getAmountSold());
        assertEquals(50, result.get(0).getCurrentStock());
        assertEquals("the hobbit", result.get(0).getName());
        assertEquals("Peter Jackson", result.get(0).getDirector());
        assertEquals(2016, result.get(0).getYearOfRelease());
        assertEquals("worst of the lotr movies but still great", result.get(0).getDescription());
        assertEquals("blu ray", result.get(0).getType());
        assertEquals("fantasy", result.get(0).getGenre());
        assertEquals(200, result.get(0).getWatchTimeInMin());

        assertEquals(80.0, result.get(1).getPurchasePrice());
        assertEquals(30.0, result.get(1).getSellingPrice());
        assertEquals(200, result.get(1).getOriginalStock());
        assertEquals(100, result.get(1).getAmountSold());
        assertEquals(100, result.get(1).getCurrentStock());
        assertEquals("the hobbit 2", result.get(1).getName());
        assertEquals("Peter Jackson", result.get(1).getDirector());
        assertEquals(2017, result.get(1).getYearOfRelease());
        assertEquals("worst of the lotr movies but still great", result.get(1).getDescription());
        assertEquals("blu ray", result.get(1).getType());
        assertEquals("fantasy", result.get(1).getGenre());
        assertEquals(300, result.get(1).getWatchTimeInMin());
    }

    @Test
    @DisplayName("test by ID")
    public void testMovieById() {
        Movie movie1 = createMovie(1L, 50.0,20.0,100,50,50
        ,"the hobbit","Peter Jackson", 2016, "worst of the lotr movies but still great",
                "blu ray", "fantasy", 200);

        when(movieRepository.findById(1L)).thenReturn(Optional.of(movie1));

        Mockito.when(movieMapper.fromEntity((Movie) ArgumentMatchers.any())).thenAnswer(invocation -> {
            Movie movie = invocation.getArgument(0);
            MovieModel movieModel = new MovieModel();
            movieModel.setId(movie.getId());
            movieModel.setPurchasePrice(movie.getPurchasePrice());
            movieModel.setSellingPrice(movie.getSellingPrice());
            movieModel.setOriginalStock(movie.getOriginalStock());
            movieModel.setAmountSold(movie.getAmountSold());
            movieModel.setCurrentStock(movie.getCurrentStock());
            movieModel.setName(movie.getName());
            movieModel.setDirector(movie.getDirector());
            movieModel.setYearOfRelease(movie.getYearOfRelease());
            movieModel.setDescription(movie.getDescription());
            movieModel.setType(movie.getType());
            movieModel.setGenre(movie.getGenre());
            movieModel.setWatchTimeInMin(movie.getWatchTimeInMin());
            return movieModel;
        });

        MovieModel result = movieService.getMovieById(1L);


        assertEquals(50, result.getPurchasePrice());
        assertEquals(20, result.getSellingPrice());
        assertEquals(50, result.getAmountSold());
        assertEquals(50, result.getCurrentStock());
        assertEquals("the hobbit", result.getName());
        assertEquals("Peter Jackson", result.getDirector());
        assertEquals(2016, result.getYearOfRelease());
        assertEquals("worst of the lotr movies but still great", result.getDescription());
        assertEquals("blu ray", result.getType());
        assertEquals("fantasy", result.getGenre());
        assertEquals(200, result.getWatchTimeInMin());
    }

    @Test
    @DisplayName("movie genre not found")
    public void testGenreMovieNotFound() {
        List<Movie> mockMovies = new ArrayList<>();
        Movie movie = new Movie();
        movie.setGenre("Horror");
        mockMovies.add(movie);

        Mockito.when(movieRepository.findByGenreContainingIgnoreCase(Mockito.anyString())).thenAnswer(invocation -> {
            String movie1 = invocation.getArgument(0);
            if ("fantasy".equalsIgnoreCase(movie1)) {
                return Collections.emptyList();
            } else {
                return mockMovies;
            }
        });

        assertThrows(RecordNotFoundException.class, () -> {
            movieService.getMoviesByGenre("fantasy");

        });
    }

    @Test
    @DisplayName("genre test")
    public void testMovieByGenre() {
        List<Movie> mockMovies = new ArrayList<>();

        Movie movie = new Movie();
        movie.setGenre("sci fi");
        mockMovies.add(movie);

        when(movieRepository.findByGenreContainingIgnoreCase("sci fi")).thenReturn(mockMovies);

        List<MovieModel> result = movieService.getMoviesByGenre("sci fi");

        assertFalse(result.isEmpty());
        assertEquals(mockMovies.size(), result.size());
        verify(movieRepository).findByGenreContainingIgnoreCase(argThat(arg -> arg.equalsIgnoreCase("sci fi")));
        verifyNoMoreInteractions(movieRepository);
    }

    @Test
    @DisplayName("cannot find movie by name")
    public void testNotFindMovieByName(){
        List<Movie> mockMovies = new ArrayList<>();
        Movie movie = new Movie();
        movie.setGenre("Lord of the rings");
        mockMovies.add(movie);

        Mockito.when(movieRepository.findMovieByNameIsContainingIgnoreCase(Mockito.anyString())).thenAnswer(invocation -> {
            String name = invocation.getArgument(0);
            if ("Harry Potter".equalsIgnoreCase(name)){
                return Collections.emptyList();
            } else {
                return mockMovies;
            }
        });
        assertThrows(RecordNotFoundException.class, () -> {
            movieService.getMovieByName("Harry Potter");
        });
    }

    @Test
    @DisplayName("find by name")
    public void testFindMovieByName(){
        List<Movie>mockMovies = new ArrayList<>();
        Movie movie1 = new Movie();
        movie1.setName("lord of the rings and the fellowship of the ring");
        mockMovies.add(movie1);

       Movie movie2 = new Movie();
       movie2.setName("Lord of the rings and the two towers");
       mockMovies.add(movie2);

       Movie movie3 = new Movie();
       movie3.setName("Lord of the rings and the return of the king");
       mockMovies.add(movie3);

       Mockito.when(movieRepository.findMovieByNameIsContainingIgnoreCase("lord of the rings")).thenReturn(mockMovies);

       List<MovieModel>result = movieService.getMovieByName("lord of the rings");

        assertFalse(result.isEmpty());
        assertEquals(mockMovies.size(), result.size());
        verify(movieRepository).findMovieByNameIsContainingIgnoreCase(argThat(arg -> arg.equalsIgnoreCase("lord of the rings")));
        verifyNoMoreInteractions(movieRepository);
    }

    @Test
    @DisplayName("create Movie")
    public void testCreateMovie() {
        MovieModel movieModel = createMovieModel(50.0,20.0,100,50,50,
        "the hobbit", "Peter Jackson", 2016, "worst of the lotr movies but still great",
                "blu ray", "fantasy", 200);

        Mockito.when(movieMapper.fromEntity(Mockito.any(Movie.class))).thenAnswer(invocation -> {
            Movie movieArgument = invocation.getArgument(0);
            return movieModel;
        });

        Mockito.when(maxPurchasePrice.isPurchasePriceValid(Mockito.anyDouble(), Mockito.anyDouble()))
                .thenAnswer(invocation -> {
                    double purchasePrice = invocation.getArgument(0);
                    double sellingPrice = invocation.getArgument(1);
                    return (purchasePrice < sellingPrice * 0.8) ? sellingPrice * 0.75 : purchasePrice;
                });

        Mockito.when(movieMapper.toEntity(Mockito.any(MovieModel.class))).thenAnswer(invocation -> {
            MovieModel movieModelArgument = invocation.getArgument(0);
            Movie movieEntity = new Movie();
            movieEntity.setPurchasePrice(movieModelArgument.getPurchasePrice());
            movieEntity.setSellingPrice(movieModelArgument.getSellingPrice());
            movieEntity.setOriginalStock(movieModelArgument.getOriginalStock());
            movieEntity.setAmountSold(movieModelArgument.getAmountSold());
            movieEntity.setCurrentStock(movieModelArgument.getCurrentStock());
            movieEntity.setName(movieModelArgument.getName());
            movieEntity.setDirector(movieModelArgument.getDirector());
            movieEntity.setYearOfRelease(movieModelArgument.getYearOfRelease());
            movieEntity.setDescription(movieModelArgument.getDescription());
            movieEntity.setType(movieModelArgument.getType());
            movieEntity.setGenre(movieModelArgument.getGenre());
            movieEntity.setWatchTimeInMin(movieModelArgument.getWatchTimeInMin());
            return movieEntity;
        });

        Movie savedMovieEntity = new Movie();
        savedMovieEntity.setId(1L);
        Mockito.when(movieRepository.save(Mockito.any(Movie.class))).thenReturn(savedMovieEntity);

        MovieModel result = movieService.createMovie(movieModel);

        assertNotNull(result);
        verify(movieMapper).toEntity(movieModel);
        verify(movieRepository).save(Mockito.any(Movie.class));
        verify(movieMapper).fromEntity(savedMovieEntity);
        verifyNoMoreInteractions(movieMapper, movieRepository);
    }

    @Test
    @DisplayName("testing updating movie")
    public void testUpdateMovie() {
        Movie existingMovie = createMovie(1L, 50.0, 20.0,0,100, 50, "the hobbit",
                "Peter Jackson", 2016,"worst of the lotr movies but still great","blu ray", "fantasy", 200);


        Mockito.when(movieRepository.findById(1L)).thenReturn(Optional.of(existingMovie));

        MovieModel updatedMovieModel = new MovieModel();
        updatedMovieModel.setId(1L);
        updatedMovieModel.setPurchasePrice(80.0);
        updatedMovieModel.setSellingPrice(50.0);
        updatedMovieModel.setOriginalStock(500);
        updatedMovieModel.setAmountSold(200);
        updatedMovieModel.setCurrentStock(300);
        updatedMovieModel.setName("the hobbit1");
        updatedMovieModel.setDirector("peter Jackson");
        updatedMovieModel.setYearOfRelease(2018);
        updatedMovieModel.setDescription("worst of the lotr movies but still great");
        updatedMovieModel.setType("blu ray");
        updatedMovieModel.setGenre("fantasy");
        updatedMovieModel.setWatchTimeInMin(200);

        Mockito.when(maxPurchasePrice.isPurchasePriceValid(Mockito.anyDouble(), Mockito.anyDouble()))
                .thenAnswer(invocation -> {
                    double purchasePrice = invocation.getArgument(0);
                    double sellingPrice = invocation.getArgument(1);
                    return (purchasePrice < sellingPrice * 0.8) ? sellingPrice * 0.75 : purchasePrice;
                });

        Mockito.when(movieRepository.save(Mockito.any(Movie.class))).thenAnswer(invocation -> {
            Movie movie = invocation.getArgument(0);
            return movie;
        });

        Mockito.when(movieMapper.fromEntity(Mockito.any(Movie.class))).thenReturn(updatedMovieModel);

        MovieModel returnedUpdatedMovieModel = movieService.updateMovie(1L, updatedMovieModel);

        assertEquals(updatedMovieModel.getPurchasePrice(), returnedUpdatedMovieModel.getPurchasePrice());
        assertEquals(updatedMovieModel.getSellingPrice(), returnedUpdatedMovieModel.getSellingPrice());
        assertEquals(updatedMovieModel.getOriginalStock(), returnedUpdatedMovieModel.getOriginalStock());
        assertEquals(updatedMovieModel.getAmountSold(), returnedUpdatedMovieModel.getAmountSold());
        assertEquals(updatedMovieModel.getCurrentStock(), returnedUpdatedMovieModel.getCurrentStock());
        assertEquals(updatedMovieModel.getName(), returnedUpdatedMovieModel.getName());
        assertEquals(updatedMovieModel.getDirector(), returnedUpdatedMovieModel.getDirector());
        assertEquals(updatedMovieModel.getYearOfRelease(), returnedUpdatedMovieModel.getYearOfRelease());
        assertEquals(updatedMovieModel.getDescription(), returnedUpdatedMovieModel.getDescription());
        assertEquals(updatedMovieModel.getType(), returnedUpdatedMovieModel.getType());
        assertEquals(updatedMovieModel.getGenre(), returnedUpdatedMovieModel.getGenre());
        assertEquals(updatedMovieModel.getWatchTimeInMin(), returnedUpdatedMovieModel.getWatchTimeInMin());
    }

    @Test@DisplayName("update record not found")
    public void testUpdateRecordNotFoundMovie(){
        MovieModel movieModel = new MovieModel();
        movieModel.setId(1L);

        RecordNotFoundException exception = assertThrows(RecordNotFoundException.class, ()->{
            movieService.updateMovie(2L, movieModel);
        });

        assertEquals("Movie with ID 2 does not exist", exception.getMessage());
    }

    @Test
    @DisplayName("test delete movie")
    public void testDeleteMovie(){
        Long movieId = 1L;

        assertDoesNotThrow(() -> movieService.deleteMovie(movieId));
    }

}
