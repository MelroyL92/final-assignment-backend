package nl.novi.finalAssignmentBackend.model;


// geen id of entity gezien er nooit een instantie gemaakt zal worden van de productklasse!

public abstract class ProductModel {

    private Integer price;

    private Integer originalStock;

    private String description;

    private String name;

    private Integer amountSold;

    private String yearOfRelease;




    public ProductModel() {

    }


    public ProductModel(Integer price, Integer originalStock, String description, String name, Integer amountSold, String yearOfRelease){
        this.price = price;
        this.originalStock = originalStock;
        this.description = description;
        this.name = name;
        this.amountSold = amountSold;
        this.yearOfRelease = yearOfRelease;
    }

    public String getYearOfRelease() {
        return yearOfRelease;
    }

    public void setYearOfRelease(String yearOfRelease) {
        this.yearOfRelease = yearOfRelease;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
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
}
