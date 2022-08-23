import java.util.ArrayList;
import java.util.List;

public class WorkerV2 {
    private List<String> list1 = new ArrayList<>();//создаем коллекцию лист 1
    private List<String> list2 = new ArrayList<>();//создаем коллекцию лист 2

    private void addToListOne() {//синхронизированный метод
        synchronized (list1) {
            for (int i = 0; i < 100_000; i++) {//создаем цикл
                list1.add("one");//добавляем значение в первый лист
            }
        }
    }

    private void addToListTwo() {//синхронизированный метод
        synchronized (list2) {
            for (int i = 0; i < 100_000; i++) {//создаем цикл
                list2.add("two");//добавляем значение во второй лист
            }
        }
    }

    class Process extends Thread {//создаем класс - наследник суперклласса поток

        public void run() {//переопределяем метод ран
            for (int i = 0; i < 300; i++) {//цикл
                addToListOne();//выполняем метод по 1-му листу
                addToListTwo();//выполняем метод по 2-му листу
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        WorkerV2 worker = new WorkerV2();//создаем объект воркер
        Thread t1 = worker.new Process();//??? создаем поток равный вложенному классу процесс
        Thread t2 = worker.new Process();

        long start = System.currentTimeMillis();//фиксируем время начала работы

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        long finish = System.currentTimeMillis();//фиксируем время окончания работы
        System.out.println("list 1 size is: " + worker.list1.size());
        System.out.println("list 2 size is: " + worker.list2.size());
        System.out.println("Time take: " + (finish - start) + " ms");
    }
}
