package com.company;

public class Process implements Comparable<Process> {
    int PID = 0;
    int arrivalTime = 0;
    int cpuBurstTime = 0;

    public Process(int PID, int arrivalTime, int cpuBurstTime)
    {
        this.PID = PID;
        this.arrivalTime = arrivalTime;
        this.cpuBurstTime = cpuBurstTime;
    }

    @Override
    public int compareTo(Process o) {
        return this.arrivalTime - o.arrivalTime;
    }
}
