package gov.noaa.nws.ncep.common.dataplugin.editedevents.request;

import gov.noaa.nws.ncep.common.dataplugin.editedevents.EventBin;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.request.intf.IRequest;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.util.EditedEventsConstants;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.util.EditedEventsConstants.Origin;

import com.raytheon.uf.common.serialization.annotations.DynamicSerialize;
import com.raytheon.uf.common.serialization.annotations.DynamicSerializeElement;

/**
 * Class for allowing the client to request the back-end to
 * select best events
 * 
 * <pre>
 * 
 * SOFTWARE HISTORY
 * 
 * Date         Ticket#    Engineer    Description
 * ------------ ---------- ----------- --------------------------
 * Dec 12, 2015 R9583      jtravis     Initial creation
 * 
 * </pre>
 * 
 * @author jtravis
 * @version 1.0
 */
@DynamicSerialize
public class SelectBestEventRequest extends Request<SelectBestEventRequest> implements IRequest {
	
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
     * The event ID
     */
    @DynamicSerializeElement
    private Integer eventID = null;
    
    /**
     * The event bin
     */
    @DynamicSerializeElement
    private EventBin eventBin = null;
        
    /**
     * The event codedType
     */
    @DynamicSerializeElement
    private Integer eventCodedType = null;
    
    /**
     * Origin of SeletBestEventRequest
     */
    @DynamicSerializeElement
    private Origin origin = Origin.PROCESS_EVENTS;
	
	/**
	 * 
	 */
	public SelectBestEventRequest() {
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
		if (this.origin == EditedEventsConstants.Origin.PROCESS_EVENTS && this.beginDTTM != 0L) {
			
			return true;
		
		} else 	if (this.origin == EditedEventsConstants.Origin.REBIN_EVENT && this.eventID != null) {
			
			return true;
	
		} else 	if (this.origin == EditedEventsConstants.Origin.REVISIT_OLD_BIN && this.beginDTTM != 0L && this.eventBin != null && this.getEventCodedType() != null) {
			
			return true;
			
		} else {
			
			return false;
		}
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
	 * @return the origin
	 */
	public Origin getOrigin() {
		return origin;
	}

	/**
	 * @param origin the origin to set
	 */
	public void setOrigin(Origin origin) {
		this.origin = origin;
	}

	/**
	 * @return the eventID
	 */
	public Integer getEventID() {
		return eventID;
	}

	/**
	 * @param eventID the eventID to set
	 */
	public void setEventID(Integer eventID) {
		this.eventID = eventID;
	}

	/**
	 * @return the eventBin
	 */
	public EventBin getEventBin() {
		return eventBin;
	}

	/**
	 * @param eventBin the eventBin to set
	 */
	public void setEventBin(EventBin eventBin) {
		this.eventBin = eventBin;
	}

	/**
	 * @return the eventCodedType
	 */
	public Integer getEventCodedType() {
		return eventCodedType;
	}

	/**
	 * @param eventCodedType the eventCodedType to set
	 */
	public void setEventCodedType(Integer eventCodedType) {
		this.eventCodedType = eventCodedType;
	}

}
