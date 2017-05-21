package syncs;

import java.util.LinkedList;
import java.util.Random;

/**
 * Created by hesham on 5/12/17.
 */
public class MProcessor {
    private final int SIZE = 50;
    private LinkedList<Integer> buff = new LinkedList<>();
    private Object mutex = new Object();


    public void producer1() throws InterruptedException {
        int v = 0;
        Random r = new Random();
        while (true) {
            Thread.sleep(100 + r.nextInt(500));
            synchronized (mutex) {
                while (buff.size() == SIZE) {
                    System.out.println("1:Buff is full, can't add");
                    mutex.wait();
                }
                buff.add(v++);
                System.out.println("1:Produce: " + v + ";Buffer size:" + buff.size());
                mutex.notifyAll();
//                Thread.sleep(200 + r.nextInt(3000));
            }
        }


    }

    public void producer2() throws InterruptedException {
        int v = 0;
        Random r = new Random();
        while (true) {
            Thread.sleep(100 + r.nextInt(600));
            synchronized (mutex) {
                while (buff.size() == SIZE) {
                    System.out.println("2:Buff is full, can't add");
                    mutex.wait();
                }
                buff.add(v++);
                System.out.println("2:Producer: " + v + ";Buffer size:" + buff.size());
                mutex.notifyAll();
//                Thread.sleep(200 + r.nextInt(3000));
            }
        }


    }

    public void consumer1() throws InterruptedException {
        Random r = new Random();
        while (true) {
            Thread.sleep(200 + r.nextInt(3000));
            synchronized (mutex) {
                if (buff.size() == 0) {
                    System.out.println("1:Buff is empty, can't remove");
                    mutex.wait();
                }
                int v = buff.removeFirst();
                System.out.println("1:Consumer: " + v + ";Buffer size:" + buff.size());
                mutex.notifyAll();

            }
        }


    }

    public void consumer2() throws InterruptedException {
        Random r = new Random();
        while (true) {
            Thread.sleep(200 + r.nextInt(3000));
            synchronized (mutex) {
                if (buff.size() == 0) {
                    System.out.println("2:Buff is empty, won't remove");
                    mutex.wait();
                }
                int v = buff.removeFirst();
                System.out.println("2:Consumer: " + v + ";Buffer size:" + buff.size());
                mutex.notifyAll();

            }
        }


    }

    public void consumer3() throws InterruptedException {
        Random r = new Random();
        while (true) {
            Thread.sleep(20 + r.nextInt(300));
            synchronized (mutex) {
                if (buff.size() == 0) {
                    System.out.println("3:Buff is empty, fail remove");
                    mutex.wait();
                }
                int v = buff.removeFirst();
                System.out.println("3:Consumer: " + v + ";Buffer size:" + buff.size());
                mutex.notifyAll();

            }
        }


    }

}
