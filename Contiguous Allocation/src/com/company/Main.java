package com.company;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Vector;

public class Main {

    public static void main(String[] args) {
	// write your code here
        int numOfBlocks = 0;
        System.out.println("Enter the number of blocks: ");
        Scanner sc = new Scanner(System.in);
        numOfBlocks = sc.nextInt();
        Vector<Integer> memory = new Vector<Integer>();
        Map<String, ObjectOfFile> collectionOfFiles = new HashMap<String, ObjectOfFile>();
        for(int i=0; i<numOfBlocks; i++)
        {
            memory.add(0);
        }

        int startIndex = 0;
        int tempIndex = 0;
        while(true) {
            System.out.println("Press 1 for adding new file");
            System.out.println("Press 2 for viewing file information");
            System.out.println("Press 3 to terminate");

            int choice = sc.nextInt();
            sc.nextLine();
            if(choice == 1)
            {
                String tempName = "";
                System.out.println("Enter the name of the file: ");
                tempName = sc.nextLine();
                int tempSize = 0;
                System.out.println("Enter the size: ");
                tempSize = sc.nextInt();

                int available = 0;

                for(int i = startIndex ; i<numOfBlocks; i++)
                {
                    if(memory.get(i) !=0)
                    {
                        break;
                    }
                    else {
                        available +=1;
                        if(available == tempSize)
                        {
                            tempIndex = i + 1;
                            break;
                        }
                    }
                }
                if(available >= tempSize)
                {

                    ObjectOfFile objectOfFile = new ObjectOfFile(tempName, tempSize, startIndex);

                    for(int i = startIndex; i < tempSize; i++)
                    {
                        memory.set(i, 1);
                    }
                    startIndex = tempIndex;
                    System.out.println("File "+tempName+" Created");
                    collectionOfFiles.put(tempName, objectOfFile);
                }
                else {
                    System.out.println("File "+tempName+" Could not be created");
                }

            }

            else if(choice == 2)
            {
                System.out.println("Find A File");
                String tempName = "";
                System.out.println("Enter the file name:");
                tempName = sc.nextLine();
                if(collectionOfFiles.containsKey(tempName))
                {
                    System.out.println("File "+tempName+" has been found");
                    System.out.println("Total size is "+collectionOfFiles.get(tempName).memorySpace);
                    System.out.println("Occupied memory spaces are :");
                    int start = collectionOfFiles.get(tempName).startIndex;
                    int total = collectionOfFiles.get(tempName).memorySpace;
                    for(int i = start; i< (start+total); i++)
                    {
                        System.out.print(i);
                        if(i!= ((start+total) -1))
                        {
                            System.out.print(", ");
                        }
                    }
                    System.out.println();
                }
                else
                {
                    System.out.println("File "+tempName+" cannot be found");
                }
            }

            else
            {
                System.exit(0);
            }
        }
    }
}
