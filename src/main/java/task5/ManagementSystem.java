package task5;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author Nechaev Roman
 */
public class ManagementSystem {

    private final Deque<Map.Entry<Integer, FutureTask<Double>>> storage = new ArrayDeque<>();

    private int id;

    private final ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);

    public void add(Task calculation) {
        FutureTask<Double> task = new FutureTask<>(calculation);
        id++;
        printLog();
        storage.addFirst(Map.entry(id, task));
        threadPoolExecutor.submit(task);
    }

    public Map.Entry<Integer, FutureTask<Double>> get() throws NullPointerException, ExecutionException, InterruptedException {
        return storage.pollLast();
    }

    public void printLog() {
        System.out.println("Задача создана, id=" + id);
    }

}