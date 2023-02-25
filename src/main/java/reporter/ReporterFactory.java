package reporter;

import backend.ReportType;

public class ReporterFactory {

	public IReporter createReporter(ReportType type) {
		
		if(type==ReportType.TEXT) {
			return new TsvReporter();
		}else if(type==ReportType.MD) {
			return new MdReporter();
		}else{
			return new HtmlReporter();
		}
			    
	}
	
}
