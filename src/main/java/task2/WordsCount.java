package task2;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Nechaev Roman
 */

public class WordsCount {
    public static void main(String[] args) {
        try {
            Map<String, Long> numberOfWordInfile = getNumberOfWordInFile(new File("src/main/resources/Лев_Толстой_Война_и_мир_Том_1,_2,_3,_4_(UTF-8).txt"));
            Deque<Map.Entry<String, Long>> sortedWords = numberOfWordInfile.entrySet().stream().sorted(Comparator.comparingLong(Map.Entry::getValue)).collect(Collectors.toCollection(ArrayDeque::new));
            printLastTenWords(sortedWords);
            printTopTenWords(sortedWords);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Map<String, Long> getNumberOfWordInFile(File file) throws IOException {
        Map<String, Long> numberOfWordInfile = new HashMap<>();
        try (LineIterator iterator = FileUtils.lineIterator(file, "UTF-8")) {
            while (iterator.hasNext()) {
                String[] words = iterator.nextLine().split("\s");
                for (String rawWord : words) {
                    String word = deletePunctuationMarksFromWord(rawWord).toLowerCase();
                    if (!word.equals(""))
                        numberOfWordInfile.put(deletePunctuationMarksFromWord(word), numberOfWordInfile.getOrDefault(word, 0L) + 1);
                }
            }
            return numberOfWordInfile;
        }
    }

    public static String deletePunctuationMarksFromWord(String word) {
        return word.replaceAll("[\\d\"'`:()| —.,?!;-]", "");
    }

    private static void printTopTenWords(Deque<Map.Entry<String, Long>> sortedWords) {
        System.out.println("Top 10 usage word");
        for (int i = 0; i < 10; i++) {
            System.out.println(sortedWords.pollLast());
        }

    }

    private static void printLastTenWords(Deque<Map.Entry<String, Long>> sortedWords) {
        System.out.println("Last 10 usage word");
        sortedWords.stream().limit(10).forEach(System.out::println);
    }
}
