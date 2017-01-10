package gov.noaa.nws.ncep.common.dataplugin.editedregions.request;

import com.raytheon.uf.common.serialization.annotations.DynamicSerialize;
import com.raytheon.uf.common.serialization.annotations.DynamicSerializeElement;

import gov.noaa.nws.ncep.common.dataplugin.editedregions.Event;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.request.intf.IRequest;

/**
 * Class for allowing the client to request the back-end to
 * create a composite event
 * 
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
public class GetAssignedRegionReportsRequest extends
        Request<GetAssignedRegionReportsRequest> implements IRequest {

    /**
     * The Requests ID
     */
    @DynamicSerializeElement
    private final long id;
    
    /**
     * The event to persist
     */
    @DynamicSerializeElement
    private Event event = null;  
    
    /**
     * The event ID of the event that is persisted/updated
     */
    @DynamicSerializeElement
    private Integer eventID = null; 
    
    
	/**
	 * 
	 */
	public GetAssignedRegionReportsRequest() {
		this.id = System.currentTimeMillis();
	}

    /*
     * (non-Javadoc)
     * 
     * @see
     * gov.noaa.nws.ncep.common.dataplugin.editedevents.request.IRequest#getID()
     */
    @Override
    public long getId() {
        return this.id;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * gov.noaa.nws.ncep.common.dataplugin.editedevents.request.IRequest#isValid
     * ()
     */
    @Override
    public boolean isValid() {
    	boolean valid = false;
        
        if (this.event != null) {
            valid = true;
        }

        return valid;
    }

    @Override
    public void setId(long ID) {
    	// The id is set when the request is initialized
    	// but the system still requires the method for
    	// serialization / deserialization through the gateway.
    	//
    	// thus, this method does not actually change the id
    	//
    	// TODO - verify this
    	;;
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
    
}
