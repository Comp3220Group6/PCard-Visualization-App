import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

//This class represents one excel file, but in our case they are actually CSVs
public class Excel {
    
    //Arrays for storing the stripped data
    public String[] columnNames;
    public ArrayList<String> division = new ArrayList<>();
    public ArrayList<String> transactionid = new ArrayList<>();
    public ArrayList<String> transactiondate = new ArrayList<>();
    public ArrayList<String> cardpostdate = new ArrayList<>();
    public ArrayList<String> merchant = new ArrayList<>();
    public ArrayList<String> transactionamt = new ArrayList<>();
    public ArrayList<String> glaccount = new ArrayList<>();
    public ArrayList<String> glaccountdesc = new ArrayList<>();
    public ArrayList<String> merchanttype = new ArrayList<>();
    public ArrayList<String> merchanttypedesc = new ArrayList<>();
    public ArrayList<String> purpose = new ArrayList<>();

    //Constructor used to call the setup function with a specific path (ONLY USES CSVs IN THE FORMAT OF THE PCARD EXCEL FILES)
    public Excel(String path) throws IOException {
        this.setup(path);
    }
    
    //Function that sets up the array lists with data
    private void setup(String path) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(new File(path)))) {//Opens a buffered reader with a specific path
            //Gets the first line of data; the column names
            String currentString = br.readLine();
            columnNames = currentString.split("\t");
            
            //Gets the rest of the lines
            while((currentString = br.readLine()) != null) {
                
                //This section is specifically to replace commas and quotations around the floating point transaction amounts
                //As well as to remove tabs
                currentString = currentString.replace("\"", "");
                currentString = currentString.replace(",", "");
                String[] currentLine = currentString.split("\t");
                
                //Add to the arrays
                try {
                    //This if statement is so that the sums that appear between sections do not get their info stripped
                    //Can be changed if needed
                    if (!"".equals(currentLine[0])) {
                        division.add(currentLine[0]);
                        transactionid.add(currentLine[1]);
                        transactiondate.add(currentLine[2]);
                        cardpostdate.add(currentLine[3]);
                        merchant.add(currentLine[4]);
                        transactionamt.add(currentLine[5]);
                        glaccount.add(currentLine[9]);
                        glaccountdesc.add(currentLine[10]);
                        merchanttype.add(currentLine[13]);
                        merchanttypedesc.add(currentLine[14]);
                        purpose.add(currentLine[15]);
                    }
                } catch (Exception e) {
                    // TODO: handle exception
                }
            }
            br.close();
        }
    }

    //Sums the transactions
    public float getSumOfTrans() {
        float sum = 0;
        for (String f : transactionamt) {
            sum += Float.parseFloat(f);
        }
        return sum;
    }

    //Gets all unique divisions for a file
    public String[] getUniqueDivisions() {
        ArrayList<String> uniqueDivList = new ArrayList<>();
        for (String string : division) {
            if (!uniqueDivList.contains(string)) {
                uniqueDivList.add(string);
            }
        }
        String[] uniqueDivs = new String[uniqueDivList.size()];
        return uniqueDivs = uniqueDivList.toArray(uniqueDivs);
    }

    //Gets a row of the file as a String
    public String getLineAsString(int row) {
        String line = division.get(row) + " " + transactionid.get(row) + " " + transactiondate.get(row) + " " + cardpostdate.get(row) + " " + merchant.get(row) + " " + transactionamt.get(row) + " " + glaccount.get(row) + " " + glaccountdesc.get(row) + " " + merchanttype.get(row) + " " + merchanttypedesc.get(row) + " " + purpose.get(row);
        return line;
    }

    //Gets a row of the file as an array of Strings
    public String[] getLineAsStringArray(int row) {
        String[] line = {division.get(row), transactionid.get(row), transactiondate.get(row), cardpostdate.get(row), merchant.get(row), transactionamt.get(row), glaccount.get(row), glaccountdesc.get(row), merchanttype.get(row), merchanttypedesc.get(row), purpose.get(row)};
        return line;
    }
}