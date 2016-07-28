package gov.noaa.nws.ncep.common.dataplugin.editedevents.results;

import gov.noaa.nws.ncep.common.dataplugin.editedevents.EventType;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.results.intf.IResults;

import java.util.Collections;
import java.util.Enumeration;
import java.util.Vector;

import com.raytheon.uf.common.serialization.annotations.DynamicSerialize;
import com.raytheon.uf.common.serialization.annotations.DynamicSerializeElement;

/**
 * Class used to hold any results from executing the GetEventTypesCommand
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
public class GetEventTypesResults implements IResults {

	@DynamicSerializeElement
	private Vector<EventType> results = null;
	
	/**
	 * 
	 */
	public GetEventTypesResults() {
		results = new Vector<EventType>();
	}

	@Override
	public int numResults() {
		return results.size();
	}
		
	/**
	 * @return the results
	 */
	public Vector<EventType> getResults() {
		return results;
	}

	/**
	 * @param results the results to set
	 */
	public void setResults(Vector<EventType> results) {
		this.results = results;
	}	
		
	
	/**
	 * Returns the list of distinct event types
	 * 
	 * @return Vector<String>
	 */
	public Vector<String> getEventTypes() {
		
		Vector<String> results = new Vector<String>();
		
		Enumeration<EventType> en = this.results.elements();
		
		while (en.hasMoreElements()) {
			
			EventType et = en.nextElement();
			
			if (!results.contains(et.getType())) {
			
				results.add(et.getType());
			}			
		}
		
		Collections.sort(results);
		
		return results;
		
	}
	
	/**
	 * Returns the list of eventtypes for a given type
	 * 
	 * @param type
	 * @return Vector<EventType>
	 */
	public Vector<EventType> getResults(String type) {
		
		Vector<EventType> results = new Vector<EventType>();
		
		Enumeration<EventType> en = this.results.elements();
		
		while (en.hasMoreElements()) {
			
			EventType et = en.nextElement();
			
			if (et.getType().compareToIgnoreCase(type) == 0) {
				results.add(et);
			}			
		}
		
		return results;
		
	}
	
	/**
	 * Returns the list of codedtypes for a given type
	 * 
	 * @param type
	 * @return Vector<String>
	 */
	public Vector<String> getCodedTypes(String type) {
		
		Vector<String> results = new Vector<String>();
		
		Enumeration<EventType> en = this.results.elements();
		
		while (en.hasMoreElements()) {
			
			EventType et = en.nextElement();
			
			if (et.getType().compareToIgnoreCase(type) == 0) {
				results.add(String.valueOf(et.getCode()));
			}			
		}
		
		Collections.sort(results);
		
		return results;
		
	}
	
	/**
	 * Returns a frequency value associated with given a event type and coded type
	 * combination.
	 * 
	 * @param type
	 * @param codedType
	 * @return
	 */
	public String getFrequency(String type, int codedType) {
		
		String frequency = null;;
		
		Enumeration<EventType> en = this.results.elements();
		
		while (en.hasMoreElements()) {
			
			EventType et = en.nextElement();
			
			if (et.getType().compareToIgnoreCase(type) == 0 && et.getCode() == codedType) {
				frequency = String.valueOf(et.getFrequency());
				break;
			}
			
		}
		
		return frequency;
		
	}

}
