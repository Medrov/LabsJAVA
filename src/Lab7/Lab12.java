package Lab7;

import Lab7.services.ComputerService;
import Lab7.services.VehicleService;
import Lab7.technics.bunch.Car;
import Lab7.technics.bunch.Computer;
import Lab7.technics.bunch.MobileDevice;
import Lab7.technics.bunch.Truck;

public class Lab12 {


    public static void main(String[] args) throws InterruptedException {

        Car car = new Car("Toyota");

        VehicleService<Car> carService = new VehicleService<Car>(2);
        VehicleService<Truck> truckService = new VehicleService<Truck>(2);
        ComputerService<Computer> computerService = new ComputerService<Computer>(2);
        ComputerService<MobileDevice> mobileDeviceService = new ComputerService<MobileDevice>(2);



        carService.repairDevice(car);
        carService.showServicedDevices();
        carService.createTransports();
    }
}
