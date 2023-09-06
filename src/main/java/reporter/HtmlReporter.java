package reporter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import dom.Task;

public class HtmlReporter implements IReporter {

	private BufferedWriter outputStream = null;
	
	@Override
	public int createReport(String path, ArrayList<Task> taskList) {
		try {
			outputStream = new BufferedWriter(new FileWriter(path));
			outputStream.write(
				"<!doctype html>
				<html>
				<head>
					<title>Gantt chart html report</title>
				</head>
				<body>
				<table>
					<tr>
						<th>TaskId</th>
						<th>TaskText</th>
						<th>MamaId</th>
						<th>Start</th>
						<th>End</th>
						<th>Cost</th>
					</tr>\n"
			);
			for (Task task : taskList) {
				outputStream.write(
					"<tr><td>"+task.getId()+
					"</td><td>"+task.getName()+
					"</td><td>"+task.getMamaId()+
					"</td><td>"+task.getStart()+
					"</td><td>"+task.getEnd()+
					"</td><td>"+task.getCost()+
					"</td></tr>\n"
				);
				if(task.getChildTaskList()!=null) {
					for(Task child : task.getChildTaskList()) {
						outputStream.write(
							"<tr><td>"+child.getId()+
							"</td><td>"+child.getName()+
							"</td><td>"+child.getMamaId()+
							"</td><td>"+child.getStart()+
							"</td><td>"+child.getEnd()+
							"</td><td>"+child.getCost()+
							"</td></tr>\n"
						);
					}
				}
			}
			outputStream.write("</table></body></html>\n");
		} catch (IOException e) {
			System.err.println("Problem with opening, writing to or closing output file stream while trying to create html report");
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
