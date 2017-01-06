package gov.noaa.nws.ncep.common.dataplugin.editedregions.results;

import java.util.LinkedHashMap;

import com.raytheon.uf.common.serialization.annotations.DynamicSerialize;
import com.raytheon.uf.common.serialization.annotations.DynamicSerializeElement;

import gov.noaa.nws.ncep.common.dataplugin.editedregions.Event;
import gov.noaa.nws.ncep.common.dataplugin.editedregions.results.intf.IResults;

/**
 * Class used to hold any results from executing the GetEventsCommand
 * 
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
public class GetEventsResults implements IResults {

	/**
	 * The list of events as a key-value pair, id as the key 
	 * and the associated event as the value.
	 */
	@DynamicSerializeElement
	private LinkedHashMap<Integer, Event> events = null;	
	
	/**
	 * Default constructor
	 */
	public GetEventsResults() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see gov.noaa.nws.ncep.common.dataplugin.editedevents.results.intf.IResults#numResults()
	 */
	@Override
	public int numResults() {
	int numResults = (this.events != null) ? this.events.size() : 0;
		
		return numResults;
	}
	
	/**
	 * Get the list of events
	 * 
	 * @return LinkedHashMap<Integer, Event>
	 */
	public LinkedHashMap<Integer, Event> getEvents() {
		return events;
	}

	/**
	 * Set the list of events
	 * 
	 * @param events
	 */
	public void setEvents(LinkedHashMap<Integer, Event> events) {
		this.events = events;
	}

}
