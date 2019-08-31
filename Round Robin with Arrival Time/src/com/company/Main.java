package com.company;

import java.util.*;

public class Main {
    public static void main(String[] args) {
	// write your code here
        Queue<Process> processQueue = new PriorityQueue<Process>();
        Queue<Process> waitingQueue = new LinkedList<Process>();

        Vector<Integer> arrivalTimeVector = new Vector<Integer>();
        Vector<Integer> cpuBurstTimeVector = new Vector<Integer>();

        int numberOfProcess = 0;
        int currentTime = 0;
        int quantTime = 3;
        System.out.println("Enter the number of processes: ");
        Scanner sc = new Scanner(System.in);
        numberOfProcess = sc.nextInt();
        System.out.println("Enter the Arrival Times ");
        for(int i=0;i<numberOfProcess;i++)
        {
            arrivalTimeVector.add(sc.nextInt());
        }

        System.out.println("Enter the CPU Burst Times ");

        for(int i=0;i<numberOfProcess;i++)
        {
            cpuBurstTimeVector.add(sc.nextInt());
        }

        for(int i=0; i<numberOfProcess; i++)
        {
            processQueue.add(new Process((i+1), arrivalTimeVector.get(i), cpuBurstTimeVector.get(i)));
        }

        Process currentProcess = processQueue.remove();

        waitingQueue.add(currentProcess);
        System.out.print("0 ");
        while(waitingQueue.size() != 0)
        {
            currentProcess = waitingQueue.remove();
            int tempArrivalTime = currentProcess.arrivalTime;
            int tempCPUtime = currentProcess.cpuBurstTime;
            int tempPID = currentProcess.PID;

            if(tempCPUtime >= quantTime) {
                currentTime += quantTime;
                tempCPUtime -= quantTime;
                currentProcess.cpuBurstTime = tempCPUtime;
            }

            else{
                currentTime += tempCPUtime;
                tempCPUtime = 0;
            }
            System.out.print("--- P"+tempPID+" --- "+currentTime);

            Process nextProcess = null;
            int nextArrivalTime = 0;
            while(processQueue.size()!=0 && nextArrivalTime<= currentTime)
            {

                    nextProcess = processQueue.peek();
                    nextArrivalTime = nextProcess.arrivalTime;

                    if(nextArrivalTime<=currentTime){

                waitingQueue.add(nextProcess);
                processQueue.remove();}

            }
            if(tempCPUtime!=0)
            {
                waitingQueue.add(currentProcess);
            }


        }
    }
}
