package tsvTranspositer;

import java.io.FileReader;
import java.io.IOException;

import com.opencsv.CSVReader;

public class InputFile extends InOutFile {

	public InputFile(String name) {
		super(name);
	}
	
	public InputFile(String name, int lineToStart) {
		super(name, lineToStart);
	}
	
	public InputFile(String name, int lineToStart, int linesToCopy) {
		super(name, lineToStart);
		this.linesToCopy = linesToCopy;
	}

	private int linesToCopy;

	public int getLinesToCopy() {
		return linesToCopy;
	}

	public void setLinesToCopy(int linesToCopy) {
		this.linesToCopy = linesToCopy;
	}
	
	@SuppressWarnings("finally")
	public String[] readLine() {
		
		String[] row = null;
		try (FileReader fr = new FileReader(getName());
				CSVReader reader = new CSVReader(fr, '\t', '\'', getCurrentLine() - 1);)
		
				{
					row = reader.readNext();
//					for (String x : row) {
//						System.out.println(x);
//					}
				}
		
				catch (IOException e) {
					e.printStackTrace();
				}
				finally {

					return row;
				}
	}
	
	// To rework, has to detect it alone, without parameter 
	public boolean allDone() {
		return (getLength() == getCurrentLine());
	}
	


	

}
