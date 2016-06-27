package tsvTranspositer;


import java.io.IOException;
import com.opencsv.CSVReader;



/**
 * InputFile is the class charged of reading the TSV file. 
 * Its methods include reading a line and reading the head of the file (the lines that should be copy/pasted).
 * Lines are read one after the other in their natural order.
 * It got its own CSVReader from start to the end.
 * Uses opencsv package for reading lines.
 * @author hamme
 */


public class InputFile extends InOutFile {
	/**
	 * Constructor
	 * @param name
	 * 		Name of the file to be transposed
	 * @param linesToCopy
	 * 		Number of lines in the file that should be copy/pasted 
	 */
	public InputFile(String name, int linesToCopy) {
		super(name);
		allDone = false;
		this.linesToCopy = linesToCopy;
	}
	
	/**
	 * Our reader for the input file
	 */
	private CSVReader reader;
	
	public CSVReader getReader() {
		return reader;
	}

	public void setReader(CSVReader reader) {
		this.reader = reader;
	}

	/**
	 * 	Number of lines in the file that should be copy/pasted in the output file.
	 */
	private int linesToCopy;

	/**
	 * A boolean that is true when the whole file has been read. Means the CSVReader reached EOF.
	 */
	private boolean allDone;
	
	public boolean isAllDone() {
		return allDone;
	}

	public int getLinesToCopyNb() {
		return linesToCopy;
	}

	public void setLinesToCopyNb(int linesToCopy) {
		this.linesToCopy = linesToCopy;
	}
	
	/**
	 * Method reading the next line of the file.
	 * Updates allDone to true if the line is empty (EOF).
	 * @return the next line in form of a String[].
	 * @throws IOException if bad things happen during the read. Shouldn't happen in the mains current state.
	 * @see InputFile#allDone
	 */
	public String[] readLine() throws IOException {
		
		String[] row = null;
		
		if ((row = reader.readNext()) == null) { 
			allDone = true;
		}		
		
		incrLine(); 
		return row;
	}

	/**
	 * A two dimensional array that contains the head of the file (the lines that should be copy/pasted).
	 * The lines are sorted in their natural order. Their number is the number of lines to copy.
	 * @see InputFile#linesToCopy
	 */
	private String[][] headFile;

	public String[][] getHeadFile() {
		return headFile;
	}

	/**
	 * Reads the head of the file (its number of lines is determined by the number of lines to copy) and stores it in headFile.
	 * @throws IOException if bad things happen readLine().
	 * @see InputFile#readLine()
	 * @see InputFile#headFile
	 * @see InputFile#linesToCopy
	 */
	public void readHead() throws IOException {
		
		headFile = new String[linesToCopy][];
		for (int i = 0; i < linesToCopy; i++) {			
			headFile[i] = readLine();

		}
	}	
}
