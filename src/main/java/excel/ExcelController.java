package excel;

import java.io.IOException;
import java.util.ArrayList;

//Controller for the Excel related classes
public class ExcelController {

    //This function returns a list of transactions given a year
    public static ArrayList<Transaction> createTransactionList(String year) throws IOException {
        //Creates the excel list for the given year
        ExcelList e = new ExcelList(year);
        //Gets the array list of Excel files
        ArrayList<Excel> eList = e.getExcelList();
        //Creates an empty Transaction array list
        ArrayList<Transaction> transactions = new ArrayList<>();

        //For each excel file in the year, put j amt of transactions per file
        //in the list and return it
        for (int i = 0; i < eList.size(); i++) {
            try {
                for (int j = 0; j < 10; j++) {
                    String[] array = eList.get(i).getLineAsStringArray(j);
                    Transaction transaction = new Transaction(
                            array[0], // division
                            array[2], // date
                            array[4], // merchant
                            array[5], // amount
                            array[10], // purpose
                            array[1], // id
                            array[6], // gl account
                            array[7], // gl account desc
                            array[8]  // merchant type desc
                    );
    
                    transactions.add(transaction);
                }
            } catch (Exception ex) {
                System.err.println("Stopped getting indexes from Month: " + (i+1) + "\nProceeding...");
            }
        }
        return transactions;
    }

    //This function returns the list of excels in a specific year
    public static ArrayList<Excel> getExcelArrayList(String year) throws IOException {
        ExcelList list = new ExcelList(year);
        return list.getExcelList();
    }
}