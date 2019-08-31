package com.company;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        //The queue will be used to keep track of the processes whose
        //arrival times will be less than or equal to that of the current process
        //and its remove() method will return and remove the process with the lowest CPU burst time
        Queue<Process> processQueue = new PriorityQueue<Process>();

        //The vector will be used to store all the processes
        Vector<Process> processVector = new Vector<Process>();

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
            processVector.add(new Process((i), arrivalTimeVector.get(i), cpuTimeVector.get(i)));
        }

        for(int i=0;i<numOfProcesses;i++)
        {
            waitingTime.add(0);
            turnAroundTime.add(0);
        }

        //sorted the vector according to the arrival times of the processes
        Collections.sort(processVector, new SortByArrivalTime());
        System.out.print(currentTime);
        int i = 0;
        while (processVector.size()!=0)
        {
            System.out.print(" ---- ");
            Process process = processVector.get(i);
            int tempPID = process.getPID();
            int tempArrivalTime = process.getArrivalTime();
            int tempCPUtime = process.getCpuTime();
            waitingTime.set(tempPID, (currentTime - tempArrivalTime));
            currentTime+=tempCPUtime;
            turnAroundTime.set(tempPID, (waitingTime.get(tempPID) + cpuTimeVector.get(tempPID)));
            System.out.print(" P"+(tempPID+1)+" ---- "+currentTime);
            for(int j=0;j<processVector.size() && j!=i; j++)
            {
                if(processVector.get(j).getArrivalTime()<=currentTime)
                {
                    Process tempProcess = processVector.get(j);
                    processQueue.add(tempProcess);

                }
            }

            processVector.remove(i);
            i++;
            while (processQueue.size()!=0)
            {
                System.out.print(" ---- ");
                Process process2 = processQueue.remove();
                int tempPID2 = process2.getPID();
                int tempArrivalTime2 = process2.getArrivalTime();
                int tempCPUtime2 = process2.getCpuTime();
                waitingTime.set(tempPID2, (currentTime - tempArrivalTime2));
                currentTime+=tempCPUtime2;
                turnAroundTime.set(tempPID2, (waitingTime.get(tempPID2) + cpuTimeVector.get(tempPID2)));

                System.out.print(" P"+(tempPID2+1)+" ---- "+currentTime);
               processVector.remove(process2);
            }
        }

        System.out.println();
        System.out.println();

        double totalWaitingTime = 0;
        double totalTurnAroundTime = 0;
        System.out.println("Waiting times and Turnaround Times are: ");
        for(i=0;i<numOfProcesses;i++)
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
