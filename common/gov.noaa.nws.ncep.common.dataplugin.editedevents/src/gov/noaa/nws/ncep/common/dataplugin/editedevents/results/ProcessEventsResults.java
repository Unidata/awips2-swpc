package gov.noaa.nws.ncep.common.dataplugin.editedevents.results;

import gov.noaa.nws.ncep.common.dataplugin.editedevents.Event;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.results.intf.IResults;

import java.util.Vector;

import com.raytheon.uf.common.serialization.annotations.DynamicSerialize;
import com.raytheon.uf.common.serialization.annotations.DynamicSerializeElement;

/**
 * A lightweight container around the results from executing
 * the ProcessEventsCommand
 * 
 * <pre>
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
public class ProcessEventsResults implements IResults {

	/**
	 * The list of events processed
	 */
	@DynamicSerializeElement
	private Vector<Event> events = null;
	
	public ProcessEventsResults() {
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
	 * Get the list of events processed
	 * 
	 * @return List<Event> 
	 */
	public Vector<Event> getEvents() {
		return events;
	}
	
	/**
	 * Set the list of events processed
	 * 
	 * @param events
	 */
	public void setEvents(Vector<Event> events) {
		this.events = events;
	}

}
