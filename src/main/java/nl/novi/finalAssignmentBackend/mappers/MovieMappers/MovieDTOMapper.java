package nl.novi.finalAssignmentBackend.mappers.MovieMappers;

import nl.novi.finalAssignmentBackend.dtos.movie.MovieInputDto;
import nl.novi.finalAssignmentBackend.dtos.movie.MovieResponseDto;
import nl.novi.finalAssignmentBackend.model.MovieModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MovieDTOMapper {


    public MovieResponseDto toMovieDto(MovieModel movie){
        return toMovieDto(movie, new MovieResponseDto());
    }

    public <D extends MovieResponseDto> D toMovieDto(MovieModel movie, D target) {
        target.setId(movie.getId());
        target.setDirector(movie.getDirector());
        target.setName(movie.getName());
        target.setGenre(movie.getGenre());
        target.setType(movie.getType());
        target.setPlaytime(movie.getPlaytimeInMin());
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
            result.add(toMovieDto(movieModel));
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
        movie.setPlaytimeInMin(dto.getPlaytimeInMin());
        movie.setYearOfRelease(dto.getYearOfRelease());
        movie.setAmountSold(dto.getAmountSold());
        movie.setDescription(dto.getDescription());
        movie.setOriginalStock(dto.getOriginalStock());
        movie.setCurrentStock(dto.getCurrentStock());
        return movie;
    }
}
