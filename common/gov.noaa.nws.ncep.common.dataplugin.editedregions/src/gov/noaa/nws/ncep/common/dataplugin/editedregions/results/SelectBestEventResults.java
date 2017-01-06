package gov.noaa.nws.ncep.common.dataplugin.editedregions.results;

import java.util.Vector;

import com.raytheon.uf.common.serialization.annotations.DynamicSerialize;
import com.raytheon.uf.common.serialization.annotations.DynamicSerializeElement;

import gov.noaa.nws.ncep.common.dataplugin.editedregions.results.intf.IResults;

/**
 * Class used to hold any results from executing the SelectBestEventCommand
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
public class SelectBestEventResults implements IResults {

	/**
	 * TODO - this vector should hold the best events from the duplicateEvents vector
	 */
	@DynamicSerializeElement
	private Vector<String> results = null;

	/**
	 * 
	 */
	public SelectBestEventResults() {
		this.results = new Vector<String>();
	}
	
	/**
	 * @return the results
	 */
	public Vector<String> getResults() {
		return this.results;
	}

	/**
	 * @param results the results to set
	 */
	public void setResults(Vector<String> results) {
		this.results = results;
	}
	
	/**
	 * Add a single result to the collection of results
	 * 
	 * @param result
	 */
	public void setResult(String result) {
		this.results.add(result);
	}

	/* (non-Javadoc)
	 * @see gov.noaa.nws.ncep.edex.plugin.editedevents.results.IResults#numResults()
	 */
	@Override
	public int numResults() {
		return results.size();
	}

}
