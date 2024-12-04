import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
public class CurrencyConverterGUI {
    private static final String[] currencies = {
        "USD", "EUR", "GBP", "INR", "AUD", "CAD", "SGD", "JPY", "CNY", "ZAR"
    };
    private static final double[][] exchangeRates = {
        {1.00, 0.92, 0.78, 82.50, 1.35, 1.25, 1.36, 110.09, 6.45, 14.31},  // USD
        {1.08, 1.00, 0.85, 89.91, 1.47, 1.36, 1.48, 119.47, 7.02, 15.55},  // EUR
        {1.29, 1.17, 1.00, 105.91, 1.73, 1.60, 1.74, 140.77, 8.26, 18.29}, // GBP
        {0.012, 0.011, 0.0094, 1.0, 0.016, 0.015, 0.016, 1.33, 0.078, 0.17}, // INR
        {0.74, 0.68, 0.58, 61.20, 1.0, 0.92, 1.01, 81.42, 4.78, 10.56},    // AUD
        {0.80, 0.73, 0.63, 67.12, 1.09, 1.0, 1.10, 88.73, 5.20, 11.50},    // CAD
        {0.74, 0.68, 0.58, 61.05, 0.99, 0.91, 1.0, 80.69, 4.74, 10.47},    // SGD
        {0.0091, 0.0084, 0.0071, 0.75, 0.012, 0.011, 0.012, 1.0, 0.059, 0.13}, // JPY
        {0.16, 0.14, 0.12, 12.75, 0.21, 0.19, 0.21, 16.90, 1.0, 2.21},     // CNY
        {0.070, 0.064, 0.055, 5.77, 0.095, 0.087, 0.096, 7.78, 0.45, 1.0}  // ZAR
    };

    public static void main(String[] args) {
        // Create main frame
        JFrame frame = new JFrame("Currency Converter");
        frame.setSize(400, 250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(5, 2, 10, 10));
        // Currency selection components
        JLabel fromLabel = new JLabel("From:");
        JComboBox<String> fromCurrency = new JComboBox<>(currencies);

        JLabel toLabel = new JLabel("To:");
        JComboBox<String> toCurrency = new JComboBox<>(currencies);
        JLabel amountLabel = new JLabel("Amount:");
        JTextField amountField = new JTextField();
        JLabel resultLabel = new JLabel("Converted Amount:");
        JLabel resultField = new JLabel("0.0");
        JButton convertButton = new JButton("Convert");
        frame.add(fromLabel);
        frame.add(fromCurrency);
        frame.add(toLabel);
        frame.add(toCurrency);
        frame.add(amountLabel);
        frame.add(amountField);
        frame.add(resultLabel);
        frame.add(resultField);
        frame.add(new JLabel());  // Empty space
        frame.add(convertButton);

        // Action Listener for the Convert button
        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int fromIndex = fromCurrency.getSelectedIndex();
                    int toIndex = toCurrency.getSelectedIndex();
                    double amount = Double.parseDouble(amountField.getText());

                    // Get exchange rate and convert
                    double rate = exchangeRates[fromIndex][toIndex];
                    double convertedAmount = amount * rate;

                    // Display result
                    resultField.setText(String.format("%.2f", convertedAmount) + " " + currencies[toIndex]);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Please enter a valid amount.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Set frame visibility
        frame.setVisible(true);
    }
}