package Lab7;

public class Lab7 {
    public static void main(String[] args) {

        Car car = new Car("Toyota");
        Truck truck = new Truck("Volvo");
        Computer computer = new Computer("HP");
        MobileDevice mobileDevice = new MobileDevice("Samsung");

        TechnicalService<Car> carService = new TechnicalService<Car>();
        TechnicalService<Truck> truckService = new TechnicalService<Truck>();
        TechnicalService<Computer> computerService = TechnicalService.createTechnicalService();
        TechnicalService<MobileDevice> mobileDeviceService = TechnicalService.createTechnicalService();

        System.out.println(computer.brand);
        carService.repairDevice(car);
        //car.breakDown();
        truckService.repairDevice(truck);
        computerService.repairDevice(computer);
        //computer.breakDown();
        mobileDeviceService.repairDevice(mobileDevice);
        carService.showServicedDevices();
        truckService.showServicedDevices();
        computerService.showServicedDevices();
        mobileDeviceService.showServicedDevices();


    }
}
