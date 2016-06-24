package tsvTranspositer;

import java.io.FileReader;
import java.io.IOException;

import com.opencsv.CSVReader;

import testTools.StringArray;

public class InputFile extends InOutFile {

	public InputFile(String name) {
		super(name);
		allDone = false;
	}
	
	public InputFile(String name, int linesToCopy) {
		super(name);
		allDone = false;
		this.linesToCopy = linesToCopy;
	}
	
	private CSVReader reader;
	
	public CSVReader getReader() {
		return reader;
	}

	public void setReader(CSVReader reader) {
		this.reader = reader;
	}

	private int linesToCopy;

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
	

	public String[] readLine() throws IOException {
		
		System.out.println("Tentative de lecture de la ligne " + getCurrentLine()); // TODO : To be removed once testing done
		String[] row = null;

		if ((row = reader.readNext()) == null) {
			allDone = true;
		}
		System.out.println("On est ligne " + getCurrentLine() +" et on a fini : " + allDone); // TODO : TBR
		incrLine(); // TODO : useless now?
		return row;
				
		
	}
	

	
	private String[][] headFile;

	public String[][] getHeadFile() {
		return headFile;
	}


	public void readHead() throws IOException {
		
		headFile = new String[linesToCopy][];
		for (int i = 0; i < linesToCopy; i++) {
			System.out.println("readLine() num�ro " + i + " sur " + (linesToCopy - 1) + " inc"); // TODO : To be removed once testing done
			headFile[i] = readLine();
			StringArray head = new StringArray(headFile[i]);// TODO : To be removed
			System.out.println("headLine print inc");// TODO : To be removed
			head.print();// TODO : To be removed
			System.out.println("head[" + i + "] charg�"); // TODO : To be removed once testing done
		}
	}

	
	
	

}
