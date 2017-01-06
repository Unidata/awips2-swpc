package gov.noaa.nws.ncep.common.dataplugin.editedregions.request;

import com.raytheon.uf.common.serialization.annotations.DynamicSerialize;
import com.raytheon.uf.common.serialization.annotations.DynamicSerializeElement;

import gov.noaa.nws.ncep.common.dataplugin.editedregions.request.intf.IRequest;

/**
 * 
 * <pre>
 * 
 * SOFTWARE HISTORY
 * 
 * Date         Ticket#    Engineer    Description
 * ------------ ---------- ----------- --------------------------
 * Jan 14, 2016  R9583     jtravis     Initial creation
 * 
 * </pre>
 * 
 * @author jtravis
 * @version 1.0
 */
@DynamicSerialize
public class SaveRequest extends Request<SaveRequest> implements IRequest {

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
	 * 
	 */
	public SaveRequest() {
		this.id = System.currentTimeMillis();
	}

	/* (non-Javadoc)
	 * @see gov.noaa.nws.ncep.common.dataplugin.editedevents.request.intf.IRequest#getId()
	 */
	@Override
	public long getId() {
		return this.id;
	}

	/* (non-Javadoc)
	 * @see gov.noaa.nws.ncep.common.dataplugin.editedevents.request.intf.IRequest#setId(long)
	 */
	@Override
	public void setId(long Id) {
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

	/* (non-Javadoc)
	 * @see gov.noaa.nws.ncep.common.dataplugin.editedevents.request.intf.IRequest#isValid()
	 */
	@Override
	public boolean isValid() {
		// TODO - not sure what validation is needed...might just return true and be done with it
		return false;
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
		
		// only set the begin data time if
		// the begin date time has not been
		// set yet
		
		if (this.beginDTTM == 0L) {
			this.beginDTTM = beginDTTM;
		}
		
		
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
		
		// only set the end data time if
		// the end date time has not been
		// set yet
		
		if (this.endDTTM == 0L) {
			this.endDTTM = endDTTM;
		}
	}

}
