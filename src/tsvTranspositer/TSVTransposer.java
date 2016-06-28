package tsvTranspositer;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import tsvExceptions.ArgsExceptions;
import tsvExceptions.EmptyArgsException;
import tsvExceptions.OutOfBordersArgsException;

/**
 * The main. 
 * Expected inputs : TSV files which can have some irrelevant lines at the start who would be copied (indicate number as 4th argument)
 * A number or columns (>= 1) should indicated the serial (indicate number as 3rd argument) followed by many values from différent periods.
 * Will transpose a TSV file displayed as expected to have only one value 
 * (meaning one period) per line producing as many lines pro serial as there are periods.
 * Arguments should be displayed in this order :
 * -NameOfInputFile -NameOfOutPutFile -serieNb -linesToCopy.
 * The strict minimum of arguments required is 1 (Name of the input file).
 * This project uses the openCSV API.
 * @author hamme
 */
public class TSVTransposer {

	public static void main(String[] args) throws ArgsExceptions {

		// Boolean set to true while everything is good
		Boolean everythingOk = true;
		
		String inputFile = null; // Name of the entry file to be transposed.
		String outputFile = null; // Name of the output file.
		int serieNb = 1 ; // Number of columns before the actual values in the input file. Can be columns describing the product as well as empty columns before the values.
		int linesToCopy = 0; // Number of lines composing the header of the file (those lines will be copy/pasted in the output)
		
		/*
		 * Handling the arguments first. 
		 */
		try {
			switch (args.length) {
			case 0:
				throw new EmptyArgsException();
			case 1:
				inputFile = args[0];
				String[] parts = inputFile.split("\\.");
				// If no outPutFile name is given, will add "Transposed" to the inputFile Name
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
			a.notOk(everythingOk);
		}
		catch (NumberFormatException n) {
			System.out.println("Arguments 3 & 4 should be numbers."
					+ " Number 3 is the Number of columns before the actual values in the input file. \n"
					+ "(Can be columns describing the product as well as empty columns before the values. (1 by default)) \n"
					+ "Number 4 is the number of lines to copy/pasta. (0 by default) \n"
					+ "Please try again.");
			everythingOk = false;
		}
		// Creating an InputFile and an OutputFile
		InputFile ex1 = new InputFile(inputFile, linesToCopy); 
		OutputFile ex2 = new OutputFile(outputFile);
		
		if (everythingOk) {
			try (	FileReader fr = new FileReader(inputFile);
					CSVReader reader = new CSVReader(fr, '\t', '\'', 0);
					FileWriter fw = new FileWriter(outputFile);
					CSVWriter writer = new CSVWriter(fw, '\t', CSVWriter.NO_QUOTE_CHARACTER)) 
			{
				
				ex1.setReader(reader);
				ex2.setWriter(writer);
				// Reading the header of the file
				ex1.readHead();
				// Writing the header of the file (copy/pasta)
				ex2.write(ex1.getHeadFile());
					
				// Handling the line containing the columns names
				HeadOfValuesHandler handler = new HeadOfValuesHandler(ex1.readLine(), serieNb);
				ex2.writeLine(handler.createOutputHOV());

				// Each lien will be read and written (in multiple lines) one after the other.
				String[] row;
				CommonLine cl1;	
				// If the period is monthly
				if (handler.isMonthly()) { 
					
					while (!ex1.isAllDone()) { 
						
						row = ex1.readLine();
						if (!ex1.isAllDone()) {
							cl1 = new CommonLine(row, handler.getYears(), handler.getMonths(), serieNb);
								
							ex2.write(cl1.exportOutputLines());
						}	
					}
				}
				// If the period is yearly
				else {
					
					while (!ex1.isAllDone()) { 
						
						row = ex1.readLine();
						if (!ex1.isAllDone()) {
							cl1 = new CommonLine(row, handler.getYears(), serieNb);
							
							ex2.write(cl1.exportOutputLines());		
						}		
					}
				}		
			}
			catch (FileNotFoundException f) {
				System.out.println(inputFile + " can't be found. Cancelling...");
			}
			catch (IOException e) {
				System.out.println("Unknown exception raised.");
				e.printStackTrace();
			}

		}
		
	}
}
