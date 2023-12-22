package Lab7;


import Lab7.services.ComputerService;
import Lab7.services.VehicleService;
import Lab7.technics.bunch.Car;
import Lab7.technics.bunch.Computer;
import Lab7.technics.bunch.MobileDevice;
import Lab7.technics.bunch.Truck;

public class Lab7 {
    public static void main(String[] args) {

        Car car = new Car("Toyota");
        Truck truck = new Truck("Volvo");
        Computer computer = new Computer("HP");
        MobileDevice mobileDevice = new MobileDevice("Samsung");

        VehicleService<Car> carService = new VehicleService<Car>();
        VehicleService<Truck> truckService = new VehicleService<Truck>();
        ComputerService<Computer> computerService = new ComputerService<Computer>();
        ComputerService<MobileDevice> mobileDeviceService = new ComputerService<MobileDevice>();

        System.out.println(computer);
        carService.repairDevice(car);
        truckService.repairDevice(truck);
        computerService.repairDevice(computer);
        mobileDeviceService.repairDevice(mobileDevice);
        carService.showServicedDevices();
        truckService.showServicedDevices();
        computerService.showServicedDevices();
        mobileDeviceService.showServicedDevices();
    }
}

