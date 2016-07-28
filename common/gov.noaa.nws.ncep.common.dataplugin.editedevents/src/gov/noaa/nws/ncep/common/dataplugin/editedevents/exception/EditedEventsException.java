package gov.noaa.nws.ncep.common.dataplugin.editedevents.exception;

import com.raytheon.uf.common.serialization.annotations.DynamicSerialize;

/**
 * Custom exception class for wrapping all exceptions that
 * can occure during execution of Edited Events functionality
 * 
 * <pre>
 * 
 * SOFTWARE HISTORY
 * 
 * Date         Ticket#    Engineer    Description
 * ------------ ---------- ----------- --------------------------
 * Aug 5, 2015  R9583      jtravis     Initial creation
 * 
 * </pre>
 * 
 * @author jtravis
 * @version 1.0
 */
@DynamicSerialize
public class EditedEventsException extends Exception {
	
	/**
	 * SerialVersionUID
	 */
	private static final long serialVersionUID = 4066458609683381151L;

	/**
	 * Default Constructor
	 */
	public EditedEventsException() {}

	/**
	 * Create an EditedEventsException using the message
	 * passed
	 * 
	 * @param message
	 */
	public EditedEventsException(String message) {
		super(message);
	}

	/**
	 * Create an EditedEventsException using the cause
	 * passed
	 * 
	 * @param cause
	 */
	public EditedEventsException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Create an EditedEventsException using the message
	 * and cause passed
	 * 
	 * @param message
	 * @param cause
	 */
	public EditedEventsException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Create an EditedEventsException using the
	 * message and cause and specifying if suppression should
	 * be enabled and if the stack trace is writable
	 * 
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public EditedEventsException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
