package ReportGeneratorFiles;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ReportController {
    private final DataFilter dataFilter;
    private final PDFReportGenerator pdfGenerator;

    public ReportController() {
        this.dataFilter = new DataFilter();
        this.pdfGenerator = new PDFReportGenerator();
    }

    public String generateReport(File[] selectedFiles, int filterField, String filterValue, String savePath) {
        try {
            // Process data by calling the processAllFiles method
            List<String[]> allData = dataFilter.processAllFiles(selectedFiles);

            // Validate filter Field
            if (!allData.isEmpty() && filterField >= allData.get(0).length) {
                return "Invalid filter selected for the dataset.";
            }

            // Filter data
            List<String[]> filteredData = dataFilter.filterData(allData, filterField, filterValue);

            // Generate PDF Report generation status message
            pdfGenerator.generatePDFReport(filteredData, filterField, filterValue, savePath);
            return "Report Generated Successfully at: " + savePath;
        } catch (IOException e) {
            return "Error generating report: " + e.getMessage();
        }
    }
}
