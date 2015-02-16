import java.math.BigDecimal;
import java.util.*;

public class SRTPreemptive extends Scheduler
{

	public static int[] Quanta = new int[2000];
	public static int count = 0, SizeQ = 0, IdleL = 0;
	public static boolean end = false, Stop = false;

	public static float clock = 0;
	public static float endtime = 0;
	public static int[] waitingP = new int[100];;
	public static int SizeW = 0;

	public static void calculation(ArrayList<Process> queue)
	{
		System.out
				.println("Process id \t Arrival Time \t Start Time \t Finish Time \t Burst Time \t Turn Around Time \t Waiting Time \t Response Time");
		System.out
				.println("---------------------------------------------------------------------------------------------------------------------------------------");

		for (Process p : queue)
		{

			// Wait time
			p.setWaitingTime(round(p.getResponseTime() - p.getArrivalTime() - p.getBurstTime(), 1));

			// Turn around time
			p.setTurnAroundTime(round(p.getEndTime() - p.getArrivalTime(), 1));

			// Response time
			p.setResponseTime(p.getTurnAroundTime());
			
			//Print data
			System.out.println(p.getId() + "\t\t "
					+ p.getArrivalTime() + "\t\t"
					+ p.getStartTime() + "\t\t " + p.getEndTime()
					+ "\t\t " + p.getBurstTime() + "\t\t "
					+ p.getTurnAroundTime() + "\t\t\t "
					+ p.getWaitingTime() + "\t\t "
					+ p.getResponseTime());
		}
	}

	public static ArrayList<Process> srt(ArrayList<Process> queue)
	{

		// Sort queue by arrival time
		Collections.sort(queue);
		Process temp;

		SizeQ = (int) Math.ceil(queue.get(0).getArrivalTime());

		// System.out.println(SizeQ+"he");
		while (end == false)
		{
			// running
			// System.out.println(count+"count");
			if (queue.get(count).getBurstTime() > 0)
			{
				Quanta[SizeQ] = queue.get(count).getId();
				// System.out.println(queue.get(count).getId());
				SizeQ++;
				// System.out.println("H"+queue.get(count).getBurstTime()+"H");
				// float tt = round((float) (.1 +
				// (queue.get(count).getBurstTime()-1), 1);
				float tt = (float) (queue.get(count).getBurstTime() - 1);
				queue.get(count).setBurstTime(tt);
				queue.get(count).setIsStarted(true);
				// System.out.println("H"+queue.get(count).getBurstTime()+"H");
				// System.out.print(Quanta[SizeQ]+"\t");
			}
			// end running
			if (queue.get(count).getBurstTime() <= 0)
			{
				queue.get(count).setTurnAroundTime(
						(float) (SizeQ - queue.get(count).getArrivalTime()));
				count++;
				// do calculation here
				// calculation(queue.get(count-1));

				if (queue.get(count).IsStarted() == false && Stop == true)
					end = true;

			}
			// this is to get the length of waiting list
			for (int a = count; a < queue.size(); a++)
			{
				if (queue.get(a).getArrivalTime() <= SizeQ)
				{
					IdleL = a;
				}
			}

			if (SizeQ > 10)
			{
				Stop = true;
				for (int b = count; b <= IdleL; b++)
				{
					if (queue.get(b).IsStarted() == false)
					{
						IdleL = b;
					}

				}
			}

			// end get the length of waiting list

			// sorting part
			for (int b = count + 1; b <= IdleL; b++)
			{
				for (int c = b; c <= IdleL; c++)
					if (queue.get(b).getBurstTime() > queue.get(c)
							.getBurstTime())
					{
						// SizeW++;
						temp = queue.get(b);
						queue.set(b, queue.get(c));
						queue.set(c, temp);
						// /waitingP[SizeW]=b;
						// System.out.println(waitingP[SizeW]);
					}
			}

			// end sorting part

			// Put the back to the end of the waiting list and sort base on
			// remaining time
			for (int d = count; d < IdleL; d++)
			{
				// System.out.println( IdleL+"IdleL");
				if (queue.get(d).getBurstTime() >= queue.get(d + 1)
						.getBurstTime())
				{
					temp = queue.get(d);
					queue.set(d, queue.get(d + 1));
					queue.set(d + 1, temp);
				}

			}

		}

		return queue;
	}
}