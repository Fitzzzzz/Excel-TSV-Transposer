package tsvTranspositer;

import java.io.FileReader;
import java.io.IOException;

import com.opencsv.CSVReader;

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
	
	@SuppressWarnings("finally") // TODO : TBR ? 
	public String[] readLine() {
		
		System.out.println("Tentative de lecture de la ligne " + getCurrentLine()); // TODO : To be removed once testing done
		String[] row = null;
		try (FileReader fr = new FileReader(getName());
				CSVReader reader = new CSVReader(fr, '\t', '\'', getCurrentLine() - 1);)
		
				{
					row = reader.readNext();
					if (reader.readNext() == null) {
						allDone = true;
					}
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
