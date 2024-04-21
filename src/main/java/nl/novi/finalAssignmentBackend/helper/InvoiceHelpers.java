package nl.novi.finalAssignmentBackend.helper;

import jakarta.persistence.EntityNotFoundException;
import nl.novi.finalAssignmentBackend.Repository.InvoiceRepository;

import nl.novi.finalAssignmentBackend.entities.Game;
import nl.novi.finalAssignmentBackend.entities.Invoice;
import nl.novi.finalAssignmentBackend.entities.Movie;
import nl.novi.finalAssignmentBackend.entities.ShoppingList;
import org.springframework.stereotype.Component;

@Component
public class InvoiceHelpers {

    private final InvoiceRepository invoiceRepository;

    public InvoiceHelpers(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }


    public void calculateProfit(Long id) {
        Invoice invoice = invoiceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Invoice not found, the profit cannot be calculated"));

        double profit = 0.00;

        for (ShoppingList shoppingList : invoice.getShoppingList()) {
                for (Game game : shoppingList.getGames()) {
                    profit += calculateProfitGame(game);
                }
                for (Movie movie : shoppingList.getMovies()) {
                    profit += calculateProfitMovie(movie);
                }
                if(shoppingList.getDeliveryCost() == 0){
                    profit -= 10;
                }
            }

        invoice.setProfit(profit);
        invoiceRepository.save(invoice);
        }


    private Double calculateProfitGame(Game game) {
       return game.getSellingPrice() - game.getPurchasePrice();
    }

    private Double calculateProfitMovie(Movie movie) {
        return movie.getSellingPrice() - movie.getPurchasePrice();
    }



    public void calculateTotalPrice(Long id){
        Invoice invoice = invoiceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Invoice not found, the profit cannot be calculated"));

        double totalPrice = 0.00;

        for (ShoppingList shoppingList : invoice.getShoppingList()) {
            var subtotal = shoppingList.getSubtotal();
            var packaging = shoppingList.getPackagingCost();
            var delivery = shoppingList.getDeliveryCost();

            totalPrice += subtotal + packaging + delivery;
            invoice.setTotalPrice(totalPrice);
            invoiceRepository.save(invoice);

        }

    }


}






    // if deliverycost = 0 then profit - 10 (max delivery cost);

