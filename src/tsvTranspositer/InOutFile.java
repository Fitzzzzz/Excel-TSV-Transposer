package tsvTranspositer;


/**
 * A class describing a file. Can be a file in which it will be written (OutputFile) OR read (Inputfile) but not both.
 * 
 * @author hamme
 *
 */

public class InOutFile {

	/**
	 * The name of the file.
	 */
	private String name;
	
	/**
	 * The line in which it will be written or read next. Starts at 1.
	 */
	private int currentLine; 
	

	public int getCurrentLine() {
		return currentLine;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	} 
	
	/**
	 * Sets the name of the file and the current line to 1.
	 * @param name Name of the file.
	 * @see InOutFile#currentLine
	 */
	public InOutFile(String name) {
		this.name = name;
		this.currentLine = 1;
	}
	
	/**
	 * Increments the current line. Called after each readNext() or writeNext().
	 * @see InOutFile#currentLine
	 */
	public void incrLine() {

		currentLine++;
	}
}
