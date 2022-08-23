package Z1465;
/*
Задание 14.6.5
Выберите правильный ответ, что делает данный код:
Результат в консоли не определен до запуска, race condition не происходит (данные не сломаны) (мой выбор) ВЕРНО
Пустой map, race condition не происходит (данные не сломаны)
Результат в консоли не определен до запуска, возможен race condition (данные могут быть сломаны)
Пустой map, возможен race condition (данные могут быть сломаны)
 */
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

class Processor implements Runnable {

    private int elem;

    public Processor(int elem) {
        this.elem = elem;
    }

    @Override
    public void run() {
        Main.set.put("Key" + elem, 12);
    }
}

class AnotherProcessor implements Runnable {

    private int elem;

    public AnotherProcessor(int elem) {
        this.elem = elem;
    }

    @Override
    public void run() {
        Main.set.remove("Key" + elem);
    }
}

class Main {

    static Map<String, Integer> set = new ConcurrentHashMap<>();

    public static void main(String[] args) throws InterruptedException {

        for (int i = 0; i < 10; i++) {
            new Thread(new Processor(i)).start();
            new Thread(new AnotherProcessor(i)).start();
        }

        Thread.sleep(1000); // хватит для готового результата

        System.out.println(set);
    }

}