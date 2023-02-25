package parser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import dom.Project;
import dom.Task;

public class TsvParser implements IParser{
	@Override
	public void parse(Project project, String inputFileName, String delimeter) {
		BufferedReader inputStream = null;
		Task task =null;
		try {
			inputStream = new BufferedReader(new FileReader(inputFileName));
			String nextInputLine = "";
			try {
				while ((nextInputLine = inputStream.readLine()) != null) {
					String[] stringParts = nextInputLine.split(delimeter);
					if(stringParts.length==6) {
						task = new Task(stringParts[0], stringParts[1], stringParts[2], stringParts[3], stringParts[4], stringParts[5]);
					}else if(stringParts.length==3) {//mama, -1's should change later
						task = new Task(stringParts[0], stringParts[1], stringParts[2], "-1", "-1", "-1");
					}
					project.addTask(task);	
				}
			} catch (IOException e) {
				System.err.println("Could not read line from input file stream");
				e.printStackTrace();
			}
		}
		catch (FileNotFoundException e) {
			System.err.println("Could not open input file stream");
			e.printStackTrace();
		}
		finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					System.err.println("Could not close input file stream");
					e.printStackTrace();
				}
			}
			System.out.println("Done parsing.");
		}
	}
}
