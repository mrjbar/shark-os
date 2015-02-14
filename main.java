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
        
        ArrayList<Process> processList = new ArrayList<>();        
        for ( int i = 0 ; i < 30 ; i++)
           processList.add(new Process(i, expecRunTime(), ranArrival(), priority()));

		// Sort queue by arrival time
		Collections.sort(processList);
		
        
        //ArrayList<Process> fcfs = Scheduler.fcfs(processList);
    	System.out.println("Process id \t Arrival Time \t Start Time \t Finish Time \t Burst Time \t Turn Around Time \t Waiting Time \t Response Time");
    	System.out.println("---------------------------------------------------------------------------------------------------------------------------------------");
        for(Process process: processList)
        	System.out.println(process.getId() + "\t\t " + process.getArrivalTime() + "\t\t" + process.getStartTime() + "\t\t "+ process.getEndTime() + "\t\t " + process.getBurstTime() + "\t\t "+ process.getTurnAroundTime() + "\t\t\t " + process.getWaitingTime() + "\t\t " + process.getResponseTime());	
        
        ArrayList<Process> roundRobin = Scheduler.roundRobin(processList);
        /*System.out.println(roundRobin.size());
        Process p;
        for(int i=0; i<roundRobin.size(); i++)
        {
        	p = roundRobin.get(i);
        	if(p != null)
        		System.out.println("Quantum = " + i + "\t\tProcedd ID = P" + p.getId() + "\t\tArrival Time " + p.getArrivalTime()  + "\t\tStart Time " + p.getStartTime() + "\tBurst Time " + p.getBurstTime() );
        	else
        		System.out.println("Quantum = " + i + "\tIDLE");
        }*/
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

