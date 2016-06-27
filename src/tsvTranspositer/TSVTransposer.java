package tsvTranspositer;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

//import testTools.StringArray; // TODO : TBR
//import testTools.StringArray2D; // TODO : TBR
import tsvExceptions.ArgsExceptions;
import tsvExceptions.EmptyArgsException;
import tsvExceptions.OutOfBordersArgsException;

public class TSVTransposer {

	public static void main(String[] args) throws ArgsExceptions {

		long startTime = System.nanoTime(); // TODO : TBR
		
		String inputFile = null; // Nom du fichier d'entrée
		String outputFile = null; // TODO : nom du fichier de sortie
		int serieNb = 1 ; // TODO : nombre de colonnes correspondant (nombre de colonnes avant les données : elles seront recopiées)
		int linesToCopy = 0; // TODO : nombre de lignes ("header") en début de fichier à recopier		
				
		try {
			switch (args.length) {
			case 0:
				throw new EmptyArgsException();
			case 1:
				inputFile = args[0];
				String[] parts = inputFile.split("\\.");
//				System.out.println(inputFile + " découpé en " + parts[0] + " et " + parts[1]); // TODO : TBR
				outputFile = parts[0] + "Transposed." + parts[1];
				break;
			case 2:
				inputFile = args[0];
				outputFile = args[1];
				break;
			case 3:
				inputFile = args[0];
				outputFile = args[1];
				serieNb = Integer.parseInt(args[2]);
				break;
			case 4:
				inputFile = args[0];
				outputFile = args[1];
				serieNb = Integer.parseInt(args[2]);
				linesToCopy = Integer.parseInt(args[3]);
				break;
			default:
				inputFile = args[0];
				outputFile = args[1];
				serieNb = Integer.parseInt(args[2]);
				linesToCopy = Integer.parseInt(args[3]);
				throw new OutOfBordersArgsException();
					
			}
		}
		catch (ArgsExceptions a) {
			
		}
		
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
				
//			StringArray2D head = new StringArray2D(ex1.getHeadFile()); // TODO : TBR
//			head.print(); // TODO : TBR
				
			
				
			ex2.writeHead(ex1.getHeadFile());
				
			HeadOfValuesHandler handler = new HeadOfValuesHandler(ex1.readLine(), serieNb);
			ex2.writeLine(handler.createOutputHOV());
				
//			StringArray years = new StringArray(handler.getYears()); // TODO : TBR
//			years.print(); // TODO : TBR

//			int i = 2; // TODO : TBR
				
			String[] row;
			CommonLine cl1;	
			if (handler.isMonthly()) {
				
				while (!ex1.isAllDone()) { // TODO : ugly test if (!ex1.isAllDone) and might be expensive
					
//					System.out.println("Gestion de la ligne " + i); // TODO : TBR
									
					row = ex1.readLine();
					if (!ex1.isAllDone()) {
						cl1 = new CommonLine(row, handler.getYears(), handler.getMonths(), serieNb);
							
//						StringArray2D l12D = new StringArray2D(cl1.exportOutputLines()); // TODO : TBR
//						l12D.print(); // TODO : TBR
							
						ex2.writeAll(cl1.exportOutputLines());
							
//						i++; // TODO : TBR
					}
						
				}
			}
			
			else {
				
				while (!ex1.isAllDone()) { // TODO : ugly test if (!ex1.isAllDone) and might be expensive
					
//					System.out.println("Gestion de la ligne " + i); // TODO : TBR
									
					row = ex1.readLine();
					if (!ex1.isAllDone()) {
						cl1 = new CommonLine(row, handler.getYears(), serieNb);
							
//						StringArray2D l12D = new StringArray2D(cl1.exportOutputLines()); // TODO : TBR
//						l12D.print(); // TODO : TBR
							
						ex2.writeAll(cl1.exportOutputLines());
							
//						i++; // TODO : TBR
					}
						
				}
			}
			
				
		}
		catch (FileNotFoundException f) {
			System.out.println(inputFile + " est introuvable.");
		}
		catch (IOException e) {
			e.printStackTrace();
		}
			
			
		
	long endTime = System.nanoTime(); // TODO : TBR
	long duration = (endTime - startTime); // TODO : TBR
	System.out.println(duration); // TODO : TBR
	}
}
