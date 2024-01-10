package Lab8;

import Lab8.bunch.*;
import Lab8.services.CarService;
import Lab8.services.PersonalComputerService;
import Lab8.services.MobileDeviceService;
import Lab8.services.TruckService;
import Lab8.utils.*;



import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Lab8 {


    public static void main(String[] args) {

        ArrayList<Car> car = new ArrayList<Car>();
        car.add(new Car("Car1", "1-ый", false, 19));
        car.add(new Car("Car2", "2-ой", false, 7));
        car.add(new Car("Car3", "3-ий", false, 4));
        car.add(new Car("Car4", "4-ый", false, 10));
        car.add(new Car("Car5", "5-ый", false, 11));

        ArrayList<Truck> truck = new ArrayList<Truck>();
        truck.add(new Truck("Truck1", "1-ый", false, 15));
        truck.add(new Truck("Truck2", "2-ой", false, 8));
        truck.add(new Truck("Truck3", "3-ий", false, 12));
        truck.add(new Truck("Truck4", "4-ый", false, 2));
        truck.add(new Truck("Truck5", "5-ый", false, 16));

        ArrayList<PersonalComputer> personalComputer = new ArrayList<PersonalComputer>();
        personalComputer.add(new PersonalComputer("PC1", "1-ый", false, 4));
        personalComputer.add(new PersonalComputer("PC2", "2-ой", false, 7));
        personalComputer.add(new PersonalComputer("PC3", "3-ий", false, 10));
        personalComputer.add(new PersonalComputer("PC4", "4-ый", false, 5));
        personalComputer.add(new PersonalComputer("PC5", "5-ый", false, 13));

        ArrayList<MobileDevice> mobileDevice = new ArrayList<MobileDevice>();
        mobileDevice.add(new MobileDevice("MD1", "1-ый", false, 6));
        mobileDevice.add(new MobileDevice("MD2", "2-ой", false, 8));
        mobileDevice.add(new MobileDevice("MD3", "3-ий", false, 14));
        mobileDevice.add(new MobileDevice("MD4", "4-ый", false, 12));
        mobileDevice.add(new MobileDevice("MD5", "5-ый", false, 5));

        ArrayList<AbstractTech> allTechs = new ArrayList<>();
        allTechs.addAll(mobileDevice);
        allTechs.addAll(personalComputer);
        allTechs.addAll(truck);
        allTechs.addAll(car);

        Thread randomCrashBehaviorThread = new Thread(new CrashRandomizer(allTechs));
        randomCrashBehaviorThread.start();

        MobileDeviceService mobileService = new MobileDeviceService();
        Runnable mobileServiceFixer = new Fixer<MobileDevice>(mobileDevice, mobileService);
        Thread fixMobileDeviceThread = new Thread(mobileServiceFixer);
        fixMobileDeviceThread.start();

        PersonalComputerService computerService = new PersonalComputerService();
        Runnable csServiceFixer = new Fixer<PersonalComputer>(personalComputer, computerService);
        Thread fixPersonalComputerThread = new Thread(csServiceFixer);
        fixPersonalComputerThread.start();

        CarService carService = new CarService();
        Runnable carServiceFixer = new Fixer<Car>(car, carService);
        Thread fixCarThread = new Thread(carServiceFixer);
        fixCarThread.start();

        TruckService truckService = new TruckService();
        Runnable truckServiceFixer = new Fixer<Truck>(truck, truckService);
        Thread fixTruckThread = new Thread(truckServiceFixer);
        fixTruckThread.start();

        TimerTask shutdownTask = new TimerTask() {
            @Override
            public void run() {
                System.out.println("Программа завершается...");
                System.exit(0);
            }
        };

        Timer timer = new Timer();
        timer.schedule(shutdownTask, 1000 * 60);
    }
}
