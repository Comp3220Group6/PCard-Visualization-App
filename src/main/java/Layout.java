import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import excel.ExcelController;
import excel.Transaction;

public class Layout  extends JPanel {

    private JTable table;
    private DefaultTableModel tableModel;
    ArrayList<Transaction> transactionList;

    public Layout() throws IOException {
        setLayout(new BorderLayout());
        String[] columnNames = {"Merchant Name", "Amount", "Purpose","Date" };
        tableModel = new DefaultTableModel(columnNames, 0);
        String[] years = {"2011", "2012", "2013", "2014", "2015", "2016", "2017","2018", "2019", "2020", "2021"};
        JComboBox<String> comboBox = new JComboBox<String>(years);
        comboBox.setSelectedItem(years[0]);


        add(comboBox, BorderLayout.NORTH);
        transactionList = ExcelController.createTransactionList((String) comboBox.getSelectedItem());
        createTransactions(transactionList);
        comboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    tableModel.setRowCount(0);
                    transactionList = ExcelController.createTransactionList((String) comboBox.getSelectedItem());
                    createTransactions(transactionList);
                } catch (Exception e1) {
                    System.err.println("Error creating new list.");
                }
            }
        });


        // Create a table with the model
        table = new JTable(tableModel);
        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e){
                if (e.getClickCount() == 2){
                    // get selected row
                    int selectedRow = table.getSelectedRow();
                    DetailedTransaction detailedTransaction = new DetailedTransaction(transactionList,table.getValueAt(selectedRow,0),table.getValueAt(selectedRow,1),table.getValueAt(selectedRow,2),table.getValueAt(selectedRow,3));

                }
            }
        }
        );  
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(table.getModel());
        table.setRowSorter(sorter);
        List<RowSorter.SortKey> sortKeys = new ArrayList<>();
        sortKeys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
        sorter.setSortKeys(sortKeys);
        table.setDefaultEditor(Object.class, null);
        // Add the table to a scroll pane
        JScrollPane scrollPane = new JScrollPane(table);

        // Add components to the panel
        add(scrollPane, BorderLayout.CENTER);
    }

    void createTransactions(ArrayList<Transaction> list) {
        for (Transaction transaction : list) {
            tableModel.addRow(new String[]{transaction.getMerchantName(),transaction.getTransactionAmount(), transaction.getTransactionPurpose(),transaction.getDate()});
        }
    }
}

