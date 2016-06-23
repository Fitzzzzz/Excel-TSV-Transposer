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
		
		System.out.println("Tentative de lecture de la ligne " + getCurrentLine()); // TODO : To be removed once testing done
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
					System.out.println("Lecture de la ligne " + getCurrentLine() + " terminée, return inc"); // TODO : To be removed once testing done

					incrLine();

					return row;
				}
		
	}
	
	// To rework, has to detect it alone, without parameter 
	public boolean allDone() {
		return (getLength() == getCurrentLine());
	}
	
	private String[][] headFile;

	public String[][] getHeadFile() {
		return headFile;
	}


	public void readHead() {
		
		headFile = new String[linesToCopy][];
		for (int i = 0; i < linesToCopy; i++) {
			System.out.println("readLine() numéro " + i + " sur " + (linesToCopy - 1) + " inc"); // TODO : To be removed once testing done
			headFile[i] = readLine();
			System.out.println("head[" + i + "] chargé"); // TODO : To be removed once testing done
		}
	}
	
	
	

}
