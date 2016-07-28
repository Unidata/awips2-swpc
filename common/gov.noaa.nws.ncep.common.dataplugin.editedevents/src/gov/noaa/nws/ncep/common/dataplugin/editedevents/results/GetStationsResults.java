/**
 * This code has unlimited rights, and is provided "as is" by the National Centers 
 * for Environmental Prediction, without warranty of any kind, either expressed or implied, 
 * including but not limited to the implied warranties of merchantability and/or fitness 
 * for a particular purpose.
 * 
 * This code has been developed by the NCEP-SIB for use in the AWIPS2 system.
 * 
 **/
package gov.noaa.nws.ncep.common.dataplugin.editedevents.results;

import gov.noaa.nws.ncep.common.dataplugin.editedevents.results.intf.IResults;
import gov.noaa.nws.ncep.common.swpcrefdb.Station;

import java.util.Vector;

import com.raytheon.uf.common.serialization.annotations.DynamicSerialize;
import com.raytheon.uf.common.serialization.annotations.DynamicSerializeElement;

/**
 * Class used to hold any results from executing the GetStationsCommand
 * 
 * <pre>
 *
 * SOFTWARE HISTORY
 *
 * Date         Ticket#    Engineer    Description
 * ------------ ---------- ----------- --------------------------
 * Feb 01, 2016 R9583           sgurung     Initial creation
 *
 * </pre>
 *
 * @author sgurung
 * @version 1.0	
 */
@DynamicSerialize
public class GetStationsResults  implements IResults {
	
	/**
	 * The list of stations
	 */
	@DynamicSerializeElement
	private Vector<Station> results = null;
	
	/**
	 * Default constructor
	 */
	public GetStationsResults() {
		
	}

	/* (non-Javadoc)
	 * @see gov.noaa.nws.ncep.common.dataplugin.editedevents.results.intf.IResults#numResults()
	 */
	@Override
	public int numResults() {
		
		int numResults = (this.results != null) ? this.results.size() : 0;
		
		return numResults;
	}

	/**
	 * Get the list of stations
	 * 
	 * @return List<Station> 
	 */
	public Vector<Station> getResults() {
		return results;
	}
	
	public Vector<String> getStationDesignations() {
		
		Vector<String> results = new Vector<String>();
		
		for (int i=0; i <this.numResults(); i++) {
			
			Station st = this.results.get(i);			
			results.add(st.getDesignation());
		}
		
		return results;		
	}
	

	/**
	 * Set the list of stations
	 * 
	 * @param stations
	 */
	public void setResults(Vector<Station> stations) {
		this.results = stations;
	}

}
