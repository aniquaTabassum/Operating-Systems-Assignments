import java.util.*;

public class Main {
    public static void main(String[] args)
    {
        Queue<Process> processes = new LinkedList<>();
        int numOfProcess = 0;
        List<Integer> arrivalTimes = new ArrayList<Integer>();
        List<Integer> CPUTimes = new ArrayList<Integer>();

        System.out.println("Enter the number of processes: ");
        Scanner sc = new Scanner(System.in);
        numOfProcess = sc.nextInt();

        for(int i=0;i<numOfProcess;i++)
        {
            System.out.println("Enter the CPU times: ");
            CPUTimes.add(sc.nextInt());
        }

        for(int i=0;i<numOfProcess;i++)
        {
            Process p = new Process(CPUTimes.get(i), (i+1));
            processes.add(p);
        }

        int startTime = 0;
        int timeQuant = 3;
        while (processes.size()!=0)
        {
            Process p = processes.remove();
            int endtIME = 0;
            if(p.CPUTime <= timeQuant)
            {
                endtIME = p.CPUTime;
            }
            else
            {
                endtIME = timeQuant;
            }
            System.out.println(startTime+"-- p"+p.processNum+" --"+(endtIME + startTime));
            startTime+= endtIME;
            if((p.CPUTime - timeQuant) >0)
            {
                p.CPUTime = p.CPUTime - timeQuant;
                processes.add(p);
            }

        }
    }
}
