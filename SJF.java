import java.util.*;

public class SJF {
	public void SJF() {

		
		float countExpectedRuntime = 0, countArrivalTime = 0;
		float numerator = 0;
		int maxProcessesToRun;
		float averageWaitTime = 0;
		float Throughput = 0;
		float totalProcessRunTime = 0;
		float averageTurnAroundTime = 0;
		float runTime;
		int processCounter = 0;
		int counter = 0;
                int n = 13;
		maxProcessesToRun = (int) (n / 0.1);

		ArrayList<Process> processes = new ArrayList<Process>(maxProcessesToRun);

		while (totalProcessRunTime < n) {
			Random rand = new Random();
			runTime = (float) (Math.random() * (9.9 - 0.1) + 0.1); 
			float arrivalTime = rand.nextFloat();

			int priority = rand.nextInt(4);

			processes.add(new Process(processCounter, arrivalTime, runTime,
					priority));

			totalProcessRunTime += runTime;
			processCounter++;

		}

		
		for (int k = 0; k < 5; k++) {
			int processRunning = 0;
			Collections.sort(processes, new arrivalTimeComparator2());
			System.out
					.println("Time\tProcessName\tArrivalTime\tExpectedTotalRunTime \tPriority\n\n");
			for (int j = 0; j < n; j++) {

				System.out.println(j
						+ "\t"
						+ "P "
						+ processes.get(processRunning).getId()
						+ "\t\t"
						+ String.format("%.2f", processes.get(processRunning)
								.getArrivalTime())
						+ "\t\t"
						+ String.format("%.2f", processes.get(processRunning)
								.getBurstTime()) + "\t\t\t"
						+ processes.get(processRunning).getPriority());

				counter++;
				if (counter >= processes.get(processRunning)
						.getBurstTime()) {

					counter = 0;
					processRunning++;

				}

			}

			for (int i = 0; i < processes.size(); i++) {

				countExpectedRuntime = countExpectedRuntime
						+ processes.get(i).getBurstTime();
				Throughput = countExpectedRuntime;

				averageTurnAroundTime += countExpectedRuntime - processes.get(i).getArrivalTime();

			}
			for (int i = 0; i < processes.size() - 1; i++) {

				countExpectedRuntime = processes.get(i)
						.getBurstTime();

				averageWaitTime += countExpectedRuntime;

			}

			averageTurnAroundTime = averageTurnAroundTime / processes.size();
			System.out.println();
			System.out.println("Average turnaround time:"
					+ String.format("%.2f", averageTurnAroundTime));
			System.out.println("Average waiting time: "
					+ String.format("%.2f", averageWaitTime));
			System.out.println("Average response time:"
					+ String.format("%.2f", averageWaitTime));
			System.out.println("Throughput:" + processCounter + "/"
					+ String.format("%.2f", Throughput) + "\n\n");

		}
	}
}
class arrivalTimeComparator implements Comparator<Process> {

	@Override
	public int compare(Process a, Process b) {
		if (a.getArrivalTime() > b.getArrivalTime()) {

			return 1;

		} else if (a.getArrivalTime() < (b.getArrivalTime())) {
			return -1;
		}

		return 0;
	}

}

class arrivalTimeComparator2 implements Comparator<Process> {

	@Override
	public int compare(Process a, Process b) {
		if (a.getBurstTime() > b.getBurstTime()) {

			return 1;

		} else if (a.getBurstTime() < (b.getBurstTime())) {
			return -1;
		}

		return 0;
	}

}
