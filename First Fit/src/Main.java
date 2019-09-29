import java.util.Scanner;
import java.util.Vector;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of pocesses: ");
        int numOfProcess = sc.nextInt();
        System.out.println("Enter the processes: ");
        Vector<Integer> processes = new Vector<Integer>();
        for (int i = 0; i < numOfProcess; i++) {
            processes.add(sc.nextInt());
        }

        System.out.println("Enter the number of Memory Blocks: ");
        int blocks = sc.nextInt();
        System.out.println("Enter their sizes: ");
        Vector<Integer> sizes = new Vector<Integer>();
        for (int i = 0; i < blocks; i++) {
            sizes.add(sc.nextInt());
        }

        for (int i = 0; i < numOfProcess; i++){
            boolean served = false;
            //cout << req[r] << " -> ";
            for (int p = 0; p < blocks; p++){
                if (sizes.get(p) >= processes.get(i)) {
                    sizes.set(p, (sizes.get(p) - processes.get(i)));
                    served = true;
                    break;
                }
            }
            if (served){
                for (int j = 0; j < blocks; j++){
                    System.out.print(sizes.get(j)+" ");
                }
                System.out.println();
            }
            else {

                System.out.println("Cannot server, sorry");
                int sum = 0;
                for (int j = 0; j < blocks; j++){
                    sum += sizes.get(j);
                }

                System.out.println("Extrernal fragmentation: "+sum);
                break;
            }
        }



    }
    }

