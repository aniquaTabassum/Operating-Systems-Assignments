package com.company;

import java.util.Scanner;
import java.util.Vector;

public class Main {

    public static void main(String[] args) {
        // write your code here
        Vector<Integer> vector = new Vector<>();
        Vector<Integer> memory = new Vector<>();
        Scanner sc = new Scanner(System.in);
        int p=0;
        int n = 0;
        int f = 0;
        System.out.println("Enter the number of pages: ");
        p = sc.nextInt();
        System.out.println("Enter the number of Reference: ");
        n = sc.nextInt();
        System.out.println("Reference string: ");
        for(int i=0 ;i<n; i++)
        {
            vector.add(sc.nextInt());
        }
        System.out.println("Number of memory page: ");
        f = sc.nextInt();
        for(int i=0 ;i<f; i++)
        {
            memory.add(-1);
        }
        int value = 0;
        int cnt = 0;
        int fault = 0;
        int flag = 0;
        int j = 0;
        while(cnt!=n)
        {
            for(int i=0; i<f; i++)
            {
                if(memory.get(i) == vector.get(cnt))
                    flag=1;
            }
            if(flag==0)
            {
                value= vector.get(cnt);
                memory.set(j, value);
                j++;
                fault++;
            }
            if(j==f)
                j=0;
            cnt++;
            flag=0;
        }
        System.out.println("Number of pages fault using FIFO page replacement algorithm "+fault);
        System.out.println("Page fault rate: "+((double)fault/(double)n)*100);
    }
}
