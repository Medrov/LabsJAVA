package Lab7;

abstract class Transport implements Repairable{
    public final String brand;
    private boolean isFunctional;

    public Transport(String brand) {
        this.brand = brand;
        this.isFunctional = true;
    }

    @Override
    public void breakDown() {
        System.out.println("Транспорт " + brand + " сломан.");
        isFunctional = false;
    }

    @Override
    public String repair() {
        System.out.println("Транспорт " + brand + " починен.");
        isFunctional = true;
        return "Транспорт починен.";
    }

    @Override
    public boolean isFunctional() {
        return isFunctional;
    }
}
