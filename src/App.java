class Processor extends Thread {
    private volatile boolean running = true;//добавим ключевое слово volatile

    @Override
    public void run() {
        while (running) {
            System.out.println("Processing...");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void shutDown() {
        running = false;
    }
}

public class App {
    public static void main(String[] args) throws InterruptedException {
        Processor processor1 = new Processor();
        processor1.start();
        Thread.sleep(1000);
        processor1.shutDown();//а разных машинах по рахзному, иногда поток прерывается а иногда этот код эгнорится и поток работает дальше
    }
}

