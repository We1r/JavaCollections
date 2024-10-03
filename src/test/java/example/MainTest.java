package example;

import org.example.Main;
import org.example.Main.WordFrequency;
import org.junit.jupiter.api.Test;
import java.util.*;
import static org.example.Main.*;
import static org.junit.jupiter.api.Assertions.*;

public class MainTest {

    @Test
    public void testCollectionMethods() {
        System.out.println("Задание 1:");
        //a)
        int[] randomArray = createRandomArray(20);
        assertEquals(20, randomArray.length);
        System.out.println("Массив: " + Arrays.toString(randomArray));

        //b)
        List<Integer> list = arrayToList(randomArray);
        assertEquals(20, list.size());
        System.out.println("Список: " + list);

        //c)
        List<Integer> sortedList = sortListAscending(new ArrayList<>(list));
        assertTrue(isSorted(sortedList, true));
        System.out.println("Отсортированный список: " + sortedList);

        //d)
        List<Integer> sortedListDescending = sortListDescending(new ArrayList<>(list));
        assertTrue(isSorted(sortedListDescending, false));
        System.out.println("Отсортированный список в обратном порядке: " + sortedListDescending);

        //e)
        List<Integer> shuffledList = shuffleList(new ArrayList<>(list));
        assertNotEquals(list, shuffledList);
        System.out.println("Перемешанный список: " + shuffledList);

        //f)
        List<Integer> rotatedList = rotateList(new ArrayList<>(list));
        assertEquals(list.get(list.size() - 1), rotatedList.get(0));
        System.out.println("Список сдвиженный на 1: " + rotatedList);

        //g)
        List<Integer> uniqueList = uniqueElements(new ArrayList<>(list));
        for (Integer num : uniqueList) {
            assertEquals(1, Collections.frequency(list, num));
        }
        System.out.println("Список с уникальными элементами: " + uniqueList);

        //h
        List<Integer> duplicateList = duplicateElements(new ArrayList<>(list));
        for (Integer num : duplicateList) {
            assertTrue(Collections.frequency(duplicateList, num) > 1);
            assertEquals(Collections.frequency(list, num), Collections.frequency(duplicateList, num));
        }
        System.out.println("Список с дублированными элементами: " + duplicateList);

        //i
        int[] newArray = listToArray(list);
        assertArrayEquals(randomArray, newArray);
        System.out.println("Массив из списка: " + Arrays.toString(newArray));

        //j
        Map<Integer, Integer> occurrences = countOccurrences(newArray);
        for (int num : randomArray) {
            assertEquals(Collections.frequency(list, num), occurrences.get(num));
        }
        System.out.println("Все вхождения: " + occurrences);
    }

    private boolean isSorted(List<Integer> list, boolean ascending) {
        for (int i = 0; i < list.size() -1; i++) {
            if (ascending && list.get(i) > list.get(i + 1)) return false;
            if (!ascending && list.get(i) < list.get(i + 1)) return false;
        }

        return true;
    }

    @Test
    public void testPrimesGeneration() {
        System.out.println("Задание 2:");
        PrimesGenerator generator = new PrimesGenerator(10);

        System.out.println("Первые 10 простых чисел в прямом порядке:");
        for (int prime : generator) {
            System.out.print(prime + " ");
        }

        System.out.println("\nПервые 10 простых чисел в обратном порядке:");
        Iterator<Integer> reverseIterator = generator.reverseIterator();
        while (reverseIterator.hasNext()) {
            System.out.print(reverseIterator.next() + " ");
        }
    }

    @Test
    public void testHumanSets() {
        System.out.println("Задание 3:");
        List<Human> humans = new ArrayList<>();
        humans.add(new Human("John", "Doe", 30));
        humans.add(new Human("Jane", "Doe", 25));
        humans.add(new Human("Alice", "Johnson", 35));
        humans.add(new Human("Bob", "Smith", 28));
        humans.add(new Human("Charlie", "Brown", 40));

        //a) HashSet
        Set<Human> hashSet = new HashSet<>(humans);
        assertEquals(5, hashSet.size());
        System.out.println("Список людей через HashSet: " + hashSet);

        //b) LinkedHashSet
        Set<Human> linkedHashSet = new LinkedHashSet<>(humans);
        assertEquals(5, linkedHashSet.size());
        System.out.println("Список людей через LinkedHashSet: " + linkedHashSet);


        //c) TreeSet
        Set<Human> treeSet = new TreeSet<>(humans);
        assertEquals(4, treeSet.size());
        System.out.println("Список людей через treeSet: " + treeSet);

        //d) TreeSet с компаратором по фамилии
        Set<Human> treeSetByLastName = new TreeSet<>(new Human.HumanComparatorByLastName());
        treeSetByLastName.addAll(humans);
        assertEquals(4, treeSetByLastName.size());
        System.out.println("Список людей через treeSet с компаратором по фамилии: " + treeSetByLastName);

        //e) TreeSet с компаратором по возрасту
        Set<Human> treeSetByAge = new TreeSet<>(Comparator.comparingInt(Human::getAge));
        treeSetByAge.addAll(humans);
        assertEquals(5, treeSetByAge.size());
        System.out.println("Список людей через treeSet с компаратором по возрасту: " + treeSetByAge);

        // f)
        // HashSet — это коллекция, которая хранит элементы без дубликатов и не сохраняет порядок добавления элементов.
        // LinkedHashSet — это разновидность HashSet, которая сохраняет порядок добавления элементов без дубликатов.
        // TreeSet — это коллекция, которая хранит элементы в отсортированном порядке без дубликатов.
        // TreeSet с компаратором по фамилии - элементы сортируются по фамилии через внешний компаратор, люди с одинаковыми фамилиями считаются дубликатами и удалаяются.
        // TreeSet с компаратором по возрасту - элементы сортируются по возрасту через внешний компаратор, люди с одинаковым возрастом считаются дубликатами и удалаяются.

    }

    @Test
    public void testWordFrequency() {
        System.out.println("Задание 4:");
        String text = "Проверка подсчёта слов проверка подсчёта";
        System.out.println("Текст: " + text);
        Map<String, Integer> wordFrequency = WordFrequency.countWordFrequency(text);

        assertEquals(2, wordFrequency.get("проверка"));
        assertEquals(2, wordFrequency.get("подсчёта"));
        assertEquals(1, wordFrequency.get("слов"));

        Set<String> uniqueWords = Main.WordFrequency.getUniqueWords(text);
        System.out.println("Уникальные слова: " + uniqueWords);

        Map<String, Integer> wordCount = Main.WordFrequency.countWordFrequency(text);
        System.out.println("Частота встречаемости слов:");
        for (Map.Entry<String, Integer> entry : wordCount.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    @Test
    public void testMapKeyValueSwap() {
        System.out.println("Задание 5:");
        Map<String, Integer> originalMap = new HashMap<>();
        originalMap.put("Один", 1);
        originalMap.put("Два", 2);
        originalMap.put("Три", 3);
        originalMap.put("Четыре", 4);
        originalMap.put("Пять", 5);

        System.out.println("Оригинальная карта: " + originalMap);

        Map<Integer, String> swappedMap = Main.MapInverter.swapKeyValue(originalMap);
        System.out.println("Карта с переставленными ключами и значениями: " + swappedMap);

        assertEquals("Один", swappedMap.get(1));
        assertEquals("Два", swappedMap.get(2));
        assertEquals("Три", swappedMap.get(3));
        assertEquals("Четыре", swappedMap.get(4));
        assertEquals("Пять", swappedMap.get(5));
    }
}