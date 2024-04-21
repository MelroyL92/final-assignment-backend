package nl.novi.finalAssignmentBackend.helper.PDFCreator;

import nl.novi.finalAssignmentBackend.entities.Invoice;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;

import java.awt.*;
import java.io.IOException;

public class PdfFile {


    public void createPdf(Invoice invoice) throws IOException {
        PDDocument document = new PDDocument();
        PDPage page = new PDPage(PDRectangle.A4);
        document.addPage(page);

        PDPageContentStream contentStream = new PDPageContentStream(document, page);
        contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.TIMES_BOLD),10);
        contentStream.beginText();
        contentStream.newLineAtOffset(100, 700);
        contentStream.showText("Invoice number: " + invoice.getOrderNumber());
        contentStream.newLineAtOffset(0, -20);
        contentStream.showText("Delivery date: " + invoice.getDeliveryDate());
        contentStream.newLineAtOffset(0, -20);
        contentStream.showText("Ordered by: " + invoice.getUser().getUsername());//add null exception
        contentStream.newLineAtOffset(0, -20);
        contentStream.showText("ordered games : " + invoice.getShoppingList().get(0).getGames().get(0).getName());// perhaps make a loop to show the name/prices and such
        contentStream.newLineAtOffset(0, -20);
        contentStream.showText("ordered movies " + invoice.getShoppingList().get(0).getMovies().get(0).getName());// do the same for this one if thats a option within the library
        contentStream.newLineAtOffset(0, -20);
        contentStream.showText("subtotal  € " + invoice.getShoppingList().get(0).getSubtotal());
        contentStream.newLineAtOffset(0, -20);
        contentStream.showText("Delivery cost is  € " + invoice.getShoppingList().get(0).getDeliveryCost());
        contentStream.newLineAtOffset(0, -20);
        contentStream.showText("packaging cost is  € " + invoice.getShoppingList().get(0).getPackagingCost());
        contentStream.newLineAtOffset(0, -20);
        contentStream.showText("total price  € " + invoice.getTotalPrice());
                contentStream.endText();
        contentStream.close();

        // Save the document
        document.save("invoice_" + invoice.getOrderNumber() + ".pdf");

        // Close the document
        document.close();
    }
}