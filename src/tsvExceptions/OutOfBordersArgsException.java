package tsvExceptions;

public class OutOfBordersArgsException extends ArgsExceptions {

	private static final long serialVersionUID = 1L;
	
	public OutOfBordersArgsException() {
		System.out.println("Trop d'arguments en entr�e. Arguments de num�ros sup�rieurs � 6 ignor�s.");
	}

}
