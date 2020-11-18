package Counter;

import java.util.concurrent.Callable;


public class MyCallable implements Callable<Integer> {
    final private String threadName;

    public MyCallable(String threadName) {
        this.threadName = threadName;
    }

    @Override
    public Integer call() throws Exception {
        Thread.currentThread().setName(threadName);
        int count = 0;
        for (int i = 0; i < 3; i++) {
            Thread.sleep(3000);
            System.out.printf("Всем привет! Я поток %s ;) %n", Thread.currentThread().getName());
            count++;
        }
        return count;
    }
}
