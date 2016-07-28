/**
 * 
 */
package gov.noaa.nws.ncep.common.dataplugin.editedevents.results;

import com.raytheon.uf.common.serialization.annotations.DynamicSerialize;
import com.raytheon.uf.common.serialization.annotations.DynamicSerializeElement;

import gov.noaa.nws.ncep.common.dataplugin.editedevents.EventBin;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.results.intf.IResults;

/**
 * Class used to hold any results from executing the AddEventCommand
 * 
 *  <pre>
 * 
 * SOFTWARE HISTORY
 * 
 * Date         Ticket#    Engineer    Description
 * ------------ ---------- ----------- --------------------------
 * Mar 22, 2016 R9583      sgurung     Initial creation
 * 
 * </pre>
 * 
 * @author sgurung
 * @version 1.0
 */
@DynamicSerialize
public class AddNewBinForEventResults implements IResults {
	
	@DynamicSerializeElement
	private EventBin bin = null;

	@Override
	public int numResults() {
		return 1;
	}

	/**
	 * @return the bin
	 */
	public EventBin getBin() {
		return bin;
	}

	/**
	 * @param bin the bin to set
	 */
	public void setBin(EventBin bin) {
		this.bin = bin;
	}

}
