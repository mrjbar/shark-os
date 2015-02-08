import java.util.*;

public class Scheduler 
{
	public static void fcfs(ArrayList<Process> queue)
	{
		// Sort queue by arrival time
		Collections.sort(queue);
		
		// Process queue
		float runTime = 0.0f;
        System.out.println("Process id \t Arrival Time \t Burst Time \t Turn Around Time \t Waiting Time \t Response Time");
        System.out.println("-------------------------------------------------------------------------------------------------------");
		for (Process process: queue)
		{
			// Set waiting time
			process.setWaitingTime(runTime);
		    for(float i = 0.0f; i<= process.getBurstTime(); i = i + 0.1f)
		    {
		    	runTime = process.getBurstTime();
		    }
		    
			/*while(process.getBurstTime() > 0)
			{
                process.setBurstTime(process.getBurstTime()-1);
				runTime++;
			}*/
			
			// Turn around time
			//process.setTurnAroundTime(runTime - process.getArrivalTime());
			process.setTurnAroundTime(runTime);

			
			// Response time
			//process.setResponseTime(runTime - process.getArrivalTime());
			process.setResponseTime(runTime);
		
			System.out.println(process.getId() + "\t\t " + process.getArrivalTime() + "\t\t " + process.getExpectedFinishTime() + "\t\t "+ process.getTurnAroundTime() + "\t\t\t " + process.getWaitingTime() + "\t\t " + process.getResponseTime());	
		}
	}
}