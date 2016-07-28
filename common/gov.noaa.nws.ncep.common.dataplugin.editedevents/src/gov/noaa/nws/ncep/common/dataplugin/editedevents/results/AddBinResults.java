package gov.noaa.nws.ncep.common.dataplugin.editedevents.results;

import gov.noaa.nws.ncep.common.dataplugin.editedevents.EventBin;
import gov.noaa.nws.ncep.common.dataplugin.editedevents.results.intf.IResults;

import com.raytheon.uf.common.serialization.annotations.DynamicSerialize;

/**
 * Class used to hold any results from executing the AddBinCommand
 * 
 *   <pre>
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
public class AddBinResults implements IResults {

	private EventBin bin = null;
	
	/**
	 * 
	 */
	public AddBinResults() {
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
