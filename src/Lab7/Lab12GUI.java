package Lab7;

import Lab7.services.ComputerService;
import Lab7.services.VehicleService;
import Lab7.technics.ElectronicDevice;
import Lab7.technics.Transport;
import Lab7.technics.bunch.Car;
import Lab7.technics.bunch.Truck;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Lab12GUI {
    private JFrame frame;
    private JButton startButton;
    private JPanel panel;
    private JProgressBar electronicDeviceProgressBar;
    private JProgressBar vehicleProgressBar;
    private JLabel electronicDeviceLabel;
    private JLabel vehicleLabel;
    private ComputerService computerService;
    private VehicleService<Transport> vehicleService;

    public Lab12GUI() {
        try {
            UIManager.setLookAndFeel(new FlatMacDarkLaf());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        frame = new JFrame("Service Center");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        electronicDeviceProgressBar = new JProgressBar(0, 100);
        electronicDeviceProgressBar.setStringPainted(true);
        electronicDeviceLabel = new JLabel("Electronic Device Service:");

        vehicleProgressBar = new JProgressBar(0, 100);
        vehicleProgressBar.setStringPainted(true);
        vehicleLabel = new JLabel("Vehicle Service:");

        startButton = new JButton("Start Services");
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                startServices();
            }
        });

        panel.add(electronicDeviceLabel);
        panel.add(electronicDeviceProgressBar);
        panel.add(vehicleLabel);
        panel.add(vehicleProgressBar);
        panel.add(startButton);

        frame.add(panel);
        frame.setVisible(true);
    }

    private void startServices() {
        computerService = new ComputerService<>(2); // 2 ремонтных места
        vehicleService = new VehicleService<>(2); // 2 ремонтных места

        Thread electronicDeviceThread = new Thread(() -> {
            while (true) {
                int progress = (int) ((double) computerService.getRepairedCount() / 5 * 100);
                electronicDeviceProgressBar.setValue(progress);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        electronicDeviceThread.start();

        Thread vehicleThread = new Thread(() -> {
            while (true) {
                int progress = (int) ((double) vehicleService.getRepairedCount() / 5 * 100);
                vehicleProgressBar.setValue(progress);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        vehicleThread.start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Lab12GUI();
            }
        });
    }
}
