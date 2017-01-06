/**
 * This code has unlimited rights, and is provided "as is" by the National Centers 
 * for Environmental Prediction, without warranty of any kind, either expressed or implied, 
 * including but not limited to the implied warranties of merchantability and/or fitness 
 * for a particular purpose.
 * 
 * This code has been developed by the NCEP-SIB for use in the AWIPS2 system.
 * 
 **/
package gov.noaa.nws.ncep.common.dataplugin.editedregions.request;

import com.raytheon.uf.common.serialization.annotations.DynamicSerialize;
import com.raytheon.uf.common.serialization.annotations.DynamicSerializeElement;

import gov.noaa.nws.ncep.common.dataplugin.editedregions.request.intf.IRequest;

/**
 * Class for allowing the client to request the back-end to obtain 
 * the list of bins
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
public class GetBinsRequest extends Request<GetBinsRequest> implements
IRequest {

	/**
	* The Request ID
	*/
	@DynamicSerializeElement
	private final long id;
	
	/**
	* The start of the date time range to process events
	*/
	@DynamicSerializeElement
	private long beginDTTM = 0L;
	
	/**
	* The end of the date time range to process events
	*/
	@DynamicSerializeElement
	private long endDTTM = 0L;
	
	/**
	* 
	*/
	public GetBinsRequest() {
		this.id = System.currentTimeMillis();
	}
	
	/*
	* (non-Javadoc)
	* 
	* @see
	* gov.noaa.nws.ncep.common.dataplugin.editedevents.request.IRequest#getID()
	*/
	@Override
	public long getId() {
		return this.id;
	}
	
	/*
	* (non-Javadoc)
	* 
	* @see
	* gov.noaa.nws.ncep.common.dataplugin.editedevents.request.IRequest#isValid
	* ()
	*/
	@Override
	public boolean isValid() {
		
		boolean isValid = false;
		
		if (this.beginDTTM != 0 && this.endDTTM != 0) {
			isValid = true;
		}
		
		return isValid;
	}
	
	@Override
	public void setId(long Id) {
		// The id is set when the request is initialized
		// but the system still requires the method for
		// serialization / deserialization through the gateway.
		//
		// thus, this method does not actually change the id
		//
		// TODO - verify this
		;;
	}
	
	/**
     * Get the start date time range used for filtering
     * which events should be processed
     * 
     * @return long
     */
	public long getBeginDTTM() {
		return this.beginDTTM;
	}
	
	  /**
     * Set the start date time range for filtering which
     * events should be processed
     * 
     * @param beginDTTM
     */
	public void setBeginDTTM(long beginDTTM) {
		this.beginDTTM = beginDTTM;
	}
	
	 /**
     * Get the end date time range used for filtering
     * which events should be processed
     * 
     * @return Calendar
     */
	public long getEndDTTM() {
		return this.endDTTM;
	}
	
	 /**
     * Set the end date time range for filtering which
     * events should be processed
     * 
     * @param endDTTM
     */
	public void setEndDTTM(long endDTTM) {
		this.endDTTM = endDTTM;
	}

}

