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
        int hit = 0;
        int fault = 0;
        for(int i=0;i<n;i++)
        {
            boolean flag = false;
            for(int j=0; j<f; j++)
            {
                if(memory.get(j)== vector.get(i))
                {
                    flag=true;

                }
            }
            if(flag)
                hit+=1;
            else
            {
                fault++;
                boolean done=false;
                for(int j=0; j<f; j++)
                {
                    if(memory.get(j)==-1)
                    {
                        memory.set(j, vector.get(i));

                        done=true;
                        break;
                    }
                }
                if(!done)
                {
                    int mi=-1;
                    int mx=-1;
                    for(int j=0; j<f; j++)
                    {
                        int dis=n;
                        for(int k=i-1; k>=0; k--)
                        {
                            if(memory.get(j)== vector.get(k))
                            {
                                dis=k;
                                break;
                            }
                        }
                        if(dis-i>mx)
                        {
                            mx=dis-i;
                            mi=j;
                        }
                    }
                    if(mi >= 0)
                    memory.set(mi, vector.get(i));

                }
            }
        }

        System.out.println("Number of pages fault using FIFO page replacement algorithm "+fault);
        System.out.println("Page fault rate: "+((double)fault/(double)n)*100);
    }
}
