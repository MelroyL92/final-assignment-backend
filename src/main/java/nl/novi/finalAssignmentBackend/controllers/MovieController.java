package nl.novi.finalAssignmentBackend.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import nl.novi.finalAssignmentBackend.Service.MovieService;
import nl.novi.finalAssignmentBackend.dtos.movie.MovieInputDto;
import nl.novi.finalAssignmentBackend.dtos.movie.MovieResponseDto;
import nl.novi.finalAssignmentBackend.helper.UrlHelper;
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
    private final MovieService movieService;
    private final HttpServletRequest request;

    public MovieController(MovieDTOMapper movieDTOMapper, MovieService movieService, HttpServletRequest request) {
        this.movieDTOMapper = movieDTOMapper;
        this.movieService = movieService;
        this.request = request;
    }


    @GetMapping("")
    public ResponseEntity<List<MovieResponseDto>>getAllMovies(){
        var movies = movieService.getMovies();
        var albumDTO = movies.stream().map(movieDTOMapper::toMovieDTO).collect(Collectors.toList());
        return new ResponseEntity<>(albumDTO, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieResponseDto>getMovieById(@PathVariable Long id){
        var movie = movieService.getMovieById(id);
        if (movie == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        var movieDTO = movieDTOMapper.toMovieDTO(movie);
        return new ResponseEntity<>(movieDTO, HttpStatus.OK);
    }

    // what to return when a non valid param is entered?
    @GetMapping("/genre")
    public List<MovieModel> getMoviesByGenre(@RequestParam String genre) {
        List<MovieModel> movies = movieService.getMoviesByGenre(genre);
        return ResponseEntity.ok(movies).getBody();
    }

    @PostMapping("")
    public ResponseEntity<MovieResponseDto>createMovie(@RequestBody @Valid MovieInputDto movieInputDto){
        var movieModel = movieDTOMapper.createMovieModel(movieInputDto);
        var newMovie = movieService.createMovie(movieModel);
        var movieDto = movieDTOMapper.toMovieDTO(newMovie);
        return ResponseEntity.created(UrlHelper.getCurrentURLWithId(request, movieDto.getId())).body(movieDto);

    }

    @PutMapping("{id}")
    public ResponseEntity<MovieResponseDto>updateMovie(@PathVariable Long id,@RequestBody MovieInputDto movieInputDto) {
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
