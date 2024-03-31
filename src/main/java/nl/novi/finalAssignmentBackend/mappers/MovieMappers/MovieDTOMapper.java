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

