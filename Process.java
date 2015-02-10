/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Owner
 */
public class Process implements Comparable<Process>
{
	int id;
	int priority;
	float endTime;
	float startTime;
    float burstTime;
    float waitingTime;
    float arrivalTime;
    float responseTime;
    float turnAroundTime;
    float timeLeftInQuantum;
    float expectedFinishTime;
	
    public Process(int id, float burstTime, float arrivalTime, int priority) 
    {
        this.burstTime = burstTime;
        this.arrivalTime = arrivalTime;
        this.priority = priority;
        this.turnAroundTime = 0;
        this.waitingTime = 0;
        this.id = id;
        this.timeLeftInQuantum = 0;
        this.expectedFinishTime = 0;
        this.endTime = 0;
        this.responseTime = 0;    
        this.startTime = 0;
    }

    public float getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(float responseTime) {
        this.responseTime = responseTime;
    }
    
    public Process(float arrivalTime){
        this.arrivalTime = arrivalTime;
    }

    public float getBurstTime() {
        return burstTime;
    }

    public void setBurstTime(float burstTime) {
        this.burstTime = burstTime;
    }

    public float getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(float arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public float getTurnAroundTime() {
        return turnAroundTime;
    }

    public void setTurnAroundTime(float turnAroundTime) {
        this.turnAroundTime = turnAroundTime;
    }

    public float getWaitingTime() {
        return waitingTime;
    }

    public void setWaitingTime(float waitingTime) {
        this.waitingTime = waitingTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getTimeLeftInQuantum() {
        return timeLeftInQuantum;
    }

    public void setTimeLeftInQuantum(float timeLeftInQuantum) {
        this.timeLeftInQuantum = timeLeftInQuantum;
    }

    public float getExpectedFinishTime() {
        return expectedFinishTime;
    }

    public void setExpectedFinishTime(float expectedFinishTime) {
        this.expectedFinishTime = expectedFinishTime;
    }

    public float getEndTime() {
        return endTime;
    }

    public void setEndTime(float endTime) {
        this.endTime = endTime;
    }
    
    public float getStartTime() {
		return startTime;
	}
    
	public void setStartTime(float startTime) {
		this.startTime = startTime;
	}

	public int compareTo(Process o) {
		float comparedFrom = o.getArrivalTime();
		if (this.getArrivalTime() > comparedFrom) {
			return 1;
		} else if (this.getArrivalTime() == comparedFrom) {
			return 0;
		} else {
			return -1;
		}
	} 
}