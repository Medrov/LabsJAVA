package Lab7;

public class MobileDevice implements Repairable {
    private String brand;
    private boolean isFunctional;

    public MobileDevice(String brand) {
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
