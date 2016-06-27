package tsvTranspositer;


/**
 * Manages the reading and the writing of the first real line of the file after the lines to copy/paste. 
 * These lines usually contain the different names of the columns.
 * (HOV : headOfValues)
 * @author hamme
 *
 */
public class HeadOfValuesHandler {
	
	/**
	 * The line as it was in the input file. Can be seen as the input of this class.
	 */
	private String[] inputHOV;
	/**
	 * The line as it will be in the output file. Can be seen as the output of this class.
	 */
	private String[] outputHOV;
	
	/**
	 * Number of columns before the actual values in the input file. 
	 * Can be columns describing the product as well as empty columns before the values.
	 */
	private int serieRowsNb;
	
	/**
	 * Contains all the years for which data is written in the input file.
	 */
	private String[] years;
	
	/**
	 * Contains all the months for which data is written in the input file 
	 * in case the data are written per month. Is set to null if not yearly.
	 * @see HeadOfValuesHandler#monthly
	 */
	private String[] months;
	
	/**
	 * A boolean set to true if the data are written monthly. False if the data are written yearly.
	 */
	private boolean monthly;
	
	/**
	 * A regular expression composed of 4 numbers followed by an M and 2 numbers (with/out spaces before and after) 
	 * recognizing the way months are written in TSV input files.
	 */
	private String regex = "[ ]*[0-9]{4}M[0-9]{1,2}[ ]*";
	

	public boolean isMonthly() {
		return monthly;
	}

	public String[] getYears() {
		return years;
	}
	
	public String[] getMonths() {
			return months;
	}
	
	public String getRegex() {
		return regex;
	}

	/**
	 * Constructor. It checks directly if the data is sorted monthly or yearly.
	 * @param inputHOV The first line as it is in the input file.
	 * @param serieRowsNb The number of lines before the real data.
	 * @see HeadOfValuesHandler#serieRowsNb
	 */
	public HeadOfValuesHandler(String[] inputHOV, int serieRowsNb) {
		super();
		this.inputHOV = inputHOV;
		this.serieRowsNb = serieRowsNb;
		checkMonthly(); 
	}
	/**
	 * Checks if the data are displayed monthly or yearly thanks to a match test 
	 * between the first period in the file and the regex of monthly periods.
	 * @return a boolean true if the data are monthly displayed. False if yearly.
	 * @see HeadOfValuesHandler#regex
	 */
	public boolean checkMonthly() {
	
		monthly = inputHOV[serieRowsNb].matches("[ ]*[0-9]{4}M[0-9]{1,2}[ ]*");
		return (monthly);
	}
	
	/**
	 * Creates the first line (HOV) for the output file.
	 * @return the first line of the values for the output file.
	 * @see HeadOfValuesHandler#initOutputHOV
	 * @see HeadOfValuesHandler#addSerieRows()
	 * @see HeadOfValuesHandler#getYearsArray()
	 * @see HeadOfValuesHandler#addColumnHeaders()
	 */
	public String[] createOutputHOV() {
	
		initOutputHOV();
		addSerieRows();
		getYearsArray();
		addColumnHeaders();
		
		return outputHOV;
	}
	
	/**
	 * Initializes the outputHOV depending of isMonthly. In case of monthly data, 
	 * one more column is needed to display the months.
	 * @see HeadOfValuesHandler#monthly
	 */
	private void initOutputHOV() {

		if (isMonthly()) {
			outputHOV = new String[serieRowsNb + 3];
		}
		else {
			outputHOV = new String[serieRowsNb + 2]; 
		}
		
	}
	/**
	 * Copies the name(s) of the column(s) describing the product which data are displayed.
	 */
	private void addSerieRows() {
		for (int i = 0; i < serieRowsNb; i++) {
			outputHOV[i] = inputHOV[i];
		}
	}

	/**
	 * Stores in years all the years whose data are displayed.
	 * If monthly, also stores the months : the strings containing year + month is separated between years and months.
	 * @see HeadOfValuesHandler#years
	 * @see HeadOfValuesHandler#monthly
	 * @see HeadOfValuesHandler#months
	 */
	
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
	
	/**	
	 *	Adds the names of the columns displaying the period and the value. 2 if yearly, 3 if monthly.
	 */	
	private void addColumnHeaders() {
		
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
