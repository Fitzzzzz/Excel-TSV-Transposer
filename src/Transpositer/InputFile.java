package Transpositer;

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
				CSVReader reader = new CSVReader(fr, '\t', '\'', getCurrentLine());)
		
				{
					row = reader.readNext();		
				}
		
				catch (IOException e) {
					e.printStackTrace();
				}
				finally {
					return row;
				}
	}

	public boolean isDone() {
		return (getLength() == getCurrentLine());
	}
}
