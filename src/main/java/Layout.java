import test.Excel;
import test.ExcelList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

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
        ExcelList test = new ExcelList((String) comboBox.getSelectedItem());
        ArrayList<Transaction> transactionList = new ArrayList<>();
        final ArrayList<Excel>[] list = new ArrayList[]{test.getExcelList()};
        createTransactions(list, transactionList);
        comboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                transactionList.clear();
                tableModel.setRowCount(0);
                list[0].clear();
                try {
                    ExcelList test = new ExcelList((String) comboBox.getSelectedItem());
                    list[0] = test.getExcelList();
                    createTransactions(list, transactionList);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
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

    void createTransactions(ArrayList<Excel>[] list, ArrayList<Transaction> transactionList) {
        for (int i = 0; i < list[0].size(); i++) {
            for (int j = 0; j < 10; j++) {
                String[] array = (list[0].get(i).getLineAsStringArray(j));
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

                transactionList.add(transaction);
            }
        }
        for (Transaction transaction : transactionList) {
            tableModel.addRow(new String[]{transaction.getDate(),transaction.getTransactionAmount(), transaction.getTransactionPurpose(),transaction.getMerchantName()});
        }
    }
}

