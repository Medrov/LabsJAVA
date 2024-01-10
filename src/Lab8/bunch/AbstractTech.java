package Lab8.bunch;

import java.util.concurrent.ThreadLocalRandom;

public abstract class AbstractTech {
    private String brand;
    private String model;
    private boolean isBroken;
    private int coeff;

    protected AbstractTech(String brand, String model, boolean isBroken, int coeff) {
        this.brand = brand;
        this.model = model;
        this.isBroken = isBroken;
        this.coeff = coeff;
    }

    public abstract void tryCrash();

    public double calculateBrokeChance() {
        double p = ThreadLocalRandom.current().nextDouble(1, 10) * coeff;
        return Math.min(p, 100); // Ограничиваем значение до 100
    }

    @Override
    public final String toString() {
        return brand + " " + model;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public boolean getIsBroken() {
        return isBroken;
    }

    public void setIsBroken(boolean isBroken) {
        this.isBroken = isBroken;
    }

    public int getCoeff() {
        return coeff;
    }

    public void setCoeff(int coeff) {
        this.coeff = coeff;
    }
}
