/*задание 14.3.3.
что, будет выведено в консоль:
1. List size: 400, потоки отработают параллельно (мой выбор) не правильно:
мы синхронизируемся на одном и том же объекте, synchronized(this) — то же, что и synchronized на методе объекта.
2. List size: неопределенное значение
3. List size: 400, не будет параллельного исполнения (правильно)
4. Ошибка компиляции*/

import java.util.ArrayList;
import java.util.List;

public class Z1433Main {

    private List<String> stringList = new ArrayList<>();//создается лист

    private void addElemToList() {//разные варианты синхронизации
        synchronized (this) {//синхронизация блок
            stringList.add("Hello");
        }
    }

    private synchronized void addAnotherElemToList() {//разные варианты синхронизации
        stringList.add("Hello again");
    }

    public static void main(String[] args) throws InterruptedException {
        Z1433Main main = new Z1433Main();//создаем объект

        Thread t1 = new Thread(){//запускаем поток
            public void run() {
                for (int i = 0; i < 100; i++) {
                    main.addElemToList();
                    main.addAnotherElemToList();
                }
            }
        };

        Thread t2 = new Thread() {
            public void run() {
                for (int i = 0; i < 100; i++) {
                    main.addElemToList();
                    main.addAnotherElemToList();
                }
            }
        };

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("List size: " + main.stringList.size());//неизвестно
    }

}