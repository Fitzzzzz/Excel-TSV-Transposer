package tsvTranspositer;

import java.io.FileReader;
import java.io.IOException;

import com.opencsv.CSVReader;

public class InputFile extends InOutFile {

	public InputFile(String name) {
		super(name);
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
	
	
	public boolean allDone() {
		return (getLength() == getCurrentLine());
	}
}
