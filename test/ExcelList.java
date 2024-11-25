import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

//This class contains the excel info in a list
public class ExcelList {
    public ArrayList<Excel> excelList = new ArrayList<Excel>();

    public ExcelList() throws IOException {
        //specific path for files
        File folder = new File("./expenditures/2013");
        File[] listOfFiles = folder.listFiles();
        //loops through files and adds them to the list
        if(listOfFiles != null) {
            for (File file : listOfFiles) {
                if (file.isFile()) {
                    System.out.println(file.getName());
                    Excel e;
                    excelList.add(e = new Excel(file.getAbsolutePath()));
                }
            }
        }
    }
}
