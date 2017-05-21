package main;

import syncs.MProcessor;
import syncs.Processor;

import java.util.Random;

/**
 * Created by hesham on 5/12/17.
 */
public class MainClass {

    public static void main(String[] args) {
        Random r = new Random();
//        int i=200+r.nextInt(3000);
//        System.out.println(i);
        Processor p = new Processor();
        MProcessor p2 = new MProcessor();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    p2.producer1();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    p2.consumer1();
                    p2.producer2();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    p2.consumer2();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread t4 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    p2.consumer3();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
        Thread t5 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    p2.producer2();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
//        try {
//            t1.join();
//            t2.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }


    }
}
