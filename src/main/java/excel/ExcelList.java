package excel;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

//This class contains the excel info in a list
public class ExcelList {
    public ArrayList<Excel> excelList = new ArrayList<Excel>();

    public ExcelList() {}

    public ExcelList(String year) throws IOException {
        //specific path for files
        System.out.println();
        File folder = new File("./src/main/java/expenditures/"+year);//currently set to 2013 (will need to be changed to work with any/filter)
        System.out.println(folder.getName());
        File[] listOfFiles = folder.listFiles();
        //loops through files and adds them to the list
        if(listOfFiles != null) {
            for (File file : listOfFiles) {
                if (file.isFile()) {
                    Excel e;
                    excelList.add(e = new Excel(file.getAbsolutePath()));
                }
            }
        }
        System.out.println(excelList.size());
        float sum = 0;
        for (Excel e : excelList) {
            sum += e.getSumOfTrans();
        }
        System.out.println(sum);
    }

    public ArrayList<Excel> getExcelList(){
        return excelList;
    }
}
