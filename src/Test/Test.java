package Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test {

    private Object lock1 = new Object();//нажать контрол+д
    private Object lock2 = new Object();//нажать контрол+д

    public void doWork1(){
        synchronized (lock1){
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lock2){
                System.out.println("работаем на работе 1");
            }
        }
    }

    public void doWork2(){
        synchronized (lock2){
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lock1){
                System.out.println("работаем на работе 1");
            }
        }
    }

    public static void main(String[] args) {
        Test test = new Test();
//      сервисное обсл.           исполнители.новыйФиксированныйПулПотоков(2 шт)
        ExecutorService service = Executors.newFixedThreadPool(2);
        //      выполнять    док.поток
        service.execute(()-> test.doWork1());
        service.execute(()-> test.doWork2());

        service.shutdown();
        /*запускаем программу получим дедлок - программа зависнет с потоками 1 и 2
        внизу есть иконка фото- нанее наживаем и нам будет выдана информация о состоянии потоков на даный помент с описанием
        на каких объектах они заблокировались на определенный момент времени
        */
    }


}
