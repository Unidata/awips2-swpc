package gov.noaa.nws.ncep.common.dataplugin.editedregions.request;

import com.raytheon.uf.common.serialization.annotations.DynamicSerialize;
import com.raytheon.uf.common.serialization.annotations.DynamicSerializeElement;

import gov.noaa.nws.ncep.common.dataplugin.editedregions.Event;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.request.intf.IRequest;

/**
 * Class for allowing the client to request the back-end add a new bin 
 * and associate the new bin to a selected event.
 * 
 * <pre>
 * 
 * SOFTWARE HISTORY
 * 
 * Date         Ticket#    Engineer    Description
 * ------------ ---------- ----------- --------------------------
 * Dec 12, 2015  R9583      sgurung     Initial creation
 * 
 * </pre>
 * 
 * @author sgurung
 * @version 1.0
 */
@DynamicSerialize
public class AddNewBinForEventRequest extends Request<AddNewBinForEventRequest> implements IRequest { 

	  /**
     * The request id
     */
    @DynamicSerializeElement
    private final long id;
    
    /**
     * The event that is going to be associated to the new bin
     */
    @DynamicSerializeElement
    private Event event = null;
    
    /**
     * The unique id of the event to be re-binned
     */
    @DynamicSerializeElement
    private int eventId = 0;
    
    /**
     * The original/current bin number the event is associated with
     */
    @DynamicSerializeElement
    private int originalBin = 0;
    
    /**
     * The user defined start date/time
     */
    @DynamicSerializeElement
    private long startDTTM = 0L;
	
    
	/**
	 * 
	 */
	public AddNewBinForEventRequest() {
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
        boolean valid = false;

        if (this.event != null) {
        	valid = true;	
        }

        return valid;
	}

	/**
	 * @return the event
	 */
	public Event getEvent() {
		return event;
	}

	/**
	 * @param event the event to set
	 */
	public void setEvent(Event event) {
		this.event = event;
	}

	/**
	 * @return the eventId
	 */
	public int getEventId() {
		return eventId;
	}

	/**
	 * @param eventId the eventId to set
	 */
	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	/**
	 * @return the originalBin
	 */
	public int getOriginalBin() {
		return originalBin;
	}

	/**
	 * @param originalBin the originalBin to set
	 */
	public void setOriginalBin(int originalBin) {
		this.originalBin = originalBin;
	}

	/**
	 * @return the startDTTM
	 */
	public long getStartDTTM() {
		return startDTTM;
	}

	/**
	 * @param startDTTM the startDTTM to set
	 */
	public void setStartDTTM(long startDTTM) {
		this.startDTTM = startDTTM;
	}	
	
}