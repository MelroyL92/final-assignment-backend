package nl.novi.finalAssignmentBackend.Service;


import nl.novi.finalAssignmentBackend.Repository.MovieRepository;
import nl.novi.finalAssignmentBackend.entities.Movie;
import nl.novi.finalAssignmentBackend.exceptions.RecordNotFoundException;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class MovieServiceTest {

    @Mock
    private MovieRepository movierepository;

    @Mock
    private MovieMapper movieMapper;

    @InjectMocks
    MovieService movieService;


    @BeforeEach
    public void Setup() {

    }

    @Test
    @DisplayName("get all movies")
    public void testGetAllMovies() {
        List<Movie> mockMovies = new ArrayList<>();

        Movie movie1 = new Movie();
        movie1.setId(1L);
        movie1.setPurchasePrice(50.0);
        movie1.setSellingPrice(20.0);
        movie1.setOriginalStock(100);
        movie1.setAmountSold(50);
        movie1.setCurrentStock(50);
        movie1.setName("the hobbit");
        movie1.setDirector("peter Jackson");
        movie1.setYearOfRelease(2016);
        movie1.setDescription("worst of the lotr movies but still great");
        movie1.setType("blu ray");
        movie1.setGenre("fantasy");
        movie1.setWatchTimeInMin(200);
        mockMovies.add(movie1);

        Movie movie2 = new Movie();
        movie2.setId(2L);
        movie2.setPurchasePrice(80.0);
        movie2.setSellingPrice(30.0);
        movie2.setOriginalStock(200);
        movie2.setAmountSold(100);
        movie2.setCurrentStock(100);
        movie2.setName("the hobbit2");
        movie2.setDirector("peter Jackson");
        movie2.setYearOfRelease(2017);
        movie2.setDescription("worst of the lotr movies but still great");
        movie2.setType("blu ray");
        movie2.setGenre("fantasy");
        movie2.setWatchTimeInMin(300);
        mockMovies.add(movie2);

        when(movierepository.findAll()).thenReturn(mockMovies);

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
        assertEquals("peter Jackson", result.get(0).getDirector());
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
        assertEquals("the hobbit2", result.get(1).getName());
        assertEquals("peter Jackson", result.get(1).getDirector());
        assertEquals(2017, result.get(1).getYearOfRelease());
        assertEquals("worst of the lotr movies but still great", result.get(1).getDescription());
        assertEquals("blu ray", result.get(1).getType());
        assertEquals("fantasy", result.get(1).getGenre());
        assertEquals(300, result.get(1).getWatchTimeInMin());
    }

    @Test
    @DisplayName("test by ID")
    public void testMovieById() {
        Movie movie1 = new Movie();
        movie1.setId(1L);
        movie1.setPurchasePrice(50.0);
        movie1.setSellingPrice(20.0);
        movie1.setOriginalStock(100);
        movie1.setAmountSold(50);
        movie1.setCurrentStock(50);
        movie1.setName("the hobbit");
        movie1.setDirector("peter Jackson");
        movie1.setYearOfRelease(2016);
        movie1.setDescription("worst of the lotr movies but still great");
        movie1.setType("blu ray");
        movie1.setGenre("fantasy");
        movie1.setWatchTimeInMin(200);

        when(movierepository.findById(1L)).thenReturn(Optional.of(movie1));

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
        assertEquals("peter Jackson", result.getDirector());
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

        Mockito.when(movierepository.findByGenreContainingIgnoreCase(Mockito.anyString())).thenAnswer(invocation -> {
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

        when(movierepository.findByGenreContainingIgnoreCase("sci fi")).thenReturn(mockMovies);

        List<MovieModel> result = movieService.getMoviesByGenre("sci fi");

        assertFalse(result.isEmpty());
        assertEquals(mockMovies.size(), result.size());
        verify(movierepository).findByGenreContainingIgnoreCase(argThat(arg -> arg.equalsIgnoreCase("sci fi")));
        verifyNoMoreInteractions(movierepository);
    }

    @Test
    @DisplayName("create Movie")
    public void testCreateMovie() {
        MovieModel movieModel = new MovieModel();
        movieModel.setPurchasePrice(50.0);
        movieModel.setSellingPrice(20.0);
        movieModel.setOriginalStock(100);
        movieModel.setAmountSold(50);
        movieModel.setCurrentStock(50);
        movieModel.setName("the hobbit");
        movieModel.setDirector("peter Jackson");
        movieModel.setYearOfRelease(2016);
        movieModel.setDescription("worst of the lotr movies but still great");
        movieModel.setType("blu ray");
        movieModel.setGenre("fantasy");
        movieModel.setWatchTimeInMin(200);

        Mockito.when(movieMapper.fromEntity(Mockito.any(Movie.class))).thenAnswer(invocation -> {
            Movie movieArgument = invocation.getArgument(0);
            return movieModel;
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
        Mockito.when(movierepository.save(Mockito.any(Movie.class))).thenReturn(savedMovieEntity);

        MovieModel result = movieService.createMovie(movieModel);

        assertNotNull(result);
        verify(movieMapper).toEntity(movieModel);
        verify(movierepository).save(Mockito.any(Movie.class));
        verify(movieMapper).fromEntity(savedMovieEntity);
        verifyNoMoreInteractions(movieMapper, movierepository);
    }

    @Test
    @DisplayName("testing updating movie")
    public void testUpdateMovie() {
        Movie existingMovie = new Movie();
        existingMovie.setId(1L);
        existingMovie.setPurchasePrice(50.0);
        existingMovie.setSellingPrice(20.0);
        existingMovie.setOriginalStock(100);
        existingMovie.setAmountSold(50);
        existingMovie.setCurrentStock(50);
        existingMovie.setName("the hobbit");
        existingMovie.setDirector("peter Jackson");
        existingMovie.setYearOfRelease(2016);
        existingMovie.setDescription("worst of the lotr movies but still great");
        existingMovie.setType("blu ray");
        existingMovie.setGenre("fantasy");
        existingMovie.setWatchTimeInMin(200);

        Mockito.when(movierepository.findById(1L)).thenReturn(Optional.of(existingMovie));

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

        Mockito.when(movierepository.save(Mockito.any(Movie.class))).thenAnswer(invocation -> {
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
