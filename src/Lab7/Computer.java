package Lab7;

public class Computer implements Repairable {
    public final String brand;
    private boolean isFunctional;

    static {
        System.out.println("Чисто актив закинуть");
    }

    public Computer(String brand) {
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