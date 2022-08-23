
/*Запустите этот код и получите следующий вывод в консоль:
Got food!
Got water!
Программа продолжает работать, пока вы ее не убьете. Это типичный пример deadlock, так как оба потока
заблокированы навечно, каждый из них ждет освобождения объекта, который никогда не будет освобожден.
 */
class Food {
}

class Water {
}

public class Fox {
    private void eatAndDrink(Food food, Water water) {
        synchronized (food) {//ждем высвобождения объекта food
            System.out.println("got food");
            move();
            synchronized (water) {//ждем высвобождения объекта weter
                System.out.println("got water");
            }
        }
    }

    private void drinkAndEat(Food food, Water water) {
        synchronized (water) {//ждем высвобождения объекта weter
            System.out.println("got water");
            move();
            synchronized (food) {//ждем высвобождения объекта food
                System.out.println("got food");
            }
        }
    }

    private void move() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Food food = new Food();
        Water water = new Water();
        Fox fox1 = new Fox();
        Fox fox2 = new Fox();
        Thread thread1 = new Thread() {
            public void run() {
                fox1.eatAndDrink(food, water);
            }
        };

        Thread thread2 = new Thread() {
            public void run() {
                fox2.drinkAndEat(food, water);
            }
        };
        thread1.start();
        thread2.start();
    }
}
