import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import excel.Transaction;

public class DetailedTransaction extends JFrame{
    public DetailedTransaction(ArrayList<Transaction> transactions, Object merchant, Object amount, Object purpose, Object date){
        setSize(400,300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Transaction selectedTransaction = new Transaction("","","","","","","","","");
        
        for (Transaction transaction : transactions){
            // check for right transaction
            if (
                transaction.getMerchantName() == (String) merchant && 
                transaction.getTransactionAmount() == (String) amount && 
                transaction.getTransactionPurpose() == (String) purpose &&
                transaction.getDate() == (String) date
                ){
                    selectedTransaction = transaction;
                    System.out.println("FOUND");
                
            }
        }

        // create visuals

        JLabel headerLabel = new JLabel("Transaction Details" + "             " + selectedTransaction.getDate());
        headerLabel.setFont(new Font("Arial", Font.BOLD, 20));
        headerLabel.setForeground(Color.BLACK);
        headerLabel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        GridLayout gl = new GridLayout(0,1);
        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(gl);
        JLabel priceLabel = new JLabel("$" + selectedTransaction.getTransactionAmount());
        priceLabel.setFont(new Font("Arial", Font.BOLD, 20));
        priceLabel.setForeground(Color.BLACK);
        priceLabel.setBorder(BorderFactory.createEmptyBorder(0,30,10,10));

        JLabel descLabel = new JLabel("DIVISION: " + selectedTransaction.getDivision());
        descLabel.setFont(new Font("Arial", Font.BOLD, 20));
        descLabel.setForeground(Color.BLACK);
        descLabel.setBorder(BorderFactory.createEmptyBorder(-30,10,-20,10));

        JLabel merchantLabel = new JLabel(selectedTransaction.getMerchantName());
        merchantLabel.setFont(new Font("Arial", Font.BOLD, 20));
        merchantLabel.setForeground(Color.BLACK);
        merchantLabel.setBorder(BorderFactory.createEmptyBorder(-40,30,-20,0));

        JLabel purposeLabel = new JLabel(selectedTransaction.getTransactionPurpose());
        purposeLabel.setFont(new Font("Arial", Font.ITALIC, 20));
        purposeLabel.setForeground(Color.BLACK);
        purposeLabel.setBorder(BorderFactory.createEmptyBorder(-20,50,0,0));




        detailsPanel.add(headerLabel);
        detailsPanel.add(priceLabel);
        detailsPanel.add(descLabel);
        detailsPanel.add(merchantLabel);
        detailsPanel.add(purposeLabel);

        
        

        setLayout(new BorderLayout());
        add(detailsPanel, BorderLayout.NORTH);


        setVisible(true);
    }
}
