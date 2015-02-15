import java.math.BigDecimal;
import java.util.*;

public class Scheduler 
{	
	
    public static float round(float d, int decimalPlace) {
        BigDecimal bd = new BigDecimal(Float.toString(d));
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
        return bd.floatValue();
    }
	
	public static ArrayList<Process> fcfs(ArrayList<Process> queue)
	{
		// Return ArrayList with all completed processes up to 100 quantum
		ArrayList<Process> completedProcesses = new ArrayList<Process>();
        
        // The clock keeps track of the process in the time line
        float clock = 0;
        
        // Process the queue
        for (Process process: queue)
		{
        	//If this is the first process, set the clock to the arrival time of the first process
			if(clock == 0)
				clock = process.getArrivalTime();
						
			// Set the start time of the current process
			if(process.getArrivalTime() - clock > 1)
			{
				process.setStartTime((float)Math.ceil(process.getArrivalTime()));
				clock = process.getArrivalTime();
			}
			else
				process.setStartTime((float)Math.ceil(clock));
			
			clock = clock + (int)Math.ceil(process.getBurstTime());
			
			// Set the completion time of the process
			process.setEndTime(round(process.getStartTime() + process.getBurstTime(), 1));
			
		    if(process.getStartTime() < 100)
		    {
			    // Wait time
				process.setWaitingTime(round(process.getStartTime() - process.getArrivalTime(), 1));
				
				// Turn around time
				process.setTurnAroundTime(round(process.getEndTime() - process.getArrivalTime(), 1));
				
				// Response time
				process.setResponseTime(process.getTurnAroundTime());
				completedProcesses.add(process);	
		    }		
		}
        return completedProcesses;
	}
	
	/**
	 * 
	 * @param arrivalQueue - A queue or processes sorted by arrival time
	 * @return - A queue of processes
	 */
	public static ArrayList<Process> roundRobin(ArrayList<Process> arrivalQueue)
	{
		// Return ArrayList with all completed processes up to 100 quantum
		ArrayList<Process> quantumQueue = new ArrayList<Process>();
		LinkedList<Process> roundRobinQueue = new LinkedList<Process>();
		
        // The clock keeps track of the process in the time line
        int index=0;
        int quantum = 0;
        Process idleProcess = null;
        Process currentProcess = arrivalQueue.remove(index);
        
        // Run the round robin algorithm until both the round robin queue
        // and the arrival queue are empty and there are no more processes
        // to execute
        while(!roundRobinQueue.isEmpty() || !arrivalQueue.isEmpty())
        {
        	// Add one or more process to the round robin queue if the process 
        	// arrival time is equal to the current quantum time
        	while(Math.ceil(currentProcess.getArrivalTime()) == quantum)
        	{
        		// Add the current process to the round robin queue
        		roundRobinQueue.add(currentProcess);
        		
        		// Exit if no more processes are available in the arrival queue
        		if(arrivalQueue.isEmpty())
        			break;
        		
        		// Get the next process in the arrival queue
        		currentProcess = arrivalQueue.remove(index);
        	}
        	
        	// Check if processes are available and ready to run
        	if(!roundRobinQueue.isEmpty())
        	{
        		// Get the head process in the round robin queue
            	Process roundRobinProcess = roundRobinQueue.removeFirst();
            	
            	// If this is the first execution of this program then 
            	// set the start time to the time of the current quantum
            	if(roundRobinProcess.getStartTime() == 0)
            	{
            		// Set the start time to the current quantum
            		roundRobinProcess.setStartTime(quantum);
            		
            		// Calculate and set the response time by getting the difference
            		// between the time the process started and the time it arrived
            		roundRobinProcess.setResponseTime(roundRobinProcess.getStartTime() - roundRobinProcess.getArrivalTime());
            		            		
            		// Calculate and set the wait time
            		
            	}
            	
            	// Deduct a quantum from the current running process
            	roundRobinProcess.setExpectedFinishTime(roundRobinProcess.getExpectedFinishTime() - 1);
            	
            	// Add the process to the quantum queue
        		quantumQueue.add(roundRobinProcess);
            	
            	// If the process is not finish add it to the end of the round robin queue
            	// otherwise remove it from the roundRobinQueue
            	if(roundRobinProcess.getExpectedFinishTime() > 0)
            		roundRobinQueue.add(roundRobinProcess);
            	else
            	{
            		// Calculate and set the Turn Around Time by getting the difference between the Finish Time
            		// and the Arrival Time.
            		roundRobinProcess.setTurnAroundTime(round(quantum - roundRobinProcess.getArrivalTime(), 1));
            		roundRobinQueue.remove(roundRobinProcess);
            	}
            	System.out.println("Quantum = " + quantum + "\t\tP" + roundRobinProcess.getId() + "\t\tArrival Time " + roundRobinProcess.getArrivalTime()  + "\t\tStart Time " + roundRobinProcess.getStartTime() + "\t\tBurst Time " + roundRobinProcess.getBurstTime() + "\t\tRemaining Quantum " + roundRobinProcess.getExpectedFinishTime() + "\t\tTurn Around Time " + roundRobinProcess.getTurnAroundTime());   
        	}
        	else
        	{
        		// Add an idle process to the quantumQueue if no processes are available
        		quantumQueue.add(idleProcess);  
        		System.out.println("Quantum = " + quantum + "\tIDLE");
        	}
        	
        	// Increase quantum to keep track of time line
        	quantum++;
        	
        	//if(quantum > 99) arrivalQueue.removeAll(arrivalQueue);
        }
        
		return quantumQueue;
	}
}