package tsvTranspositer;

import com.opencsv.CSVWriter;


/**
 * The class charged of writing in the Output File.
 * It got its own CSVWriter from start to end.
 * Lines are written one after the other.
 * Uses opencsv package for writing lines.
 * @author hamme
 */


public class OutputFile extends InOutFile {

	private CSVWriter writer;
	
	public CSVWriter getWriter() {
		return writer;
	}

	public void setWriter(CSVWriter writer) {
		this.writer = writer;
	}

	/**
	 * Constructor
	 * @param name Name of the file in which the transposition will be written. Everything is written into this file.
	 */
	public OutputFile(String name) {
		super(name);
	}
	
	/**
	 * Writes a line in the file.
	 * @param line Line that will be written in the output file "name". Each case contains one case.
	 */
	public void writeLine(String[] line) {
		
			writer.writeNext(line);
			incrLine();
	}

	/**
	 * Writes multiples lines in once. Number determined by the number of lines in parameter.
	 * Can be seen as a transposition of the 2D array in parameter in the output file.
	 * @param lines 2D array that will be written in the output file.
	 * @see writeLine
	 */
	public void write(String[][] lines) { 
		
		for (String[] l : lines) {
			writeLine(l);
			incrLine();
		}
	}
}
