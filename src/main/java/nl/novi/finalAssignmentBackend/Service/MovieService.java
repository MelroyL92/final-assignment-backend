package nl.novi.finalAssignmentBackend.Service;


import nl.novi.finalAssignmentBackend.Repository.MovieRepository;
import nl.novi.finalAssignmentBackend.entities.Movie;
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


    public List<MovieModel> getMovies(){
        return movieRepository.findAll().stream().map(movieMapper:: fromEntity).collect(Collectors.toList());
    }

    public Movie getMoviesbyId(Long id) {
        Optional<Movie> specificMovie = movieRepository.findById(id);
        if (specificMovie.isEmpty()) {
            throw new RuntimeException();
            // exceptions enzo ook hierin verwerken
        } else {
            return specificMovie.get();
        }
    }

}
