package gov.noaa.nws.ncep.common.dataplugin.editedevents.request;

import gov.noaa.nws.ncep.common.dataplugin.editedevents.Event;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.request.intf.IRequest;

import com.raytheon.uf.common.serialization.annotations.DynamicSerialize;
import com.raytheon.uf.common.serialization.annotations.DynamicSerializeElement;

/**
 * The request class created when a client wishes to associate an
 * event with another bin.
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
public class ReBinEventRequest extends Request<ReBinEventRequest> implements IRequest {

    /**
     * The request id
     */
    @DynamicSerializeElement
    private final long id;
    
    /**
     * The event to re-bin
     */
    @DynamicSerializeElement
    private Event event = null;
    
    /**
     * The unique id of the event to be re-binned
     * TODO - talk with Raytheon about why they made the id of the event non-serializable. 
     */
    @DynamicSerializeElement
    private int eventId = 0;
    
    /**
     * The current bin number the event is associated with
     */
    @DynamicSerializeElement
    private int currentBin = 0;
    
    /**
     * The new bin number the event should be associated with
     */
    @DynamicSerializeElement
    private int newBin = 0;
    
    /**
     * The user defined start date/time
     */
    @DynamicSerializeElement
    private long startDTTM = 0L;
	
	/**
	 * 
	 */
	public ReBinEventRequest() {
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
	 * 
	 * failure conditions
	 * 1) the event is null
	 * 2) the eventId = 0;
	 * 3) the currentBin = 0;
	 * 4) the newBin = 0;
	 * 5) the currentBin == newBin (nothing to rebin)
	 * 
	 * TODO - find a way to tell the client exactly why the request is invalid
	 * 
	 */
	@Override
	public boolean isValid() {
		
		if (this.event != null && 
				this.eventId != 0 && 
				this.currentBin != 0 && 
				this.newBin != 0 && 
				(this.currentBin != this.newBin)) {
			
			return true;
			
		} else {
			
			return false;
		}
		
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
	 * @return the currentBin
	 */
	public int getCurrentBin() {
		return currentBin;
	}

	/**
	 * @param currentBin the currentBin to set
	 */
	public void setCurrentBin(int currentBin) {
		this.currentBin = currentBin;
	}

	/**
	 * @return the newBin
	 */
	public int getNewBin() {
		return newBin;
	}

	/**
	 * @param newBin the newBin to set
	 */
	public void setNewBin(int newBin) {
		this.newBin = newBin;
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
