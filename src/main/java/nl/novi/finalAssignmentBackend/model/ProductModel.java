package nl.novi.finalAssignmentBackend.model;



public abstract class ProductModel {


    private Double sellingPrice;

    private Integer originalStock;

    private String description;

    private String name;

    private Integer amountSold;

    private Integer yearOfRelease;

    public Integer getYearOfRelease() {
        return yearOfRelease;
    }

    public void setYearOfRelease(Integer yearOfRelease) {
        this.yearOfRelease = yearOfRelease;
    }

    private Double purchasePrice;

    private Integer currentStock;


    public Double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(Double price) {
        this.sellingPrice = price;
    }

    public Double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(Double purchasePrice) {
        this.purchasePrice = purchasePrice;
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
        this.currentStock = originalStock - amountSold;
    }
}
