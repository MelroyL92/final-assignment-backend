package nl.novi.finalAssignmentBackend.helper.PDFCreator;

import nl.novi.finalAssignmentBackend.entities.Game;
import nl.novi.finalAssignmentBackend.entities.Order;
import nl.novi.finalAssignmentBackend.entities.Movie;
import nl.novi.finalAssignmentBackend.entities.ShoppingList;
import nl.novi.finalAssignmentBackend.exceptions.RecordNotFoundException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;
import java.io.IOException;


public class PdfFile {


    public void createPdf(Order order) throws IOException {

        if (order.getUser() == null) {
            throw new RecordNotFoundException("Order does not have a user associated with it.");
        }

        boolean hasGamesOrMovies = false;

        for (ShoppingList shoppingList : order.getShoppingList()) {
            if (!shoppingList.getGames().isEmpty() || !shoppingList.getMovies().isEmpty()) {
                hasGamesOrMovies = true;
                break;
            }
        }
        if (!hasGamesOrMovies) {
            throw new RecordNotFoundException("Invoice does not contain any games or movies.");
        }


        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.TIMES_BOLD), 12);

                float yPosition = 700;
                contentStream.beginText();
                contentStream.newLineAtOffset(100, yPosition);
                contentStream.showText("Ordernumber: " + order.getOrderNumber());
                yPosition -= 20;
                contentStream.newLineAtOffset(0, -20);
                contentStream.showText("Delivery date: " + order.getDeliveryDate());
                yPosition -= 20;
                contentStream.newLineAtOffset(0, -20);
                contentStream.showText("Ordered by: " + (order.getUser() != null ? order.getUser().getUsername() : ""));
                yPosition -= 20;
                contentStream.newLineAtOffset(0, -50);


                for (ShoppingList shoppingList : order.getShoppingList()) {
                    contentStream.newLineAtOffset(0, -0);
                    contentStream.showText("Shopping List ID: " + shoppingList.getId());
                    yPosition -= 20;

                    contentStream.newLineAtOffset(0, -20);
                    contentStream.showText("Subtotal: €" + shoppingList.getSubtotal());
                    yPosition -= 20;
                    contentStream.newLineAtOffset(0, -20);
                    contentStream.showText("Delivery cost: €" + shoppingList.getDeliveryCost());
                    yPosition -= 20;
                    contentStream.newLineAtOffset(0, -20);
                    contentStream.showText("Packaging cost: €" + shoppingList.getPackagingCost());
                    yPosition -= 20;
                    contentStream.newLineAtOffset(0, -40);


                    // Loop through games
                    for (Game game : shoppingList.getGames()) {
                        contentStream.newLineAtOffset(0, -20);
                        contentStream.showText("Game: " + game.getName() + " - Price: €" + game.getSellingPrice());
                        yPosition -= 20;
                    }

                    // Loop through movies
                    for (Movie movie : shoppingList.getMovies()) {
                        contentStream.newLineAtOffset(0, -20);
                        contentStream.showText("Movie: " + movie.getName() + " - Price: €" + movie.getSellingPrice());
                        yPosition -= 20;
                    }

                }
                contentStream.newLineAtOffset(0, -40);
                contentStream.showText("Total price: €" + order.getTotalPrice());
                contentStream.endText();
            }

            // Save the document
            document.save("order_" + order.getOrderNumber() + ".pdf");

        }
    }
}
