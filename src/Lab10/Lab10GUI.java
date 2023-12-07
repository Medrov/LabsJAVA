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
import java.io.FileWriter;
import java.io.IOException;

public class Lab10GUI {

    private JTextField brandField, modelField, yearField, priceField;
    private DefaultTableModel tableModel;
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Lab10GUI::runApp);
    }

    private static void runApp() {
        try {
            UIManager.setLookAndFeel(new FlatMacLightLaf());
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

        Font textFont = new Font("Arial", Font.PLAIN, 16);

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem createItem = new JMenuItem("Create Record");
        createItem.addActionListener(e -> showRecordDialog(null));
        JMenuItem deleteItem = new JMenuItem("Delete Record");
        deleteItem.addActionListener(e -> deleteRecord());
        JMenuItem saveItem = new JMenuItem("Save to File");
        saveItem.addActionListener(e -> saveToFile());

        fileMenu.add(createItem);
        fileMenu.add(deleteItem);
        fileMenu.add(saveItem);

        JMenu editMenu = new JMenu("Edit");
        JMenuItem editValueItem = new JMenuItem("Edit Value");
        editValueItem.addActionListener(e -> editValue());
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(e -> System.exit(0));

        editMenu.add(editValueItem);
        editMenu.add(exitItem);

        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        frame.setJMenuBar(menuBar);

        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);

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

        JButton addButton = new JButton("Добавить");
        addButton.addActionListener(e -> addRecord());
        addButton.setFont(textFont);
        inputPanel.add(addButton, gbc);

        gbc.gridy++;
        JButton saveButton = new JButton("Сохранить в файл");
        saveButton.addActionListener(e -> saveToFile());
        saveButton.setFont(textFont);
        inputPanel.add(saveButton, gbc);

        String[] columns = {"Марка", "Модель", "Год", "Цена", "Удалить"};
        tableModel = new DefaultTableModel(columns, 0);

        JTable table = new JTable(tableModel) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column != 4;
            }
        };

        table.getColumn("Удалить").setCellRenderer(new ButtonRenderer());
        table.getColumn("Удалить").setCellEditor(new ButtonEditor(new JCheckBox(), tableModel));
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int column = table.getColumnModel().getColumnIndexAtX(e.getX());
                int row = e.getY() / table.getRowHeight();

                if (row < table.getRowCount() && row >= 0 && column < table.getColumnCount() && column >= 0) {
                    Object value = table.getValueAt(row, column);
                    if (value instanceof JButton) {
                        ((JButton) value).doClick();
                    }
                }
            }
        });
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        frame.getContentPane().add(panel);
    }

    private void showRecordDialog(String[] recordData) {
        JFrame recordFrame = new JFrame(recordData == null ? "Добавить запись" : "Изменить запись");
        recordFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        recordFrame.setLocationRelativeTo(null);
        recordFrame.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);

        Font textFont = new Font("Arial", Font.PLAIN, 16);

        JLabel brandLabel = new JLabel("Марка:");
        brandLabel.setFont(textFont);
        inputPanel.add(brandLabel, gbc);

        gbc.gridy++;
        JLabel modelLabel = new JLabel("Модель:");
        modelLabel.setFont(textFont);
        inputPanel.add(modelLabel, gbc);

        //... добавляем остальные метки для других полей ...

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        brandField = new JTextField(recordData != null ? recordData[0] : "");
        brandField.setFont(textFont);
        inputPanel.add(brandField, gbc);

        gbc.gridy++;
        modelField = new JTextField(recordData != null ? recordData[1] : "");
        modelField.setFont(textFont);
        inputPanel.add(modelField, gbc);

        //... добавляем остальные текстовые поля ...

        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JButton saveButton = new JButton(recordData == null ? "Добавить" : "Изменить");
        saveButton.addActionListener(e -> {
            if (recordData == null) {
                addRecord();
            } else {
                updateRecord(recordData);
            }
            recordFrame.dispose();
        });
        saveButton.setFont(textFont);
        inputPanel.add(saveButton, gbc);

        recordFrame.add(inputPanel, BorderLayout.NORTH);
        recordFrame.pack();
        recordFrame.setVisible(true);
    }

    private void addRecord() {
        String brand = brandField.getText();
        String model = modelField.getText();
        String year = yearField.getText();
        String price = priceField.getText();

        Object[] rowData = {brand, model, year, price};
        tableModel.addRow(rowData);

        // Очистка полей после добавления записи
        brandField.setText("");
        modelField.setText("");
        yearField.setText("");
        priceField.setText("");
    }

    private void updateRecord(String[] recordData) {
        // Logic to update record with provided recordData
        // Example: displayArea.replaceRange(updatedRecord, start, end);
    }

    private void saveToFile() {
        //String content = displayArea.getText();
        String content = "";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("автомобили.txt"))) {
            writer.write(content);
            writer.flush();
            showAlert("Успех", "Данные сохранены в файл");
        } catch (IOException e) {
            showAlert("Ошибка", "Ошибка при сохранении данных");
        }
    }

    private void editValue() {
        //String selectedRecord = displayArea.getSelectedText();
        String selectedRecord = "displayArea.getSelectedText()";
        if (selectedRecord != null) {
            // Splitting the selected record into individual fields
            String[] fields = selectedRecord.split(", ");
            if (fields.length == 4) {
                // Extracting values and populating text fields for editing
                String brand = fields[0].substring(fields[0].indexOf(": ") + 2);
                String model = fields[1].substring(fields[1].indexOf(": ") + 2);
                String year = fields[2].substring(fields[2].indexOf(": ") + 2);
                String price = fields[3].substring(fields[3].indexOf(": ") + 2);

                brandField.setText(brand);
                modelField.setText(model);
                yearField.setText(year);
                priceField.setText(price);
            }
        }
    }

    private void deleteRecord() {

        JTable table = new JTable(tableModel);// Получаем текущую таблицу, предположим, что у вас есть метод, возвращающий текущую таблицу

        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            table.remove(selectedRow);
        } else {
            JOptionPane.showMessageDialog(null, "Выберите строку для удаления", "Ошибка", JOptionPane.ERROR_MESSAGE);
        }

    }

    private void showAlert(String title, String message) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
    }
}
