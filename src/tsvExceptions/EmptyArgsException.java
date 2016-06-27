package tsvExceptions;

/**
 * Is called when no arguments were given. 1 is required.
 * @author hamme
 *
 */
public class EmptyArgsException extends ArgsExceptions {


	private static final long serialVersionUID = 1L;

	public EmptyArgsException() {
		System.out.println("At least the name of the input TSV file is required. Cancelling.");
	}
	
	public void notOk(boolean ok) {
		ok = false;
	}
	
}
