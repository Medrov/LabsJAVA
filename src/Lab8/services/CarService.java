package Lab8.services;

import Lab8.bunch.Car;

import java.util.concurrent.Semaphore;



public final class CarService extends ProtoService<Car> {

    @Override
    public void fix(Car brokenTech, Semaphore semaphore) throws InterruptedException {
        semaphore.acquire();
        synchronized (brokenTech) {
            System.out.println("\u001B[33m" + "Требуется ремонт машины " + brokenTech.toString() + "\u001B[0m");
            System.out.println("Выполняется ремонт машины " + brokenTech.toString());
            Thread.sleep(2000);
            brokenTech.setIsBroken(false);
            System.out.println("\u001B[32m" + "Ваша машина " + brokenTech.toString() + " отремонтирована" + "\u001B[0m");
            semaphore.release();
        }
    }

}

