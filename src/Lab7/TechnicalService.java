package Lab7;

import java.util.ArrayList;
import java.util.List;

public class TechnicalService<T extends Repairable> {
    private List<T> servicedDevices;

    public TechnicalService() {
        this.servicedDevices = new ArrayList<>();
    }

    public void repairDevice(T device) {
        System.out.println("Ремонт " + device.getClass().getSimpleName() + ": " + device.repair());
        servicedDevices.add(device);
        System.out.println();
    }

    public void showServicedDevices() {
        System.out.println("Обслуженные устройства:");
        for (Repairable device : servicedDevices) {
            System.out.println(device.getClass().getSimpleName() + ": " + (device.isFunctional() ? "Исправно" : "Неисправно"));
        }
        System.out.println();
    }
}

