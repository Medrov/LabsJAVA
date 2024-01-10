package Lab8.services;

import Lab8.bunch.MobileDevice;

import java.util.concurrent.Semaphore;


public final class MobileDeviceService extends ProtoService<MobileDevice> {

    @Override
    public void fix(MobileDevice brokenTech, Semaphore semaphore) throws InterruptedException {
        semaphore.acquire();
        synchronized (brokenTech) {
            System.out.println("\u001B[33m" + "Требуется ремонт " + brokenTech.toString() + " телефона..." + "\u001B[0m");
            System.out.println("Выполняется ремонт телефона " + brokenTech.toString());
            Thread.sleep(2000);
            brokenTech.setIsBroken(false);
            System.out.println("\u001B[32m" + "Ваш телефон " + brokenTech.toString() + " отремонтирован" + "\u001B[0m");
            semaphore.release();
        }
    }

}

