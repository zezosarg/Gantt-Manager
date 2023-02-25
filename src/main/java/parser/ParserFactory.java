package parser;

public class ParserFactory {

	public IParser createParser() {
		return new TsvParser();	    
	}
	
}

// In case a new type needs to be supported we just need to add conditional logic to return the correct class type based on an argument added in the create method.