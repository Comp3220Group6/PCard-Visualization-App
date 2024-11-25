import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

import test.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

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