package M14_6;

import java.util.concurrent.atomic.AtomicInteger;

public class Bank {
//Задание 14.6.1
//Еще раз отрефакторите код банка. Уберите все слова synchronized из кода и «заставьте» его работать правильно с использованием класса AtomicInteger.

    //private int money = 10000;
    private AtomicInteger money = new AtomicInteger();
    //private Object lock = new Object();
    int getMoney() {
        return money.get();
    }

    void take(int money) {
        if (getMoney() >= 1000) {
            //synchronized (lock) {
                this.money.addAndGet(-money);// -= money;
            //}
        }

    }

    void repay(int money) {
        //synchronized (lock) {
            this.money.addAndGet(money);// += money;
        //}
    }

    class Client extends Thread {
        @Override
        public void run() {
            while (true) {
                take(1000);
                repay(1000);
            }
        }
    }


    public Bank() {
        //через цикл делаем несколько клиентов, чтобы не копировать
        money.set(10_000);//устанавливаем начальное значение
        for (int i = 0; i < 5; i++) {
            new Client().start();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Bank bank = new Bank();
        while (true) {
            System.out.println(bank.getMoney());
            Thread.sleep(1000);
        }
    }

}
