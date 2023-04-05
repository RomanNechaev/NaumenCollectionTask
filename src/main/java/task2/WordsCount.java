package task2;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

import java.io.*;
import java.util.*;

/**
 * @author Nechaev Roman
 */

public class WordsCount {
    public static void main(String[] args) {
        try {
            LinkedHashMap<String, Long> numberOfWordInfile = getNumberOfWordInFile(new File("src/main/resources/Лев_Толстой_Война_и_мир_Том_1,_2,_3,_4_(UTF-8).txt"));
            PriorityQueue<Map.Entry<String, Long>> topTenWords = new PriorityQueue<>((x, y) -> Long.compareUnsigned(y.getValue(), x.getValue()));
            PriorityQueue<Map.Entry<String, Long>> lastTenWords =
                    new PriorityQueue<>(Comparator.comparingLong(Map.Entry::getValue));

            for (Map.Entry<String, Long> entry : numberOfWordInfile.entrySet()) {
                topTenWords.add(entry);
                lastTenWords.add(entry);
            }
            printTopTenWords(topTenWords);
            printLastTenWords(lastTenWords);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static LinkedHashMap<String, Long> getNumberOfWordInFile(File file) throws IOException {
        LinkedHashMap<String, Long> numberOfWordInfile = new LinkedHashMap<>();
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

    private static void printTopTenWords(PriorityQueue<Map.Entry<String, Long>> topTenWords) {
        System.out.println("Top 10 usage word");
        topTenWords.stream().limit(10).forEach(System.out::println);
    }

    private static void printLastTenWords(PriorityQueue<Map.Entry<String, Long>> lastTenWords) {
        System.out.println("Last 10 usage word");
        lastTenWords.stream().limit(10).forEach(System.out::println);
    }
}
