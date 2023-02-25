package reporter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import dom.Task;

public class TsvReporter implements IReporter {
	
	private BufferedWriter outputStream = null;

	@Override
	public int createReport(String path, ArrayList<Task> taskList) {
		try {
			outputStream = new BufferedWriter(new FileWriter(path));
			outputStream.write("TaskId	TaskText	MamaId	Start	End	Cost"+'\n');
			for (Task task : taskList) {
				outputStream.write(task.toString()+'\n');
				if(task.getChildTaskList()!=null) {
					for(Task child : task.getChildTaskList()) {
						outputStream.write(child.toString()+'\n');
					}
				}
			}
			
		} catch (IOException e) {
			System.err.println("Problem with opening, writing to or closing output file stream while trying to create text report");
			e.printStackTrace();
			return 0;
		}
		if (outputStream != null) {
			try {
				outputStream.close();
			} catch (IOException e) {
				System.err.println("Could not close output file stream");
				e.printStackTrace();
			}
		}
		return 1;
	}

}
