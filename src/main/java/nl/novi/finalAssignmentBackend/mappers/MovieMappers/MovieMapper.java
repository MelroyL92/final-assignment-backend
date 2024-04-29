package nl.novi.finalAssignmentBackend.mappers.MovieMappers;

import nl.novi.finalAssignmentBackend.entities.Movie;
import nl.novi.finalAssignmentBackend.mappers.EntityMapper;
import nl.novi.finalAssignmentBackend.model.MovieModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MovieMapper implements EntityMapper<MovieModel,Movie> {


    @Override
    public MovieModel fromEntity(Movie entity){
        if (entity == null){
            return null;
        }
        MovieModel model = new MovieModel();
        model.setId(entity.getId());
        model.setName(entity.getName());
        model.setDirector(entity.getDirector());
        model.setGenre(entity.getGenre());
        model.setType(entity.getType());
        model.setWatchTimeInMin(entity.getWatchTimeInMin());
        model.setDescription(entity.getDescription());
        model.setSellingPrice(entity.getSellingPrice());
        model.setYearOfRelease(entity.getYearOfRelease());
        model.setOriginalStock(entity.getOriginalStock());
        model.setAmountSold(entity.getAmountSold());
        model.setPurchasePrice(entity.getPurchasePrice());
        model.setCurrentStock(entity.getCurrentStock());
        return model;
    }

        @Override
    public Movie toEntity(MovieModel model){
        if (model == null){
            return null;
        }
        Movie entity = new Movie();
        entity.setId(model.getId());
        entity.setName(model.getName());
        entity.setDirector(model.getDirector());
        entity.setGenre(model.getGenre());
        entity.setType(model.getType());
        entity.setWatchTimeInMin(model.getWatchTimeInMin());
        entity.setDescription(model.getDescription());
        entity.setSellingPrice(model.getSellingPrice());
        entity.setYearOfRelease(model.getYearOfRelease());
        entity.setOriginalStock(model.getOriginalStock());
        entity.setPurchasePrice(model.getPurchasePrice());
        entity.setAmountSold(model.getAmountSold());
        entity.setCurrentStock(model.getCurrentStock());
        return entity;
    }


    public List<Movie> toEntityList(List<MovieModel> movieModels) {
        List<Movie> entityList = new ArrayList<>();
        for (MovieModel model : movieModels) {
            entityList.add(toEntity(model));
        }
        return entityList;
    }

    public List<MovieModel>fromEntity(List<Movie>movies){
        List<MovieModel>modelList = new ArrayList<>();
        for(Movie movie : movies){
            modelList.add(fromEntity(movie));
        }
        return modelList;
    }
}



// nog wel kijken naar de velden die de user niet mag zien en de admin wel zoals bijvoorbeeld de inkoopprijs





