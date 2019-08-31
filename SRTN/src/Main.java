import java.util.Scanner;
import java.util.Vector;

public class Main {
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        Vector<Process> p = new Vector<Process>();
        System.out.println("Enter the number of processes: ");
        int n = sc.nextInt();
        for(int i=0;i<n;i++)
        {
            p.add(new Process(sc.nextInt(), sc.nextInt(), i+1));
        }

        SRTN srtn = new SRTN(p);
    }
}
