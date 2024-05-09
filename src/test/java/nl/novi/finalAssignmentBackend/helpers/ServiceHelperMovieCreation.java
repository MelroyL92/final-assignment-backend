package nl.novi.finalAssignmentBackend.helpers;

import nl.novi.finalAssignmentBackend.entities.Movie;
import nl.novi.finalAssignmentBackend.model.MovieModel;

public class ServiceHelperMovieCreation {

    public static Movie createMovie(
            long id,
            double purchasePrice,
            double sellingPrice,
            int originalStock,
            int amountSold,
            int currentStock,
            String name,
            String director,
            int yearOfRelease,
            String description,
            String type,
            String genre,
            int watchTimeInMin
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
            double purchasePrice,
            double sellingPrice,
            int originalStock,
            int amountSold,
            int currentStock,
            String name,
            String director,
            int yearOfRelease,
            String description,
            String type,
            String genre,
            int watchTimeInMin
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
