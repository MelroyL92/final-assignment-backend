package nl.novi.finalAssignmentBackend.dtos.order;

public class ExtendedOrderResponseDTO extends OrderResponseDTO {

    private Double profit;


    public Double getProfit() {
        return profit;
    }

    public void setProfit(Double profit) {
        this.profit = profit;
    }
}
