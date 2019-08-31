package com.company;

import java.util.Comparator;

//This class is responsible for sorting the Vector of processes according to their arrival times
public class SortByArrivalTime implements Comparator<Process> {
    @Override
    public int compare(Process o1, Process o2) {
        return o1.getArrivalTime() - o2.getArrivalTime();
    }
}
