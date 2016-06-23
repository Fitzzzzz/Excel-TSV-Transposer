package tsvTranspositer;

import java.io.FileWriter;
import java.io.IOException;

import com.opencsv.CSVWriter;

public class OutputFile extends InOutFile {

	public OutputFile(String name) {
		super(name);
	}

	public void writeLine(String[] line) {
		
		try (FileWriter fw = new FileWriter(getName());
				CSVWriter writer = new CSVWriter(fw, '\t'))
		{
			writer.writeNext(line);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			incrLine();
		}
		
	}
	
//	public void writeHead(String[][] head) {
//		for (String[] l : head) {
//			
//		}
//	}
	 
}
