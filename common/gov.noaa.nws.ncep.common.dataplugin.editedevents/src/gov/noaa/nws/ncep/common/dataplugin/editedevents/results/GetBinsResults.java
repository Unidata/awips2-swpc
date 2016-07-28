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

import java.util.List;

import com.raytheon.uf.common.serialization.annotations.DynamicSerialize;
import com.raytheon.uf.common.serialization.annotations.DynamicSerializeElement;

/**
 * Class used to hold any results from executing the GetBinsCommand
 * 
 * <pre>
 *
 * SOFTWARE HISTORY
 *
 * Date         Ticket#    Engineer    Description
 * ------------ ---------- ----------- --------------------------
 * Jan 14, 2016 R9583      sgurung     Initial creation
 *
 * </pre>
 *
 * @author sgurung
 * @version 1.0	
 */
@DynamicSerialize
public class GetBinsResults  implements IResults {
	
	/**
	 * The list of event bin numbers
	 */
	@DynamicSerializeElement
	private List<Integer> eventBins = null;
	
	/**
	 * Default constructor
	 */
	public GetBinsResults() {
		
	}

	/* (non-Javadoc)
	 * @see gov.noaa.nws.ncep.common.dataplugin.editedevents.results.intf.IResults#numResults()
	 */
	@Override
	public int numResults() {
		
		int numResults = (this.eventBins != null) ? this.eventBins.size() : 0;
		
		return numResults;
	}

	/**
	 * Get the list of event bin numbers
	 * 
	 * @return List<EventBin> 
	 */
	public List<Integer> getEventBins() {
		return eventBins;
	}

	/**
	 * Set the list of event bin numbers
	 * 
	 * @param eventBins
	 */
	public void setEventBins(List<Integer> eventBins) {
		this.eventBins = eventBins;
	}

}
