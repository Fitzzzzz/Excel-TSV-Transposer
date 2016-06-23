package tsvTranspositer;

import testTools.StringArray; // TODO : To be removed once testing done
import testTools.StringArray2D; // TODO : To be removed once testing done

public class SmallTest {

	public static void main(String[] args) {

		long startTime = System.nanoTime();
		
		String inputFile = "exempleVraiTsv.tsv"; // TODO : nom du fichier d'entrée
		
		String outputFile = "resultatVraiTsv.tsv"; // TODO : nom du fichier de sortie
		
		int linesToCopy = 0; // TODO : nombre de lignes ("header") en début de fichier à recopier
		
		String valueName = "quantity" ; // TODO : nom de la colonne donnant les quantités
		
		String periodName = "period" ; // TODO : nom de la colonne donnant les périodes
		
		int serieNb = 1 ; // TODO : nombre de colonnes correspondant (nombre de colonnes avant les données : elles seront recopiées)
		
		
		InputFile ex1 = new InputFile(inputFile, linesToCopy); 
		ex1.readHead();
		StringArray2D head = new StringArray2D(ex1.getHeadFile()); // TODO : TBR
		head.print(); // TODO : TBR
		
		OutputFile ex2 = new OutputFile(outputFile);
		
		ex2.writeHead(ex1.getHeadFile());
		
		HeadOfValuesHandler handler = new HeadOfValuesHandler(ex1.readLine(), valueName, periodName, serieNb);
		ex2.writeLine(handler.createOutputHOV());
		
		StringArray years = new StringArray(handler.getYears()); // TODO : TBR
		years.print(); // TODO : TBR
		
		int i = 2; // TODO : TBR
		
		
		while (!ex1.isAllDone()) {
			
			System.out.println("Gestion de la ligne " + i);
			
			CommonLine cl1 = new CommonLine(ex1.readLine(), handler.getYears(), serieNb);
			
			StringArray2D l12D = new StringArray2D(cl1.exportOutputLines()); // TODO : TBR
			// l12D.print(); // TODO : TBR
			
			ex2.writeAll(cl1.exportOutputLines());
			
			i++;
		}
		long endTime = System.nanoTime();
		long duration = (endTime - startTime);
		System.out.println(duration);
	}
}
