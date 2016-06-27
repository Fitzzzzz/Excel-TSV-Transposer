package tsvExceptions;

/**
 * Is called when there is a problem concerning the arguments of the main.
 * @author hamme
 *
 */
public abstract class ArgsExceptions extends Exception {

	private static final long serialVersionUID = 1L;
	
	public abstract void notOk(boolean ok); 
}
