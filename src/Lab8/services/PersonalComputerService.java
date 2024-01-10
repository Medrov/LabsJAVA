package Lab8.services;

import Lab8.bunch.PersonalComputer;

import java.util.concurrent.Semaphore;




public final class PersonalComputerService extends ProtoService<PersonalComputer> {

    @Override
    public void fix(PersonalComputer brokenTech, Semaphore semaphore) throws InterruptedException {
        semaphore.acquire();
        synchronized (brokenTech) {
            System.out.println("\u001B[33m" + "Требуется ремонт ПК " + brokenTech.toString() +"\u001B[0m");
            System.out.println("Выполняется ремонт ПК " + brokenTech.toString() );
            Thread.sleep(2000);
            brokenTech.setIsBroken(false);
            System.out.println("\u001B[32m" + "Ваш ПК " + brokenTech.toString() + " отремонтирован" + "\u001B[0m");
            semaphore.release();
        }
    }

}
