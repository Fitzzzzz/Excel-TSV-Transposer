package tsvTranspositer;

/**
 * Handles a regular line (often name of the product and value for every period) of the input file, 
 * transforming it into multiple lines for the output file meaning one line for each period with
 * the cases before the values repeated each line. For each regular line of the input, a CommonLine is constructed.
 *  
 * @author hamme
 *
 */
public class CommonLine {

	/**
	 * Contains all the years for which data is written in the input file.
	 */
	private String[] years;
	/**
	 * The line as it stands in the input file.
	 */
	private String[] line;
	/**
	 * Contains all the months for which data is written in the input file in case the data are written per month. Is set to null if not yearly.
	 * @see CommonLine#monthly
	 */
	private String[] months;
	/**
	 * Number of columns before the actual values in the input file. Can be columns describing the product as well as empty columns before the values.
	 */
	private int serieRowsNb;

	/**
	 * A boolean set to true if the data are written monthly. False if the data are written yearly.
	 */
	private boolean monthly;
	
	public boolean isMonthly() {
		return monthly;
	}
	/**
	 * The piece of the line containing the name of the product, its serial, and so on. 
	 */
	private String[] serieRows;
	/**
	 * The multiple lines that will be written in the output file (can be seen as the output of this class).
	 */
	private String[][] outputLines;
	
	/**
	 * Constructor in the case the period is yearly. Sets monthly to false.
	 * @param line Line of the input file that will be displayed in multiples lines in the output.
	 * @param years All the years for whose data are displayed.
	 * @param serieRowsNb Number of columns before the actual values in the input file. Can be columns describing the product as well as empty columns before the values.
	 */
	public CommonLine(String[] line, String[] years, int serieRowsNb) {
		this.years = years;
		this.line = line;
		this.serieRowsNb = serieRowsNb;
		this.monthly = false;
		initSerieRows();
	}
	/**
	 * Constructor in the case the period is monthly. Sets monthly to true.
	 * @param line Line of the input file that will be displayed in multiples lines in the output.
	 * @param years All the years for whose data are displayed.
	 * @param months All the months number, one per year.
	 * @param serieRowsNb Number of columns before the actual values in the input file. Can be columns describing the product as well as empty columns before the values.
	 */
	public CommonLine(String[] line, String[] years, String[] months, int serieRowsNb) {
		this.years = years;
		this.line = line;
		this.serieRowsNb = serieRowsNb;
		this.months = months;
		this.monthly = true;
		initSerieRows();
	}

	/**
	 * Called by the constructors to fill serieRows adequately with the first cases of line.
	 * @see CommonLine#serieRows
	 * @see CommonLine#line
	 */
	private void initSerieRows() {
		
		serieRows = new String[serieRowsNb];
		System.arraycopy(line, 0, serieRows, 0, serieRowsNb);
		
	}
	/**
	 * Fills each line of outputLines with, in order, 
	 * - the serieRows
	 * - the year
	 * - the month (if monthly)
	 * - the value
	 * @return The 2D array that will be written in the OutPutFile corresponding to ONE line from the inputFile
	 * @see CommonLine#outputLines
	 * @see CommonLine#years
	 * @see CommonLine#months
	 */
	public String[][] exportOutputLines() {
		
		if (!isMonthly()) {
			outputLines = new String[years.length][serieRowsNb + 2]; 
			for (int i = 0; i < years.length; i++) {
				System.arraycopy(serieRows, 0, outputLines[i], 0, serieRowsNb);
				outputLines[i][serieRowsNb] = years[i];
				outputLines[i][serieRowsNb + 1] = line[serieRowsNb + i];
			}
		}
		else {
			outputLines = new String[years.length][serieRowsNb + 3];
			for (int i = 0; i < years.length; i++) {
				System.arraycopy(serieRows, 0, outputLines[i], 0, serieRowsNb);
				outputLines[i][serieRowsNb] = years[i];
				outputLines[i][serieRowsNb + 1] = months[i];
				outputLines[i][serieRowsNb + 2] = line[serieRowsNb + i];
			}
		}
		return outputLines;
	}
}
