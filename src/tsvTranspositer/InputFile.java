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

	public int getLinesToCopyNb() {
		return linesToCopy;
	}

	public void setLinesToCopyNb(int linesToCopy) {
		this.linesToCopy = linesToCopy;
	}
	
	@SuppressWarnings("finally")
	public String[] readLine() {
		
		System.out.println("Tentative de lecture de la ligne " + getCurrentLine());
		String[] row = null;
		try (FileReader fr = new FileReader(getName());
				CSVReader reader = new CSVReader(fr, '\t', '\'', getCurrentLine() - 1);)
		
				{
					row = reader.readNext();
				}
		
				catch (IOException e) {
					e.printStackTrace();
				}
				finally {
					System.out.println("Lecture de la ligne " + getCurrentLine() + " termin�e, return inc");

					incrLine();

					return row;
				}
		
	}
	
	// To rework, has to detect it alone, without parameter 
	public boolean allDone() {
		return (getLength() == getCurrentLine());
	}
	
	private String[][] head;

	public String[][] getHead() {
		return head;
	}


	public void readHead() {
		
		head = new String[linesToCopy][];
		for (int i = 0; i < linesToCopy; i++) {
			System.out.println("readLine() num�ro " + i + " sur " + (linesToCopy - 1) + " inc");
			head[i] = readLine();
			System.out.println("head[" + i + "] charg�");
		}
	}
	

}
