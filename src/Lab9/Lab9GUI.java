package Lab9;

import Lab7.*;
import Lab7.services.TechnicalService;
import Lab7.technics.bunch.Car;
import Lab7.technics.bunch.Computer;
import Lab7.technics.bunch.MobileDevice;
import Lab7.technics.bunch.Truck;
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

        JFrame frame = new JFrame("Техническое обслуживание");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        inputPanel.setFont(inputPanel.getFont().deriveFont(16f)); // Установка размера шрифта для панели

        JLabel nameLabel = new JLabel("Имя клиента:");
        nameLabel.setFont(nameLabel.getFont().deriveFont(16f)); // Установка размера шрифта для метки
        JTextField nameField = new JTextField();
        nameField.setFont(nameField.getFont().deriveFont(16f)); // Установка размера шрифта для поля ввода

        JLabel typeLabel = new JLabel("Тип оборудования:");
        typeLabel.setFont(typeLabel.getFont().deriveFont(16f)); // Установка размера шрифта для метки
        String[] types = {"Автомобиль", "Грузовик", "Компьютер", "Мобильное устройство"};
        JComboBox<String> typeComboBox = new JComboBox<>(types);
        typeComboBox.setFont(typeComboBox.getFont().deriveFont(16f)); // Установка размера шрифта для выпадающего списка

        JButton createButton = new JButton("Создать");
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String clientName = nameField.getText();
                String selectedType = (String) typeComboBox.getSelectedItem();
                String result;
                if (!clientName.isEmpty() && selectedType != null) {
                    switch (selectedType) {
                        case "Автомобиль":
                            Car car = new Car(clientName);
                            result = serve(clientName, car);
                            outputTextArea.append(result + "\n");
                            break;
                        case "Грузовик":
                            Truck truck = new Truck(clientName);
                            result = serve(clientName, truck);
                            outputTextArea.append(result + "\n");
                            break;
                        case "Компьютер":
                            Computer computer = new Computer(clientName);
                            result = serve(clientName, computer);
                            outputTextArea.append(result + "\n");
                            break;
                        case "Мобильное устройство":
                            MobileDevice mobileDevice = new MobileDevice(clientName);
                            result = serve(clientName, mobileDevice);
                            outputTextArea.append(result + "\n");
                            break;
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Пожалуйста, введите имя клиента и выберите тип оборудования.",
                            "Ошибка", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        createButton.setFont(createButton.getFont().deriveFont(16f)); // Установка размера шрифта для кнопки

        JButton breakButton = new JButton("Сломать");
        breakButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String clientName = nameField.getText();
                String selectedType = (String) typeComboBox.getSelectedItem();
                String result;
                if (!clientName.isEmpty() && selectedType != null) {
                    switch (selectedType) {
                        case "Автомобиль":
                            Car car = new Car(clientName);
                            result = breakDown(clientName, car);
                            outputTextArea.append(result + "\n");
                            break;
                        case "Грузовик":
                            Truck truck = new Truck(clientName);
                            result = breakDown(clientName, truck);
                            outputTextArea.append(result + "\n");
                            break;
                        case "Компьютер":
                            Computer computer = new Computer(clientName);
                            result = breakDown(clientName, computer);
                            outputTextArea.append(result + "\n");
                            break;
                        case "Мобильное устройство":
                            MobileDevice mobileDevice = new MobileDevice(clientName);
                            result = breakDown(clientName, mobileDevice);
                            outputTextArea.append(result + "\n");
                            break;
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Пожалуйста, введите имя клиента и выберите тип оборудования.",
                            "Ошибка", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        breakButton.setFont(breakButton.getFont().deriveFont(16f)); // Установка размера шрифта для кнопки

        JButton fixButton = new JButton("Починить");
        fixButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String clientName = nameField.getText();
                String selectedType = (String) typeComboBox.getSelectedItem();
                String result;
                if (!clientName.isEmpty() && selectedType != null) {
                    switch (selectedType) {
                        case "Автомобиль":
                            Car car = new Car(clientName);
                            result = repair(clientName, car);
                            outputTextArea.append(result + "\n");
                            break;
                        case "Грузовик":
                            Truck truck = new Truck(clientName);
                            result = repair(clientName, truck);
                            outputTextArea.append(result + "\n");
                            break;
                        case "Компьютер":
                            Computer computer = new Computer(clientName);
                            result = repair(clientName, computer);
                            outputTextArea.append(result + "\n");
                            break;
                        case "Мобильное устройство":
                            MobileDevice mobileDevice = new MobileDevice(clientName);
                            result = repair(clientName, mobileDevice);
                            outputTextArea.append(result + "\n");
                            break;
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Пожалуйста, введите имя клиента и выберите тип оборудования.",
                            "Ошибка", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        fixButton.setFont(fixButton.getFont().deriveFont(16f)); // Установка размера шрифта для кнопки

        inputPanel.add(nameLabel);
        inputPanel.add(nameField);
        inputPanel.add(typeLabel);
        inputPanel.add(typeComboBox);
        inputPanel.add(createButton);
        inputPanel.add(breakButton);
        inputPanel.add(fixButton);

        outputTextArea = new JTextArea(15, 30);
        outputTextArea.setEditable(false);
        outputTextArea.setFont(outputTextArea.getFont().deriveFont(16f)); // Установка размера шрифта для текстовой области

        JScrollPane scrollPane = new JScrollPane(outputTextArea);

        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    public static <T extends Repairable> TechnicalService create(String clientName, T selectedType) {
        System.out.println(clientName + " создан и является классом типа" + selectedType);
        System.out.println();
        return TechnicalService.<T>createTechnicalService();
    }
    public static <T extends Repairable> String repair(String clientName, T selectedType) {
        String result = clientName + " починен и является классом типа" + selectedType;
        TechnicalService<T> techService = create(clientName, selectedType);
        techService.repairDevice(selectedType);
        techService.showServicedDevices();
        System.out.println(result);
        System.out.println();
        return result;
    }

    public static <T extends Repairable> String breakDown(String clientName, T selectedType) {
        String result = clientName + " сломан и является классом типа" + selectedType;
        TechnicalService<T> techService = create(clientName, selectedType);
        selectedType.breakDown();
        techService.showServicedDevices();
        System.out.println(result);
        System.out.println();
        return result;
    }

    public static <T extends Repairable> String serve(String clientName, T selectedType) {
        String result = clientName + " создан и является классом типа" + selectedType;
        TechnicalService<T> techService = create(clientName, selectedType);
        techService.showServicedDevices();
        System.out.println(result);
        System.out.println();
        return result;
    }

}
