package syncs;

import java.util.*;

/**
 * Created by hesham on 5/12/17.
 */
public class Processor {
    private final int LIMIT = 5;
    private LinkedList<Integer> list = new LinkedList<>();
    private Object lock = new Object();

    public void produce() throws InterruptedException {
        int v = 0;
        Random r = new Random();
        while (true) {
            Thread.sleep(200 + r.nextInt(3000));
            synchronized (lock) {
                while (list.size() == LIMIT) {
                    System.out.println("Buffer is full, can't add");
                    lock.wait();
                }
                list.add(v++);
                System.out.println("Produce: " + v + ";Buffer size:" + list.size());
                lock.notify();
//                Thread.sleep(200 + r.nextInt(3000));
            }
        }


    }

    public void consume() throws InterruptedException {
        Random r = new Random();
        while (true) {
            Thread.sleep(200 + r.nextInt(2500));
            synchronized (lock) {
                if (list.size() == 0) {
                    System.out.println("Buffer is empty, can't remove");
                    lock.wait();
                }
                int v = list.removeFirst();
                System.out.println("Consume: " + v + ";Buffer size:" + list.size());
                lock.notify();

            }
        }


    }


}
