package com.company;

import java.util.*;

/*
 * @author Aniqua Tabassum
 */
public class Main {

    static Vector<Vector<Integer>> list;
    static boolean[] visited;
    static int[] color;
    static int[] parent;
    static int[] discover;
    static int[] finish;
    static int white = 1;
    static int grey = 2;
    static int black = 3;
    static int time;
    static int nodes;
    static int edges;
    static Map<Character, Integer> charToInt = new HashMap<Character, Integer>();
    static Map<Integer, Character> intToChar = new HashMap<Integer, Character>();
    static Stack<Integer> toRemove = new Stack<Integer>();
   // static Queue<Integer> path = new LinkedList<Integer>();
    static boolean cycle = false;
    static Vector<Integer> path = new Vector<Integer>();
    public static void dfs() {
        for (int i = 0; i < nodes; i++) {
            color[i] = white;
            parent[i] = Integer.MIN_VALUE;
        }
        time = 0;
        for (int i = 0; i < nodes; i++) {
            if (color[i] == white) {
                dfsVisit(i);
            }
        }
    }

    public static void dfsVisit(int s) {
        path.add(s);
        color[s] = grey;
        for (int i = 0; i < list.get(s).size(); i++) {
            if (color[list.get(s).get(i)] == white) {
               parent[i] = s;
                dfsVisit(list.get(s).get(i));
            }

            if (color[list.get(s).get(i)] == grey) {
                System.out.println("Cycle found");
                int dest = list.get(s).get(i);
                int currentSize = path.size() -1;
                Vector<Integer> reverse = new Vector<Integer>();
               // System.out.println(s);
              //  reverse.add(s);
                for(int j = currentSize; path.get(j)!=dest;j--)
                {
                   // System.out.print(path.get(j)+" ");
                    reverse.add(path.get(j));
                }
                reverse.add(dest);
                //System.out.println(dest);
                for(int j = reverse.size() -1; j >=0 ; j--)
                {
                    int integer = reverse.get(j);
                    System.out.print(intToChar.get(integer)+" ");
                }
                System.out.println();

                int toDelInt = reverse.get(0);
                toRemove.push(toDelInt);


            }

        }
        color[s] = black;
        time += 1;
        finish[s] = time;

    }

    public static void main(String[] args) {
        System.out.println("Enter the number of nodes and edges: ");
        Scanner sc = new Scanner(System.in);
        nodes = sc.nextInt();
        edges = sc.nextInt();
        sc.nextLine();
        visited = new boolean[nodes];
        color = new int[nodes];
        parent = new int[nodes];
        discover = new int[nodes];
        finish = new int[nodes];
        list = new Vector<Vector<Integer>>(nodes);
        System.out.println("Enter the nodes: ");
        for (int i = 0; i < nodes; i++) {
            list.add(new Vector<Integer>());
        }

        for(int i=0;i<nodes;i++)
        {
            char c = sc.nextLine().charAt(0);
            charToInt.put(c,i);
            intToChar.put(i,c);
        }
        System.out.println("Enter the connections: ");
        for (int i = 0; i < edges; i++) {
            char c = sc.nextLine().charAt(0);
            char d = sc.nextLine().charAt(0);

            int x = charToInt.get(c);
            int y = charToInt.get(d);
            list.get(x).add(y);
           // System.out.println("got char "+x+""+y);

            //list.get(y).add(x);
        }
        dfs();



        while(toRemove.size()!=0)
        {
            int toDelInt = toRemove.pop();
            for(int index = 0; index<list.size();index++)
            {
                if(index == toDelInt)
                {
                    list.removeElement(list.get(index));
                }
                for(int se =0; se<list.get(index).size();se++)
                {
                    if(list.get(index).get(se) == toDelInt)
                    {
                        list.get(index).removeElement(toDelInt);
                    }
                }
            }
        }

        for(int i=0;i<list.size();i++)
        {

            System.out.print(intToChar.get(i)+" ==> ");
            for(int j=0; j<list.get(i).size(); j++)
            {
                 {
                    System.out.print(intToChar.get(list.get(i).get(j)) + " ");
                }
            }
            System.out.println();
        }

    }
}