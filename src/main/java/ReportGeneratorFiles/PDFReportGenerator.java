package ReportGeneratorFiles;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.IOException;
import java.util.List;

public class PDFReportGenerator {
    public void generatePDFReport(List<String[]> data, int filterField, String filterValue, String filePath) throws IOException {
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);

        PDPageContentStream contentStream = new PDPageContentStream(document, page);

        try {
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 18);
            contentStream.beginText();
            contentStream.newLineAtOffset(50, 750);
            contentStream.showText("PCard Spending Report");
            contentStream.newLineAtOffset(0, -25);
            contentStream.setFont(PDType1Font.HELVETICA, 12);
            contentStream.showText("Filtered By: " + getFieldName(filterField) + " = " + filterValue);
            contentStream.newLineAtOffset(0, -25);
            contentStream.showText("Report Generation Date: " + java.time.LocalDate.now());
            contentStream.newLineAtOffset(0, -25);
            contentStream.endText();

            float margin = 50;
            float yStart = 675;
            float yPosition = yStart;
            float lineSpacing = 15;

            contentStream.setFont(PDType1Font.HELVETICA, 10);
            for (String[] row : data) {
                if (yPosition - (lineSpacing * (row.length + 2)) < 50) {
                    contentStream.close();
                    page = new PDPage();
                    document.addPage(page);
                    contentStream = new PDPageContentStream(document, page);
                    contentStream.setFont(PDType1Font.HELVETICA, 10);
                    yPosition = yStart;
                }
                yPosition = writeBlockText(contentStream, row, yPosition, margin, lineSpacing);
            }
        } finally {
            contentStream.close();
            document.save(filePath);
            document.close();
        }
    }

    //Method to display the report as block text
    private float writeBlockText(PDPageContentStream contentStream, String[] row, float yPosition, float margin, float lineSpacing) throws IOException {
        String[] fieldDescriptions = {
                "Division", "BTrxId", "Transaction Date", "Card Posting Date", "Merchant Name",
                "Transaction Amount", "Transaction Currency", "Original Amount", "Original Currency",
                "G/L Account", "G/L Account Description", "Cost Center/WBS Element Order ID",
                "Cost Center/WBS Element Order Description", "Merchant Type", "Merchant Type Description", "Purpose"
        };

        contentStream.beginText();
        contentStream.newLineAtOffset(margin, yPosition);

        int maxIndex = Math.min(row.length, fieldDescriptions.length);
        for (int i = 0; i < maxIndex; i++) {
            contentStream.showText(fieldDescriptions[i] + ": " + row[i]);
            contentStream.newLineAtOffset(0, -lineSpacing);
        }

        contentStream.endText();
        return yPosition - (lineSpacing * maxIndex) - (lineSpacing * 2);
    }

    private String getFieldName(int index) {
        String[] fieldNames = {
                "Division", "BTrxId", "Transaction Date", "Card Posting Date", "Merchant Name",
                "Transaction Amount", "Transaction Currency", "Original Amount", "Original Currency",
                "G/L Account", "G/L Account Description", "Cost Center/WBS Element Order ID",
                "Cost Center/WBS Element Order Description", "Merchant Type", "Merchant Type Description", "Purpose"
        };
        return index >= 0 && index < fieldNames.length ? fieldNames[index] : "Unknown Field";
    }
}

