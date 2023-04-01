package task1;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

/**
 * @author Nechaev Roman
 */

public class FirstTask {

    public static void main(String[] args) {
        var firstUserInCol1 = new User("Petr", "petr@gmail.com", "123");
        var secondUserInCol1 = new User("Max", "max@gmail.com", "456");
        var thirdUserInCol1 = new User("Anton", "anton@gmail.com", "789");
        var firstUserInCol2 = new User("Petr", "petr@gmail.com", "123");
        var secondUserInCol2 = new User("Jon", "jon@gmail.com", "456");
        var thirdUserInCol2 = new User("Anna", "anna@gmail.com", "789");
        var col1 = new HashSet<User>();
        var col2 = new HashSet<User>();

        col1.add(firstUserInCol1);
        col1.add(secondUserInCol1);
        col1.add(thirdUserInCol1);
        col2.add(firstUserInCol2);
        col2.add(secondUserInCol2);
        col2.add(thirdUserInCol2);

        findDuplicates(col1, col2).forEach(System.out::println);
    }

    public static List<User> findDuplicates(Collection<User> collA, Collection<User> collB) {
        HashSet<User> collectionFirst = new HashSet<>(collA);
        HashSet<User> collectionSecond = new HashSet<>(collB);
        collectionFirst.retainAll(collectionSecond);
        return collectionFirst.stream().toList();
    }
}
