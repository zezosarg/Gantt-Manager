package backend;

public class MainControllerFactory {

	public IMainController createMainController() {
		return new GanttManager();
	}

}

// In case a new type needs to be supported we just need to add conditional logic to return the correct class type based on an argument added in the create method.