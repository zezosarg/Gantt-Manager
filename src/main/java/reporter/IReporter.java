package reporter;

import java.util.ArrayList;
import dom.Task;

public interface IReporter {

	int createReport(String path, ArrayList<Task> taskList);
	
}

/* We notice that the reporter types have similar code.
 * Going a step further we could turn Reporter to an abstract class that implements the common code and move the differences to the subclasses.
 * However requirements request an interface.
 */