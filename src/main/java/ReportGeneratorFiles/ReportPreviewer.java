package ReportGeneratorFiles;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class ReportPreviewer{
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("PCard Expense Report Generator");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(500, 500);

            // Create an instance or object of RePortController
            ReportController controller = new ReportController();

            // Set layout
            JPanel mainPanel = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(10, 10, 10, 10); // Padding
            gbc.fill = GridBagConstraints.HORIZONTAL;

            // Filters Field names or lables and Dropdown
            JLabel filterFieldLabel = new JLabel("Generate Report Based On:");
            filterFieldLabel.setHorizontalAlignment(SwingConstants.CENTER);
            JComboBox<String> filterFieldDropdown = new JComboBox<>(new String[]{
                    "Division", "BTrxId", "Transaction Date", "Card Posting Date", "Merchant Name",
                    "Transaction Amount", "Transaction Currency", "Original Amount", "Original Currency",
                    "G/L Account", "G/L Account Description", "Cost Center/WBS Element Order ID",
                    "Cost Center/WBS Element Order Description", "Merchant Type", "Merchant Type Description", "Purpose"
            });

            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 2;
            mainPanel.add(filterFieldLabel, gbc);

            gbc.gridy = 1;
            mainPanel.add(filterFieldDropdown, gbc);

            // Filter Value Label and Text Field
            JLabel filterValueLabel = new JLabel("Enter Field Value:");
            filterValueLabel.setHorizontalAlignment(SwingConstants.CENTER);
            JTextField filterValueField = new JTextField(20);

            gbc.gridy = 2;
            mainPanel.add(filterValueLabel, gbc);

            gbc.gridy = 3;
            mainPanel.add(filterValueField, gbc);

            // File Selection Button and Label
            JButton selectFilesButton = new JButton("Select Excel Files");
            JLabel fileLabel = new JLabel("No files selected");
            fileLabel.setHorizontalAlignment(SwingConstants.CENTER);

            gbc.gridy = 4;
            mainPanel.add(selectFilesButton, gbc);

            gbc.gridy = 5;
            mainPanel.add(fileLabel, gbc);

            // Clear Files Button
            JButton clearFilesButton = new JButton("Clear Selected Files");

            gbc.gridy = 6;
            mainPanel.add(clearFilesButton, gbc);

            // Save Location Button and Label
            JButton saveLocationButton = new JButton("Choose Location To Save Report");
            JLabel saveLabel = new JLabel("Default: Report.pdf");
            saveLabel.setHorizontalAlignment(SwingConstants.CENTER);

            gbc.gridy = 7;
            mainPanel.add(saveLocationButton, gbc);

            gbc.gridy = 8;
            mainPanel.add(saveLabel, gbc);

            // Generate Report Button
            JButton generateButton = new JButton("Generate PDF Report");

            gbc.gridy = 9;
            mainPanel.add(generateButton, gbc);

            frame.add(mainPanel);

            // File chooser setup
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setMultiSelectionEnabled(true);
            final File[][] selectedFiles = {null}; // To hold selected files
            String[] savePath = { "Report.pdf" }; // Default save path

            // Button Actions
            selectFilesButton.addActionListener(e -> {
                int result = fileChooser.showOpenDialog(frame);
                if (result == JFileChooser.APPROVE_OPTION) {
                    selectedFiles[0] = fileChooser.getSelectedFiles();
                    fileLabel.setText(selectedFiles[0].length + " file(s) selected");
                }
            });

            clearFilesButton.addActionListener(e -> {
                selectedFiles[0] = null;
                fileLabel.setText("No files selected");
            });

            saveLocationButton.addActionListener(e -> {
                JFileChooser saveChooser = new JFileChooser();
                saveChooser.setDialogTitle("Choose Location To Save Report");
                saveChooser.setSelectedFile(new File("Report.pdf"));
                int saveOption = saveChooser.showSaveDialog(frame);
                if (saveOption == JFileChooser.APPROVE_OPTION) {
                    File saveFile = saveChooser.getSelectedFile();
                    savePath[0] = saveFile.getAbsolutePath();
                    saveLabel.setText("Save Report In: " + savePath[0]);
                }
            });

            generateButton.addActionListener(e -> {
                if (selectedFiles[0] == null || selectedFiles[0].length == 0) {
                    JOptionPane.showMessageDialog(frame, "Please select files first.");
                    return;
                }

                String filterValue = filterValueField.getText();
                if (filterValue.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Please enter a field value.");
                    return;
                }

                int filterField = filterFieldDropdown.getSelectedIndex();
                String result = controller.generateReport(selectedFiles[0], filterField, filterValue, savePath[0]);
                JOptionPane.showMessageDialog(frame, result);
            });

            frame.setVisible(true);
        });
    }
}
