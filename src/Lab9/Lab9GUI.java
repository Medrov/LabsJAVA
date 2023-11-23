package Lab9;

import com.formdev.flatlaf.FlatDarkLaf;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Lab9GUI {

    private static JTextArea outputTextArea;

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        JFrame frame = new JFrame("Technical Service");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        inputPanel.setFont(inputPanel.getFont().deriveFont(16f)); // Установка размера шрифта для панели

        JLabel nameLabel = new JLabel("Client Name:");
        nameLabel.setFont(nameLabel.getFont().deriveFont(16f)); // Установка размера шрифта для метки
        JTextField nameField = new JTextField();
        nameField.setFont(nameField.getFont().deriveFont(16f)); // Установка размера шрифта для поля ввода

        JLabel typeLabel = new JLabel("Equipment Type:");
        typeLabel.setFont(typeLabel.getFont().deriveFont(16f)); // Установка размера шрифта для метки
        String[] types = {"Car", "Truck", "Computer", "Mobile Device"};
        JComboBox<String> typeComboBox = new JComboBox<>(types);
        typeComboBox.setFont(typeComboBox.getFont().deriveFont(16f)); // Установка размера шрифта для выпадающего списка

        JButton createButton = new JButton("Create");
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String clientName = nameField.getText();
                String selectedType = (String) typeComboBox.getSelectedItem();

                if (!clientName.isEmpty() && selectedType != null) {
                    String result = serve(clientName, selectedType);
                    outputTextArea.append(result + "\n");
                } else {
                    JOptionPane.showMessageDialog(frame, "Please enter the client name and select the equipment type.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        createButton.setFont(createButton.getFont().deriveFont(16f)); // Установка размера шрифта для кнопки

        inputPanel.add(nameLabel);
        inputPanel.add(nameField);
        inputPanel.add(typeLabel);
        inputPanel.add(typeComboBox);
        inputPanel.add(createButton);

        outputTextArea = new JTextArea(15, 30);
        outputTextArea.setEditable(false);
        outputTextArea.setFont(outputTextArea.getFont().deriveFont(16f)); // Установка размера шрифта для текстовой области

        JScrollPane scrollPane = new JScrollPane(outputTextArea);

        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    public static String serve(String clientName, String selectedType) {
        String result = clientName + " is servicing " + selectedType;
        return result;
    }
}
