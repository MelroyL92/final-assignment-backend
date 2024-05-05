package nl.novi.finalAssignmentBackend.dtos.product;

//created a dto for the product to exclude some fields for the response dto of both movies and the games



public class productResponseDTO {
    private Double sellingPrice;

    private Integer originalStock;

    private String description;

    private String name;

    private Integer currentStock;

    private Integer amountSold;


    private Integer yearOfRelease;


    public Integer getYearOfRelease() {
        return yearOfRelease;
    }

    public void setYearOfRelease(Integer yearOfRelease) {
        this.yearOfRelease = yearOfRelease;
    }

    public Double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(Double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public Integer getOriginalStock() {
        return originalStock;
    }

    public void setOriginalStock(Integer originalStock) {
        this.originalStock = originalStock;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAmountSold() {
        return amountSold;
    }

    public void setAmountSold(Integer amountSold) {
        this.amountSold = amountSold;
    }



    public Integer getCurrentStock() {
        return currentStock;
    }

    public void setCurrentStock(Integer currentStock) {

        if (amountSold != null) {
            this.currentStock = originalStock - amountSold;
        } else {
            this.currentStock = originalStock;
        }


    }

}
