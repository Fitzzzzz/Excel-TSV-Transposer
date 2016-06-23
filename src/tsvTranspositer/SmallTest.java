package tsvTranspositer;

import testTools.StringArray; // To be removed once testing done
import testTools.StringArray2D; // same

public class SmallTest {

	public static void main(String[] args) {

		InputFile ex1 = new InputFile("exemplePerso1.tsv", 1, 2);
		ex1.readHead();
		StringArray2D head = new StringArray2D(ex1.getHead());
		head.print();
		
		OutputFile ex2 = new OutputFile("resultat1.tsv");
		

		
		
	}

}
