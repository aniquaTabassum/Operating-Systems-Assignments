public class Process {
    int PID;
    int arrivalTime;
    int CPUTime;

    public Process(int arrivalTime, int CPUTime, int PID)
    {
        this.arrivalTime = arrivalTime;
        this.CPUTime = CPUTime;
        this.PID = PID;
    }
}
