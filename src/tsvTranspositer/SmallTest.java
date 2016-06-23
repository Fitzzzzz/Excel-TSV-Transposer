package tsvTranspositer;

import testTools.StringArray;

public class SmallTest {

	public static void main(String[] args) {

		InputFile ex1 = new InputFile("exemplePerso1.tsv", 4);
		String[] ligne1 = ex1.readLine(); 
		StringArray ligne1String = new StringArray(ligne1);
		ligne1String.print();
	}

}
