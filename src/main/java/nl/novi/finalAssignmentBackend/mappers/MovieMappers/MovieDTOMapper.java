package nl.novi.finalAssignmentBackend.mappers.MovieMappers;

import nl.novi.finalAssignmentBackend.dtos.movie.MovieInputDto;
import nl.novi.finalAssignmentBackend.dtos.movie.MovieResponseDto;
import nl.novi.finalAssignmentBackend.model.MovieModel;
import org.springframework.stereotype.Component;

@Component
public class MovieDTOMapper {

    public MovieResponseDto toMovieDto(MovieModel movie) {
        MovieResponseDto dto = new MovieResponseDto();
        dto.setId(movie.getId());
        dto.setDirector(movie.getDirector());
        dto.setName(movie.getName());
        dto.setGenre(movie.getGenre());
        dto.setType(movie.getType());
        dto.setPlaytime(movie.getPlaytime());
        dto.setDescription(movie.getDescription());
        dto.setSellingPrice(movie.getSellingPrice());
        dto.setYearOfRelease(movie.getYearOfRelease());
        dto.setOriginalStock(movie.getOriginalStock());
        return dto;
    }


    public MovieModel createMovie(MovieInputDto dto) {
        var movie = new MovieModel();
        movie.setName(movie.getName());
        movie.setType(movie.getType());
        movie.setSellingPrice(movie.getSellingPrice());
        movie.setPurchasePrice(movie.getSellingPrice());
        movie.setGenre(movie.getGenre());
        movie.setDirector(movie.getDirector());
        movie.setPlaytime(movie.getPlaytime());
        movie.setYearOfRelease(movie.getYearOfRelease());
        movie.setAmountSold(movie.getAmountSold());
        movie.setDescription(movie.getDescription());
        movie.setOriginalStock(movie.getOriginalStock());
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

