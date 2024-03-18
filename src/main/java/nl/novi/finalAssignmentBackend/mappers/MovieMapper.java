package nl.novi.finalAssignmentBackend.mappers;

import nl.novi.finalAssignmentBackend.dtos.movie.MovieResponseDto;
import nl.novi.finalAssignmentBackend.model.MovieModel;
import nl.novi.finalAssignmentBackend.model.ProductModel;

public class MovieMapper {

    public MovieResponseDto toMovieDto (MovieModel MovieModel){
        MovieResponseDto dto = new MovieResponseDto();
        dto.setId(MovieModel.getId());
        dto.setDirector(MovieModel.getDirector());
        dto.setGenre(MovieModel.getGenre());
        dto.setType(MovieModel.getType());
        dto.setPlaytime(MovieModel.getPlaytime());
        dto.setDescription(MovieModel.getDescription());
        dto.setSellingPrice(MovieModel.getSellingPrice());
        dto.setYearOfRelease(MovieModel.getYearOfRelease());
        return dto;
    }




}
