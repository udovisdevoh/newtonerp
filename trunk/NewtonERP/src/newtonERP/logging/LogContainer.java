package newtonERP.logging;

/**
 * 
 * @author r3hallejo
 *
 * This class is actually used to centralize the logging so we do not create one logger per class
 * and so we cannot have something like 5 loggers writing to the log file at the same time causing
 * absolutely unreadable logs.
 * 
 * Where to use it? Personnally I would recommend using it in the default constructor of the controller
 * only if the server is not created by default in the controller else when we are gonna do dozens of 
 * controller objects to gain access to the controller it's gonna start dozens of server.
 */
public class LogContainer {
	private Logger logger = new Logger();

	/**
	 * @return the logger
	 */
	public Logger getLogger() {
		return logger;
	}
}
