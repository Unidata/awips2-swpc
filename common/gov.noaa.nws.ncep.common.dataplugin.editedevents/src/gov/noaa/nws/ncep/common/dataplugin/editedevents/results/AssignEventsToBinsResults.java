package gov.noaa.nws.ncep.common.dataplugin.editedevents.results;

import gov.noaa.nws.ncep.common.dataplugin.editedevents.results.intf.IResults;

import java.util.Hashtable;
import java.util.Vector;

import com.raytheon.uf.common.serialization.annotations.DynamicSerialize;
import com.raytheon.uf.common.serialization.annotations.DynamicSerializeElement;

/**
 * Class used to hold any results from executing the AssignEventsToBinsCommand
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
public class AssignEventsToBinsResults implements IResults {

	@DynamicSerializeElement
	private Hashtable<Integer, Vector<Long>> results = null;
	
	/**
	 * Default Constructure
	 */
	public AssignEventsToBinsResults() {
		this.results = new Hashtable<Integer, Vector<Long>>();
	}

	/**
	 * @return the results
	 */
	public Hashtable<Integer, Vector<Long>> getResults() {
		return results;
	}

	/**
	 * @param results the results to set
	 */
	public void setResults(Hashtable<Integer, Vector<Long>> results) {
		this.results = results;
	}
	
	/**
	 * Add a single result to the collection of results
	 * 
	 * @param binNumber
	 * @param results
	 */
	public void setResult(int binNumber, Vector<Long> results) {
		this.results.put(binNumber, results);
	}

	/* (non-Javadoc)
	 * @see gov.noaa.nws.ncep.edex.plugin.editedevents.results.IResults#numResults()
	 */
	@Override
	public int numResults() {
		return results.size();
	}

}
