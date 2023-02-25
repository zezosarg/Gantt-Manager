package backend;

import org.apache.commons.collections4.trie.PatriciaTrie;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import dom.Project;
import dom.Task;
import dom2app.SimpleTableModel;
import parser.IParser;
import parser.ParserFactory;
import reporter.IReporter;
import reporter.ReporterFactory;

public class GanttManager implements IMainController {
	private Project project;
	private List<String[]> data;
	private static final String[] COLUMN_NAMES = {"TaskId" , "TaskText", "MamaId","Start" , "End" , "Cost" };
	
	public GanttManager() { this.project = new Project(); }
	
	@Override
	public SimpleTableModel load(String fileName, String delimiter) {
		IParser parser = new ParserFactory().createParser();
		parser.parse(project, fileName, delimiter);
		project.assembleTaskList();
		data = new ArrayList<String[]>();
		for (Task task : project.getTaskList()) {
			data.add(task.taskToStringArray());
			if(task.getChildTaskList()!=null) {
				for (Task child : task.getChildTaskList()) {
					data.add(child.taskToStringArray());
				}
			}
		}
		return new SimpleTableModel("Name: Load", "Project: Gantt", COLUMN_NAMES, data);
	}
	
	@Override
	public SimpleTableModel getTasksByPrefix(String prefix) {
		PatriciaTrie<Task> paTrie = new PatriciaTrie<Task>();
		for(Task task : project.getTaskList()) {
			paTrie.put(task.getName(), task);
			if(task.getChildTaskList()!=null) {
				for (Task child : task.getChildTaskList()) {
					paTrie.put(child.getName(), child);
				}
			}
		}
		SortedMap<String,Task> matches = paTrie.prefixMap(prefix);
		data = new ArrayList<String[]>();
		for (Task task : matches.values()) {
			data.add(task.taskToStringArray());
		}
		return new SimpleTableModel("Name: Get tasks by prefix", "Project: Gantt", COLUMN_NAMES, data);
	}
	
	@Override
	public SimpleTableModel getTaskById(int id) {
		data = new ArrayList<String[]>();
		for (Task task : project.getTaskList()) {
			if(id==task.getId()) {
				data.add(task.taskToStringArray());
			}
			if(task.getChildTaskList()!=null) {
				for (Task child : task.getChildTaskList()) {
					if(id==child.getId()) {
						data.add(child.taskToStringArray());
					}
				}
			}
		}
		return new SimpleTableModel("Name: Get task by id", "Project: Gantt", COLUMN_NAMES, data);
	}
	
	@Override
	public SimpleTableModel getTopLevelTasks() {
		data = new ArrayList<String[]>();
		for (Task task : project.getTaskList()) {
			data.add(task.taskToStringArray());
		}
		return new SimpleTableModel("Name: Get top level tasks", "Project: Gantt", COLUMN_NAMES, data);
	}
	
	@Override
	public int createReport(String path, ReportType type) {
		IReporter reporter = new ReporterFactory().createReporter(type);
		return reporter.createReport(path, project.getTaskList());
	}
	
	public List<String[]> getData(){ return this.data; }
}
