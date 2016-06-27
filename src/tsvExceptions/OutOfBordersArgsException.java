package tsvExceptions;

/**
 * Is called when there are too many arguments.
 * @author hamme
 *
 */
public class OutOfBordersArgsException extends ArgsExceptions {

	private static final long serialVersionUID = 1L;
	
	public OutOfBordersArgsException() {
		System.out.println("Too many arguments given. Ignoring numbers > 4. Continuing...");
	}

	@Override
	public void notOk(boolean ok) {
		
	}

}
