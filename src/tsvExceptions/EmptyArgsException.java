package tsvExceptions;


public class EmptyArgsException extends ArgsExceptions {


	private static final long serialVersionUID = 1L;

	public EmptyArgsException() {
		System.out.println("Un argument minimum est strictement nécessaire : veuillez indiquer le fichier TSV à transposer.");
	}
	
}
