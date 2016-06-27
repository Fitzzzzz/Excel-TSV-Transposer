package tsvTranspositer;

public class HeadOfValuesHandler {
	
	private String[] inputHOV;
	private String[] outputHOV;
	
	private int serieRowsNb;
	private String[] years;
	private String[] months;
	
	
	private boolean monthly;
	
	public boolean isMonthly() {
		return monthly;
	}

	public String[] getYears() {
		return years;
	}
	
	public String[] getMonths() {
		return months;
	}

	public HeadOfValuesHandler(String[] inputHOV, int serieRowsNb) {
		super();
		this.inputHOV = inputHOV;
		this.serieRowsNb = serieRowsNb;
		checkMonthly();
	}
	
	public String[] createOutputHOV() {
	
		initOutputHOV();
		addSerieRows();
		getYearsArray();
		addColomnHeaders();
		
		return outputHOV;
	}
	
	private void initOutputHOV() {

		if (isMonthly()) {
			outputHOV = new String[serieRowsNb + 3];
		}
		else {
			outputHOV = new String[serieRowsNb + 2]; 
		}
		
	}
	private void addSerieRows() {
		for (int i = 0; i < serieRowsNb; i++) {
			outputHOV[i] = inputHOV[i];
		}
	}
	
	public boolean checkMonthly() {
	
		monthly = inputHOV[serieRowsNb].matches("[ ]*[0-9]{4}M[0-9]{1,2}[ ]*");
		return (monthly);
	}
	
	
	private	void getYearsArray() {

		years = new String[inputHOV.length - serieRowsNb];
		if (!isMonthly()) {
			for (int i = 0; i < years.length; i++) {
				years[i] = inputHOV[i + serieRowsNb];
			}
		}
		else {
			months = new String[inputHOV.length - serieRowsNb];
			for (int i = 0; i < years.length; i++) {
				String[] parts = inputHOV[i + serieRowsNb].split("M");
				years[i] = parts[0];
				months[i] = parts[1];
			}
		}
	}
	
	private void addColomnHeaders() {
		
		outputHOV[serieRowsNb] = "year"; 
		if (isMonthly()) {
			outputHOV[serieRowsNb + 1] = "month";
			outputHOV[serieRowsNb + 2] = "value";
		}
		else {
			outputHOV[serieRowsNb + 1] = "value";
		}
	}
	

	
	
	
	
	
}
