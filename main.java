import java.util.*;
import java.math.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Owner
 */
public class main
{
	public static Random rand = new Random();
	public static int quanta = 100;
	
	public static void main(String[] args)
	{
		ArrayList<Process> pList= new ArrayList<Process>();
		SRTPreemptive test = new SRTPreemptive();
		
		pList = makeProcess();
		
		ArrayList<Process> calculatedTimes= new ArrayList<Process>();
		calculatedTimes = SRTPreemptive.srt(pList);
		SRTPreemptive.calculation(pList);
	}

	public static float round(float d, int decimalPlace)
	{
		BigDecimal bd = new BigDecimal(Float.toString(d));
		bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
		return bd.floatValue();
	}

	public static float ranArrival()
	{

		return round(rand.nextFloat() * 99, 1);

	}

	public static float expecRunTime()
	{
		float number = round((float) (.1 + (Math.random() * 10)), 1);
		Math.abs(number);
		return number;

	}

	public static int priority()
	{

		return 1 + (int) (Math.random() * 4);

	}

	/**
	 * Adds processes to a list containing 100 each
	 */
	public static ArrayList<Process> makeProcess()
	{
		// Runnable r1 = new Process(burstTime, arrivalTime, priority,
		// turnAroundTime, waitingTime, id, timeLeftInQuantum,
		// expectedFinishTime, endTime);
		
		ArrayList<Process> processList = new ArrayList<>();
		for (int i = 0; i < 50; i++)
		{
			processList.add(new Process(i, expecRunTime(), ranArrival(),
					priority()));
		}
		return processList;		
	}
	
	
}
