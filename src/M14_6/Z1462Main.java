/*
Задание 14.6.2
Ответьте, что делает данный код:
 */
package M14_6;

import java.util.concurrent.atomic.AtomicIntegerArray;

public class Z1462Main {
    private static AtomicIntegerArray integerArray = new AtomicIntegerArray(new int[10]);//создаемтся атомарный объект класса

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread() {
            public void run() {
                for (int i = 0; i < integerArray.length(); i+=2) {
                    integerArray.addAndGet(i, 5);//к каждому 2-му элементу массива прибавляет 5 т.к. в массиве везде 0 то будет 0,5,0,5,0,5,0,5...
                }
                System.out.print(integerArray);
            }
        };

        Thread t2 = new Thread() {
            public void run() {
                for (int i = 0; i < integerArray.length(); i++) {
                    integerArray.addAndGet(i, 3);//к каждому элементу массива прибавляет 3 при этом массив уже задолнен пурвым потоком
                }
                System.out.print(integerArray);
            }
        };

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(integerArray);
    }
}
