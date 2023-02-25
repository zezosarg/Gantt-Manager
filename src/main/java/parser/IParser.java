package parser;

import dom.Project;

public interface IParser {
	
	void parse(Project project, String inputFileName, String delimeter);
	
}
