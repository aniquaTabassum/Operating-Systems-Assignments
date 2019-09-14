package com.company;

import java.awt.*;
import java.io.*;
import java.util.Scanner;
import java.util.Vector;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of processes : ");
        int numOfProcess = sc.nextInt();
        System.out.println("Enter the number of resources : ");
        int numOfResources = sc.nextInt();
        Vector<Vector<Integer>> max = new Vector<Vector<Integer>>();
        Vector<Vector<Integer>> allocation = new Vector<Vector<Integer>>();

        Vector<Integer> recouces = new Vector<Integer>();
        Vector<Integer> totalAllocation = new Vector<Integer>();
        Vector<Integer> available = new Vector<Integer>();
        Vector<Boolean> finish = new Vector<Boolean>();

        Vector<Vector<Integer>> need = new Vector<Vector<Integer>>();

        Vector<Integer> processes = new Vector<Integer>();

        Vector<Integer> safeSequence = new Vector<Integer>();
        for(int i=0;i<numOfProcess;i++)
        {
            max.add(new Vector<Integer>());
            allocation.add(new Vector<Integer>());
            need.add(new Vector<Integer>());
            processes.add(i);
        }
        File file = new File("/home/aniqua/Desktop/bank");

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line = "";
            String lineToPrint = "";
            String[] decodedFile = null;
            while((line = bufferedReader.readLine())!=null)
            {

                //System.out.println(line);
                decodedFile = line.split("\\s+");
               /* for(int i=0;i<decodedFile.length;i++)
                {
                    System.out.println(decodedFile[i]);
                }*/

            }
            int index = 0;

            for(int i=0;i<numOfProcess;i++)
            {
                for(int j=0;j<numOfResources;j++, index++)
                {
                    max.get(i).add(Integer.valueOf(decodedFile[index]));
                }

                for(int j=0;j<numOfResources;j++, index++)
                {
                    allocation.get(i).add(Integer.valueOf(decodedFile[index]));
                }

                for(int j=0;j<numOfResources;j++)
                {
                    need.get(i).add(max.get(i).get(j) - allocation.get(i).get(j));
                }

            }

            for(int i = 0; i< numOfResources; i++, index++)
            {
                recouces.add(Integer.valueOf(decodedFile[index]));
            }

            System.out.println("   Max       Allocation");

            for(int i = 0; i< numOfProcess; i++)
            {
                System.out.print(i+"   ");
                for(int j = 0; j< numOfResources; j++)
                {
                    System.out.print(max.get(i).get(j)+" ");
                }
                System.out.print("      ");
                for(int j = 0; j< numOfResources; j++)
                {
                    System.out.print(allocation.get(i).get(j)+" ");
                }

                System.out.println();
            }

            for(int i = 0; i< numOfResources; i++)
            {
                int toAdd = 0;
                for(int j=0; j<numOfProcess; j++)
                {
                    toAdd+=allocation.get(j).get(i);
                }
                totalAllocation.add(toAdd);
            }

            for (int i=0;i<numOfResources;i++)
            {
                available.add(recouces.get(i) - totalAllocation.get(i));
            }


            for (int i=0;i<numOfProcess;i++)
            {
                finish.add(false);
            }

            int i = 0;
            int numOfFinished = 0;
            int originalSize = processes.size();

            int ready = 1;
            int systemBreak = 1;
            int iteration = 1;
            while (processes.size()!=0 && systemBreak == 1)
            {
                int currentProc = processes.get(0);
                if(finish.get(currentProc)== false)
                {
                     ready = 1;
                    for(int j = 0; j<numOfResources;j++)
                    {
                        ready = 1;
                        if(need.get(currentProc).get(j) > available.get(j))
                        {
                            ready = 0;
                            break;
                        }
                    }
                    if(ready == 1)
                    {
                        for(int j = 0; j< numOfResources; j++)
                        {
                            available.set(j, (available.get(j) + allocation.get(currentProc).get(j)));
                            allocation.get(currentProc).set(j, 0);
                        }
                        numOfFinished+=1;
                        finish.set(currentProc, Boolean.TRUE);
                        safeSequence.add(currentProc);
                       // System.out.println("finished "+currentProc);
                    }

                    processes.removeElementAt(0);

                    if(ready == 0 && iteration == 1)
                    {

                        systemBreak = 0;
                    }

                    if(ready == 0)
                    {
                        processes.add(currentProc);
                   //     System.out.println("added "+currentProc);
                    }

                }
                i+=1;
                iteration+=1;
            }


                int safe = 1;
                for(int j =0; j< numOfProcess; j++)
                {
                    if(finish.get(j) == false)
                    {
                        safe = 0;
                    }
                }

                if(safe == 1 && systemBreak ==1)
                {
                    System.out.println("Safe process");
                    System.out.println("Printing safe sequence: ");
                    for(int j=0;j<safeSequence.size();j++)
                    {
                        System.out.print(safeSequence.get(j)+" ");
                    }
                }

                else {
                    System.out.println("Process not safe");
                }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
