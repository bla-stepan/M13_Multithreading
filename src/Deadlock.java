public class Deadlock implements Runnable {

    private static final Object lock1 = new Object();
    private static final Object lock2 = new Object();

    public static void main(String[] args) {
        Deadlock deadlock = new Deadlock();
        new Thread(deadlock).start();
        new Thread(deadlock).start();
    }

    @Override
    public void run() {
        doTask1();
        doTask2();
    }

    private void doTask1() {
        synchronized (lock1) {
            synchronized (lock2) {
                System.out.println("Doing task 1");
            }
        }
    }

    private void doTask2() {
        synchronized (lock1){//lock2) { меняем местами лок1 и лок2, чтобы сделать потоки одинаковыми и избежать ситуации deadlock
            synchronized (lock2){//lock1) {
                System.out.println("Doing task 2");
            }
        }
    }
}