package tsvTranspositer;

import java.io.FileWriter;
import java.io.IOException;

import com.opencsv.CSVWriter;

public class OutputFile extends InOutFile {

	public OutputFile(String name) {
		super(name);
	}
	
	public void writeFirstLine(String[] line) {
		
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

	public void writeLine(String[] line) {
		
		try (FileWriter fw = new FileWriter(getName(), true);
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
	
	public void writeHead(String[][] head) { // TODO : TBC to classic writeALL?
		int i = 0;
		for (String[] l : head) {
			if (i != 0) {
				writeLine(l);
			}
			else {
				writeFirstLine(l);
			}
			i++;
		}
	}
	
	public void writeAll(String[][] lines) { // TODO : TBC to classic writeALL? would be way better!
		for (String[] l : lines) {
			writeLine(l);
		}
	}
	
	private String valueName = "Nombre"; // TODO : TBC, argument, constant?
	private String[] timeName = {"Année"}; // TODO : TBC : Tableau à deux cases si mensuel!

	 
}
