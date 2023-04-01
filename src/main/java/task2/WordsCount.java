package task2;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Nechaev Roman
 */

public class WordsCount {
    public static void main(String[] args) {
        try {
            HashMap<String, Long> numberOfWordInfile = getNumberOfWordInFile(new BufferedReader(new FileReader("src/main/resources/Лев_Толстой_Война_и_мир_Том_1,_2,_3,_4_(UTF-8).txt")));
            LinkedHashMap<String, Long> sortedMap = numberOfWordInfile.entrySet().stream()
                    .sorted(Comparator.comparingLong(Map.Entry::getValue))
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
            Set<Map.Entry<String, Long>> wordsSet = sortedMap.entrySet();
            printTopTenWords(wordsSet);
            printLastTenWords(wordsSet);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static HashMap<String, Long> getNumberOfWordInFile(Reader reader) throws IOException {
        HashMap<String, Long> numberOfWordInfile = new HashMap<>();
        StreamTokenizer streamTokenizer = new StreamTokenizer(reader);
        int currentToken = streamTokenizer.nextToken();
        while (currentToken != StreamTokenizer.TT_EOF) {
            if (streamTokenizer.sval != null) {
                String word = deletePunctuationMarksFromWord(streamTokenizer.sval);
                if (word.length() > 0)
                    numberOfWordInfile.put(word, numberOfWordInfile.getOrDefault(streamTokenizer.sval, 0L) + 1);
            }
            currentToken = streamTokenizer.nextToken();
        }
        return numberOfWordInfile;
    }

    public static String deletePunctuationMarksFromWord(String word) {
        return word.replaceAll("[ —.,?!;-]", "");
    }

    private static void printTopTenWords(Set<Map.Entry<String, Long>> numberOfWord) {
        System.out.println("Top 10 usage word");
        numberOfWord.stream().limit(10).forEach(System.out::println);
    }

    private static void printLastTenWords(Set<Map.Entry<String, Long>> numberOfWord) {
        System.out.println("Last 10 usage word");
        numberOfWord.stream().skip(Math.max(0, numberOfWord.size() - 10)).forEach(System.out::println);
    }

}

