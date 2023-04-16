package task5;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.*;

/**
 * @author Nechaev Roman
 */
public class ManagementSystem {

    private final Queue<Map.Entry<Integer, FutureTask<Double>>> storage = new ConcurrentLinkedQueue<>();

    private int id;

    private final ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);

    public void add(Task calculation) {
        FutureTask<Double> task = new FutureTask<>(calculation);
        id++;
        printLog();
        storage.add(Map.entry(id, task));
        threadPoolExecutor.submit(task);
    }

    public Map.Entry<Integer, FutureTask<Double>> get() throws NullPointerException {
        return storage.poll();
    }

    public void printLog() {
        System.out.println("Задача создана, id=" + id);
    }

}