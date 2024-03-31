package nl.novi.finalAssignmentBackend.mappers.MovieMappers;

import nl.novi.finalAssignmentBackend.dtos.movie.MovieInputDto;
import nl.novi.finalAssignmentBackend.dtos.movie.MovieResponseDto;
import nl.novi.finalAssignmentBackend.model.MovieModel;
import org.springframework.stereotype.Component;

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
        target.setPlaytime(movie.getPlaytime());
        target.setDescription(movie.getDescription());
        target.setSellingPrice(movie.getSellingPrice());
        target.setYearOfRelease(movie.getYearOfRelease());
        target.setOriginalStock(movie.getOriginalStock());
        return target;
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
        movie.setPlaytime(dto.getPlaytime());
        movie.setYearOfRelease(dto.getYearOfRelease());
        movie.setAmountSold(dto.getAmountSold());
        movie.setDescription(dto.getDescription());
        movie.setOriginalStock(dto.getOriginalStock());
        return movie;
    }
}
//    Twee soorten mappers maken? 1 voor de dto en 1 voor de vertaling van het model naar de entiteit?

    //    sellingPrice;
    //
    //    originalStock;
    //
    //    description;
    //
    //    name;
    //
    //    amountSold;
    //
    //    yearOfRelease;
    //
    //    purchasePrice;
    //    genre;
    //    type;
    //    director;
    //
    //    playtime;

