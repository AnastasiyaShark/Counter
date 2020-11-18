package Counter;

import java.util.Arrays;
import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) {

        final MyCallable myCallableNorthern = new MyCallable("Northern");
        final MyCallable myCallableSouthern = new MyCallable("Southern");
        final MyCallable myCallableOriental = new MyCallable("Oriental");
        final MyCallable myCallableWest = new MyCallable("West");

        final ExecutorService threadPool = Executors.newFixedThreadPool(4);

        System.out.println("Создаю потоки..");
        final Future<Integer> taskNorthern = threadPool.submit(myCallableNorthern);
        final Future<Integer> taskSouthern = threadPool.submit(myCallableSouthern);
        final Future<Integer> taskOriental = threadPool.submit(myCallableOriental);
        final Future<Integer> taskWest = threadPool.submit(myCallableWest);

        try {
            final Integer resultOfTaskNorthern = taskNorthern.get();
            final Integer resultOfTaskSouthern = taskSouthern.get();
            final Integer resultOfTaskOriental = taskOriental.get();
            final Integer resultOfTaskWest = taskWest.get();
            System.out.printf("resultOfTaskNorthern = %d %nresultOfTaskSouthern = %d %nresultOfTaskOriental = %d %n" +
                            "resultOfTaskWest = %d%n ", resultOfTaskNorthern, resultOfTaskSouthern, resultOfTaskOriental, resultOfTaskWest);

        } catch (InterruptedException | ExecutionException err) {
            System.out.println("error" + err.getMessage());
        }

        try {
            Integer result = threadPool.invokeAny(Arrays.asList(myCallableNorthern, myCallableOriental, myCallableSouthern, myCallableWest));
            System.out.println(result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("Завершаю все потоки.");
        threadPool.shutdown();
    }
}
