/*
Задание 14.6.3
Ответьте, что делает данный код:
Выводит в консоль 100 100
Программа бросает ошибку при запуске
Результат вывода не может быть заранее определен (Правильно)
Выводит в консоль 100 99
 */
package M14_6;

import java.util.concurrent.atomic.AtomicInteger;

public class Z1463SheepManager {
    private static AtomicInteger sheepCount1 = new AtomicInteger(0);
    private static int sheepCount2 = 0;

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            new Thread() {
                public void run() {
                    sheepCount1.getAndIncrement();//100
                    sheepCount2++;//100
                }
            }.start();
        }
        Thread.sleep(1000); // достаточное время для завершения всех задач
        System.out.println(sheepCount1 + " " + sheepCount2);
        //с учетом времени сна основного потока, цикл со вложенным потоком может выполниться несколько
        // раз с одним и тем же значением по этому результат непредсказуем
    }
}
