package Lab7.services;

import Lab7.Repairable;
import Lab7.technics.Transport;
import Lab7.technics.bunch.Car;
import Lab7.technics.bunch.Truck;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class VehicleService<T extends Repairable> extends TechnicalService<T> {
    private final List<Transport> transports;
    private final Random random;

    public VehicleService() {
        this.transports = new ArrayList<>();
        this.random = new Random();
        createTransports();
        startBreaking();
    }

    private void createTransports() {
        for (int i = 0; i < 5; i++) {
            transports.add(new Car("Car" + (i + 1)));
            transports.add(new Truck("Truck" + (i + 1)));
        }
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
}
