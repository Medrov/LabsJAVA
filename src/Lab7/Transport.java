package Lab7;

abstract class Transport implements Repairable{
    private String brand;
    private boolean isFunctional;

    public Transport(String brand) {
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
