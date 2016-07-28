package gov.noaa.nws.ncep.common.dataplugin.editedevents.exception;

/**
 * The top level Edited Events exception class used to hold all exceptions
 * that can be thrown by the Edited Events application
 * 
 * <pre>
 * 
 * SOFTWARE HISTORY
 * 
 * Date         Ticket#    Engineer    Description
 * ------------ ---------- ----------- --------------------------
 * Sep 5, 2015  R9583      jtravis     Initial creation
 * 
 * </pre>
 * 
 * @author jtravis
 * @version 1.0
 */

public class EventsDecoderException extends Exception {

    private static final long serialVersionUID = 7977095441357639806L;

    /**
     * 
     */
    public EventsDecoderException() {
	// TODO Auto-generated constructor stub
    }

    /**
     * @param message
     */
    public EventsDecoderException(String message) {
	super(message);
	// TODO Auto-generated constructor stub
    }

    /**
     * @param cause
     */
    public EventsDecoderException(Throwable cause) {
	super(cause);
	// TODO Auto-generated constructor stub
    }

    /**
     * @param message
     * @param cause
     */
    public EventsDecoderException(String message, Throwable cause) {
	super(message, cause);
	// TODO Auto-generated constructor stub
    }

    /**
     * @param message
     * @param cause
     * @param enableSuppression
     * @param writableStackTrace
     */
    public EventsDecoderException(String message, Throwable cause,
	    boolean enableSuppression, boolean writableStackTrace) {
	super(message, cause, enableSuppression, writableStackTrace);
	// TODO Auto-generated constructor stub
    }

}
