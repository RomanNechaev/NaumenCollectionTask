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
            Map<String, Long> numberOfWordInfile = getNumberOfWordInFile(new File("src/main/resources/Лев_Толстой_Война_и_мир_Том_1,_2,_3,_4_(UTF-8).txt"));
            Queue<Map.Entry<String,Long>> topTenWords =  new PriorityQueue<>(10, (a, b) -> Long.compare(b.getValue(), a.getValue()));
            Queue<Map.Entry<String,Long>> lastTenWords = new PriorityQueue<>(10,Comparator.comparingLong(Map.Entry::getValue));

            makeRating(topTenWords,lastTenWords,numberOfWordInfile);
            printLastTenWords(lastTenWords);
            printTopTenWords(topTenWords);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void makeRating(Queue<Map.Entry<String,Long>> top,Queue<Map.Entry<String,Long>> last, Map<String, Long> numberOfWordInfile) {
        for (Map.Entry<String, Long> el : numberOfWordInfile.entrySet()) {
            top.add(el);
            last.add(el);
            if(top.size()>10)
                top.poll();
            if(last.size()>10)
                last.poll();
        }
    }

    public static Map<String, Long> getNumberOfWordInFile(File file) throws IOException {
        Map<String, Long> numberOfWordInfile = new LinkedHashMap<>();
        try (LineIterator iterator = FileUtils.lineIterator(file, "UTF-8")) {
            while (iterator.hasNext()) {
                String[] words = iterator.nextLine().split(" ");
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

    private static void printTopTenWords(Queue<Map.Entry<String, Long>> sortedWords) {
        System.out.println("Top 10 usage word");
        sortedWords.forEach(System.out::println);

    }

    private static void printLastTenWords(Queue<Map.Entry<String, Long>> sortedWords) {
        System.out.println("Last 10 usage word");
        sortedWords.forEach(System.out::println);
    }
}
