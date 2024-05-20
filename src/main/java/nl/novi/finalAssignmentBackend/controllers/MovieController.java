package nl.novi.finalAssignmentBackend.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import nl.novi.finalAssignmentBackend.Service.MovieService;
import nl.novi.finalAssignmentBackend.dtos.movie.ExtendedMovieResponseDTO;
import nl.novi.finalAssignmentBackend.dtos.movie.MovieInputDTO;
import nl.novi.finalAssignmentBackend.dtos.movie.MovieResponseDTO;
import nl.novi.finalAssignmentBackend.helper.UrlHelper;
import nl.novi.finalAssignmentBackend.mappers.MovieMappers.ExtendedMovieDTOMapper;
import nl.novi.finalAssignmentBackend.mappers.MovieMappers.MovieDTOMapper;
import nl.novi.finalAssignmentBackend.model.MovieModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/movies")
@RestController
public class MovieController {

    private final MovieDTOMapper movieDTOMapper;
    private final ExtendedMovieDTOMapper extendedMovieDTOMapper;
    private final MovieService movieService;
    private final HttpServletRequest request;

    public MovieController(MovieDTOMapper movieDTOMapper, ExtendedMovieDTOMapper extendedMovieDTOMapper, MovieService movieService, HttpServletRequest request) {
        this.movieDTOMapper = movieDTOMapper;
        this.extendedMovieDTOMapper = extendedMovieDTOMapper;
        this.movieService = movieService;
        this.request = request;
    }


    @GetMapping("")
    public ResponseEntity<List<MovieResponseDTO>>getAllMovies(){
        var movies = movieService.getMovies();
        var movieDTO = movies.stream().map(movieDTOMapper::toMovieDTO).collect(Collectors.toList());
        return new ResponseEntity<>(movieDTO, HttpStatus.OK);
    }

    @GetMapping("/admin")
    public ResponseEntity<List<ExtendedMovieResponseDTO>>getAllMoviesAdmin(){
        var movies = movieService.getMovies();
        var extendedMovieDTO = movies.stream().map(extendedMovieDTOMapper::toExtendedMovieDTO).collect(Collectors.toList());
        return new ResponseEntity<>(extendedMovieDTO, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieResponseDTO>getMovieById(@PathVariable Long id){
        var movie = movieService.getMovieById(id);
        if (movie == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        var movieDTO = movieDTOMapper.toMovieDTO(movie);
        return new ResponseEntity<>(movieDTO, HttpStatus.OK);
    }

    @GetMapping("/admin/{id}")
    public ResponseEntity<ExtendedMovieResponseDTO>getMovieByIdAdmin(@PathVariable Long id){
        var movie = movieService.getMovieById(id);
        if (movie == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        var extendedMovieDTO = extendedMovieDTOMapper.toExtendedMovieDTO(movie);
        return new ResponseEntity<>(extendedMovieDTO, HttpStatus.OK);
    }


    @GetMapping("/genre")
    public ResponseEntity<List<MovieResponseDTO>> getMoviesByGenre(@RequestParam String genre) {
       var movies = movieService.getMoviesByGenre(genre);
        var movieDto = movieDTOMapper.toMovieDTOs(movies);
        return new ResponseEntity<>(movieDto, HttpStatus.OK);
    }

    @GetMapping("/name")
    public ResponseEntity<List<MovieResponseDTO>>getMoviesByName(@RequestParam String name){
        List<MovieModel> movies = movieService.getMovieByName(name);
        var movieDto = movieDTOMapper.toMovieDTOs(movies);
        return new ResponseEntity<>(movieDto, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<MovieResponseDTO>createMovie(@Valid @RequestBody MovieInputDTO movieInputDto){
        var movieModel = movieDTOMapper.createMovieModel(movieInputDto);
        var newMovie = movieService.createMovie(movieModel);
        var movieDto = movieDTOMapper.toMovieDTO(newMovie);
        return ResponseEntity.created(UrlHelper.getCurrentURLWithId(request, movieDto.getId())).body(movieDto);

    }

    @PutMapping("{id}")
    public ResponseEntity<MovieResponseDTO>updateMovie(@PathVariable Long id, @Valid @RequestBody MovieInputDTO movieInputDto) {
        var updateMovie = movieService.updateMovie(id,  movieDTOMapper.createMovieModel(movieInputDto));
        var movieDto = movieDTOMapper.toMovieDTO(updateMovie);
        return new ResponseEntity<>(movieDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object>deleteMovie(@PathVariable Long id){
        movieService.deleteMovie(id);
        return ResponseEntity.noContent().build();
    }

}
