import java.io.IOException;
import java.util.Scanner;

//main for testing
public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter year to check (2011 - 2021): ");
        String input = scanner.nextLine();
        ExcelList e = new ExcelList(input);
    }
}