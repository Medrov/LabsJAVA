package Lab7.services;

import Lab7.Repairable;
import Lab7.technics.Transport;
import Lab7.technics.bunch.Car;
import Lab7.technics.bunch.Truck;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class VehicleService<T extends Repairable> extends TechnicalService<T> {
    private static List<Transport> transports;

    private final int repairSpots;
    private final ExecutorService repairThreadPool;
    private int repairedCount;
    private final Random random;

    public VehicleService(int repairSpots) {
        this.transports = new ArrayList<>();
        this.repairSpots = repairSpots;
        this.repairThreadPool = Executors.newFixedThreadPool(repairSpots);
        this.repairedCount = 0;
        this.random = new Random();
        createTransports();
        startRepair();
    }

    public static void createTransports() {
        for (int i = 0; i < 5; i++) {
            transports.add(new Car("Car" + (i + 1)));
            transports.add(new Truck("Truck" + (i + 1)));
        }
        System.out.println(transports);
    }

    private void startBreaking() {
        Thread breakingThread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000); // Раз в секунду
                    breakRandomTransports();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        breakingThread.start();
    }

    private void breakRandomTransports() {
        int randomIndex = random.nextInt(transports.size());
        Transport transport = transports.get(randomIndex);
        if (!transport.isFunctional()) {
            System.out.println("Транспорт " + transport.brand + " уже сломан и отправлен в ремонт.");
            return;
        }
        transport.breakDown();
    }

    public int getRepairedCount() {
        return repairedCount;
    }

    private void startRepair() {
        for (Transport transport : transports) {
            repairThreadPool.submit(() -> {
                try {
                    repairDevice((T) transport); // приведение к типу T
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    public void repairDevice(T device) throws InterruptedException {
        synchronized (device) {
            while (!device.isFunctional()) {
                System.out.println("Транспорт " + ((Transport) device).brand + " отправлен на ремонт.");
                TimeUnit.SECONDS.sleep(2); // ремонт в течение 2 секунд
                device.repair();
                System.out.println("Транспорт " + ((Transport) device).brand + " отремонтирован.");
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
}
