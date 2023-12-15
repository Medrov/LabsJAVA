package Lab11;

public class Lab11 {
    private final Object lock1 = new Object();
    private final Object lock2 = new Object();

    public void method1() {
        boolean interrupted = false;
        synchronized (lock1) {
            System.out.println("Метод 1 получает lock1");

            // Преднамеренная задержка для увеличения вероятности deadlock'а
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                interrupted = true;
                Thread.currentThread().interrupt(); // Восстановление флага прерывания


            }
        }

        if (!interrupted) {
            synchronized (lock2) {
                System.out.println("Восстановление флага прерывания");
                System.out.println("Метод 1 получает lock2");
                // Код, требующий lock2
            }
        }
    }

    public void method2() {
        boolean interrupted = false;
        synchronized (lock2) {
            System.out.println("Метод 2 получает lock2");

            // Преднамеренная задержка для увеличения вероятности deadlock'а
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                interrupted = true;

                Thread.currentThread().interrupt(); // Восстановление флага прерывания
            }
        }

        if (!interrupted) {
            synchronized (lock1) {
                System.out.println("Восстановление флага прерывания");
                System.out.println("Метод 2 получает lock1");
                // Код, требующий lock1
            }
        }
    }


    public static void main(String[] args) {
        Lab11 deadlockExample = new Lab11();

        Thread thread1 = new Thread(deadlockExample::method1);
        Thread thread2 = new Thread(deadlockExample::method2);

        thread1.start();
        thread2.start();

    }
}
