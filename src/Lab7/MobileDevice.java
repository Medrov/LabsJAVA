package Lab7;

public class MobileDevice implements Repairable {
    public static String brand;
    private static boolean isFunctional;

    {
        brand = "SOYO";
        isFunctional = false;
    }

    public MobileDevice(String brand) {
        MobileDevice.brand = brand;
        isFunctional = true;
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
