package com.company;

//This class is responsible for extracting the process having the shortest CPU time from the Queue
public class Process implements Comparable<Process> {

    private int PID = 0;
    private int arrivalTime = 0;
    private int cpuTime = 0;

    public Process(int PID, int arrivalTime, int cpuTime)
    {
        this.arrivalTime = arrivalTime;
        this.cpuTime = cpuTime;
        this.PID = PID;
    }
    @Override
    public int compareTo(Process o) {
        return this.cpuTime - o.cpuTime;
    }

    public int getArrivalTime()
    {
        return this.arrivalTime;
    }

    public int getCpuTime()
    {
        return this.cpuTime;
    }
    public int getPID()
    {
        return this.PID;
    }
}
