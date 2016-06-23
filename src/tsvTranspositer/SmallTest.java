package tsvTranspositer;

import testTools.StringArray; // TODO : To be removed once testing done
import testTools.StringArray2D; // TODO : To be removed once testing done

public class SmallTest {

	public static void main(String[] args) {

		InputFile ex1 = new InputFile("exemplePerso1.tsv", 1, 3);
		ex1.readHead();
		StringArray2D head = new StringArray2D(ex1.getHeadFile());
		head.print();
		
		OutputFile ex2 = new OutputFile("resultat1.tsv");
		
		ex2.writeHead(ex1.getHeadFile());

		
		
	}

}
