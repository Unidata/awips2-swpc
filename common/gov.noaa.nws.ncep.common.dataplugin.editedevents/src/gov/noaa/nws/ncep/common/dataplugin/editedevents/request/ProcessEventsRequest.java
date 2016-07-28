package gov.noaa.nws.ncep.common.dataplugin.editedevents.request;

import gov.noaa.nws.ncep.common.dataplugin.editedevents.request.intf.IRequest;

import com.raytheon.uf.common.serialization.annotations.DynamicSerialize;
import com.raytheon.uf.common.serialization.annotations.DynamicSerializeElement;

/**
 * Class for allowing the client to request the back-end to process events
 * 
 * 
 * <pre>
 * 
 * SOFTWARE HISTORY
 * 
 * Date         Ticket#    Engineer    Description
 * ------------ ---------- ----------- --------------------------
 * Jan 14, 2016 R9583      jtravis     Initial creation
 * 
 * </pre>
 * 
 * @author jtravis
 * @version 1.0
 */
@DynamicSerialize
public class ProcessEventsRequest extends Request<ProcessEventsRequest>
        implements IRequest {

    /**
     * The request id
     */
    @DynamicSerializeElement
    private final long id;
    
    /**
     * The start of the date time range to process events
     */
    @DynamicSerializeElement
    private long beginDTTM = 0L;

    /**
     * The end of the date time range to process events
     */
    @DynamicSerializeElement
    private long endDTTM = 0L;
    
    /**
     * Indicates whether to process other events 
     */
    @DynamicSerializeElement
    private boolean processOtherEvents = true;
   
	/**
	 * 
	 */
	public ProcessEventsRequest() {
		this.id = System.currentTimeMillis();
	}
	
	/**
	 * 
	 */
	public ProcessEventsRequest(boolean processOtherEvents) {
		this.id = System.currentTimeMillis();
		this.processOtherEvents = processOtherEvents;
	}

    /*
     * (non-Javadoc)
     * 
     * @see
     * gov.noaa.nws.ncep.common.dataplugin.editedevents.request.intf.IRequest
     * #getId()
     */
    @Override
    public long getId() {
        return this.id;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * gov.noaa.nws.ncep.common.dataplugin.editedevents.request.intf.IRequest
     * #setId(long)
     */
    @Override
    public void setId(long id) {
        // The id is set when the request is initialized
        // but the system still requires the method for
        // serialization / deserialization through the gateway.
        //
        // thus, this method does not actually change the id
        //
        // TODO - verify this
        ;
        ;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * gov.noaa.nws.ncep.common.dataplugin.editedevents.request.intf.IRequest
     * #isValid()
     */
    @Override
    public boolean isValid() {

        boolean valid = false;
        
        if (this.getBeginDTTM() != 0L && this.getEndDTTM() != 0L) {

        	valid = true;
        }

        return valid;

    }

    /**
     * Get the start date time range used for filtering
     * which events should be processed
     * 
     * @return long
     */
	public long getBeginDTTM() {
		return this.beginDTTM;
	}

	  /**
     * Set the start date time range for filtering which
     * events should be processed
     * 
     * @param beginDTTM
     */
	public void setBeginDTTM(long beginDTTM) {
		this.beginDTTM = beginDTTM;
	}

	 /**
     * Get the end date time range used for filtering
     * which events should be processed
     * 
     * @return Calendar
     */
	public long getEndDTTM() {
		return this.endDTTM;
	}

	 /**
     * Set the end date time range for filtering which
     * events should be processed
     * 
     * @param endDTTM
     */
	public void setEndDTTM(long endDTTM) {
		this.endDTTM = endDTTM;
	}

	/**
	 * @return the processOtherEvents
	 */
	public boolean isProcessOtherEvents() {
		return processOtherEvents;
	}

	/**
	 * @param processOtherEvents the processOtherEvents to set
	 */
	public void setProcessOtherEvents(boolean processOtherEvents) {
		this.processOtherEvents = processOtherEvents;
	}

}
