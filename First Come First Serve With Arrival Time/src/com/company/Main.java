package com.company;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.Vector;

public class Main {

    public static void main(String[] args) {

        //The Queue will be used to store all the processes
        //its remove() method will return and remove the process having the shortest arrival time
        Queue<Process> processQueue = new PriorityQueue<Process>();
        System.out.println("Enter the number of processes: ");
        Scanner sc = new Scanner(System.in);
        int numOfProcesses = sc.nextInt();
        int currentTime = 0;
        Vector<Integer> arrivalTimeVector = new Vector<Integer>();
        Vector<Integer> cpuTimeVector = new Vector<Integer>();
        Vector<Integer> waitingTime = new Vector<Integer>();
        Vector<Integer> turnAroundTime = new Vector<Integer>();
        System.out.println("Enter the arrival times: ");
        for(int i=0;i<numOfProcesses;i++)
        {
            arrivalTimeVector.add(sc.nextInt());
        }

        System.out.println("Enter the CPU times: ");
        for(int i=0;i<numOfProcesses;i++)
        {
            cpuTimeVector.add(sc.nextInt());
        }

        for(int i=0;i<numOfProcesses;i++)
        {
            processQueue.add(new Process((i), arrivalTimeVector.get(i), cpuTimeVector.get(i)));
        }

        for(int i=0;i<numOfProcesses;i++)
        {
        waitingTime.add(0);
        turnAroundTime.add(0);
        }

        System.out.print(currentTime);
        while (processQueue.size()!=0)
        {
            System.out.print(" ----");
            Process process = processQueue.remove();
            int tempArrivalTime = process.getArrivalTime();
            int tempCpuTime = process.getCpuTime();
            int tempPID = process.getPID();
            waitingTime.set(tempPID, (currentTime - tempArrivalTime));
            currentTime+=tempCpuTime;
            turnAroundTime.set(tempPID, (waitingTime.get(tempPID) + cpuTimeVector.get(tempPID)));
            System.out.print(" P"+(tempPID+1)+" ---- "+currentTime);
        }

        System.out.println();
        System.out.println();
        double totalWaitingTime = 0;
        double totalTurnAroundTime = 0;

        System.out.println("Waiting times and Turnaround Times are: ");
        for(int i=0;i<numOfProcesses;i++)
        {
            System.out.println("P"+(i+1)+"  "+waitingTime.get(i)+"  "+turnAroundTime.get(i));
            totalWaitingTime+=waitingTime.get(i);
            totalTurnAroundTime+=turnAroundTime.get(i);
        }

        System.out.println();
        System.out.println();

        System.out.println("Average waiting time is "+(totalWaitingTime/numOfProcesses));
        System.out.println("Average turnaround time is "+(totalTurnAroundTime/numOfProcesses));

    }
}
