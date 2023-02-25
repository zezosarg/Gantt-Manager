package dom;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Project {
	private ArrayList<Task> taskList;
	private HashMap<Integer,ArrayList<Task>> taskMap;
		
	public Project() {
		this.taskList = new ArrayList<Task>();
		this.taskMap = new HashMap<Integer,ArrayList<Task>>();
	}
	public void assembleTaskList() {
		taskList = taskMap.get(0);
		for (int mamaId: taskMap.keySet()) {
			if (mamaId!=0) {
				for (Task task : taskList) {
					if (task.getId() == mamaId) {
						task.setChildTaskList(taskMap.get(mamaId));
					}
				}
			}
		}
		updateAggregateTaskFields();
		sortTaskList();
	}
	public void updateAggregateTaskFields() {
		for (Task task : taskList) {
			if (task.getChildTaskList()!=null) {
				task.computeAggregateTaskCost();
				task.computeAggregateTaskStart();
				task.computeAggregateTaskEnd();
			}
		}
	}
	public void sortTaskList() {
		Collections.sort(taskList);
		for(Task t : taskList) {
			if(t.getChildTaskList()!=null) {
				Collections.sort(t.getChildTaskList());
			}
		}
	}
	
	public void addTask(Task task) {
		if(!taskMap.containsKey(task.getMamaId())) {
			taskMap.put(task.getMamaId(), new ArrayList<Task>());
		}
		taskMap.get(task.getMamaId()).add(task);
	}
	public ArrayList<Task> getTaskList(){
		return this.taskList;
	}
}
