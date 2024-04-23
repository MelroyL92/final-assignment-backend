package nl.novi.finalAssignmentBackend.Service;


import nl.novi.finalAssignmentBackend.Repository.MovieRepository;
import nl.novi.finalAssignmentBackend.mappers.MovieMappers.MovieMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;






@ExtendWith(MockitoExtension.class)
public class MovieServiceTest {

    @Mock
    private MovieRepository movierepository;

    @Mock
    private MovieMapper movieMapper;

    @InjectMocks
    MovieService movieService;



    @BeforeEach
    public void Setup(){
        movieService = Mockito.mock();
        movieMapper = Mockito.mock();
        movierepository = Mockito.mock();
    }

    @Test
    @DisplayName("get all movies")
    public void testGetAllMovies(){





    }
}
