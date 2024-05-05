package nl.novi.finalAssignmentBackend.dtos.movie;

public class ExtendedMovieResponseDTO extends  MovieResponseDTO{

    private Double purchasePrice;


    public Double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(Double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }
}
