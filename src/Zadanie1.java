/*
Задача 1
Создать пул из двух потоков. В цикле добавить в пул 5 потоков с задержкой 4 с. Вывести результат их работы в консоль.
 */

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Zadanie1 {
    public static void main(String[] args) throws InterruptedException {
        //создаем пул из 2х потоков
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        for (int i = 0; i < 5; i++)
            executorService.submit(new Work(i));
            executorService.shutdown();
            System.out.println("Все потоки закончили работу.");

    }
}

//создаем класс work реализующий интерфейс Runneble
class Work implements Runnable {
    //переменная процесса
    int number;

    //конструктор
    Work(int number) {
        this.number = number;
    }

    //переоапределяем метод ран
    @Override
    public void run() {
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("поток " + number + " работу завершил");
    }
}


