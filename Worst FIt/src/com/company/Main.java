package com.company;

import java.util.Scanner;
import java.util.Vector;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of pocesses: ");
        int numOfProcess = sc.nextInt();
        System.out.println("Enter the processes: ");
        Vector<Integer> processes = new Vector<Integer>();
        for (int i = 0; i < numOfProcess; i++) {
            processes.add(sc.nextInt());
        }

        System.out.println("Enter the number of Memory Blocks: ");
        int blocks = sc.nextInt();
        System.out.println("Enter their sizes: ");
        Vector<Integer> sizes = new Vector<Integer>();
        for (int i = 0; i < blocks; i++) {
            sizes.add(sc.nextInt());
        }

        for (int i = 0; i < numOfProcess; i++) {
            int maxValue = Integer.MIN_VALUE;
            int minValue = -1;
            for (int j = 0; j < blocks; j++) {
                if (sizes.get(j) > maxValue) {
                    maxValue = sizes.get(j);
                    minValue = j;
                }
            }
            if (sizes.get(minValue) >= processes.get(i)){
                sizes.set(minValue, (sizes.get(minValue) - processes.get(i)));
                for (int k = 0; k < blocks; k++){
                    System.out.print(sizes.get(k)+" ");
                }
                System.out.println("");
            } else {
                System.out.println("Cannot serve, sorry");
                int sum = 0;
                for (int k = 0; k < blocks; k++){
                    sum += sizes.get(k);
                }
                System.out.println("External Fragmentation is: "+sum);
                break;
            }


        }



    }
}

