import java.util.*;
import java.math.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Team Shark
 */
public class main {
    public static Random rand = new Random();
    public static int quanta = 100;
    public static void main(String[] args) {
        

        for(int j=0; j<5; j++)
        {
        	float averageTurnAroundTime = 0; 
            float averageWaitTime = 0; 
            float averageResponseTime = 0;
            int quantum = 0;
        
	        ArrayList<Process> fcfsList = new ArrayList<>();
	        ArrayList<Process> roundRobinList = new ArrayList<>();        
	
	        for ( int i = 0; i < 30 ; i++) 
	        {
	        	float expectedRunTime = expecRunTime();
	        	float arrivalTime = ranArrival();
	        	int prioity = priority();
	        	
	           fcfsList.add(new Process(i, expectedRunTime, arrivalTime, prioity));
	           roundRobinList.add(new Process(i, expectedRunTime, arrivalTime, prioity));
	
	        }     		
			
	    	//System.out.println("\t\tProcess id \t Arrival Time \t Start Time \t Finish Time \t Burst Time \t Turn Around Time \t Waiting Time \t Response Time");
	    	//System.out.println("---------------------------------------------------------------------------------------------------------------------------------------");
	        //for(Process process: fcfsList)
	        	//System.out.println(process.getId() + "\t\t " + process.getArrivalTime() + "\t\t" + process.getStartTime() + "\t\t "+ process.getEndTime() + "\t\t " + process.getBurstTime() + "\t\t "+ process.getTurnAroundTime() + "\t\t\t " + process.getWaitingTime() + "\t\t " + process.getResponseTime());	
	        
	    	System.out.println("\n\nFirst Come First Serve");
	    	System.out.println("---------------------------------------------------------------------------------------------------------------------------------------");
	        
	    	
	    	ArrayList<Process> fcfs = Scheduler.fcfs(fcfsList);
	        ArrayList<Process> fcfsClone = (ArrayList<Process>)fcfs.clone();
	        while(!fcfs.isEmpty())
	        {
	        	Process p = fcfs.remove(0);
	        	while(p.getStartTime() != quantum)
	        	{
	        		//System.out.println("Quantum: " + quantum  + "\t\tIdle");
	        		quantum++;
	        	}
	        	
	        	if(p.getStartTime() == quantum)
	        	{
	        		//for(float i=p.getBurstTime(); i>0; i--)
	            	//System.out.println("Quantum: " + quantum++ + "\t\tP" + p.getId() + "\tArrival Time: " + p.getArrivalTime()  + "\tBurst Time: " + p.getBurstTime() + "\t\tTurn Around Time: " + p.getTurnAroundTime() + "\tWait Time: " + p.getWaitingTime() + "\t\tResponse Time: " + p.getResponseTime());   
	        	}	
	        }
	        for(Process p: fcfsClone) 
	        {
	        	averageTurnAroundTime = averageTurnAroundTime + p.getTurnAroundTime();
	        	averageWaitTime = averageWaitTime + p.getWaitingTime();
	        	averageResponseTime = averageResponseTime + p.getResponseTime();
	        }
	        
	        averageTurnAroundTime = round(averageTurnAroundTime / fcfsClone.size(), 1);
	        averageWaitTime = round(averageWaitTime / fcfsClone.size(), 1);
	        averageResponseTime = round(averageResponseTime / fcfsClone.size(), 1);
	        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------");
	        System.out.println("First Come First Serve Averages \t\t\t\t\t\t" + "Turn Around Time: " + averageTurnAroundTime + "\tWait Time: " + averageWaitTime + "\t\tResponse Time: " + averageResponseTime);        
	        
	        
	    	System.out.println("\n\nRound Robin");
	    	System.out.println("---------------------------------------------------------------------------------------------------------------------------------------");
	    	ArrayList<Process> roundRobin = Scheduler.roundRobin(roundRobinList);  
	
	        quantum = 0;
	        HashSet<Process> set = new HashSet<Process>();
	        
	        for(Process p: roundRobin)
	        {
	        	if(p != null)
	        	{
	        		set.add(p);
	            	//System.out.println("Quantum: " + quantum++ + "\t\tP" + p.getId() + "\tArrival: " + p.getArrivalTime()  + "\tBurst: " + p.getBurstTime() + "\t\tTurn Around: " + p.getTurnAroundTime() + "\tWait: " + p.getWaitingTime() + "\t\tResponse: " + p.getResponseTime());   
	
	        	}
	        	else
	        	{
	        		//System.out.println("Quantum: " + quantum++  + "\t\tIdle");
	
	        	}
	        }
	        averageTurnAroundTime=0; averageWaitTime=0; averageResponseTime = 0;
	        for(Process p: set) 
	        {
	        	averageTurnAroundTime = averageTurnAroundTime + p.getTurnAroundTime();
	        	averageWaitTime = averageWaitTime + p.getWaitingTime();
	        	averageResponseTime = averageResponseTime + p.getResponseTime();
	        }
	        
	        averageTurnAroundTime = round(averageTurnAroundTime / set.size(), 1);
	        averageWaitTime = round(averageWaitTime / set.size(), 1);
	        averageResponseTime = round(averageResponseTime / set.size(), 1);
	        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------");
	        System.out.println("Round Robin Averages \t\t\t\t\t\t\t" + "Turn Around: " + averageTurnAroundTime + "\tWait: " + averageWaitTime + "\t\tResponse: " + averageResponseTime);        
    
        }
    }
        
    
    public static float round(float d, int decimalPlace) {
        BigDecimal bd = new BigDecimal(Float.toString(d));
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
        return bd.floatValue();
    }
    
    public static float ranArrival() {       
        return round(rand.nextFloat() * 99, 1);
    }
    
    public static float expecRunTime() {  
        return round((float) (.1 + (Math.random()*10)), 1); 
    }
    
    public static int priority() {
        return 1 + (int)(Math.random()*4) ;
    }
}

