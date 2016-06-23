package tsvTranspositer;

public class HeadOfValuesHandler {
	
	private String[] inputHOV;
	private String[] outputHOV;
	
	private String valueName;
	private String[] timeName;
	
	int serieRowsNb;
	
	
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

	private void addColomnHeaders() {

		System.arraycopy(timeName, 0, outputHOV, serieRowsNb, timeName.length);
		System.out.println("timeName added"); // TODO : TBR
		outputHOV[serieRowsNb + timeName.length] = valueName;

	}
	
	
	
	
	
}
