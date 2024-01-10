package Lab7.services;

import Lab7.Repairable;
import Lab7.technics.ElectronicDevice;
import Lab7.technics.bunch.Computer;
import Lab7.technics.bunch.MobileDevice;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


public class ComputerService<T extends Repairable> extends TechnicalService<T> {
    private static List<ElectronicDevice> electronicDevices;
    private final int repairSpots;
    private final ExecutorService repairThreadPool;
    private int repairedCount;
    private Random random;

    public ComputerService(int repairSpots) {
        this.electronicDevices = new ArrayList<>();
        this.repairSpots = repairSpots;
        this.repairThreadPool =  Executors.newFixedThreadPool(repairSpots);
        this.repairedCount = 0;
        this.random = new Random();
        createElectronicDevices();
        startRepair();
    }

    public static void createElectronicDevices() {
        for (int i = 0; i < 5; i++) {
            electronicDevices.add(new Computer("Computer" + (i + 1)));
            electronicDevices.add(new MobileDevice("MobileDevice" + (i + 1)));
        }
    }

    private void startRepair() {
        for (ElectronicDevice device : electronicDevices) {
            repairThreadPool.submit(() -> {
                try {
                    repairDevice(device);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    private void repairDevice(ElectronicDevice device) throws InterruptedException {
        synchronized (device) {
            while (!device.isFunctional()) {
                System.out.println("Устройство " + device.brand + " отправлено на ремонт.");
                TimeUnit.SECONDS.sleep(2); // ремонт в течение 2 секунд
                device.repair();
                System.out.println("Устройство " + device.brand + " отремонтировано.");
                repairedCount++;
            }
        }
    }

    public void stopRepair() {
        repairThreadPool.shutdown();
        try {
            if (!repairThreadPool.awaitTermination(60, TimeUnit.SECONDS)) {
                repairThreadPool.shutdownNow();
            }
        } catch (InterruptedException ex) {
            repairThreadPool.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
    public int getRepairedCount() {
        return repairedCount;
    }

    private void startBreaking() {
        Thread breakingThread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000); // Раз в секунду
                    breakRandomElectronicDevices();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        breakingThread.start();
    }

    private void breakRandomElectronicDevices() {
        int randomIndex = random.nextInt(electronicDevices.size());
        ElectronicDevice device = electronicDevices.get(randomIndex);
        if (!device.isFunctional()) {
            System.out.println("Устройство " + device.brand + " уже сломано и отправлено в ремонт.");
            return;
        }
        device.breakDown();
    }
}
