import java.math.BigDecimal;
import java.util.*;

public class Scheduler 
{
	public static float clock = 0;
	
	public static void cpu(Process p)
	{
		// Set the start time for the process
		int expectedRunTime = (int)Math.ceil(p.getBurstTime());
		if(p.getArrivalTime() - clock > 1)
		{
			p.setStartTime((float)Math.ceil(p.getArrivalTime()));
			clock = p.getArrivalTime();
		}
		else
		{
			p.setStartTime((float)Math.ceil(clock));
		}
		
		for(int runTime = expectedRunTime; runTime>0; runTime--)
		{
			clock++;
		}
		p.setEndTime(round(p.getStartTime() + p.getBurstTime(), 1));
	}
	
    public static float round(float d, int decimalPlace) {
        BigDecimal bd = new BigDecimal(Float.toString(d));
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
        return bd.floatValue();
    }
	
	public static void fcfs(ArrayList<Process> queue)
	{
		// Sort queue by arrival time
		Collections.sort(queue);
		
		// Process queue
        System.out.println("Process id \t Arrival Time \t Start Time \t Finish Time \t Burst Time \t Turn Around Time \t Waiting Time \t Response Time");
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------");
		for (Process process: queue)
		{
			if(clock == 0)
			{
				clock = process.getArrivalTime();
			}
			
			// Run process
		    cpu(process);
		    
		    // Wait time
			process.setWaitingTime(round(process.getStartTime() - process.getArrivalTime(), 1));
			
			// Turn around time
			process.setTurnAroundTime(round(process.getEndTime() - process.getArrivalTime(), 1));
			
			// Response time
			process.setResponseTime(process.getTurnAroundTime());
		
			System.out.println(process.getId() + "\t\t " + process.getArrivalTime() + "\t\t" + process.getStartTime() + "\t\t "+ process.getEndTime() + "\t\t " + process.getBurstTime() + "\t\t "+ process.getTurnAroundTime() + "\t\t\t " + process.getWaitingTime() + "\t\t " + process.getResponseTime());	
		}
	}
}