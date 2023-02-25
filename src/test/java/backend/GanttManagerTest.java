package backend;

import static org.junit.Assert.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import dom2app.SimpleTableModel;

public class GanttManagerTest {
	private GanttManager gManager;
	private SimpleTableModel expectedStm;
	private SimpleTableModel actualStm;
	private List<String[]> expectedData;
	private static final String[] COLUMN_NAMES = {"TaskId" , "TaskText", "MamaId","Start" , "End" , "Cost" };
	
	@Before
	public void setUp() {
		this.gManager = new GanttManager();
		actualStm = gManager.load("./src/main/resources/input/EggsScrambled.tsv", "\t");
		expectedData = Arrays.asList(
				new String[]{"100", "Prepare Fry", "0", "1", "12", "60"},
				new String[]{"101", "Turn on burner (low)", "100", "1",	"1", "10"},
				new String[]{"102", "Break eggs and pour into fry", "100", "2", "4", "10"},
				new String[]{"103", "Steer mixture to avoid sticking", "100", "5", "10", "10"},
				new String[]{"105", "Salt, pepper", "100", "5", "5", "10"},
				new String[]{"104", "Throw yellow cheese into fry", "100", "6", "12", "10"},
				new String[]{"106", "Turn burner off", "100", "12", "12", "10"},
				new String[]{"200", "Prepare the bread", "0", "10", "12", "20"},
				new String[]{"201", "Heat bread in toaster", "200", "10", "12", "10"},
				new String[]{"202", "Little bit of salt, galric spice to bread", "200", "12", "12", "10"},
				new String[]{"300", "Serve eggs", "0", "13", "20", "30"},
				new String[]{"301", "Put bread in plate", "300", "13", "13", "10"},
				new String[]{"302", "Put eggs on bread", "300", "14", "14", "10"},
				new String[]{"303", "Wash fry", "300", "15", "20", "10"}
		);
	}
	
	@Test
	public void testLoad() {
		expectedStm = new SimpleTableModel("Name: Load", "Project: Gantt", COLUMN_NAMES, expectedData);
		assertEquals(expectedStm, actualStm);
	}
	
	@Test
	public void testGetTopLevelTasks() {
		expectedData = Arrays.asList(expectedData.get(0), expectedData.get(7), expectedData.get(10)); 
		expectedStm = new SimpleTableModel("Name: Get top level tasks", "Project: Gantt", COLUMN_NAMES, expectedData);
		actualStm = gManager.getTopLevelTasks();
		assertEquals(expectedStm, actualStm);
	}
	
	@Test
	public void testGetTasksById() {
		expectedData = expectedData.subList(10, 11);
		expectedStm = new SimpleTableModel("Name: Get task by id", "Project: Gantt", COLUMN_NAMES, expectedData);
		actualStm = gManager.getTaskById(300);
		assertEquals(expectedStm, actualStm);
	}
	
	@Test
	public void testGetTasksByPrefix() {
		expectedData = Arrays.asList(expectedData.get(11), expectedData.get(12));
		expectedStm = new SimpleTableModel("Name: Get tasks by prefix", "Project: Gantt", COLUMN_NAMES, expectedData);
		actualStm = gManager.getTasksByPrefix("Put");
		assertEquals(expectedStm, actualStm);
	}
	
	@Test
	public void testCreateReport() throws IOException {
		String expectedReport = "TaskId"+"\t"+"TaskText"+"\t"+"MamaId"+"\t"+"Start"+"\t"+"End"+"\t"+"Cost";		
		for (int i = 0; i < expectedData.size(); i++) {
			for(int j = 0; j < expectedData.get(i).length; j++) {
				expectedReport += expectedData.get(i)[j]+"\t";
			}	
		}
		gManager.createReport("./src/test/resources/output/EggsScrambled.tsv", ReportType.TEXT);
		String actualReport = "";	
        BufferedReader reader = new BufferedReader(new FileReader("./src/test/resources/output/EggsScrambled.tsv"));
        String line = reader.readLine();
		while (line != null) {
			actualReport += line;
			line = reader.readLine();
		}
		reader.close();

		assertEquals(expectedReport, actualReport);		
	}
}
