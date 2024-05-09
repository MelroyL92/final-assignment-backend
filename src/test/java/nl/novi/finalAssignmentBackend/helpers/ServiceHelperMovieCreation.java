package nl.novi.finalAssignmentBackend.helpers;

import nl.novi.finalAssignmentBackend.entities.Movie;
import nl.novi.finalAssignmentBackend.model.MovieModel;

public class ServiceHelperMovieCreation {

    public static Movie createMovie(
            Long id,
            Double purchasePrice,
            Double sellingPrice,
            Integer originalStock,
            Integer amountSold,
            Integer currentStock,
            String name,
            String director,
            Integer yearOfRelease,
            String description,
            String type,
            String genre,
            Integer watchTimeInMin
    ) {
        Movie movie = new Movie();
        movie.setId(id);
        movie.setPurchasePrice(purchasePrice);
        movie.setSellingPrice(sellingPrice);
        movie.setOriginalStock(originalStock);
        movie.setAmountSold(amountSold);
        movie.setCurrentStock(currentStock);
        movie.setName(name);
        movie.setDirector(director);
        movie.setYearOfRelease(yearOfRelease);
        movie.setDescription(description);
        movie.setType(type);
        movie.setGenre(genre);
        movie.setWatchTimeInMin(watchTimeInMin);
        return movie;
    }

    public static MovieModel createMovieModel(
            Double purchasePrice,
            Double sellingPrice,
            Integer originalStock,
            Integer amountSold,
            Integer currentStock,
            String name,
            String director,
            Integer yearOfRelease,
            String description,
            String type,
            String genre,
            Integer watchTimeInMin
    ){
        MovieModel movieModel = new MovieModel();
        movieModel.setPurchasePrice(purchasePrice);
        movieModel.setSellingPrice(sellingPrice);
        movieModel.setOriginalStock(originalStock);
        movieModel.setAmountSold(amountSold);
        movieModel.setCurrentStock(currentStock);
        movieModel.setName(name);
        movieModel.setDirector(director);
        movieModel.setYearOfRelease(yearOfRelease);
        movieModel.setDescription(description);
        movieModel.setType(type);
        movieModel.setGenre(genre);
        movieModel.setWatchTimeInMin(watchTimeInMin);
        return movieModel;
    }
}
