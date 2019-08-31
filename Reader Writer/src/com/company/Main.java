package com.company;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Vector;
import java.util.concurrent.Semaphore;

class Main {

    static int readerCount = 0;
    static Semaphore readerSemaphore = new Semaphore(1);
    static Semaphore writerSemaphore = new Semaphore(1);
    static Vector<Integer> vector = new Vector<Integer>(Arrays.asList(1,2,3,4));

    static class Read implements Runnable {
        @Override
        public void run() {
            try {
                readerSemaphore.acquire();
                readerCount++;
                if (readerCount == 1) writerSemaphore.acquire();
                readerSemaphore.release();

                int item = vector.get(0);
                System.out.println("Thread "+Thread.currentThread().getName() + " is READING "+item);

                Thread.sleep(1500);
                vector.removeElementAt(0);
                System.out.println("Thread "+Thread.currentThread().getName() + " has FINISHED READING");

                readerSemaphore.acquire();
                readerCount--;
                if (readerCount == 0) writerSemaphore.release();
                readerSemaphore.release();

            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    static Scanner sc = new Scanner(System.in);
    static class Write implements Runnable {
        @Override
        public void run() {
            try {
                writerSemaphore.acquire();
                System.out.println("Enter a number: ");
                int n = sc.nextInt();
                System.out.println("Thread "+Thread.currentThread().getName() + " is WRITING "+n);
                Thread.sleep(2500);
                vector.add(n);
                System.out.println("Thread "+Thread.currentThread().getName() + " has finished WRITING");
                writerSemaphore.release();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Read read = new Read();
        Write write = new Write();
        Thread t1 = new Thread(read);
        t1.setName("thread1");
        Thread t2 = new Thread(read);
        t2.setName("thread2");
        Thread t3 = new Thread(write);
        t3.setName("thread3");
        Thread t4 = new Thread(write);
        t4.setName("thread4");
        while(true) {
            t1.run();
            t3.run();
            t2.run();
            t4.run();
        }
    }
}
