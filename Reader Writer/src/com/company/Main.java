package com.company;

import java.io.Reader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

class Main {

    static int readerCount = 0;
    static Semaphore readerSemaphore = new Semaphore(1);
    static Semaphore writerSemaphore = new Semaphore(1);
    static Vector<Integer> vector = new Vector<Integer>(Arrays.asList(1,2,3,4,5,6,7,8,9,20,11,12));

    static class Read implements Runnable {
        int id = 0;
        public Read(int id)
        {
            this.id = id;
        }
        @Override
        public void run() {
            try {
                readerSemaphore.acquire();
                readerCount++;
                if (readerCount == 1) writerSemaphore.acquire();
                readerSemaphore.release();


                int item = vector.get(0);
                System.out.println("Reader "+id+ " is READING "+item);

                Thread.sleep(1500);
                vector.removeElementAt(0);
                System.out.println("Reader "+id + " has FINISHED READING");

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
        int id = 0;
        public Write(int id)
        {
            this.id = id;
        }
        @Override
        public void run() {
            try {
                writerSemaphore.acquire();
                System.out.println("Enter a number: ");
                int n = sc.nextInt();
                System.out.println("Writer "+id + " is WRITING "+n);
                Thread.sleep(2500);
                vector.add(n);
                System.out.println("Writer "+id + " has finished WRITING");
                writerSemaphore.release();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void main(String[] args) throws Exception {
        // Read read = new Read();
        // Write write = new Write();
        //Thread t1 = new Thread(read);
        //t1.setName("thread1");
        //Thread t2 = new Thread(read);
        // t2.setName("thread2");
        // Thread t3 = new Thread(write);
        //t3.setName("thread3");
        //Thread t4 = new Thread(write);
        //t4.setName("thread4");
        ExecutorService executor= Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        while (true) {
            executor.execute(new Read(1));
            executor.execute(new Read(2));
            executor.execute(new Write(1));

            executor.execute(new Write(2));
        }

        // t1.start();
        //t3.start();
        //  t2.start();
        // t4.start();

    }
}
