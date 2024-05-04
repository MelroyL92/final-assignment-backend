package nl.novi.finalAssignmentBackend.dtos.game;


public class ExtendedGameResponseDTO extends GameResponseDTO {


    private Double purchasePrice;


    public Double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(Double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }
}