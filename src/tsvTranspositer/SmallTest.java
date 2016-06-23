package tsvTranspositer;

import testTools.StringArray; // TODO : To be removed once testing done
import testTools.StringArray2D; // TODO : To be removed once testing done

public class SmallTest {

	public static void main(String[] args) {

		InputFile ex1 = new InputFile("exemplePerso1.tsv", 2);
		ex1.readHead();
		StringArray2D head = new StringArray2D(ex1.getHeadFile()); // TODO : TBR
		head.print(); // TODO : TBR
		
		OutputFile ex2 = new OutputFile("resultat1.tsv");
		
		ex2.writeHead(ex1.getHeadFile());
		
		HeadOfValuesHandler handler = new HeadOfValuesHandler(ex1.readLine(), "Sold", "Year", 3);
		ex2.writeLine(handler.createOutputHOV());
		
		StringArray years = new StringArray(handler.getYears()); // TODO : TBR
		years.print(); // TODO : TBR
		
		CommonLine cl1 = new CommonLine(ex1.readLine(), handler.getYears(), 3);
		
		StringArray2D l12D = new StringArray2D(cl1.exportOutputLines()); // TODO : TBR
		l12D.print();
	}

}
