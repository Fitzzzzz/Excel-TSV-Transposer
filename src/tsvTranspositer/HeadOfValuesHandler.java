package tsvTranspositer;

public class HeadOfValuesHandler {
	
	private String[] inputHOV;
	private String[] outputHOV;
	
	private int serieRowsNb;
	private String[] years;
	
	public String[] getYears() {
		return years;
	}

	public HeadOfValuesHandler(String[] inputHOV, int serieRowsNb) {
		super();
		this.inputHOV = inputHOV;
		this.serieRowsNb = serieRowsNb;
	}
	
	public String[] createOutputHOV() {
	
		initOutputHOV();
		addSerieRows();
		getYearsArray();
		addColomnHeaders();
		
		return outputHOV;
	}
	
	private void initOutputHOV() {

		outputHOV = new String[serieRowsNb + 2]; // TODO : TBC : 2 can be 3 (if monthly)
	}
	private void addSerieRows() {
		for (int i = 0; i < serieRowsNb; i++) {
			outputHOV[i] = inputHOV[i];
		}
	}
	private	void getYearsArray() {
		years = new String[inputHOV.length - serieRowsNb];
		for (int i = 0; i < years.length; i++) {
			years[i] = inputHOV[i + serieRowsNb];
		}
	}
	private void addColomnHeaders() {
		
		outputHOV[serieRowsNb] = "year"; // TODO : TBC to work for Year/Month
		outputHOV[serieRowsNb + 1] = "value";

	}
	
	
	
	
	
}
