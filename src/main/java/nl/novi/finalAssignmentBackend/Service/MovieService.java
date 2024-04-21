package nl.novi.finalAssignmentBackend.Service;


import jakarta.persistence.EntityNotFoundException;
import nl.novi.finalAssignmentBackend.Repository.MovieRepository;
import nl.novi.finalAssignmentBackend.entities.Movie;
import nl.novi.finalAssignmentBackend.exceptions.RecordNotFoundException;
import nl.novi.finalAssignmentBackend.mappers.MovieMappers.MovieMapper;
import nl.novi.finalAssignmentBackend.model.MovieModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovieService {

    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;


    public MovieService(MovieRepository movieRepository, MovieMapper movieMapper) {
        this.movieRepository = movieRepository;
        this.movieMapper = movieMapper;
    }


    public List<MovieModel> getMovies() {
        return movieRepository.findAll().stream().map(movieMapper::fromEntity).collect(Collectors.toList());
    }

    public MovieModel getMovieById(Long id) {
        Movie movie = movieRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Movie not found with id: " + id));
        return movieMapper.fromEntity(movie);
    }

    public List<MovieModel> getMoviesByGenre(String genre) {
        List<Movie> movies = movieRepository.findByGenreContainingIgnoreCase(genre);

        if(movies.isEmpty()){
            throw new RecordNotFoundException("no movies found for genre " + genre);
        }
        return movies.stream().map(movieMapper::fromEntity).collect(Collectors.toList());
    }


    public MovieModel createMovie(MovieModel movieModel) {
        Movie movie = movieMapper.toEntity(movieModel);
        movie = movieRepository.save(movie);
        return movieMapper.fromEntity(movie);
    }

    public MovieModel updateMovie(Long id, MovieModel movieModel) {
        Optional<Movie> movieFound = movieRepository.findById(id);
        if (movieFound.isPresent()) {

            Movie existingMovie = movieFound.get();
            existingMovie.setOriginalStock(movieModel.getOriginalStock());
            existingMovie.setType(movieModel.getType());
            existingMovie.setName(movieModel.getName());
            existingMovie.setDirector(movieModel.getDirector());
            existingMovie.setGenre(movieModel.getGenre());
            existingMovie.setDescription(movieModel.getDescription());
            existingMovie.setAmountSold(movieModel.getAmountSold());
            existingMovie.setPlaytimeInMin(movieModel.getPlaytimeInMin());
            existingMovie.setSellingPrice(movieModel.getSellingPrice());
            existingMovie.setPurchasePrice(movieModel.getPurchasePrice());
            existingMovie.setYearOfRelease(movieModel.getYearOfRelease());
            existingMovie = movieRepository.save(existingMovie);
            return movieMapper.fromEntity(existingMovie);
        } else {
            throw new RecordNotFoundException("Movie with ID " + id + " does not exist");
        }
    }

    public void deleteMovie(Long id){
        movieRepository.deleteById(id);
    }
}
