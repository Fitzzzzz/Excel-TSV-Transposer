package tsvTranspositer;

public class HeadOfValuesHandler {
	
	private String[] inputHOV;
	private String[] outputHOV;
	
	private String valueName;
	private String[] timeName;
	
	private int serieRowsNb;
	private String[] years;
	
	public String[] getYears() {
		return years;
	}

	public HeadOfValuesHandler(String[] inputHOV, String valueName, String[] timeName, int serieRowsNb) {
		super();
		this.inputHOV = inputHOV;
		this.valueName = valueName;
		this.timeName = timeName;
		this.serieRowsNb = serieRowsNb;
	}
	
	public HeadOfValuesHandler(String[] inputHOV, String valueName, String timeName, int serieRowsNb) {
		super();
		this.inputHOV = inputHOV;
		this.valueName = valueName;
		String[] timeNameArray = {timeName};
		this.timeName = timeNameArray;
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

		System.arraycopy(timeName, 0, outputHOV, serieRowsNb, timeName.length);
//		System.out.println("timeName added"); // TODO : TBR
		outputHOV[serieRowsNb + timeName.length] = valueName;

	}
	
	
	
	
	
}
