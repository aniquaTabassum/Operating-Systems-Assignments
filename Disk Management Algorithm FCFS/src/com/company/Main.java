package com.company;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // write your code here
        Queue<Integer> queue = new LinkedList<Integer>();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of Processes: ");
        int numOfProcesses = sc.nextInt();
        System.out.println("Enter the Processes: ");
        for(int i=0;i<numOfProcesses;i++)
        {
            queue.add(sc.nextInt());
        }
        System.out.println("Enter the position of head: ");
        int head = sc.nextInt();
        int totalDiskMovement = 0;
        while (queue.size() != 0)
        {
            int popped = queue.remove();
            System.out.println(popped);
            if(popped > head)
            {
                totalDiskMovement+= (popped - head);
            }
            else{
                totalDiskMovement+= (head - popped);
            }
            head = popped;
        }

        System.out.println("Total disk movement is "+totalDiskMovement);

    }
}
