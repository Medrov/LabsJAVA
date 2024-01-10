package Lab8.bunch;

public final class Truck extends AbstractTech{

    public Truck(String brand, String model, Boolean isBroken, int coeff) {
        super(brand, model, isBroken, coeff);
    }

    @Override
    public void tryCrash() {
        double chance = calculateBrokeChance();

        if (chance < 50) {
            // System.out.println("Грузовик: " + this.getBrand() + " " + this.getModel() + "  ---  Не сломался");
        } else {
            this.setIsBroken(true);
            System.out.println("Грузовик: " + this.getBrand() + " " + this.getModel() + "  ---  " + "\u001B[31m" + "Сломался" + "\u001B[0m");
        }
    }
}
