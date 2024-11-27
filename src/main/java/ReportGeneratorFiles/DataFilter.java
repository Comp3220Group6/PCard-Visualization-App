package ReportGeneratorFiles;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
//import java.io.FileInputStream;
//import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataFilter {
    public List<String[]> processAllFiles(File[] files) throws IOException {
        List<String[]> allData = new ArrayList<>();
        for (File file : files) {
            allData.addAll(readExcelFile(file));
        }
        return allData;
    }

    //Method to read Excel files
    private List<String[]> readExcelFile(File file) throws IOException {
        List<String[]> data = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(file); Workbook workbook = new XSSFWorkbook(fis)) {
            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                int numCells = row.getLastCellNum();
                String[] rowData = new String[numCells];
                for (int i = 0; i < numCells; i++) {
                    Cell cell = row.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    rowData[i] = cell.toString().trim();
                }
                data.add(rowData);
            }
        }
        return data;
    }

    public List<String[]> filterData(List<String[]> data, int filterField, String filterValue) {
        List<String[]> filteredData = new ArrayList<>();
        for (String[] row : data) {
            if (row.length > filterField && row[filterField].equalsIgnoreCase(filterValue)) {
                filteredData.add(row);
            }
        }
        return filteredData;
    }
}

