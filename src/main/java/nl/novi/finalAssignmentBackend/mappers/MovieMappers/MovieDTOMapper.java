package nl.novi.finalAssignmentBackend.mappers.MovieMappers;

import nl.novi.finalAssignmentBackend.dtos.movie.MovieInputDto;
import nl.novi.finalAssignmentBackend.dtos.movie.MovieResponseDto;
import nl.novi.finalAssignmentBackend.model.MovieModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MovieDTOMapper {


    public MovieResponseDto toMovieDTO(MovieModel movie){
        return toMovieDTO(movie, new MovieResponseDto());
    }

    public <D extends MovieResponseDto> D toMovieDTO(MovieModel movie, D target) {
        target.setId(movie.getId());
        target.setDirector(movie.getDirector());
        target.setName(movie.getName());
        target.setGenre(movie.getGenre());
        target.setType(movie.getType());
        target.setWatchTimeInMin(movie.getWatchTimeInMin());
        target.setDescription(movie.getDescription());
        target.setSellingPrice(movie.getSellingPrice());
        target.setYearOfRelease(movie.getYearOfRelease());
        target.setOriginalStock(movie.getOriginalStock());
        target.setCurrentStock(movie.getCurrentStock());
        return target;
    }
    public List<MovieResponseDto> toMovieDTOs(List<MovieModel>movieModels){
        List<MovieResponseDto> result = new ArrayList<>();
        for (MovieModel movieModel: movieModels){
            result.add(toMovieDTO(movieModel));
        }
        return result;
    }

    public MovieModel createMovieModel(MovieInputDto dto) {
        var movie = new MovieModel();
        movie.setId(dto.getId());
        movie.setName(dto.getName());
        movie.setType(dto.getType());
        movie.setSellingPrice(dto.getSellingPrice());
        movie.setPurchasePrice(dto.getSellingPrice());
        movie.setGenre(dto.getGenre());
        movie.setDirector(dto.getDirector());
        movie.setWatchTimeInMin(dto.getWatchTimeInMin());
        movie.setYearOfRelease(dto.getYearOfRelease());
        movie.setAmountSold(dto.getAmountSold());
        movie.setDescription(dto.getDescription());
        movie.setOriginalStock(dto.getOriginalStock());
        movie.setCurrentStock(dto.getCurrentStock());
        return movie;
    }
}
