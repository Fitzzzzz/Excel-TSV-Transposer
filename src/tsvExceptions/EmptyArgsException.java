package tsvExceptions;


public class EmptyArgsException extends ArgsExceptions {


	private static final long serialVersionUID = 1L;

	public EmptyArgsException() {
		System.out.println("Un argument minimum est strictement n�cessaire : veuillez indiquer le fichier TSV � transposer.");
	}
	
}
