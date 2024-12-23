import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

//This class contains the excel files in a list
public class ExcelList {
    public ArrayList<Excel> excelList = new ArrayList<>();
    private String year;

    public ExcelList(String yearToList) throws IOException {
        //specific path for files
        try {
            File folder = new File("./expenditures/" + year);//currently set to 2013 (will need to be changed to work with any/filter)
            File[] listOfFiles = folder.listFiles();
            year = yearToList;
            //loops through files and adds them to the list
            if(listOfFiles != null) {
                for (File file : listOfFiles) {
                    if (file.isFile()) {
                        System.out.println(file.getName());
                        Excel e = new Excel(file.getAbsolutePath());
                        excelList.add(e);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Likely invalid year.");
        }
    }

    public String getYearAsString() {
        return year;
    }
}