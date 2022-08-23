/*
CopyOnWrite-коллекции
CopyOnWriteArrayList и CopyOnWriteArraySet отличаются от остальных синхронизированных коллекций.

Объекты этих классов копируют все элементы в новое внутреннее хранение каждый раз, когда элемент добавляется,
изменяется, либо удаляется из коллекции.
Под изменением подразумевается замена ссылки на новый элемент. Хотя данные и копируются в новую внутреннюю
структуру данных, ссылка на саму коллекцию не меняется. Это очень эффективно в мультипоточной среде,
когда необходимо итерироваться по коллекции. Любой итератор, полученный из коллекции до её модификации,
не увидит впоследствии новых изменений, и поэтому итерация будет происходить по старому набору элементов.
Рассмотрим пример:
 */
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWrite {
    public static void main(String[] args) {
        List<Integer> list = new CopyOnWriteArrayList<>(Arrays.asList(4, 3, 52));
        for (int i : list) {
            System.out.println(i + " ");
            list.add(9);
            System.out.print(list);
            System.out.println(" List size: " + list.size());
        }

        System.out.println();//пропуск строки
        System.out.println("List size: " + list.size());//6
    }
}
/*
Несмотря на то, что мы добавляем элементы в коллекцию при итерации, мы не получаем при запуске ConcurrentModificationException.
 */