import java.io.IOException;

import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) throws IOException {
        JFrame frame = new JFrame("Transaction List");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.add(new Layout());
        // Make the frame visible
        frame.setLocationRelativeTo(null); // Center the frame
        frame.setVisible(true);
    }
}