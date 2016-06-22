package tsvTranspositer;

public class SmallTest {

	public static void main(String[] args) {

		InputFile ex1 = new InputFile("exemplePerso1.tsv");
		String[] ligne1 = ex1.readLine(); 
		System.out.println(ligne1[0]);
	}

}
