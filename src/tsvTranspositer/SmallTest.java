package tsvTranspositer;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import testTools.StringArray; // TODO : To be removed once testing done
import testTools.StringArray2D; // TODO : To be removed once testing done

public class SmallTest {

	public static void main(String[] args) {

		long startTime = System.nanoTime();
		
		String inputFile = "exempleVraiTsv.tsv"; // TODO : nom du fichier d'entrée
		
		String outputFile = "resultatVraiTsvOp.tsv"; // TODO : nom du fichier de sortie
		
		int linesToCopy = 0; // TODO : nombre de lignes ("header") en début de fichier à recopier
		
		String valueName = "quantity" ; // TODO : nom de la colonne donnant les quantités
		
		String periodName = "period" ; // TODO : nom de la colonne donnant les périodes
		
		int serieNb = 1 ; // TODO : nombre de colonnes correspondant (nombre de colonnes avant les données : elles seront recopiées)
		
		
		InputFile ex1 = new InputFile(inputFile, linesToCopy); 
		OutputFile ex2 = new OutputFile(outputFile);
		
		try (	FileReader fr = new FileReader(inputFile);
				CSVReader reader = new CSVReader(fr, '\t', '\'', 0);
				FileWriter fw = new FileWriter(outputFile);
				CSVWriter writer = new CSVWriter(fw, '\t', CSVWriter.NO_QUOTE_CHARACTER)) 
		{
		
			ex1.setReader(reader);
			ex2.setWriter(writer);
			ex1.readHead();
			StringArray2D head = new StringArray2D(ex1.getHeadFile()); // TODO : TBR
			head.print(); // TODO : TBR
			
		
			
			ex2.writeHead(ex1.getHeadFile());
			
			HeadOfValuesHandler handler = new HeadOfValuesHandler(ex1.readLine(), valueName, periodName, serieNb);
			ex2.writeLine(handler.createOutputHOV());
			
			StringArray years = new StringArray(handler.getYears()); // TODO : TBR
			years.print(); // TODO : TBR

			int i = 2; // TODO : TBR
			
			String[] row;
			
			while (!ex1.isAllDone()) { // TODO : ugly test if (!ex1.isAllDone) and might be expensive
				
				System.out.println("Gestion de la ligne " + i); // TODO : TBR
				
				
				row = ex1.readLine();
				if (!ex1.isAllDone()) {
					CommonLine cl1 = new CommonLine(row, handler.getYears(), serieNb);
					
					StringArray2D l12D = new StringArray2D(cl1.exportOutputLines()); // TODO : TBR
					l12D.print(); // TODO : TBR
					
					ex2.writeAll(cl1.exportOutputLines());
					
					i++; // TODO : TBR
				}
				
			}
			
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		long endTime = System.nanoTime();
		long duration = (endTime - startTime);
		System.out.println(duration);
	}
}
