package nl.novi.finalAssignmentBackend.controllers;

import nl.novi.finalAssignmentBackend.Service.MovieService;
import nl.novi.finalAssignmentBackend.dtos.movie.MovieInputDto;
import nl.novi.finalAssignmentBackend.dtos.movie.MovieResponseDto;
import nl.novi.finalAssignmentBackend.mappers.MovieMappers.MovieDTOMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
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
        if (movie == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        var movieDTO = movieDTOMapper.toMovieDto(movie);
        return new ResponseEntity<>(movieDTO, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<MovieResponseDto>createMovie(@RequestBody MovieInputDto movieInputDto){
        var movieModel = movieDTOMapper.createMovieModel(movieInputDto);
        var newMovie = movieService.createMovie(movieModel);
        var movieDto = movieDTOMapper.toMovieDto(newMovie);
        return ResponseEntity.created(URI.create("/movies/" + newMovie.getId()))
                .body(movieDto);
    }

}
