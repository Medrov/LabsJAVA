package Lab8.services;

import Lab8.bunch.Truck;

import java.util.concurrent.Semaphore;


public final class TruckService extends ProtoService<Truck> {

    @Override
    public void fix(Truck brokenTech, Semaphore semaphore) throws InterruptedException {
        semaphore.acquire();
        synchronized (brokenTech) {
            System.out.println("\u001B[33m" + "Требуется ремонт грузовика " + brokenTech.toString() + "\u001B[0m");
            System.out.println("Выполняется ремонт грузовика " + brokenTech.toString());
            Thread.sleep(2000);
            brokenTech.setIsBroken(false);
            System.out.println("\u001B[32m" + "Ваш грузовик " + brokenTech.toString() + " отремонтирован" + "\u001B[0m");
            semaphore.release();
        }
    }

}
