import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import excel.ExcelController;
import excel.Transaction;

public class Layout  extends JPanel {

    private JTable table;
    private DefaultTableModel tableModel;

    public Layout() throws IOException {
        setLayout(new BorderLayout());
        String[] columnNames = {"Date", "Amount", "Purpose","Merchant Name" };
        tableModel = new DefaultTableModel(columnNames, 0);
        String[] years = {"2011", "2012", "2013", "2014", "2015", "2016", "2017","2018", "2019", "2020", "2021"};
        JComboBox<String> comboBox = new JComboBox<String>(years);
        comboBox.setSelectedItem(years[0]);


        add(comboBox, BorderLayout.NORTH);
        ArrayList<Transaction> transactionList = ExcelController.createTransactionList((String) comboBox.getSelectedItem());
        createTransactions(transactionList);
        comboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    tableModel.setRowCount(0);
                    ArrayList<Transaction> transactionList = ExcelController.createTransactionList((String) comboBox.getSelectedItem());
                    createTransactions(transactionList);
                } catch (Exception e1) {
                    System.err.println("Error creating new list.");
                }
            }
        });


        // Create a table with the model
        table = new JTable(tableModel);
        table.setDefaultEditor(Object.class, null);
        // Add the table to a scroll pane
        JScrollPane scrollPane = new JScrollPane(table);

        // Add components to the panel
        add(scrollPane, BorderLayout.CENTER);
    }

    void createTransactions(ArrayList<Transaction> list) {
        for (Transaction transaction : list) {
            tableModel.addRow(new String[]{transaction.getDate(),transaction.getTransactionAmount(), transaction.getTransactionPurpose(),transaction.getMerchantName()});
        }
    }
}

