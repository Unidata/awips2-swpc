package gov.noaa.nws.ncep.common.dataplugin.editedevents.results;

import com.raytheon.uf.common.serialization.annotations.DynamicSerialize;
import com.raytheon.uf.common.serialization.annotations.DynamicSerializeElement;

import gov.noaa.nws.ncep.common.dataplugin.editedevents.Event;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.results.intf.IResults;

/**
 * Class used to hold any results from executing the CreateCompositEventCommand
 * 
 *  <pre>
 * 
 * SOFTWARE HISTORY
 * 
 * Date         Ticket#    Engineer    Description
 * ------------ ---------- ----------- --------------------------
 * Mar 22, 2016 R9583      jtravis     Initial creation
 * 
 * </pre>
 * 
 * @author jtravis
 * @version 1.0
 */
@DynamicSerialize
public class CreateCompositeEventResults implements IResults {
	
	/**
     * The event ID of the newly create composite event
     */
    @DynamicSerializeElement
    private Integer eventID = null; 
    
    /**
	 * The newly create composite event 
	 */
	@DynamicSerializeElement
	private Event event = null; 

	/**
	 * 
	 */
	public CreateCompositeEventResults() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see gov.noaa.nws.ncep.common.dataplugin.editedevents.results.intf.IResults#numResults()
	 */
	@Override
	public int numResults() {
		return 1;
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

}
