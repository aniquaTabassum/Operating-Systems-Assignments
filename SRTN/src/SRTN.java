import java.util.Vector;

public class SRTN {
    Vector<Process> procedures = new Vector<Process>();
    int length;
    Vector<Integer> waitingTime = new Vector<Integer>();
    Vector<Integer> turnAroundTime = new Vector<Integer>();
     public SRTN(Vector<Process> procedures)
     {
         this.procedures = procedures;
         findWaitingTime();
         for(int i = 0; i<procedures.size();i++)
         {
             System.out.println(procedures.get(i).PID+" "+waitingTime.get(i));
         }
     }

     public void findWaitingTime()
     {
         Vector<Integer> copyOfCPUTime = new Vector<Integer>();
         for(int i=0; i< procedures.size();i++)
         {
             copyOfCPUTime.add(procedures.get(i).CPUTime);
         }
         for(int i=0; i< procedures.size();i++)
         {
             waitingTime.add(0);
         }

         int n = procedures.size();
         int completedTasks = 0;
         int time = 0;
         int indexOfShortestTask = 0;
         int minTime = Integer.MAX_VALUE;
         int finishTime = 0;
         boolean check = false;

         while(completedTasks!=n)
         {
             for(int j=0;j<n;j++)
             {
                 if((procedures.get(j).arrivalTime <= time) && (copyOfCPUTime.get(j) < minTime && copyOfCPUTime.get(j)>0) )
                 {
                     minTime = copyOfCPUTime.get(j);
                     indexOfShortestTask = j;
                     check = true;
                 }
             }

             if(check == false)
             {
                 time+=1;
                 continue;
             }

             copyOfCPUTime.set(indexOfShortestTask, (copyOfCPUTime.get(indexOfShortestTask)-1));
             minTime = copyOfCPUTime.get(indexOfShortestTask);
             if(minTime == 0)
             {
                 minTime = Integer.MAX_VALUE;
             }

             if(copyOfCPUTime.get(indexOfShortestTask) == 0)
             {
                 completedTasks+=1;
                 finishTime = time+1;
                 waitingTime.set(indexOfShortestTask, (finishTime - procedures.get(indexOfShortestTask).CPUTime - procedures.get(indexOfShortestTask).arrivalTime));
                 if(waitingTime.get(indexOfShortestTask) == 0)
                 {
                     waitingTime.set(indexOfShortestTask, 0);
                 }

             }

             time+=1;
         }
     }


}
