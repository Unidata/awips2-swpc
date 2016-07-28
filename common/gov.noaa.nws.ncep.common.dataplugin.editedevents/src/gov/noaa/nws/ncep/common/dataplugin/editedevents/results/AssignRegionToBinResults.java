package gov.noaa.nws.ncep.common.dataplugin.editedevents.results;

import com.raytheon.uf.common.serialization.annotations.DynamicSerialize;

import gov.noaa.nws.ncep.common.dataplugin.editedevents.results.intf.IResults;
/**
 * Class used to hold any results from executing the AssignRegionToBinCommand
 * 
 * <pre>
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
public class AssignRegionToBinResults implements IResults {
	
	/**
	 * 
	 */
	public AssignRegionToBinResults() {
	}

	/* (non-Javadoc)
	 * @see gov.noaa.nws.ncep.common.dataplugin.editedevents.results.intf.IResults#numResults()
	 */
	@Override
	public int numResults() {
		return 0;
	}
	
}
