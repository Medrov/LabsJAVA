package Lab10;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.formdev.flatlaf.themes.FlatMacLightLaf;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Lab10GUI {

    private JTextField brandField, modelField, yearField, priceField;
    private DefaultTableModel tableModel;

    JTable table;
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Lab10GUI::runApp);
    }

    private static void runApp() {
        try {
            UIManager.setLookAndFeel(new FlatMacDarkLaf());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        JFrame frame = new JFrame("Управление автомобилями");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Lab10GUI app = new Lab10GUI();
        app.createUI(frame);

        frame.pack();
        frame.setVisible(true);
    }

    private void createUI(JFrame frame) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem createItem = new JMenuItem("Create Record");
        createItem.addActionListener(e -> showRecordDialog());
        JMenuItem deleteItem = new JMenuItem("Delete Record");
        deleteItem.addActionListener(e -> deleteSelectedRecord());
        JMenuItem saveItem = new JMenuItem("Save to File");
        saveItem.addActionListener(e -> saveToFile());

        fileMenu.add(createItem);
        fileMenu.add(deleteItem);
        fileMenu.add(saveItem);

        JMenu editMenu = new JMenu("Edit");
        JMenuItem editValueItem = new JMenuItem("Edit Value");
        editValueItem.addActionListener(e -> updateRecord((String[]) getRecordData()));
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(e -> System.exit(0));

        editMenu.add(editValueItem);
        editMenu.add(exitItem);

        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        frame.setJMenuBar(menuBar);

        String[] columns = {"Марка", "Модель", "Год", "Цена"};
        tableModel = new DefaultTableModel(columns, 0);

        table = new JTable(tableModel) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column != 4;
            }
        };

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        panel.add(scrollPane, BorderLayout.CENTER);

        frame.getContentPane().add(panel);
    }

    private void showRecordDialog() {
        JFrame recordFrame = new JFrame( "Добавить запись" );
        recordFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        recordFrame.setLocationRelativeTo(null);
        recordFrame.setLayout(new BorderLayout());
        Font textFont = new Font("Arial", Font.PLAIN, 16);

        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(4, 16, 4, 16);

        JLabel brandLabel = new JLabel("Марка:");
        brandLabel.setFont(textFont);
        inputPanel.add(brandLabel, gbc);

        gbc.gridy++;
        JLabel modelLabel = new JLabel("Модель:");
        modelLabel.setFont(textFont);
        inputPanel.add(modelLabel, gbc);

        gbc.gridy++;
        JLabel yearLabel = new JLabel("Год:");
        yearLabel.setFont(textFont);
        inputPanel.add(yearLabel, gbc);

        gbc.gridy++;
        JLabel priceLabel = new JLabel("Цена:");
        priceLabel.setFont(textFont);
        inputPanel.add(priceLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        brandField = new JTextField();
        brandField.setFont(textFont);
        inputPanel.add(brandField, gbc);

        gbc.gridy++;
        modelField = new JTextField();
        modelField.setFont(textFont);
        inputPanel.add(modelField, gbc);

        gbc.gridy++;
        yearField = new JTextField();
        yearField.setFont(textFont);
        inputPanel.add(yearField, gbc);

        gbc.gridy++;
        priceField = new JTextField();
        priceField.setFont(textFont);
        inputPanel.add(priceField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JButton addButton = new JButton( "Добавить");
        addButton.addActionListener(e -> {
            addRecord();
            recordFrame.dispose();
        });
        addButton.setFont(textFont);
        inputPanel.add(addButton, gbc);

        gbc.gridy++;
        recordFrame.add(inputPanel, BorderLayout.NORTH);
        recordFrame.pack();
        recordFrame.setVisible(true);
    }
    public String[] getRecordData(){
        String brand = brandField.getText();
        String model = modelField.getText();
        String year = yearField.getText();
        String price = priceField.getText();

        return new String[]{brand, model, year, price};
    }

    private void addRecord() {
        String brand = brandField.getText();
        String model = modelField.getText();
        String year = yearField.getText();
        String price = priceField.getText();

        if (tryParseInt(year) && tryParseDouble(price)) {
            Object[] data = {brand, model, year, price};
            tableModel.addRow(data);

            brandField.setText("");
            modelField.setText("");
            yearField.setText("");
            priceField.setText("");
        } else {
            showError("Ошибка", "Неверно написаны числовые данные");
        }
    }

    private void updateRecord(String[] recordData) {
        JFrame editFrame = new JFrame("Редактировать запись");
        editFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        editFrame.setLayout(new BorderLayout());
        editFrame.setLocationRelativeTo(null);

        Font textFont = new Font("Arial", Font.PLAIN, 16);

        JPanel editPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(4, 16, 4, 16);

        JLabel brandLabel = new JLabel("Марка:");
        brandLabel.setFont(textFont);
        editPanel.add(brandLabel, gbc);

        gbc.gridy++;
        JLabel modelLabel = new JLabel("Модель:");
        modelLabel.setFont(textFont);
        editPanel.add(modelLabel, gbc);

        gbc.gridy++;
        JLabel yearLabel = new JLabel("Год:");
        yearLabel.setFont(textFont);
        editPanel.add(yearLabel, gbc);

        gbc.gridy++;
        JLabel priceLabel = new JLabel("Цена:");
        priceLabel.setFont(textFont);
        editPanel.add(priceLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        brandField = new JTextField();
        brandField.setFont(textFont);
        editPanel.add(brandField, gbc);

        gbc.gridy++;
        modelField = new JTextField();
        modelField.setFont(textFont);
        editPanel.add(modelField, gbc);

        gbc.gridy++;
        yearField = new JTextField();
        yearField.setFont(textFont);
        editPanel.add(yearField, gbc);

        gbc.gridy++;
        priceField = new JTextField();
        priceField.setFont(textFont);
        editPanel.add(priceField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;


        JButton saveButton = new JButton("Сохранить");
        saveButton.addActionListener(e -> {
            if (tryParseInt(yearField.getText()) && tryParseDouble(priceField.getText())) {
                String[] newData = getRecordData();
                int selectedRow = table.getSelectedRow();
                for (int i = 0; i < newData.length; i++) {
                    tableModel.setValueAt(newData[i], selectedRow, i);
                }
            } else {
                showError("Ошибка", "Неверно написаны числовые данные");
            }
            editFrame.dispose();
        });

        saveButton.setFont(textFont);
        editPanel.add(saveButton);

        editFrame.add(editPanel, BorderLayout.NORTH);
        editFrame.pack();
        editFrame.setVisible(true);
    }

    private void saveToFile() {
        File file = new File("автомобили.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (int i = 0; i < table.getRowCount(); i++) {
                for (int j = 0; j < table.getColumnCount(); j++) {
                    Object cellValue = table.getValueAt(i, j);
                    writer.write(cellValue.toString());
                    if (j < table.getColumnCount() - 1) {
                        writer.write("\t"); // Добавляем табуляцию между ячейками
                    }
                }
                writer.newLine(); // Переходим на следующую строку после завершения строки таблицы
            }
            showAlert("Успех", "Данные сохранены в файл");
        } catch (IOException e) {
            showAlert("Ошибка", "Ошибка при сохранении данных");
            e.printStackTrace(); // Обработайте исключение по вашему усмотрению
        }
    }

    public void deleteSelectedRecord() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            tableModel.removeRow(selectedRow);
            showAlert("Успех", "Данные удалены");
        }
    }

    private void showAlert(String title, String message) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
    }
    private void showError(String title, String message) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE);
    }

    private boolean tryParseInt(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean tryParseDouble(String value) {
        try {
            Double.parseDouble(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
