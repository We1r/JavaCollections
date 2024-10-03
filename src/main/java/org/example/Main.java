package org.example;

import java.util.*;

public class Main {
    //a)
    public static int[] createRandomArray(int size) {
        int[] array = new int[size];
        Random random = new Random();

        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(101);
        }

        return array;
    }

    //b
    public static List<Integer> arrayToList(int[] array) {
        List<Integer> list = new ArrayList<>();

        for (int i : array) {
            list.add(i);
        }

        return list;
    }

    //c)
    public static List<Integer> sortListAscending(List<Integer> list) {
        Collections.sort(list);
        return list;
    }

    //d)
    public static List<Integer> sortListDescending(List<Integer> list) {
        list.sort(Collections.reverseOrder());
        return list;
    }

    //e)
    public static List<Integer> shuffleList(List<Integer> list) {
        Collections.shuffle(list);
        return list;
    }

    //f
    public static List<Integer> rotateList(List<Integer> list) {
        Collections.rotate(list, 1);
        return list;
    }

    //g)
    public static List<Integer> uniqueElements(List<Integer> list) {
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (Integer num : list) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }
        List<Integer> uniqueList = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) {
            if (entry.getValue() == 1) {
                uniqueList.add(entry.getKey());
            }
        }
        return uniqueList;
    }

    //h
    public static List<Integer> duplicateElements(List<Integer> list) {
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (Integer num : list) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }
        List<Integer> duplicates = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) {
            if (entry.getValue() > 1) {
                for (int i = 0; i < entry.getValue(); i++) {
                    duplicates.add(entry.getKey());
                }
            }
        }
        return duplicates;
    }

    //i
    public static int[] listToArray(List<Integer> list) {
        int[] array = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }
        return array;
    }

    //j
    public static Map<Integer, Integer> countOccurrences(int[] array) {
        Map<Integer, Integer> occurrences = new HashMap<>();
        for (int num : array) {
            occurrences.put(num, occurrences.getOrDefault(num, 0) + 1);
        }
        return occurrences;
    }

    //Задание 2
    public static class PrimesGenerator implements Iterable<Integer> {
        private final List<Integer> primes;

        public PrimesGenerator(int N) {
            primes = new ArrayList<>();
            generatePrimes(N);
        }

        // Метод для генерации N простых чисел
        private void generatePrimes(int N) {
            int count = 0;
            int num = 2;
            while (count < N) {
                if (isPrime(num)) {
                    primes.add(num);
                    count++;
                }
                num++;
            }
        }

        // Метод для проверки, является ли число простым
        private boolean isPrime(int num) {
            if (num < 2) return false;
            for (int i = 2; i <= Math.sqrt(num); i++) {
                if (num % i == 0) return false;
            }
            return true;
        }

        @Override
        public Iterator<Integer> iterator() {
            return primes.iterator();
        }

        public Iterator<Integer> reverseIterator() {
            return new Iterator<Integer>() {
                int current = primes.size() - 1;

                @Override
                public boolean hasNext() {
                    return current >= 0;
                }

                @Override
                public Integer next() {
                    return primes.get(current--);
                }
            };
        }
    }

    //Задание 3
    public static class Human implements Comparable<Human> {
        private String firstName;
        private String lastName;
        private int age;

        public Human(String firstName, String lastName, int age) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.age = age;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public int getAge() {
            return age;
        }

        @Override
        public int compareTo(Human other) {
            return this.lastName.compareTo(other.lastName);
        }

        @Override
        public String toString() {
            return firstName + " " + lastName + ", Возраст: " + age;
        }

        public static class HumanComparatorByLastName implements Comparator<Human> {
            @Override
            public int compare(Human h1, Human h2) {
                return h1.getLastName().compareTo(h2.getLastName());
            }
        }
    }

    //Задание 4
    public static class WordFrequency {

        public static Map<String, Integer> countWordFrequency(String text) {
            String[] words = text.toLowerCase().split("\\s+");  // Преобразуем текст в массив слов
            Map<String, Integer> wordCount = new HashMap<>();
            for (String word : words) {
                wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
            }
            return wordCount;
        }

        // Метод для получения уникальных слов
        public static Set<String> getUniqueWords(String text) {
            String[] words = text.toLowerCase().split("\\s+");  // Преобразуем текст в массив слов
            return new HashSet<>(Arrays.asList(words));         // Возвращаем множество уникальных слов
        }
    }

    //Задание 5
    public static class MapInverter {

        public static <K, V> Map<V, K> swapKeyValue(Map<K, V> map) {
            Map<V, K> swappedMap = new HashMap<>();
            for (Map.Entry<K, V> entry : map.entrySet()) {
                swappedMap.put(entry.getValue(), entry.getKey());
            }
            return swappedMap;
        }
    }
}