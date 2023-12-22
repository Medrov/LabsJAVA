package Lab7.technics;

import Lab7.Repairable;

public class ElectronicDevice implements Repairable {
    public final String brand;
    protected boolean isFunctional;

    public ElectronicDevice(String brand) {
        this.brand = brand;
        this.isFunctional = true;
    }

    @Override
    public void breakDown() {
        System.out.println("Техника " + brand + " сломана.");
        isFunctional = false;
    }

    @Override
    public String repair() {
        System.out.println("Техника " + brand + " починена.");
        isFunctional = true;
        return "Техника починена.";
    }

    @Override
    public boolean isFunctional() {
        return isFunctional;
    }
}