package gov.noaa.nws.ncep.common.dataplugin.editedregions.exception;

import com.raytheon.uf.common.serialization.annotations.DynamicSerialize;

/**
 * Custom exception class for wrapping all exceptions that can can be thrown
 * during execution of Edited Regions functionality
 * 
 * <pre>
 * 
 * SOFTWARE HISTORY
 * 
 * Date         Ticket#    Engineer    Description
 * ------------ ---------- ----------- --------------------------
 * Jan 1, 2015        jtravis     Initial creation
 * 
 * </pre>
 * 
 * @author jtravis
 * @version 1.0
 */
@DynamicSerialize
public class EditedRegionsException extends Exception {

    /**
     * SerialVersionUID
     */
    private static final long serialVersionUID = 4066458609683381151L;

    /**
     * Default Constructor
     */
    public EditedRegionsException() {
    }

    /**
     * Create an EditedRegionsException using the message passed
     * 
     * @param message
     */
    public EditedRegionsException(String message) {
        super(message);
    }

    /**
     * Create an EditedRegionsException using the cause passed
     * 
     * @param cause
     */
    public EditedRegionsException(Throwable cause) {
        super(cause);
        // TODO Auto-generated constructor stub
    }

    /**
     * Create an EditedRegionsException using the message and cause passed
     * 
     * @param message
     * @param cause
     */
    public EditedRegionsException(String message, Throwable cause) {
        super(message, cause);
        // TODO Auto-generated constructor stub
    }

    /**
     * Create an EditedRegionsException using the message and cause and
     * specifying if suppression should be enabled and if the stack trace is
     * writable
     * 
     * @param message
     * @param cause
     * @param enableSuppression
     * @param writableStackTrace
     */
    public EditedRegionsException(String message, Throwable cause,
            boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        // TODO Auto-generated constructor stub
    }

}
