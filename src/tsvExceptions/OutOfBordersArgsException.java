package tsvExceptions;

public class OutOfBordersArgsException extends ArgsExceptions {

	private static final long serialVersionUID = 1L;
	
	public OutOfBordersArgsException() {
		System.out.println("Trop d'arguments en entrée. Arguments de numéros supérieurs à 6 ignorés.");
	}

}
