package nl.novi.finalAssignmentBackend.Controller;

import nl.novi.finalAssignmentBackend.Service.MovieService;
import nl.novi.finalAssignmentBackend.dtos.movie.MovieResponseDto;
import nl.novi.finalAssignmentBackend.entities.Movie;
import nl.novi.finalAssignmentBackend.mappers.MovieMappers.MovieDTOMapper;
import nl.novi.finalAssignmentBackend.model.MovieModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;


@RequestMapping("/movies")
@RestController
public class MovieController {

    private final MovieDTOMapper movieDTOMapper;
    private final MovieService movieService;

    public MovieController(MovieDTOMapper movieDTOMapper, MovieService movieService) {
        this.movieDTOMapper = movieDTOMapper;
        this.movieService = movieService;
    }


    @GetMapping
    public ResponseEntity<List<MovieResponseDto>>getAllMovies(){
        var movies = movieService.getMovies();
        var albumDTO = movies.stream().map(movieDTOMapper::toMovieDto).collect(Collectors.toList());
        return new ResponseEntity<>(albumDTO, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieResponseDto>getMovieById(@PathVariable Long id){
        var movie = movieService.getMovieById(id);
        var movieDTO = movieDTOMapper.toMovieDto(movie);
        return new ResponseEntity<>(movieDTO, HttpStatus.OK);
    }



}
