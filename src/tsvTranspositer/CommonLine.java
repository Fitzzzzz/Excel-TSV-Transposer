package tsvTranspositer;

public class CommonLine {

	private String[] years;
	private String[] line;
	private String[] months;
	private int serieRowsNb;
	
	private boolean monthly;
	
	public boolean isMonthly() {
		return monthly;
	}

	private String[] serieRows;
	private String[][] outputLines;
	
	public CommonLine(String[] line, String[] years, int serieRowsNb) {
		this.years = years;
		this.line = line;
		this.serieRowsNb = serieRowsNb;
		this.monthly = false;
		initSerieRows();
	}
	
	public CommonLine(String[] line, String[] years, String[] months, int serieRowsNb) {
		this.years = years;
		this.line = line;
		this.serieRowsNb = serieRowsNb;
		this.months = months;
		this.monthly = true;
		initSerieRows();
	}

	private void initSerieRows() {
		
		serieRows = new String[serieRowsNb];
		System.arraycopy(line, 0, serieRows, 0, serieRowsNb);
		
	}
	
	public String[][] exportOutputLines() {
//		System.out.println("serieRowsNb de CommonLine vaut " + serieRowsNb); // TODO : TBR 
		if (!isMonthly()) {
			outputLines = new String[years.length][serieRowsNb + 2]; // TODO : TBC : pour handle monthly? Prob not, years will contain months too? Years [][]
			for (int i = 0; i < years.length; i++) {
				// System.out.println("Remplissage de la ligne out " + (i+1)); // TODO : TBR
				System.arraycopy(serieRows, 0, outputLines[i], 0, serieRowsNb);
				outputLines[i][serieRowsNb] = years[i];
				outputLines[i][serieRowsNb + 1] = line[serieRowsNb + i];
			}
		}
		else {
			outputLines = new String[years.length][serieRowsNb + 3];
			for (int i = 0; i < years.length; i++) {
//				System.out.println("Remplissage de la ligne out " + (i+1)); // TODO : TBR
				System.arraycopy(serieRows, 0, outputLines[i], 0, serieRowsNb);
				outputLines[i][serieRowsNb] = years[i];
				outputLines[i][serieRowsNb + 1] = months[i];
				outputLines[i][serieRowsNb + 2] = line[serieRowsNb + i];
			}
		}
		return outputLines;
	}
}
