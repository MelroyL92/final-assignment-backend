package nl.novi.finalAssignmentBackend.mappers.MovieMappers;


import nl.novi.finalAssignmentBackend.dtos.movie.ExtendedMovieResponseDTO;
import nl.novi.finalAssignmentBackend.model.MovieModel;
import org.springframework.stereotype.Component;

@Component
public class ExtendedMovieDTOMapper  extends  MovieDTOMapper{

    public ExtendedMovieResponseDTO toExtendedMovieDTO(MovieModel movie){
        return toExtendedMovieDTO(movie, new ExtendedMovieResponseDTO());
    }


    public<D extends  ExtendedMovieResponseDTO > D toExtendedMovieDTO(MovieModel movie, D target){
        target = super.toMovieDTO(movie, target);
        target.setPurchasePrice(movie.getPurchasePrice());
        return target;
    }
}
