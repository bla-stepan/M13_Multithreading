package Z1464;


import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

public class Pizzeria {
    //создаем вложенный класс заказа
    class Order {
        //создаем объект - название пицы
        String pizzaName;
        //создаем переменную время ордера
        long orderTime;

        //создаем конструктор родера
        public Order(String pizzaName, long orderTime) {
            this.pizzaName = pizzaName;
            this.orderTime = orderTime;
        }
    }

    //создаем коллекцию сисок заказов куда будем помещать заказы (объекты класса заказ)
    LinkedBlockingDeque<Order> orders = new LinkedBlockingDeque<>();
    //создаем константу по времени начала
    final long START_TIME;
    //создаем объект пицерию с двумя машинами
    public Pizzeria() {
        //задаем стартовое время равным текущему времени потока
        START_TIME = System.currentTimeMillis();
        //создаем два объекта машины (потоки) стартуем
        new PizzaCar().start();
        new PizzaCar().start();
    }

    //создаем клсс пицемашин - наследник класса поток
    class PizzaCar extends Thread {
        //переопределяем метод ран
        public void run() {
            //пишем цикл c условием (пока текущее время - старотовое время меньше 5000
            while (System.currentTimeMillis() - START_TIME < 5000) {
                //создаем объект - заказ null
                Order order = null;
                try {
                    order = orders.poll(10, TimeUnit.MILLISECONDS);
                } catch (InterruptedException e) {
                    break;//e.printStackTrace();//обработка исключения - прекращаем работу программы
                }
                if (order != null) {//если заказ не нулевой
                    System.out.println(System.currentTimeMillis()+500-order.orderTime);
                    if (System.currentTimeMillis()+500-order.orderTime <= 750) {//время исполнения заказа не превышает 750 (норматив в задаче) то
                        //принимаем заказ
                        //спим 500 мс по условию задачи - это время готовки заказа
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            break;//e.printStackTrace();//
                        }
                        //проснулись и выводим сообщение
                        System.out.println(order.pizzaName + " is delivered");
                    } else {
                        //отменяем заказ и выводим сообщение
                        System.out.println(order.pizzaName + " is NOT delivered");
                    }
                }
            }
        }
    }
    //создаем метод
    void order(String pizzaName) throws InterruptedException{
        //заполняем коллекцию заказов
        //try {
            orders.put(new Order(pizzaName, System.currentTimeMillis()));
        //} catch (InterruptedException e) {
        //    return;
        //}
    }
}
