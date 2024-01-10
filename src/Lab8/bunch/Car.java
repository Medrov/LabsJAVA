package Lab8.bunch;

public final class Car extends AbstractTech {

    public Car(String brand, String model, Boolean isBroken, int coeff) {
        super(brand, model, isBroken, coeff);
    }

    @Override
    public void tryCrash() {
        double chance = calculateBrokeChance();
        if (chance < 50) {
            //System.out.println("Машина: " + this.getBrand() + " " + this.getModel() + "  ---  Не сломалась");
        } else {
            this.setIsBroken(true);
            System.out.println("Машина: " + this.getBrand() + " " + this.getModel() + "  ---  " + "\u001B[31m" + "Cломалась" + "\u001B[0m");
        }
    }

}
