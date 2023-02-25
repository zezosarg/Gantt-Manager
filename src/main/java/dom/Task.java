package dom;

import java.util.ArrayList;

public class Task implements Comparable<Task>{
	private int id;
	private String name;
	private int mamaId;
	private int start;
	private int end;
	private int cost;
	private ArrayList<Task> childTaskList;
	
	public Task(String id, String taskName, String mamaId, String start, String end, String cost) {
		this.id = Integer.parseInt(id);
		this.name = taskName;
		this.mamaId = Integer.parseInt(mamaId);
		this.start = Integer.parseInt(start);
		this.end = Integer.parseInt(end);
		this.cost = Integer.parseInt(cost);
	}
	public void computeAggregateTaskCost(){
		int cost = 0;
		for(Task child : childTaskList) {
			cost += child.cost;
		}
		this.cost = cost; 
	}
	public void computeAggregateTaskStart(){
		int start = 1000000;
		for(Task child : childTaskList) {
			if(child.getStart()<start) {
				start = child.getStart();
			}
		}
		this.start = start;
	}	
	public void computeAggregateTaskEnd(){
		int end = -1;
		for(Task child : childTaskList) {
			if(child.getEnd()>end) {
				end = child.getEnd();
			}
		}
		this.end = end;
	}
	@Override
	public int compareTo(Task o) {
		int result = this.start - o.start;
	    if (result != 0) {
	    	return result;
	    }else {
	    	return this.id - o.id;
	    }
	}
	public int getId() { return id; }
	
	public String getName() { return name; }
	
	public int getMamaId() { return mamaId; }
	
	public int getStart() { return start; }
	
	public int getEnd() { return end; }
	
	public int getCost() { return cost;	}
	
	public ArrayList<Task> getChildTaskList(){ return this.childTaskList; }
	
	public void setChildTaskList(ArrayList<Task> childTaskList){ this.childTaskList = childTaskList; }	
	
	public String[] taskToStringArray() { return new String[]{Integer.toString(id), name, Integer.toString(mamaId), Integer.toString(start), Integer.toString(end), Integer.toString(cost)}; }
	
	public String toString() { return Integer.toString(id)+'\t'+name+'\t'+Integer.toString(mamaId)+'\t'+Integer.toString(start)+'\t'+Integer.toString(end)+'\t'+Integer.toString(cost)+'\t'; }

}
