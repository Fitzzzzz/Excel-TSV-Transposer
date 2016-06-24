package tsvTranspositer;

import com.opencsv.CSVWriter;

public class OutputFile extends InOutFile {

	private CSVWriter writer;
	
	public CSVWriter getWriter() {
		return writer;
	}

	public void setWriter(CSVWriter writer) {
		this.writer = writer;
	}

	public OutputFile(String name) {
		super(name);
	}
	
	public void writeFirstLine(String[] line) { // TODO : useless now?
		
		
		writer.writeNext(line);
		incrLine(); // TODO : useless now?
		
	}

	public void writeLine(String[] line) {
		

			writer.writeNext(line);

			incrLine(); // TODO : useless now?

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
			writer.writeNext(l);
			incrLine();
		}


	}
	 
}
